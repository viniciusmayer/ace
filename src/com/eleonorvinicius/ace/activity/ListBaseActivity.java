package com.eleonorvinicius.ace.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;

public abstract class ListBaseActivity extends android.app.ListActivity implements BaseActivity {

	private List<Long> selectedIds;

	public ListBaseActivity() {
		this.selectedIds = new ArrayList<Long>();
	}

	public Set<String> getSelectedIdsAsStringSet() {
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
		outState.putSerializable("selectedIds", new SelectedIdsDTO(this.selectedIds));
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		SelectedIdsDTO selectedIdsDTO = (SelectedIdsDTO) state.getSerializable("selectedIds");
		this.selectedIds = selectedIdsDTO.getSelectedIds();
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		/*
		 * FIXME imagino que seja necessario passar item por item da lista
		 * marcando o checkbox como checked... serah mesmo? :(
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