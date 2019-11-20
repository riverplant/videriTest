package com.river.videriTest.service.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.river.videriTest.domain.Seckill;

/**
 * Redis工具类
 * 
 * @author jli
 *
 */
//@Component
public class RedisUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private  RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    @Autowired
    private RedisTemplate<Long, Seckill> redisTemplate;

    /**
     * 缓存获取
     * 
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(Long seckillId) {
        try {    
            //通过自定义实现序列化protostuff:pojo(有 get set 标准的 java bean)
            return seckillId == null ? null :redisTemplate.opsForValue().get("seckill:" + seckillId);   
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
           return null;
        }  
    }


}
