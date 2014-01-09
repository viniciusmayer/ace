package com.eleonorvinicius.ace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends android.app.ListActivity {

	public void select(View view){
		Toast.makeText(view.getContext(), "nao implementado", Toast.LENGTH_SHORT).show();
	}
	
	public void edit(View view) {
		Intent intent = new Intent(view.getContext(), EditActivity.class);
		startActivity(intent);
	}
	
	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(v.getContext(), EditActivity.class);
		startActivity(intent);
	}*/

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
			BaseAdapter listAdapter = (BaseAdapter) getListAdapter();
			listAdapter.notifyDataSetChanged();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class ConfigurationAdapter extends BaseAdapter {

		LayoutInflater layoutInflater;

		public ConfigurationAdapter(Context context) {
			layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return Data.getInstance().getConfigs().size();
		}

		@Override
		public Configuration getItem(int position) {
			return Data.getInstance().getConfigsAsList().get(position);
		}

		@Override
		public long getItemId(int position) {
			return Data.getInstance().getConfigsAsList().get(position).id;
		}

		@Override
		public View getView(int position, View view, ViewGroup viewGroup) {
			LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.configuration, null);

			TextView key = (TextView) linearLayout.findViewById(R.id.key);
			TextView value = (TextView) linearLayout.findViewById(R.id.value);

			key.setText(Data.getInstance().getConfigsAsList().get(position).key);
			value.setText(Data.getInstance().getConfigsAsList().get(position).value);

			return linearLayout;
		}
	}
}
