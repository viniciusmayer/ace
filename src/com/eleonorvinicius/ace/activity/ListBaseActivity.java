package com.eleonorvinicius.ace.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;

public abstract class ListBaseActivity extends android.app.ListActivity implements BaseActivity {

	private List<Long> selectedIds;

	public ListBaseActivity() {
		this.selectedIds = new ArrayList<Long>();
	}

	public List<Long> getSelectedIds() {
		return selectedIds;
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
	}

	public abstract void edit(View view);

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.create:
			onOptionItemCreateSelected();
			return true;
		case R.id.remove:
			new AlertDialog.Builder(this).setIconAttribute(android.R.attr.alertDialogIcon).setTitle(R.string.confirm)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							onOptionItemRemoveSelected();
						}
					}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
						}
					}).create().show();
			return true;
		case R.id.removeAll:
			new AlertDialog.Builder(this).setIconAttribute(android.R.attr.alertDialogIcon).setTitle(R.string.confirm)
					.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							onOptionItemRemoveAllSelected();
						}
					}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
						}
					}).create().show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public abstract void onOptionItemRemoveAllSelected();

	public abstract void onOptionItemRemoveSelected();

	public abstract void onOptionItemCreateSelected();
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("selectedIds", new Fake(this.selectedIds));
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		Fake fake = (Fake) state.getSerializable("selectedIds");
		this.selectedIds = fake.getSelectedIds();
	}
	
	class Fake implements Serializable{
		private static final long serialVersionUID = 1301540634086093853L;
		private List<Long> selectedIds;
		public Fake(List<Long> selectedIds) {
			this.selectedIds = selectedIds;
		}
		public List<Long> getSelectedIds() {
			return selectedIds;
		}
	}
}