package com.eleonorvinicius.ace.data;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.entity.Configuration;
import com.eleonorvinicius.ace.exception.ACEException;

public class ConfigurationData extends Data<Configuration> {

	private static ConfigurationData configurationData;

	private ConfigurationData() {
		for (long i = 0; i < 50; i += 1) {
			Configuration configuration = new Configuration("key " + i, "value " + i * 2);
			this.getObjects().put(configuration.getId(), configuration);
		}
	}

	public static ConfigurationData getInstance() {
		if (configurationData == null) {
			configurationData = new ConfigurationData();
		}
		return configurationData;
	}

	@Override
	public void validate(Configuration entidade) throws ACEException {
		for (Long l : this.getObjects().keySet()) {
			Configuration c = this.getObjects().get(l);
			if (c.getKey().equals(entidade.getKey())) {
				throw new ACEException(R.string.keyAlreadyExists, "key already exists");
			}
		}
	}
}