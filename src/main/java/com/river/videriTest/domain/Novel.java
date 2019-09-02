package com.river.videriTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Novel extends DomainImpl{



	private Long author;
	
	@Column(name = "name", nullable = false)
	private String name;

	

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Novel(Long author, String name) {
		this.author = author;
		this.name = name;
	}

	public Novel() {
		
	}

}
