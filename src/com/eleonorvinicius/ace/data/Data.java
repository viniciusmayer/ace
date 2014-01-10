package com.eleonorvinicius.ace.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.entity.Configuration;
import com.eleonorvinicius.ace.exception.ACEException;

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

	public Map<Long, Configuration> getConfigs() {
		return configs;
	}

	public List<Configuration> getConfigsAsList() {
		List<Configuration> dataAsList = new ArrayList<Configuration>();
		for (Long l : this.configs.keySet()) {
			dataAsList.add(this.configs.get(l));
		}
		return dataAsList;
	}

	public void update(Configuration configuration) {
		this.configs.put(configuration.getId(), configuration);
	}

	public void add(Configuration configuration) throws ACEException {
		for (Long l : this.configs.keySet()) {
			Configuration c = this.configs.get(l);
			if (c.getKey().equals(configuration.getKey())) {
				throw new ACEException(R.string.keyAlreadyExists, "key already exists");
			}
		}
		this.configs.put(configuration.getId(), configuration);
	}

	public void removeAll(List<Long> ids) {
		for (Long l : ids) {
			this.configs.remove(l);
		}
	}

	public void removeAll(Long... ids) {
		for (Long l : ids) {
			this.configs.remove(l);
		}
	}

	public void clear() {
		this.configs.clear();
	}
}