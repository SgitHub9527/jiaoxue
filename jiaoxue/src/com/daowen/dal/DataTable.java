package com.daowen.dal;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

	private  List rows=null;
	
	
	public DataTable(){
		rows=new ArrayList<DataRow>();
	}
	
	public List<DataRow> getRows(){
		return rows;
	}
	
	public DataRow  addRow(DataRow row){
		this.rows.add(row);
		return row;
	}
	
	
}
