package com.river.videriTest.dto;

public class NovelDto {

	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private Long author;
	

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public NovelDto(Long id, String name, Long author) {
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public NovelDto() {
	
	}

	
	
}
