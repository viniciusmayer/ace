package com.eleonorvinicius.ace.activity;

import android.content.Intent;
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
		/*
		 * TODO trocar o editconfigurationactivity
		 */
		Intent intent = new Intent(this, EditConfigurationActivity.class);
		intent.putExtra("selectedUsersIds", (Long) view.getTag());
		startActivity(intent);
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
		/*
		 * FIXME implementar a confirmacao
		 */
		UserData.getInstance().clear();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemRemoveSelected() {
		/*
		 * FIXME implementar a confirmacao
		 */
		UserData.getInstance().removeAll(this.getSelectedIds());
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemCreateSelected() {
		/*
		 * TODO trocar o createconfigurationactivity
		 */
		startActivity(new Intent(this, CreateConfigurationActivity.class));
	}
}