package com.river.videriTest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.river.videriTest.domain.Seckill;

@Repository
public interface SeckillRepository extends JpaRepository<Seckill, Long>{
    
    @Modifying
    @Query( value ="update seckill set number = nubmer-1 where seckill_id = ?1 and start_time <= ?2 and end_time >= ?2 and number > 0" , nativeQuery = true)
    int reduceNumber(long seckillId,Date killTime);
    
    @Query( value ="select id, name, number, start_time, end_time, create_time from sekill order by create_time desc limit ?1,?2" , nativeQuery = true)
     List<Seckill> queryAll(int offset, int limit);
}