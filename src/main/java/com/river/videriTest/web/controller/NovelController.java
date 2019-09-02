package com.river.videriTest.web.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.river.videriTest.domain.Novel;
import com.river.videriTest.dto.NovelDto;
import com.river.videriTest.repository.NovelRepository;

@RestController
@RequestMapping("/novel")
public class NovelController {

	@Autowired
	private NovelRepository novelRepository;

	@GetMapping
	@CrossOrigin(origins = "http://localhost:8088")
	public Collection<NovelDto> query() throws SQLException {
		List<NovelDto> novelDtos = new ArrayList<>();
		List<Novel> novels = novelRepository.findAll();
		novels.stream().forEach(item -> novelDtos.add(new NovelDto(item.getId(), item.getName(), item.getAuthor())));
		return novelDtos;
	}

	@PostMapping
	@CrossOrigin(origins = "http://localhost:8088")
	public NovelDto save(@RequestBody NovelDto novelDto) {
		Novel novel = new Novel();
		BeanUtils.copyProperties(novelDto, novel);
		novelRepository.save(novel);
		novelDto.setId(novel.getId());
		return novelDto;
	}

	@GetMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:8088")
	public NovelDto getInfo(@PathVariable Long id) throws Exception {
		Novel novel = novelRepository.findById(id).get();
		NovelDto novelDto = new NovelDto();
		BeanUtils.copyProperties(novel, novelDto);
		return novelDto;

	}

	@PutMapping("/{id:\\d+}")
	@CrossOrigin(origins = "http://localhost:8080")
	public NovelDto update(@RequestBody NovelDto novelDto) {

		Novel novel = new Novel();
		BeanUtils.copyProperties(novelDto, novel);
		novelRepository.save(novel);
		return novelDto;
	}

	@DeleteMapping("{/id:\\d+}")
	@CrossOrigin(origins = "http://localhost:8080")
	public void delete(@PathVariable Long id) {
		novelRepository.deleteById(id);
	}

}
