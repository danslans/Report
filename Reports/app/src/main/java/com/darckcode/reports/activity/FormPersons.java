package com.darckcode.reports.activity;
import android.app.*;
import android.os.*;
import com.darckcode.reports.*;
import android.widget.*;
import android.view.*;

public class FormPersons extends Activity
{
	EditText name;
	@Override
	public void onCreate ( Bundle savedInstanceState, PersistableBundle persistentState )
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState, persistentState);
		setContentView(R.layout.form_persons);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		name = (EditText) findViewById(R.id.eTextNamePerson);

		
	}

	@Override
	public boolean onCreateOptionsMenu ( Menu menu )
	{
		getMenuInflater().inflate(R.menu.menu_form_persons,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected ( MenuItem item )
	{
		switch(item.getItemId()){
			case R.id.itemGuardarPerson:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private boolean validarCampos(){
		return name.getText().toString().isEmpty();
	}
	
	
}
