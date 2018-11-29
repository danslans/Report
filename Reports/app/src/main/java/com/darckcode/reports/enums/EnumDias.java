package com.darckcode.reports.enums;
import java.util.*;
import java.util.function.*;

public enum EnumDias
{
	LUNES("L","LUNES"),
	MARTES("M","MARTES"),
	MIERCORES("MC","MIERCOLES"),
	JUEVES("J","JUEVES"),
	VIERNES("V","VIERNES"),
	SABADO("S","SABADOS"),
	DOMINGO("D","DOMINGOS");

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public String getDescripcion()
	{
		return descripcion;
	};

	 EnumDias(String codigo, String descripcion)
	{
		this.codigo = codigo;
		this.descripcion = descripcion;
	};
	
	private String codigo;
	private String descripcion;
	private static HashMap<String,String> listaDias= new HashMap<>();
	
	static{
		EnumDias[] en= EnumDias.values();
		for(EnumDias e :  en){
			listaDias.put(e.getCodigo(),e.getDescripcion());
		}
	}
	
	
	public static String getDia(String codigo){
		return listaDias.get(codigo);
	}
	
}
