package com.darckcode.reports.adapters;
import android.widget.*;
import android.content.*;
import java.util.*;
import com.darckcode.reports.model.*;
import android.view.*;
import android.text.*;
import com.darckcode.reports.*;

public class PersonAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<Person> persons;

	@Override
	public View getView ( int p1, View p2, ViewGroup p3 )
	{
		if(p2 != null){
			p2= LayoutInflater.from(p2.getContext()).inflate(R.layout.adapter_person,null,false);
		}else{
			p2= LayoutInflater.from(context).inflate(R.layout.adapter_person,null,false);
		}
		TextView nombre=(TextView) p2.findViewById(R.id.textName);
		TextView lastNombre=(TextView) p2.findViewById(R.id.textLastName);
		lastNombre.setText(persons.get(p1).getLastName());
		nombre.setText(persons.get(p1).getLastName());
		return p2;
	}

	@Override
	public int getCount ( )
	{
		return persons.size();
	}

	@Override
	public long getItemId ( int p1 )
	{
		return persons.get(p1).getId();
	}

	@Override
	public Object getItem ( int p1 )
	{
		return persons.get(p1);
	}

}
