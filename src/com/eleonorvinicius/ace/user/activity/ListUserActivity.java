package com.eleonorvinicius.ace.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.activity.ListBaseActivity;
import com.eleonorvinicius.ace.adapter.UserAdapter;
import com.eleonorvinicius.ace.data.impl.UserData;

public class ListUserActivity extends ListBaseActivity {

	@Override
	public void edit(View view) {
		Intent intent = new Intent(this, EditUserActivity.class);
		intent.putExtra("selectedId", (Long) view.getTag());
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new UserAdapter(this));
	}

	@Override
	public void removeAllImpl() {
		UserData.getInstance().clear();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
	}

	@Override
	public void removeImpl() {
		/*
		 * FIXME corrigir
		 */
		/*UserData.getInstance().removeAll(this.getSelectedIds());
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();*/
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
	}

	@Override
	public void create(MenuItem item) {
		startActivity(new Intent(this, EditUserActivity.class));
	}

	@Override
	public void save(MenuItem item) {
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
	}
}