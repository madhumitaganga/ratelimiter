package com.team4.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	/**
	 * for serialization and deserialization
	 */
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}


	/**
	 * 	Sending the response
	 */
	public String getToken() {
		return this.jwttoken;
	}
}