package com.river.videriTest.service.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.dto.Exposer;
import com.river.videriTest.dto.SeckillExecution;
import com.river.videriTest.enums.SeckillStatEnum;
import com.river.videriTest.repository.SeckillRepository;
import com.river.videriTest.repository.SuccesskillRepository;
import com.river.videriTest.service.dao.SeckillService;
import com.river.videriTest.service.dao.cache.RedisDao;
import com.river.videriTest.service.exception.RepeatKillException;
import com.river.videriTest.service.exception.SeckillCloseException;
import com.river.videriTest.service.exception.SeckillException;

@Service
@Transactional(rollbackFor = Exception.class)
public class SeckillServiceImpl implements SeckillService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillRepository seckillRepository;
    @Autowired
    private SuccesskillRepository successkillRepository;
    @Autowired
    private RedisDao redisDao;
    // md5 salt
    private final String salt = "safqwreqae@rqwe33@dsafsea!asdhjkluio";

    @Override
    public int reduceNumber(long seckillId, Date killTime) {
        return seckillRepository.reduceNumber(seckillId, killTime);
    }

    @Override
    public Seckill queryById(long seckillId) {
        return seckillRepository.findById(seckillId).orElseThrow();
    }

    @Override
    public List<Seckill> queryAll(int offset, int limit) {
        if (offset < 0 && limit < 0) {
            return seckillRepository.findAll();
        } else {
            return seckillRepository.queryAll(offset, limit);
        }
    }

    @Override
    public Page<Seckill> getSeckillList(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return seckillRepository.findAll(pageable);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            // 优化点:缓存优化
            seckill = queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                redisDao.putSecKill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 转化特定字符串的过程
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println("md5=" + md5);
        return md5;
    }

    @Override
    /**
     * 确保事务方法的执行时间经可能短,不要穿插其他网络操作(RPC/HTTP请求)或者拨开到事务方法外部(创建更上层方法)
     * 如果只有一条修改操作,或者只读操作,不需要事务.(两条以上修改操作需要事务)
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite...");
        }
        // 执行秒杀逻辑
        Date now = new Date();
        try {
            // 记录购买行为
            int insertCount = successkillRepository.insertIgnore(SeckillStatEnum.SUCCESS.getState(), userPhone, seckillId);
            if (insertCount <= 0) {
                // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new RepeatKillException("seckill repeated...");// 重复秒杀
            } else {
                //减库存,热点商品竞争!!!!!!!!!!!!!!!
                int updateCOunt = reduceNumber(seckillId, now);
                if (updateCOunt <= 0) {
                    // 没有更新记录, 秒杀结束
                    throw new SeckillCloseException("seckill is already closed... ");
                } else {
                    // 秒杀成功
                    SuccessKill successKill = successkillRepository.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKill);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e2;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // 编译异常转换成运行异常,实现错误情况下事务自动回滚!!!!!!!!!
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    @Override
    public SeckillExecution executeSeckillProcedure(Map<String, Object> paramMap, String md5) {
//        if (md5 == null || !md5.equals(getMD5(seckillId))) {
//            return new SeckillExecution(seckillId, SeckillStatEnum.DATA_REWRITE);
//        }
     // 执行秒杀逻辑
        Date now = new Date();
        return null;
    }



}
