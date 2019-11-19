package com.river.videriTest.service.dao;

import org.springframework.data.domain.Page;

import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.dto.SuccessKillQueryConditon;

/**
 * 记录购买明细
 * 
 * @author jli
 *
 */

public interface SuccesskillService {

    /**
     * 记录购买明细,可过滤秒杀
     * 
     * @param seckillId
     * @param userPhone
     * @return :插入的行数: 可以判断操作是否成功
     */
    SuccessKill insertSuccessKill(long seckillId, long userPhone);

    /**
     * 根据Id查询秒杀成功对象
     * 
     * @param seckillId
     * @return
     */
    SuccessKill queryByIdWithSeckillWithNull(long seckillId,long userPhone );


    long getCount();
    
    int insertIgnore(int state, long user_phone, long seckill_id);

    Page<SuccessKill> queryWithConditions(Integer page, Integer size, final SuccessKillQueryConditon successKillQueryConditon);
}
