package com.eleonorvinicius.ace;

import com.eleonorvinicius.ace.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {

	public void save(View view){
		EditText keyEditText = (EditText) findViewById(R.id.createKeyInput);
		EditText valueEditText = (EditText) findViewById(R.id.createValueInput);
		
		String key = keyEditText.getText().toString();
		String value = valueEditText.getText().toString();
		
		if (key.trim().isEmpty() || value.trim().isEmpty()){
			Toast.makeText(this, getText(R.string.invalid), Toast.LENGTH_LONG).show();
			return;
		}
		
		Configuration configuration = new Configuration(key, value);
		Data.getInstance().add(configuration);
		Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show();
		
		//NavUtils.navigateUpFromSameTask(this);		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
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
