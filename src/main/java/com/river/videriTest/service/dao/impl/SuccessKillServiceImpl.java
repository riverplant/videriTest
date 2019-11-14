package com.river.videriTest.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.repository.SuccesskillRepository;
import com.river.videriTest.service.dao.SuccesskillService;

@Service
@Transactional
public class SuccessKillServiceImpl implements SuccesskillService {

    @Autowired private SuccesskillRepository successkillRepository;
    @Override
    public int insertSuccessKill(long seckillId, long userPhone) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public SuccessKill queryByIdWithSeckill(long seckillId) {
        // TODO Auto-generated method stub
        return null;
    }

}
