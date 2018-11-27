package com.darckcode.reports;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import com.darckcode.reports.model.*;
import android.view.ViewTreeObserver.*;
import android.view.ContextMenu.*;
import android.view.*;
import android.content.*;
import com.darckcode.reports.activity.*;

public class MainActivity extends Activity 
{
	ListView listViewPersons;
	ArrayList<Person> persons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		listViewPersons = (ListView) findViewById(R.id.listPersons);
		
    }

	@Override
	public void onCreateContextMenu ( ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo )
	{
		// TODO: Implement this method
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu ( Menu menu )
	{
		getMenuInflater().inflate(R.menu.menu,menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected ( int featureId, MenuItem item )
	{
		// TODO: Implement this method
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected ( MenuItem item )
	{
		switch(item.getItemId()){
			case R.id.itemCreateReport:
				Intent openConfiguration = new Intent(this,FormReport.class);	
				startActivity(openConfiguration);
				return true;
		}
		
		return true;
	}
	
	
	
}
