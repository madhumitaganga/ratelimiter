package com.team4.model;

public class Name {
	private String firstName;
	private String lastName;

	/**
	 * Constructor method.
	 * @param firstName firstname string given.
	 * @param lastName lastname string given.
	 */
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Getters and setters for firstname and lastname
	 */

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
	
}
