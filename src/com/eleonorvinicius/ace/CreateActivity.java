package com.eleonorvinicius.ace;

import android.app.Activity;
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

		String key = keyEditText.getText().toString();
		String value = valueEditText.getText().toString();

		if (key.trim().isEmpty() || value.trim().isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid), Toast.LENGTH_LONG).show();
			return;
		}

		Configuration configuration = new Configuration(key, value);
		Data.getInstance().add(configuration);
		Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show();
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
