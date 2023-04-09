package com.daowen.dal;

import java.util.Date;


public class DataColumn {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getColumnVal() {
		return columnVal;
	}

	public void setColumnVal(Object columnVal) {
		this.columnVal = columnVal;
	}

	private Object columnVal;
	
	
	public String getString(){
        return this.columnVal.toString();
    }
	public Date  getDate(){
		return (Date)columnVal;
	}
	
	public int getInt(){
		return (Integer)columnVal;
	}
	public double getDouble(){
		return (Double)columnVal;
	}
	public Number getNumber(){
		return (Number)columnVal;
	}

	public DataColumn(String name, Object columnVal) {
		this.name = name;
		this.columnVal = columnVal;
	}
	public DataColumn(){
		
	}



	
	
	
}
