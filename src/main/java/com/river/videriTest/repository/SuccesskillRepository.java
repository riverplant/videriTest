package com.river.videriTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.river.videriTest.domain.SuccessKill;

@Repository
public interface SuccesskillRepository extends JpaRepository<SuccessKill, Long>{

}
