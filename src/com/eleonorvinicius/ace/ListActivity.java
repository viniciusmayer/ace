package com.eleonorvinicius.ace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends android.app.ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, Data.getInstance().getDataAsArray()));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(v.getContext(), EditActivity.class);
		startActivity(intent);
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
			Intent intent = new Intent(this, CreateActivity.class);
			startActivity(intent);
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
			/*
			 * FIXME atualizar a lista
			 */
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
