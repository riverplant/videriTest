package com.river.videriTest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author riverplant
 *
 */
@Entity
public class Writer extends DomainImpl{

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
