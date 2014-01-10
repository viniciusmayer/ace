package com.eleonorvinicius.ace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class ListActivity extends android.app.ListActivity {

	public void select(View view) {
		Toast.makeText(view.getContext(), "nao implementado", Toast.LENGTH_SHORT).show();
	}

	public void edit(View view) {
		Intent intent = new Intent(this, EditActivity.class);
		intent.putExtra("selectedConfigurationKey", (Long) view.getTag());
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.create:
			startActivity(new Intent(this, CreateActivity.class));
			return true;
		case R.id.remove:
			/*
			 * TODO implementar o remover
			 */
			Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
			return true;
		case R.id.removeAll:
			Data.getInstance().clear();
			Toast.makeText(this, getText(R.string.allremoved), Toast.LENGTH_LONG).show();
			((BaseAdapter) getListAdapter()).notifyDataSetChanged();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}