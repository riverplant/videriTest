package com.river.videriTest.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.river.videriTest.domain.Seckill;


public class SecKillTest extends BaseTest {

    @Autowired
    private SeckillRepository seckillRepository;


    @Test
    public void test1() {
        List<Seckill> seckills = seckillRepository.queryAll(0, 100);
        seckills.stream().forEach(System.out::println);
    }

    @Test
    public void test2() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStart = "2019-11-11 05:00:00";
        Date start_date = simpleDateFormat.parse(dateStart);
        int result = seckillRepository.reduceNumber(1, start_date);
        Seckill seckill = seckillRepository.findById(1L).orElseThrow();
        System.out.println("result="+result);
        System.out.println(seckill.toString());
    }
}
