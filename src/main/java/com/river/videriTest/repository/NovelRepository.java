package com.river.videriTest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.river.videriTest.domain.Novel;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {

	Optional<Novel> findById(Long id);
}
