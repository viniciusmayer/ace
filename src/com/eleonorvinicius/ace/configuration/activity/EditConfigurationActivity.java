package com.eleonorvinicius.ace.configuration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.activity.EditBaseActivity;
import com.eleonorvinicius.ace.data.impl.ConfigurationData;
import com.eleonorvinicius.ace.entity.Configuration;
import com.google.analytics.tracking.android.EasyTracker;

public class EditConfigurationActivity extends EditBaseActivity {

	@Override
	public void back(View view) {
		Intent intent = getIntent();
		Configuration configuration = ConfigurationData.getInstance().getObjects().get(this.getSelectedId());
		intent.putExtra("configurationKey", configuration.getKey());
		intent.putExtra("action", BACK_FROM_EDIT);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void update(View view) {
		EasyTracker.getTracker().sendEvent("Interface", "Edit configuration", "Update", 100l);
		
		EditText keyEditText = (EditText) findViewById(R.id.editKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.editValueInput);

		String key = keyEditText.getText().toString().trim();
		String value = valueEditText.getText().toString().trim();

		if (key.isEmpty() || value.isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid_key_value), Toast.LENGTH_LONG).show();
			return;
		}

		Configuration configuration = new Configuration(this.getSelectedId(), key, value);
		ConfigurationData.getInstance().update(configuration);
		Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_configuration);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.setSelectedId(getIntent().getLongExtra("selectedId", -1));

		if (this.getSelectedId() == -1) {
			Toast.makeText(this, R.string.invalid_parameter_value, Toast.LENGTH_LONG).show();
			finish();
			/*
			 * FIXME fazer o tratamento da volta para a list
			 */
			return;
		}

		Configuration configuration = ConfigurationData.getInstance().getObjects().get(this.getSelectedId());

		EditText keyInput = (EditText) findViewById(R.id.editKeyInput);
		EditText valueInput = (EditText) findViewById(R.id.editValueInput);

		keyInput.setText(configuration.getKey());
		valueInput.setText(configuration.getValue());
	}

	@Override
	public void create(MenuItem item) {
		Intent intent = new Intent(this, CreateConfigurationActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void removeImpl() {
		Configuration configuration = ConfigurationData.getInstance().getObjects().get(this.getSelectedId());
		ConfigurationData.getInstance().removeAll(this.getSelectedId());
		Intent intent = getIntent();
		intent.putExtra("configurationKey", configuration.getKey());
		intent.putExtra("action", BACK_FROM_EDIT_REMOVE);
		setResult(RESULT_OK, intent);
		finish();
	}
}