package com.river.videriTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.river.videriTest.domain.SuccessKill;

@Repository
public interface SuccesskillRepository extends JpaRepository<SuccessKill, Long>, JpaSpecificationExecutor<SuccessKill>{

    @Modifying
    @Query(value = "insert ignore into success_kill(state, user_phone, seckill_id, create_time) values(?1, ?2, ?3, NOW())", nativeQuery = true)
    int insertIgnore(int state, long user_phone, long seckill_id);
    
    @Query(value = "from  SuccessKill s where s.seckill.id= ?1 and s.userPhone = ?2")
    public SuccessKill queryByIdWithSeckill(long seckillId,long userPhone);
    
    
}


