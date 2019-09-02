package com.river.videriTest.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import com.river.videriTest.domain.Novel;


public class NovelTest extends BaseTest{

	@Autowired
	private NovelRepository novelRepository;

	
	@Test
	public void test1() {
     List<Novel> novels = novelRepository.findAll();
     novels.stream().forEach(System.out::println);
	}
	
	@Test//like
	public void test3() {
		Pageable pageable = PageRequest.of(0, 10,Direction.DESC, "id");
		Novel novel = new Novel();
		novel.setName("Le%");
		//相当于like
		ExampleMatcher matcher =ExampleMatcher.matching()
				                              .withStringMatcher(StringMatcher.CONTAINING);
		Example<Novel> example = Example.of(novel,matcher);
		Page<Novel> depts = novelRepository.findAll(example,pageable);
		Assert.assertEquals(2, depts.getContent().size());
	}
	
	
	
	@Test
	public void whenCreateSuccess() {		
		Long count = novelRepository.count();
		Novel novel = new Novel();
		novel.setName("Demain dès l'aube");
		novel.setAuthor(1L);
		novelRepository.save(novel);
		Assert.assertEquals(count+1, novelRepository.count());
	}
	
	@Test
	public void whenDeletSuccess() {
		Long count = novelRepository.count();
		novelRepository.deleteById(3L);
		Assert.assertEquals(count-1, novelRepository.count());	
	}
	
	@Test
	public void whenUpdateSuccess() {
		
		Novel novel = new Novel();
		novel.setName("La Fin de Satan");
		novel.setId(4L);		
		novelRepository.save(novel);
		Novel det = novelRepository.findById(4L).get();
		Assert.assertEquals("La Fin de Satan",det.getName().trim());
		
	}
}
