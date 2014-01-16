package com.eleonorvinicius.ace.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.activity.EditBaseActivity;
import com.eleonorvinicius.ace.data.impl.UserData;
import com.eleonorvinicius.ace.entity.User;

public class EditUserActivity extends EditBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_user);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.setSelectedId(getIntent().getLongExtra("selectedId", -1));

		if (this.getSelectedId() != -1) {
			/*Toast.makeText(this, R.string.invalid_parameter_value, Toast.LENGTH_LONG).show();
			finish();*/
			/*
			 * FIXME fazer o tratamento da volta para a list
			 * se o if for == -1 (nao veio id)
			 * observar que implementacao mudou
			 * se nao veio id, entao eh a criacao de um novo 
			 */
			/*return;*/
			User user = UserData.getInstance().getObjects().get(this.getSelectedId());
			
			EditText emailInput = (EditText) findViewById(R.id.editEmailInput);
			EditText passwordInput = (EditText) findViewById(R.id.editPasswordInput);
			
			emailInput.setText(user.getEmail());
			passwordInput.setText(user.getPassword());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void back(View view) {
		Intent intent = getIntent();
		User user = UserData.getInstance().getObjects().get(this.getSelectedId());
		intent.putExtra("userEmail", user.getEmail());
		intent.putExtra("action", BACK_FROM_EDIT);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void update(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.editEmailInput);
		EditText valueEditText = (EditText) findViewById(R.id.editPasswordInput);

		String email = keyEditText.getText().toString().trim();
		String password = valueEditText.getText().toString().trim();

		if (email.isEmpty() || password.isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid_email_password), Toast.LENGTH_LONG).show();
			return;
		}

		User user = new User(this.getSelectedId(), email, password);
		UserData.getInstance().update(user);
		Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
	}

	@Override
	public void create(MenuItem item) {
		startActivity(new Intent(this, EditUserActivity.class));
		finish();
	}

	@Override
	public void removeImpl() {
		UserData.getInstance().removeAll(this.getSelectedId());
		finish();
	}
}