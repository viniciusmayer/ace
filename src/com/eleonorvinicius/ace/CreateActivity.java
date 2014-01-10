package com.eleonorvinicius.ace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {

	public void clean(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.createKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.createValueInput);
		keyEditText.setText("");
		valueEditText.setText("");
		Toast.makeText(this, R.string.cleaned, Toast.LENGTH_SHORT).show();
	}

	public void save(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.createKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.createValueInput);

		String key = keyEditText.getText().toString().trim();
		String value = valueEditText.getText().toString().trim();

		if (key.isEmpty() || value.isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid), Toast.LENGTH_LONG).show();
			return;
		}

		Configuration configuration = new Configuration(key, value);
		try {
			Data.getInstance().add(configuration);
			Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show();
		} catch (ACEException e) {
			Toast.makeText(this, e.getMessageKey(), Toast.LENGTH_LONG).show();
		}
		
		Intent intent = new Intent(this, EditActivity.class);
		intent.putExtra("selectedConfigurationId", configuration.getId());
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
}
