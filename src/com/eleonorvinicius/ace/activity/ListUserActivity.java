package com.eleonorvinicius.ace.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.adapter.UserAdapter;
import com.eleonorvinicius.ace.data.UserData;

public class ListUserActivity extends ListBaseActivity {

	@Override
	public void edit(View view) {
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new UserAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_items, menu);
		return true;
	}

	@Override
	public void onOptionItemRemoveAllSelected() {
		UserData.getInstance().clear();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemRemoveSelected() {
		UserData.getInstance().removeAll(this.getSelectedIds());
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemCreateSelected() {
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemSaveSelected() {
		/*
		 * FIXME implementar?
		 */
	}
}