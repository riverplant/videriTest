package com.river.videriTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author riverplant
 *
 */
@Entity
@Table(name="writer", 
uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name","last_name"})})
public class Writer extends DomainImpl{

    private static final long serialVersionUID = 1L;

    @Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

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

	public Writer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	Writer() {
		// JPA only
	}

}
