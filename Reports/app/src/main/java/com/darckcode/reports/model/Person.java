package com.darckcode.reports.model;

public class Person
{
	private String name;
	private String lastName;
	private int id;

	public Person ( )
	{
	}

	public Person ( String name, String lastName )
	{
		this.name = name;
		this.lastName = lastName;
	}

	public Person ( String name, String lastName, int id )
	{
		this.name = name;
		this.lastName = lastName;
		this.id = id;
	}
	
	

	public void setName ( String name )
	{
		this.name = name;
	}

	public String getName ( )
	{
		return name;
	}

	public void setLastName ( String lastName )
	{
		this.lastName = lastName;
	}

	public String getLastName ( )
	{
		return lastName;
	}

	public void setId ( int id )
	{
		this.id = id;
	}

	public int getId ( )
	{
		return id;
	}}
