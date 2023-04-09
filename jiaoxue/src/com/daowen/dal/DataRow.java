package com.daowen.dal;

import java.util.Date;
import java.util.HashMap;



public class DataRow {

	private HashMap<String, DataColumn> columns=null;
	
	protected  DataRow(){
		columns=new HashMap<String, DataColumn>();
	}
	
	public DataColumn addColumn(DataColumn column){
		columns.put(column.getName(), column);
		return column;
	}
	
	public Object get(String columnName){
		return columns.get(columnName)==null?null:columns.get(columnName).getColumnVal();
	}
	
	
	public String getString(String columnName){
		Object val=get(columnName);
		return val==null?null:val.toString();
		
	}
	
	public Double getDouble(String columnName){
		
		if(get(columnName)==null)
			return 0.0;
		return columns.get(columnName).getDouble();
		
	}
	public Number getNumber(String columnName){
		if(get(columnName)==null)
			return 0.0;
		return columns.get(columnName).getNumber();
	}
	public DataColumn setColumn(String columnName,Object value){
		DataColumn dataColumn=new DataColumn(columnName,value);
		return columns.put(columnName,dataColumn);
	}
	
	public Date getDate(String columnName){
		if(get(columnName)==null)
			return null;
		return columns.get(columnName).getDate();
		
	}
	
	public int getInt(String columnName){
		if(get(columnName)==null)
			return 0;
		return columns.get(columnName).getInt();
		
	}
	
	
}
