package com.river.videriTest.service.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.river.videriTest.domain.Seckill;
import com.river.videriTest.repository.SeckillRepository;
import com.river.videriTest.service.dao.SeckillService;

@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {

    @Autowired private SeckillRepository seckillRepository;
    
    @Override
    public int reduceNumber(long seckillId, Date killTime) {
        return seckillRepository.reduceNumber(seckillId, killTime);
    }

    @Override
    public Seckill queryById(long seckillId) {
        return seckillRepository.findById(seckillId).orElseThrow();
    }

    @Override
    public List<Seckill> queryAll(int offset, int limit) {
        // TODO Auto-generated method stub
        return seckillRepository.queryAll(offset, limit);
    }

}
