package com.eleonorvinicius.ace;

import com.eleonorvinicius.ace.R;

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

	private String selectedConfigurationId;
	
	public void update(View view) {
		EditText keyEditText = (EditText) findViewById(R.id.editKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.editValueInput);

		String key = keyEditText.getText().toString();
		String value = valueEditText.getText().toString();

		if (key.trim().isEmpty() || value.trim().isEmpty()) {
			Toast.makeText(this, getText(R.string.invalid), Toast.LENGTH_LONG).show();
			return;
		}

		/*
		 * FIXME incluir o id da configuracao sendo editada ao construir o
		 * objeto
		 */
		Configuration configuration = new Configuration(key, value);
		Data.getInstance().add(configuration);
		Toast.makeText(this, R.string.updated, Toast.LENGTH_LONG).show();

		/*
		 * TODO para voltar automaticamente para a tela anterior, eh isso?
		 */
		// NavUtils.navigateUpFromSameTask(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		selectedConfigurationId = data.getStringExtra("selectedConfigurationId");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		EditText keyEditText = (EditText) findViewById(R.id.editKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.editValueInput);

		//Configuration configuration = Data.getInstance().getConfigs().get(this.selectedConfigurationId);
		
		keyEditText.setText("asdfg");
		valueEditText.setText("12345");		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}