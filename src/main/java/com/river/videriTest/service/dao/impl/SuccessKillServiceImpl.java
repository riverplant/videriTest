package com.river.videriTest.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.domain.SuccessKill;
import com.river.videriTest.dto.SuccessKillQueryConditon;
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

    @Override
    public Page<SuccessKill> queryWithConditions(Integer page, Integer size, SuccessKillQueryConditon successKillQueryConditon) {
       Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC,"id");
       Page<SuccessKill> successKills = successkillRepository.findAll(new Specification<SuccessKill>() {
  
        private static final long serialVersionUID = -4919545760098934531L;

        @Override
        public Predicate toPredicate(Root<SuccessKill> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            
            List<Predicate> list = new ArrayList<Predicate>();
            if(null!=successKillQueryConditon.getUserPhone()&&!"".equals(String.valueOf(successKillQueryConditon.getUserPhone()))){
                list.add(criteriaBuilder.equal(root.get("userPhone").as(String.class), successKillQueryConditon.getUserPhone()));
            }
            if(null!=successKillQueryConditon.getState()&&!"".equals(String.valueOf(successKillQueryConditon.getState()))){
                list.add(criteriaBuilder.equal(root.get("state").as(String.class), successKillQueryConditon.getState()));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));

        }
    },pageable);
        return successKills;
    }

}
