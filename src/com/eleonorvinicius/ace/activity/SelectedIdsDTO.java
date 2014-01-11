package com.eleonorvinicius.ace.activity;

import java.io.Serializable;
import java.util.List;

public class SelectedIdsDTO implements Serializable {

	private static final long serialVersionUID = 2252035691848963174L;

	private List<Long> selectedIds;

	public SelectedIdsDTO(List<Long> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public List<Long> getSelectedIds() {
		return selectedIds;
	}
}