package com.river.videriTest.service.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.river.videriTest.domain.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisDao {

    private final JedisPool jedispool;
    private Logger logger = LoggerFactory.getLogger(this.getClass());  
    
    public RedisDao() {
        jedispool = new JedisPool("127.0.0.1", 6380);
    }
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
    /**
     * 获取缓存对象
     * @param seckillId
     * @return
     */
    public Seckill getSeckill(long seckillId) {

        try (Jedis jedis = jedispool.getResource()) {
            String key = "seckill:" + seckillId;
            byte[] bytes = jedis.get(key.getBytes());
            if(bytes !=null) {
                //创建一个用于空对象
                Seckill seckill = schema.newMessage();
                //通过自定义的schema将bytes反序列化赋值给seckill对象
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                return seckill;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    /**
     * 存入缓存对象
     * @param seckill
     * @return
     */
    public String putSecKill(Seckill seckill) {
        //set Object(Seckill) -> byte[]
        try(Jedis jedis = jedispool.getResource()) {
            String key = "seckill:" + seckill.getId();
            // LinkedBuffer: 缓存器
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            //超时缓存
            int timeout = 60 * 60; //小时
          String result =  jedis.setex(key.getBytes(),timeout , bytes);//如果成功返回'ok', 如果失败返回错误信息
          return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
