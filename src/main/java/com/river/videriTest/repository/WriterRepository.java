package com.river.videriTest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.river.videriTest.domain.Writer;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {
	
	Optional<Writer> findById(Long id);
}
