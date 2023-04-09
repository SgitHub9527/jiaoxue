package com.daowen.bll;

import java.text.MessageFormat;
import java.util.List;

import com.daowen.dal.DALBase;
import com.daowen.dal.DataTable;
import com.daowen.entity.Xinxi;

public class XinxiService {

	
	public  List<Xinxi> findXinxi(int lanmuId){
		
		String sql=MessageFormat.format("select * from  xinxi where lanmuid in (select id from lanmu where parentid={0}) or lanmuid={0} order by pubtime desc",lanmuId);
		if(lanmuId<=0)
			sql="selec * from xinxi";
		List<Xinxi> list=DALBase.runNativeSQL(sql,Xinxi.class);
		return list;
	}
	
	public  List<Xinxi> findXinxi(int lanmuId ,int pageIndex,int pageSize){
		String sql=MessageFormat.format(" select * from  xinxi where lanmuid in (select id from lanmu where parentid={0}) or lanmuid={0} order by pubtime desc  ",lanmuId);
		if(lanmuId<=0)
			sql=" selec * from xinxi";
		List<Xinxi> list=DALBase.runNativeSQL(sql,Xinxi.class,pageIndex,pageSize);
		return list;
	}
	
	public  int getCount(int lanmuid ){
		
		String sql=MessageFormat.format(" select count(*) from  xinxi where lanmuid in (select id from lanmu where parentid={0}) or lanmuid={0} ",lanmuid);
		return DALBase.getUniqueResult(sql);
	}
	
	
    public  List<Xinxi> getTopXinxi(int lanmuId,int topCount){
		String sql=MessageFormat.format("select  * from  xinxi where lanmuid in (select id from lanmu where parentid={0}) or lanmuid={0} order by pubtime desc  limit {1} ",lanmuId,topCount);
    	
    	List<Xinxi> list=DALBase.runNativeSQL(sql);
		return list;
	}
    
     public DataTable  getFriendNews(String accountname){
		String sql=MessageFormat.format(" select b.*,f.gzaccount from xinxi b,huiyuan h,friends f where  h.accountname=f.hyaccount and h.accountname=''{0}'' and f.gzaccount=b.pubren      ", accountname);
	    return	DALBase.executeQuery(sql);
	}
	
	
	
}
