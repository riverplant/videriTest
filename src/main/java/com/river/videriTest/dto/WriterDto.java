package com.river.videriTest.dto;

import java.util.List;

import com.river.videriTest.domain.Novel;

public class WriterDto {
	
	private String firstName;
	
	private String lastName;
	
	private Long id;
	
	public List<Novel> novels;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Novel> getNovels() {
		return novels;
	}

	public void setNovels(List<Novel> novels) {
		this.novels = novels;
	}
	
	
}
