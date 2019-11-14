package com.river.videriTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.river.videriTest.domain.Seckill;

@Repository
public interface SeckillRepository extends JpaRepository<Seckill, Long>{

}
