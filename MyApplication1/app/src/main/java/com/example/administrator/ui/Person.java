package com.example.administrator.ui;

import java.io.Serializable;

public class Person implements Serializable{
	private Integer id;
	private String name;
	private String passwd;
	private String old;

	public Person(String name, String passwd, String old) {
		this.name = name;
		this.passwd = passwd;
		this.old = old;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}
}
