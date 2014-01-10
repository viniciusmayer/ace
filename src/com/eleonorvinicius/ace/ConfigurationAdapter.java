package com.eleonorvinicius.ace;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfigurationAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;

	public ConfigurationAdapter(Context context) {
		layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
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
		return Data.getInstance().getConfigsAsList().get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.configuration, null);

		TextView key = (TextView) linearLayout.findViewById(R.id.key);
		TextView value = (TextView) linearLayout.findViewById(R.id.value);

		key.setText(Data.getInstance().getConfigsAsList().get(position).getKey());
		value.setText(Data.getInstance().getConfigsAsList().get(position).getValue());

		linearLayout.findViewById(R.id.configuration).setTag(Data.getInstance().getConfigsAsList().get(position).getId());
		linearLayout.findViewById(R.id.select).setTag(Data.getInstance().getConfigsAsList().get(position).getId());
		return linearLayout;
	}
}