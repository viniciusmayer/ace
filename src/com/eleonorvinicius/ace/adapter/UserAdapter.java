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
import com.eleonorvinicius.ace.data.impl.UserData;
import com.eleonorvinicius.ace.entity.User;

public class UserAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;

	public UserAdapter(Context context) {
		layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return UserData.getInstance().getObjects().size();
	}

	@Override
	public User getItem(int position) {
		return UserData.getInstance().getObjectsAsList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return UserData.getInstance().getObjectsAsList().get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.user_item, null);
		TextView email = (TextView) linearLayout.findViewById(R.id.email);
		User user = UserData.getInstance().getObjectsAsList().get(position);
		email.setText(user.getEmail());
		linearLayout.findViewById(R.id.user).setTag(user.getId());
		linearLayout.findViewById(R.id.select).setTag(user.getId());
		return linearLayout;
	}
}