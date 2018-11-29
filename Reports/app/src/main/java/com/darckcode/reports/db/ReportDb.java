package com.darckcode.reports.db;
import android.database.sqlite.*;
import android.content.*;
import android.provider.*;

public class ReportDb extends SQLiteOpenHelper
{
	private static final int VERSION =1;
	private static final String NAME_DB = "report.db";

	public ReportDb ( Context context )
	{
		super(context,NAME_DB,null,VERSION);
	}

	@Override
	public void onUpgrade ( SQLiteDatabase p1, int p2, int p3 )
	{
		p1.execSQL(ConfigureTable.DROP_TABLE_REPORTS);
		p1.execSQL(ConfigureTable.DROP_TABLE_YEARVALUE);
		p1.execSQL(ConfigureTable.DROP_TABLE_PERSONS);
	}

	@Override
	public void onCreate ( SQLiteDatabase p1 )
	{
		p1.execSQL(ConfigureTable.QUERY_TABLE_PERSON);
		p1.execSQL(ConfigureTable.QUERY_TABLE_REPORTS);
		p1.execSQL(ConfigureTable.QUERY_TABLE_YEARVALUE);
	}

	public class ConfigureTable implements BaseColumns{
		public static final String NAME_TABLE_PERSON = "TBL_PERSONS";
		public static final String COLUMN_NAME_PERSON = "NAME";
		
		public static final String NAME_TABLE_REPORTS_PERSONS = "TBL_REPORT_PERSON";
		public static final String COLUMN_REPORTS_PERSONS_VALUE = "VALUE";
		public static final String COLUMN_REPORTS_PERSONS_DATE = "DATE";
		public static final String COLUMN_REPORTS_PERSONS_ID_PERSON = "ID_PERSON";
		public static final String COLUMN_REPORTS_PERSONS_ID_REPORT = "ID_REPORT";
		
		public static final String NAME_TABLE_REPORTS = "TBL_REPORTS";
		public static final String COLUMN_REPORTS_NAME = "NAME";
		public static final String COLUMN_REPORTS_TYPE = "TYPE_REPORT";
		public static final String COLUMN_REPORTS_DIFFER = "DIFFER";
		public static final String COLUMN_REPORTS_VALUE = "VALOR";
		
		public static final String QUERY_TABLE_PERSON = "CREATE TABLE "+NAME_TABLE_PERSON+" ("+
		ConfigureTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
		COLUMN_NAME_PERSON+" TEXT "+
		"); ";
		
		public static final String QUERY_TABLE_YEARVALUE = "CREATE TABLE "+NAME_TABLE_REPORTS_PERSONS+" ("+
		ConfigureTable._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
		COLUMN_REPORTS_PERSONS_VALUE+" TEXT, "+
		COLUMN_REPORTS_PERSONS_DATE+" DATE, "+
		COLUMN_REPORTS_PERSONS_ID_PERSON+ " INTEGER, "+
		COLUMN_REPORTS_PERSONS_ID_REPORT+ " INTEGER, "+
		"FOREIGN KEY ("+COLUMN_REPORTS_PERSONS_ID_PERSON+") REFERENCES "+NAME_TABLE_PERSON+"("+ConfigureTable._ID+"),"+
		"FOREIGN KEY ("+COLUMN_REPORTS_PERSONS_ID_REPORT+") REFERENCES "+NAME_TABLE_REPORTS+"("+ConfigureTable._ID+")"+
		"); ";
		public static final String QUERY_TABLE_REPORTS= "CREATE TABLE "+NAME_TABLE_REPORTS+" ("+
		ConfigureTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
		COLUMN_REPORTS_NAME+" TEXT, "+
		COLUMN_REPORTS_TYPE+" TEXT, "+
		COLUMN_REPORTS_DIFFER+" TEXT, "+
		COLUMN_REPORTS_VALUE+ " TEXT"+
		"); ";
		
		public static final String DROP_TABLE_PERSONS = "DROP TABLE IF EXISTS "+NAME_TABLE_PERSON;
		public static final String DROP_TABLE_YEARVALUE = "DROP TABLE IF EXISTS "+NAME_TABLE_REPORTS_PERSONS;
		public static final String DROP_TABLE_REPORTS = "DROP TABLE IF EXISTS "+NAME_TABLE_REPORTS;
		
	}
	
}
