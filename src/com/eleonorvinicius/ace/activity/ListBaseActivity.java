package com.eleonorvinicius.ace.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.eleonorvinicius.ace.R;

public abstract class ListBaseActivity extends android.app.ListActivity implements BaseActivity {

	//private List<Long> selectedIds;
	private MenuItem menuItem;
	
	BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			boolean booleanExtra = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			menuItem.setEnabled(!booleanExtra);
		}
	};

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(receiver);
	}

	/*public ListBaseActivity() {
		this.selectedIds = new ArrayList<Long>();
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_items, menu);
		menuItem = menu.findItem(R.id.save);
		return true;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	/*public Set<String> getSelectedIdsAsStringSet() {
		Set<String> selectedIdsAsListString = new HashSet<String>();
		for (Long l : this.selectedIds) {
			selectedIdsAsListString.add(l.toString());
		}
		return selectedIdsAsListString;
	}

	public List<Long> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(Set<String> selectedIds) {
		for (String string : selectedIds) {
			this.selectedIds.add(Long.valueOf(string));
		}
	}

	public void check(View view) {
		Long tag = (Long) view.getTag();
		if (this.selectedIds.contains(tag)) {
			this.selectedIds.remove(tag);
			Toast.makeText(view.getContext(), R.string.unchecked, Toast.LENGTH_SHORT).show();
		} else {
			this.selectedIds.add(tag);
			Toast.makeText(view.getContext(), R.string.checked, Toast.LENGTH_SHORT).show();
		}
	}*/

	public abstract void edit(View view);

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			NavUtils.navigateUpFromSameTask(this);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		/*
		 * FIXME corrigir
		 */
		//outState.putSerializable("selectedIds", new SelectedIdsDTO(this.selectedIds));
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		/*
		 * FIXME corrigir
		 */
		/*SelectedIdsDTO selectedIdsDTO = (SelectedIdsDTO) state.getSerializable("selectedIds");
		this.selectedIds = selectedIdsDTO.getSelectedIds();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();*/
	}

	public void removeAll(MenuItem item) {
		new AlertDialog.Builder(this).setIconAttribute(android.R.attr.alertDialogIcon).setTitle(R.string.confirm)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						removeAllImpl();
					}
				}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).create().show();
	}

	public abstract void removeAllImpl();

	public void remove(MenuItem item) {
		new AlertDialog.Builder(this).setIconAttribute(android.R.attr.alertDialogIcon).setTitle(R.string.confirm)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						removeImpl();
					}
				}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).create().show();
	}

	public abstract void removeImpl();

	public abstract void create(MenuItem item);

	public abstract void save(MenuItem item);
}