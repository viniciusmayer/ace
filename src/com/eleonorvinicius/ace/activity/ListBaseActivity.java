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
import com.google.analytics.tracking.android.EasyTracker;

public abstract class ListBaseActivity extends android.app.ListActivity implements iBaseActivity {

	private boolean extraNoConnectivity;
	private MenuItem menuItem;

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			extraNoConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(receiver);

		EasyTracker.getInstance().activityStop(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_items, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		((MenuItem) menu.findItem(R.id.save)).setEnabled(this.extraNoConnectivity);
		return true;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

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
		// outState.putSerializable("selectedIds", new
		// SelectedIdsDTO(this.selectedIds));
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		/*
		 * FIXME corrigir
		 */
		/*
		 * SelectedIdsDTO selectedIdsDTO = (SelectedIdsDTO)
		 * state.getSerializable("selectedIds"); this.selectedIds =
		 * selectedIdsDTO.getSelectedIds(); ((BaseAdapter)
		 * getListAdapter()).notifyDataSetChanged();
		 */
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