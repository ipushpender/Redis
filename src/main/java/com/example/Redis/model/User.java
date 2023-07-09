package com.example.Redis.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String fname;
	private String lname;
	private String emailId;
	private int age;

}
