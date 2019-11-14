package com.river.videriTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="novel", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"author","name"})},
     indexes = {@Index(columnList = "author")})
public class Novel extends DomainImpl{

    private static final long serialVersionUID = 1L;
    
    @Column(name = "author", nullable = false)
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
