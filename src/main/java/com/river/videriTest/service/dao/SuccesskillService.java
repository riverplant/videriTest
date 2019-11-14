package com.river.videriTest.service.dao;

import com.river.videriTest.domain.SuccessKill;

/**
 * 记录购买明细
 * @author jli
 *
 */

public interface SuccesskillService {
    
   /**
    * 记录购买明细,可过滤秒杀
    * @param seckillId
    * @param userPhone
    * @return :插入的行数: 可以判断操作是否成功
    */
    int insertSuccessKill(long seckillId, long userPhone);
    
    /**
     * 根据Id查询秒杀成功对象
     * @param seckillId
     * @return
     */
    SuccessKill queryByIdWithSeckill(long seckillId);
    
    
}
