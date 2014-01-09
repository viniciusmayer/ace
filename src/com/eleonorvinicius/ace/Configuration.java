package com.eleonorvinicius.ace;

import java.util.Random;

public class Configuration {

	public Long id;
	public String key;
	public String value;

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

	public String getContent() {
		String content = this.key;
		content = content.concat(": ");
		content = content.concat(this.value);
		return content;
	}
}