package com.eleonorvinicius.ace.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.adapter.ConfigurationAdapter;
import com.eleonorvinicius.ace.data.ConfigurationData;

public class ListConfigurationActivity extends ListBaseActivity {

	@Override
	public void edit(View view) {
		Intent intent = new Intent(this, EditConfigurationActivity.class);
		intent.putExtra("selectedConfigurationId", (Long) view.getTag());
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ConfigurationAdapter(this));
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
		ConfigurationData.getInstance().clear();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemRemoveSelected() {
		/*
		 * FIXME implementar a confirmacao
		 */
		ConfigurationData.getInstance().removeAll(this.getSelectedIds());
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onOptionItemCreateSelected() {
		startActivity(new Intent(this, CreateConfigurationActivity.class));
	}
}