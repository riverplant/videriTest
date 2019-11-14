package com.river.videriTest.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//可以用该注解声明父类，在括号中定义继承策略
public class DomainImpl implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue()
	private Long id;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	
		
}
