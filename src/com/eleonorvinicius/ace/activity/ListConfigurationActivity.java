package com.eleonorvinicius.ace.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.adapter.ConfigurationAdapter;
import com.eleonorvinicius.ace.data.Data;

public class ListConfigurationActivity extends android.app.ListActivity {

	private List<Long> selectedConfigurationsIds;

	public void check(View view) {
		Long tag = (Long) view.getTag();
		if (this.selectedConfigurationsIds.contains(tag)) {
			this.selectedConfigurationsIds.remove(tag);
			Toast.makeText(view.getContext(), R.string.unchecked, Toast.LENGTH_SHORT).show();
		} else {
			this.selectedConfigurationsIds.add(tag);
			Toast.makeText(view.getContext(), R.string.checked, Toast.LENGTH_SHORT).show();
		}
	}

	public void edit(View view) {
		Intent intent = new Intent(this, EditConfigurationActivity.class);
		intent.putExtra("selectedConfigurationId", (Long) view.getTag());
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.selectedConfigurationsIds = new ArrayList<Long>();
		setListAdapter(new ConfigurationAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.create:
			startActivity(new Intent(this, CreateConfigurationActivity.class));
			return true;
		case R.id.remove:
			/*
			 * FIXME implementar a confirmacao
			 */
			Data.getInstance().removeAll(this.selectedConfigurationsIds);
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();
			Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
			return true;
		case R.id.removeAll:
			/*
			 * FIXME implementar a confirmacao
			 */
			Data.getInstance().clear();
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();
			Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}