package com.eleonorvinicius.ace.entity;

import java.util.Random;

public class Configuration extends Entity {

	private String key;
	private String value;

	public Configuration(String key, String value) {
		this.setId(new Random().nextLong());
		this.key = key;
		this.value = value;
	}

	public Configuration(Long id, String key, String value) {
		this.setId(id);
		this.key = key;
		this.value = value;
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