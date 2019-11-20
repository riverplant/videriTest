package com.river.videriTest.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.dto.Exposer;
import com.river.videriTest.dto.SeckillExecution;
import com.river.videriTest.enums.SeckillStatEnum;
import com.river.videriTest.service.dao.SeckillService;


public class SecKillTest extends BaseTest {

    @Autowired
    private SeckillRepository seckillRepository;
    @Autowired
    private SeckillService seckillService;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
    
    @Test
    public void testGetSeckillLIst() {
        Page<Seckill> seckills = seckillService.getSeckillList(0, 2);
        log.info("list={}",seckills.getContent());
        seckills.stream().forEach(System.out::println);
    }
    
    @Test
    /**
     * Exposer [exposed=true, md5=403410b5deb2117c23cd07901d5ec9d8, seckillId=13, now=0, start=0, end=0] 
     * @throws ParseException
     */
    public void testExportSeckillUrl() throws ParseException{
        long id = 13;
        Exposer exoser = seckillService.exportSeckillUrl(id);
        log.info("exoser = {} ", exoser);
    }
    
    
    @Test
    public void testExcuteSeckill() throws ParseException{
        long id = 13;
        long phone = 1323256564L;
        String md5 = "403410b5deb2117c23cd07901d5ec9d8";
        
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        log.info("result = {} ", seckillExecution);
    }
    
    @Test
    public void testexecuteSeckillProcedure() throws ParseException{
        long id = 77;
        long phone = 5146606765L;   
        int result = seckillRepository.execute_seckill(id, phone, SeckillStatEnum.SUCCESS.getState(), new Date());
        log.info("result = {} ", result);
    }
    
}
