package com.eleonorvinicius.ace.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
	public View getView(int position, View view, ViewGroup viewGroup) {
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.configuration, null);

		TextView key = (TextView) linearLayout.findViewById(R.id.key);
		TextView value = (TextView) linearLayout.findViewById(R.id.value);

		key.setText(ConfigurationData.getInstance().getObjectsAsList().get(position).getKey());
		value.setText(ConfigurationData.getInstance().getObjectsAsList().get(position).getValue());

		linearLayout.findViewById(R.id.configuration).setTag(ConfigurationData.getInstance().getObjectsAsList().get(position).getId());
		linearLayout.findViewById(R.id.select).setTag(ConfigurationData.getInstance().getObjectsAsList().get(position).getId());
		return linearLayout;
	}
}