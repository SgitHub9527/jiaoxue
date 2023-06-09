package com.daowen.uibuilder;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daowen.dal.DALBase;
import com.daowen.entity.Huiyuan;
import com.daowen.entity.Yingpian;
import com.daowen.entity.Ypcate;

public class YingpianUibuilder  {
	
	private HttpServletRequest request;
	public  YingpianUibuilder(HttpServletRequest request){
		this.request=request;
	}
	
	public  String  buildVideo(int cateid)
	{
		String filter="";
		Ypcate  temYpcate=null;
		if(cateid>0){
			filter="where ypcateid="+cateid;
			temYpcate=(Ypcate)DALBase.load("ypcate", "where id="+cateid);
		}
		List<Yingpian> list=DALBase.getEntity("yingpian", filter);
		return buildVideo(list,temYpcate==null?"视频信息":temYpcate.getName());
		
	}
	public  String  buildTopVideo(int count,String lanmuName)
	{
		List<Yingpian> listyp=DALBase.getTopList("yingpian", "order by id desc", count);
		return buildVideo(listyp, lanmuName);
	}
	
	public String  buildVideo(List<Yingpian> list,String lanmuName){
		if(list==null)
			return "";
		StringBuilder sb=new StringBuilder();
		sb.append(" <div class=\"video-cover-fluid\">");
		sb.append("<div class=\"title\">");
		sb.append(MessageFormat.format("<span>{0}</span>",lanmuName));
		sb.append("</div>");
		sb.append("<ul>");
		for(Yingpian yingpian : list){
			sb.append("<li>");
			sb.append(MessageFormat.format("<a href=\"{0}\\e\\yingpianinfo.jsp?id={1}\">",request.getContextPath(),yingpian.getId()));
			sb.append(MessageFormat.format("<img src=\"{0}\" width=\"158\" height=\"108\" />",yingpian.getTupian()));
			sb.append("  <div class=\"vedio-play-button\"></div>");
			sb.append(MessageFormat.format("<span>{0}</span>",yingpian.getTitle()));
			sb.append("</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		sb.append("</div>");
		return sb.toString();
	}
	
	public YingpianUibuilder view(HttpServletResponse response,String id){
		Huiyuan huiyuan=(Huiyuan)request.getSession().getAttribute("huiyuan");
		if(huiyuan==null)
			return this;
 		String username=huiyuan==null?"":huiyuan.getAccountname();
 		//获取历史浏览
 		new ViewHistory("yingpian_browser_"+username).view(request,response,id);
 		return this;
	}

    public String  buildViewedProduct(){
	 		
    	    
	 		StringBuilder sb=new StringBuilder();
	 		Huiyuan huiyuan=(Huiyuan)request.getSession().getAttribute("huiyuan");
	 		if(huiyuan==null)
	 			return "";
	 		String username=huiyuan==null?"":huiyuan.getAccountname();
	 		
	 		//获取历史浏览
	 		List<String> ids=new ViewHistory("yingpian_browser_"+username).HistoryView(request);
	 		String temids="";
	 		int i=0;
	 		for(String id : ids){
	 			temids+=id;
	 			if(i<ids.size()-1){
	 				temids+=",";
	 			}
	 			i++;
	 		
	 		}
	 		if(ids!=null&&ids.size()>0){
	 		  List<Yingpian> shangpinlist=DALBase.getEntity("yingpian", " where id in ("+temids+")");
	 		//得到最新商品信息 
	 		  sb.append(buildVideo(shangpinlist, "猜你感兴趣视频"));
	 		}
	     	return sb.toString();
	 	} 
	
	
	
}
