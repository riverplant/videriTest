package com.river.videriTest.web.controller;

import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.river.videriTest.domain.Writer;
import com.river.videriTest.repository.WriterRepository;

@RestController
@RequestMapping("/writer")
public class WriterController {

	@Autowired
    private WriterRepository writerRepository;
	
	@GetMapping
    @CrossOrigin(origins = "http://localhost:8088")
    public Collection<Writer> listEmployees() throws SQLException {
        return writerRepository.findAll();
    }
}
