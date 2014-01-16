package com.eleonorvinicius.ace.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.data.impl.ConfigurationData;
import com.eleonorvinicius.ace.entity.Configuration;

public class ConfigurationAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;

	public ConfigurationAdapter(Context context) {
		layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return ConfigurationData.getInstance().getObjects().size();
	}

	@Override
	public Configuration getItem(int position) {
		return ConfigurationData.getInstance().getObjectsAsList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return ConfigurationData.getInstance().getObjectsAsList().get(position).getId();
	}

	@Override
	public View getView(final int position, View view, ViewGroup viewGroup) {
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.configuration_item, null);

		TextView key = (TextView) linearLayout.findViewById(R.id.key);
		TextView value = (TextView) linearLayout.findViewById(R.id.value);

		Configuration configuration = getItem(position);
		
		key.setText(configuration.getKey());
		value.setText(configuration.getValue());

		linearLayout.findViewById(R.id.configuration).setTag(configuration.getId());

		CheckBox checkBox = (CheckBox) linearLayout.findViewById(R.id.select);
		checkBox.setTag(configuration.getId());
		checkBox.setChecked(configuration.isChecked());
		
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				getItem(position).setChecked(isChecked);
			}
		});
		return linearLayout;
	}
	
	
}