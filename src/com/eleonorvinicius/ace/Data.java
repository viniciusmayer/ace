package com.eleonorvinicius.ace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

	private static Data data;

	private Map<String, Configuration> configs;

	private Data() {
		this.configs = new HashMap<String, Configuration>();

		for (long i = 0; i < 10; i += 1) {
			Configuration configuration = new Configuration("key " + i, "value " + i * 2);
			this.configs.put(configuration.key, configuration);
		}
	}

	public static Data getInstance() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}
	
	public Map<String, Configuration> getConfigs() {
		return configs;
	}

	public List<Configuration> getConfigsAsList(){
		List<Configuration> dataAsList = new ArrayList<Configuration>();
		for (String s : this.configs.keySet()) {
			dataAsList.add(this.configs.get(s));
		}
		return dataAsList;
	}

	public String[] getConfigsAsArray() {
		String[] dataAsArray = new String[this.configs.size()];

		int i = 0;
		for (String s : this.configs.keySet()) {
			Configuration configuration = this.configs.get(s);
			dataAsArray[i] = configuration.getContent();
			i += 1;
		}

		return dataAsArray;
	}

	public void update(Configuration configuration){
		this.configs.put(configuration.key, configuration);
	}
	
	public void add(Configuration configuration) throws ACEException {
		if (this.configs.containsKey(configuration.key)){
			throw new ACEException(R.string.keyAlreadyExists, "key already exists");
		}
		this.configs.put(configuration.key, configuration);
	}

	public void remove(String key) {
		this.configs.remove(key);
	}

	public void removeAll(List<String> keys) {
		for (String s : keys) {
			this.configs.remove(s);
		}
	}

	public void clear() {
		this.configs.clear();
	}
}