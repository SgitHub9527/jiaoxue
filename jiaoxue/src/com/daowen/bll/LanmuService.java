package com.daowen.bll;

import java.util.List;

import com.daowen.dal.DALBase;
import com.daowen.entity.Lanmu;

public class LanmuService {

	public List<Lanmu> getSublanmus(int lanmuId){
		
		Lanmu lanmu=null;
		List<Lanmu> listLanmu=null;
		if(lanmuId>0){
		    
	    	listLanmu=DALBase.getEntity("lanmu", "where parentid="+lanmuId);
	       
	     }
	     
	     return listLanmu;
	}
	
	
	
}
