package com.darckcode.reports.services;
import android.content.*;
import com.darckcode.reports.model.*;
import android.database.sqlite.*;
import com.darckcode.reports.db.*;
import android.widget.*;
import java.util.*;
import android.database.*;

public class ReportService
{
	private Context context;
	private SQLiteDatabase dataBase;
	private ContentValues values;
	private ReportDb db;

	public ReportService ( Context context )
	{
		this.context = context;
		db = new ReportDb(context);
	}
	
	public void saveReport (Report report){

	}
	
	public ArrayList<Report> getAllReports(){
		ArrayList<Report> result = new ArrayList<>();

		return result;
	}
	
	public void deleteReport(Report report){

	}
}

