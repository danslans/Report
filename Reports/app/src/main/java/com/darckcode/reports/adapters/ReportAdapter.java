package com.darckcode.reports.adapters;
import android.widget.*;
import android.view.*;
import java.util.*;
import com.darckcode.reports.model.*;
import android.content.*;
import com.darckcode.reports.*;

public class ReportAdapter extends BaseAdapter
{
	private ArrayList<Report> listReport;
	private Context context;

	public ReportAdapter ( ArrayList<Report> listReport, Context context )
	{
		this.listReport = listReport;
		this.context = context;
	}
	
	@Override
	public View getView ( int p1, View p2, ViewGroup p3 )
	{
		p2=LayoutInflater.from(context).inflate(R.layout.adapter_report,null,false);
		
		TextView name= (TextView) p2.findViewById(R.id.txtNameReport);
		TextView value = (TextView) p2.findViewById(R.id.txtValueReport);
		name.setText(listReport.get(p1).getName());
		value.setText(listReport.get(p1).getValue());
		return p2;
	}

	@Override
	public int getCount ( )
	{
		return listReport.size();
	}

	@Override
	public long getItemId ( int p1 )
	{
		return listReport.get(p1).getId();
	}

	@Override
	public Object getItem ( int p1 )
	{
		return listReport.get(p1);
	}
	
}
