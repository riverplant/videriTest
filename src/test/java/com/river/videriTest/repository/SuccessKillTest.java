package com.river.videriTest.repository;

import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.service.dao.SuccesskillService;


public class SuccessKillTest extends BaseTest {

    @Autowired
    private SuccesskillService successkillService;

    @Test
    public void test2() throws Exception {
        long seckillId = 2L;
        long userPhone = 5146606765L;
        SuccessKill successKill = successkillService.insertSuccessKill(seckillId, userPhone);
        System.out.println(successkillService.getCount());
        System.out.println(successKill.toString());
    }
    
    @Test
    public void test() throws Exception {
        long seckillId = 2L;
        long userPhone = 5146606765L;
        int state = 0;
        int result = successkillService.insertIgnore(state, userPhone, seckillId);
        System.out.println(result);
       
    }
}
