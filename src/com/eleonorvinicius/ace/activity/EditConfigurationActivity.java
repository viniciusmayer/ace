package com.eleonorvinicius.ace.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eleonorvinicius.ace.R;
import com.eleonorvinicius.ace.data.ConfigurationData;
import com.eleonorvinicius.ace.entity.Configuration;

public class EditConfigurationActivity extends Activity implements BaseActivity {

	private Long selectedConfigurationId;

	public void back(View view) {
		Intent intent = getIntent();
		Configuration configuration = ConfigurationData.getInstance().getObjects().get(this.selectedConfigurationId);
		intent.putExtra("configurationKey", configuration.getKey());
		intent.putExtra("action", BACK_FROM_EDIT);
		setResult(RESULT_OK, intent);
		finish();
	}

	public void update(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.editKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.editValueInput);

		String key = keyEditText.getText().toString().trim();
		String value = valueEditText.getText().toString().trim();

		if (key.isEmpty() || value.isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid_key_value), Toast.LENGTH_LONG).show();
			return;
		}

		Configuration configuration = new Configuration(this.selectedConfigurationId, key, value);
		ConfigurationData.getInstance().update(configuration);
		Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.selectedConfigurationId = getIntent().getLongExtra("selectedConfigurationId", -1);

		if (this.selectedConfigurationId == -1) {
			Toast.makeText(this, R.string.invalid_parameter_value, Toast.LENGTH_LONG).show();
			finish();
			return;
		}

		Configuration configuration = ConfigurationData.getInstance().getObjects().get(this.selectedConfigurationId);

		EditText keyInput = (EditText) findViewById(R.id.editKeyInput);
		EditText valueInput = (EditText) findViewById(R.id.editValueInput);

		keyInput.setText(configuration.getKey());
		valueInput.setText(configuration.getValue());
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void create(MenuItem item) {
		Intent intent = new Intent(this, CreateConfigurationActivity.class);
		startActivity(intent);
		finish();
	}

	public void remove(MenuItem item) {
		new AlertDialog.Builder(this).setIconAttribute(android.R.attr.alertDialogIcon).setTitle(R.string.confirm)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						Configuration configuration = ConfigurationData.getInstance().getObjects().get(selectedConfigurationId);
						ConfigurationData.getInstance().removeAll(selectedConfigurationId);
						Intent intent = getIntent();
						intent.putExtra("configurationKey", configuration.getKey());
						intent.putExtra("action", BACK_FROM_EDIT_REMOVE);
						setResult(RESULT_OK, intent);
						finish();
					}
				}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).create().show();
	}
}