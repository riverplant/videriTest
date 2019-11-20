package com.river.videriTest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.river.videriTest.domain.Seckill;

@Repository
public interface SeckillRepository extends JpaRepository<Seckill, Long>, JpaSpecificationExecutor<Seckill>{
    
    @Modifying
    @Query( value ="update seckill set number = number-1 where videri_id = ?1 and start_time <= ?2 and end_time >= ?2 and number > 0" , nativeQuery = true)
    int reduceNumber(long seckillId,Date killTime);
    
    @Query( value ="select videri_id, name, number, start_time, end_time, create_time from seckill order by create_time desc limit ?1,?2" , nativeQuery = true)
     List<Seckill> queryAll(int offset, int limit);
    

    @Procedure(name = "execute_seckill")
    int execute_seckill(@Param("v_seckill_id") long seckillId, @Param("v_phone") long phone, @Param("v_state") int state, @Param("v_kill_time") Date killTime );
}
