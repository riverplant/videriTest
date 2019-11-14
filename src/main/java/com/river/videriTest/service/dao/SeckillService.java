package com.river.videriTest.service.dao;

import java.util.Date;
import java.util.List;

import com.river.videriTest.domain.Seckill;

/**
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
    
    
}
