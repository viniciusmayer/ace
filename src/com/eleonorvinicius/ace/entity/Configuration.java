package com.eleonorvinicius.ace.entity;

import java.util.Random;

public class Configuration {

	private Long id;
	private String key;
	private String value;

	public Configuration(String key, String value) {
		this.id = new Random().nextLong();
		this.key = key;
		this.value = value;
	}

	public Configuration(Long id, String key, String value) {
		this.id = id;
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}