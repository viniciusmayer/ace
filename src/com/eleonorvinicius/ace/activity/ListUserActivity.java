package com.eleonorvinicius.ace.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.adapter.UserAdapter;
import com.eleonorvinicius.ace.data.UserData;

public class ListUserActivity extends ListBaseActivity implements OnItemLongClickListener {

	@Override
	public void edit(View view) {
		/*
		 * TODO trocar o editconfigurationactivity
		 */
		//Intent intent = new Intent(this, EditConfigurationActivity.class);
		//intent.putExtra("selectedUsersIds", (Long) view.getTag());
		//startActivity(intent);
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
		//startActivity(new Intent(this, CreateConfigurationActivity.class));
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		/*
		 * TODO implementar a confirmacao (ou a selecao da opcao desejada)
		 */
		Toast.makeText(this, getText(R.string.not_yet), Toast.LENGTH_LONG).show();
        return true;
	}
}