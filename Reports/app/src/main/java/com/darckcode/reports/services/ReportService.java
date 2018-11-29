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
		dataBase = db.getWritableDatabase();
		values = new ContentValues();
		values.put(ReportDb.ConfigureTable.COLUMN_REPORTS_NAME,report.getName());
		values.put(ReportDb.ConfigureTable.COLUMN_REPORTS_TYPE,report.getTypeReport());
		values.put(ReportDb.ConfigureTable.COLUMN_REPORTS_DIFFER,report.getDiffer());
		values.put(ReportDb.ConfigureTable.COLUMN_REPORTS_VALUE,report.getValue());
		long result = dataBase.insert(ReportDb.ConfigureTable.NAME_TABLE_REPORTS,null,values);
		values.clear();
		dataBase.close();
		Toast.makeText(context,""+result,Toast.LENGTH_LONG).show();
	}
	
	public ArrayList<Report> getAllReports(){
		ArrayList<Report> result = new ArrayList<>();
		dataBase = db.getReadableDatabase();
		String[] columns={
			ReportDb.ConfigureTable._ID,
			ReportDb.ConfigureTable.COLUMN_REPORTS_NAME,
			ReportDb.ConfigureTable.COLUMN_REPORTS_DIFFER,
			ReportDb.ConfigureTable.COLUMN_REPORTS_TYPE,
			ReportDb.ConfigureTable.COLUMN_REPORTS_VALUE
		};
		Cursor cursor= dataBase.query(ReportDb.ConfigureTable.NAME_TABLE_REPORTS,columns,null,null,null,null,null,null);
		while(cursor.moveToNext()){
			result.add(
				new Report(cursor.getInt(0),cursor.getString(1),cursor.getString(4),cursor.getString(2),cursor.getString(3))
			);
		}
		dataBase.close();
		return result;
	}
	
	public void deleteReport(Report report){
		dataBase = db.getWritableDatabase();
		long delete= dataBase.delete(ReportDb.ConfigureTable.NAME_TABLE_REPORTS,ReportDb.ConfigureTable._ID+" = "+report.getId(),null);
		Toast.makeText(context,delete==1?"eliminado":"no se pudo eliminar",Toast.LENGTH_LONG).show();
	}
}

