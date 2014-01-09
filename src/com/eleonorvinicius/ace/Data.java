package com.eleonorvinicius.ace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

	private static Data data;

	private Map<Long, Configuration> configs;

	private Data() {
		this.configs = new HashMap<Long, Configuration>();

		for (long i = 0; i < 10; i += 1) {
			Configuration configuration = new Configuration("key " + i, "value " + i * 2);
			this.configs.put(configuration.getId(), configuration);
		}
	}

	public static Data getInstance() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}

	public String[] getDataAsArray() {
		String[] dataAsArray = new String[this.configs.size()];

		int i = 0;
		for (Long l : this.configs.keySet()) {
			Configuration configuration = this.configs.get(l);
			dataAsArray[i] = configuration.getContent();
			i += 1;
		}

		return dataAsArray;
	}

	public void add(Configuration configuration) {
		this.configs.put(configuration.getId(), configuration);
	}

	public void remove(Long id) {
		this.configs.remove(id);
	}

	public void removeAll(List<Long> ids) {
		for (Long l : ids) {
			this.configs.remove(l);
		}
	}

	public void clear() {
		this.configs.clear();
	}

	public Map<Long, Configuration> getConfigs() {
		return configs;
	}
}