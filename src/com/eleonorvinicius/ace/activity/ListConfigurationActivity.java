package com.eleonorvinicius.ace.activity;

import java.util.HashSet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.adapter.ConfigurationAdapter;
import com.eleonorvinicius.ace.data.ConfigurationData;

public class ListConfigurationActivity extends ListBaseActivity {

	SharedPreferences.Editor editor;
	SharedPreferences sharedPreferences;

	@Override
	public void edit(View view) {
		Intent intent = new Intent(this, EditConfigurationActivity.class);
		intent.putExtra("selectedConfigurationId", (Long) view.getTag());
		startActivityForResult(intent, EDIT_CONFIGURATION_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		switch (requestCode) {
		case EDIT_CONFIGURATION_ACTIVITY:
			Integer action = data.getIntExtra("action", -1);
			String configurationKey = data.getStringExtra("configurationKey");
			Resources resources = getResources();
			switch (action) {
			case BACK_FROM_EDIT:
				Toast.makeText(this, String.format(resources.getString(R.string.key_updated), configurationKey), Toast.LENGTH_LONG).show();
				break;
			case BACK_FROM_EDIT_REMOVE:
				Toast.makeText(this, String.format(resources.getString(R.string.key_removed), configurationKey), Toast.LENGTH_LONG).show();
				break;
			}
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ConfigurationAdapter(this));

		sharedPreferences = this.getSharedPreferences("com.eleonorvinicius.ace.activity.configuration", 0);
		editor = sharedPreferences.edit();

		this.setSelectedIds(sharedPreferences.getStringSet("selectedIds", new HashSet<String>()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_items, menu);
		return true;
	}

	@Override
	public void removeAllImpl() {
		ConfigurationData.getInstance().clear();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
	}

	@Override
	public void removeImpl() {
		ConfigurationData.getInstance().removeAll(this.getSelectedIds());
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
	}

	@Override
	public void create(MenuItem item) {
		startActivity(new Intent(this, CreateConfigurationActivity.class));
	}

	@Override
	public void save(MenuItem item) {
		this.editor.putStringSet("selectedIds", this.getSelectedIdsAsStringSet());
		this.editor.commit();
		Toast.makeText(this, getText(R.string.saved), Toast.LENGTH_LONG).show();
	}
}