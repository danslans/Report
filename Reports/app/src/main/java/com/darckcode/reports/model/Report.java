package com.darckcode.reports.model;

public class Report
{
	private int id;
	private String name;
	private String value;
	private String differ;
	private String typeReport;

	public Report ( int id, String name, String value, String differ, String typeReport )
	{
		this.id = id;
		this.name = name;
		this.value = value;
		this.differ = differ;
		this.typeReport = typeReport;
	}

	public Report ()
	{
	}

	public Report ( String name, String value, String differ, String typeReport )
	{
		this.name = name;
		this.value = value;
		this.differ = differ;
		this.typeReport = typeReport;
	}

	public void setId ( int id )
	{
		this.id = id;
	}

	public int getId ( )
	{
		return id;
	}

	public void setName ( String name )
	{
		this.name = name;
	}

	public String getName ( )
	{
		return name;
	}

	public void setValue ( String value )
	{
		this.value = value;
	}

	public String getValue ( )
	{
		return value;
	}

	public void setDiffer ( String differ )
	{
		this.differ = differ;
	}

	public String getDiffer ( )
	{
		return differ;
	}

	public void setTypeReport ( String typeReport )
	{
		this.typeReport = typeReport;
	}

	public String getTypeReport ( )
	{
		return typeReport;
	}

	@Override
	public String toString ( )
	{
		return name + " " + value + " " + differ + " " +typeReport;
	}
	
	
}
