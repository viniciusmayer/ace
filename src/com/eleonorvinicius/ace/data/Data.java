package com.eleonorvinicius.ace.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eleonorvinicius.ace.entity.Entity;
import com.eleonorvinicius.ace.exception.ACEException;

public abstract class Data<T extends Entity> {

	private Map<Long, T> objects;

	public Data() {
		this.objects = new HashMap<Long, T>();
	}

	public void setObjects(Map<Long, T> objects) {
		this.objects = objects;
	}

	public Map<Long, T> getObjects() {
		return objects;
	}

	public List<T> getObjectsAsList() {
		List<T> dataAsList = new ArrayList<T>();
		for (Long l : this.objects.keySet()) {
			dataAsList.add(this.objects.get(l));
		}
		return dataAsList;
	}

	public void update(T entidade) {
		this.objects.put(entidade.getId(), entidade);
	}

	public void add(T entidade) throws ACEException {
		this.validate(entidade);
		this.getObjects().put(entidade.getId(), entidade);
	}

	public abstract void validate(T entidade) throws ACEException;

	public void removeAll(List<Long> ids) {
		for (Long l : ids) {
			this.objects.remove(l);
		}
	}

	public void removeAll(Long... ids) {
		for (Long l : ids) {
			this.objects.remove(l);
		}
	}

	public void clear() {
		this.objects.clear();
	}
}