package com.eleonorvinicius.ace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {

	private Long selectedConfigurationId;
	
	public void update(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.editKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.editValueInput);

		String key = keyEditText.getText().toString();
		String value = valueEditText.getText().toString();

		if (key.trim().isEmpty() || value.trim().isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid), Toast.LENGTH_LONG).show();
			return;
		}

		Configuration configuration = new Configuration(this.selectedConfigurationId, key, value);
		try {
			Data.getInstance().add(configuration);
			Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();
		} catch (ACEException e) {
			Toast.makeText(this, e.getMessageKey(), Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		this.selectedConfigurationId = getIntent().getLongExtra("selectedConfigurationKey", -1);
		
		if (this.selectedConfigurationId == -1){
			//TODO fazer tratamento
		}

		Configuration configuration = Data.getInstance().getConfigs().get(this.selectedConfigurationId);
		
		EditText keyInput = (EditText)findViewById(R.id.editKeyInput);
		EditText valueInput = (EditText)findViewById(R.id.editValueInput);
		
		keyInput.setText(configuration.key);
		valueInput.setText(configuration.value);
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
		case R.id.create:
			Intent intent = new Intent(this, CreateActivity.class);
			startActivity(intent);
			return true;
		case R.id.remove:
			/*
			 * TODO implementar o remover
			 */
			NavUtils.navigateUpFromSameTask(this);
			Toast.makeText(this, getText(R.string.removed), Toast.LENGTH_LONG).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}