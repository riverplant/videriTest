package com.river.videriTest.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.repository.SeckillRepository;
import com.river.videriTest.repository.SuccesskillRepository;
import com.river.videriTest.service.dao.SuccesskillService;

@Service
@Transactional
public class SuccessKillServiceImpl implements SuccesskillService {

    @Autowired private SuccesskillRepository successkillRepository;
    @Autowired private SeckillRepository seckillRepository;
    
    @Override
    public SuccessKill insertSuccessKill(long seckillId, long userPhone) {
        Seckill seckill = seckillRepository.findById(seckillId).orElseThrow();
        SuccessKill SuccessKill = new SuccessKill();
        SuccessKill.setSeckill(seckill);
        SuccessKill.setUserPhone(userPhone);
        return successkillRepository.save(SuccessKill) ;
    }

    @Override
    public SuccessKill queryByIdWithSeckillWithNull(long seckillId,long userPhone) {  
        SuccessKill successKill = successkillRepository.queryByIdWithSeckill(seckillId, userPhone);
        return successKill != null ? successKill : null;
    }

    @Override
    public long getCount() {
        return successkillRepository.count();
    }

    @Override
    public int insertIgnore(int state, long user_phone, long seckill_id) {
        return successkillRepository.insertIgnore(state, user_phone, seckill_id);
    }

}
