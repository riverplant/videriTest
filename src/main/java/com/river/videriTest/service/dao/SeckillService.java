package com.river.videriTest.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.dto.Exposer;
import com.river.videriTest.dto.SeckillExecution;
import com.river.videriTest.service.exception.RepeatKillException;
import com.river.videriTest.service.exception.SeckillCloseException;
import com.river.videriTest.service.exception.SeckillException;

/**使用者角度设计接口!!!!!
 * 三个方面: 方法定义力度 / 参数  / 返回类型[异常]   
 * 秒杀主要是减库存,记录购买明细
 * @author jli
 *
 */

public interface SeckillService {
    /**
     * 通过库存Id实现减库存
     * @param seckillId
     * @param killTime
     * @return :表示更新语句影响行数, 可以判断操作是否成功
     */
    int reduceNumber(long seckillId,Date killTime);
    
    /**
     * 根据Id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);
    
    /**
     * 根据偏移量查询库存列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);
    
   /**
    * 查询所有的秒杀产品列表,实现分页
    * @return
    */
    Page<Seckill> getSeckillList(Integer page, Integer size);
    
   /**
    * 秒杀开启时, 输出秒杀接口地址, 否则输出系统时间和秒杀时间
    * @param seckillId
    * @return
    */
    Exposer exportSeckillUrl(long seckillId);
    
    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5: 使用相同算法用户传入的md5做验证比较,如果验证失败, 拒绝秒杀
     * throws SeckillException, RepeatKillException, SeckillCloseException :  使用该方法具体告诉使用者需要明确抛出RepeatKillException | SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws SeckillException, RepeatKillException, SeckillCloseException;
    
   /**
    * 通过存储过程执行秒杀
    * @param paramMap
    * @param md5: 使用相同算法用户传入的md5做验证比较,如果验证失败, 拒绝秒杀
    * @return
    */
    SeckillExecution executeSeckillProcedure(Map<String,Object> paramMap, String md5);
}
