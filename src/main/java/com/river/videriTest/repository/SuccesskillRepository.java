package com.river.videriTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.river.videriTest.domain.SuccessKill;

@Repository
public interface SuccesskillRepository extends JpaRepository<SuccessKill, Long>{

    @Modifying
    @Query(value = "insert ignore into success_kill(state, user_phone, seckill_id) values(?1, ?2, ?3)", nativeQuery = true)
    int insertIgnore(int state, long user_phone, long seckill_id);
}


