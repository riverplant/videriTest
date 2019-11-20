package com.river.videriTest.repository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.service.dao.SeckillService;
import com.river.videriTest.service.dao.cache.RedisDao;

/**
 * 
 * @author jli
 *
 */
public class RedisDaoTest extends BaseTest{

    @Autowired private RedisDao redisDao ;
    @Autowired private SeckillService seckillService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Long seckillId = 15L;
    @Value("${spring.redis.host}")private String host;
    @Value("${spring.redis.port}")private String port;
    
    @Test
    public void testGetSeckill() throws Exception {
        
        Seckill seckill = redisDao.getSeckill(seckillId);
        System.out.println("host="+host);
        System.out.println("port="+port);
        System.out.println("seckill="+seckill);
    }
    
    @Test
    public void testPutSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null) {
            seckill = seckillService.queryById(seckillId);
            if(seckill != null) {
             String result =   redisDao.putSecKill(seckill);
             System.out.println(result);
            }
        }
    }
}
