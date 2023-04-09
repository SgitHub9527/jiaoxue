package com.daowen.uibuilder;

import java.text.MessageFormat;
import java.util.List;

import com.daowen.dal.DALBase;
import com.daowen.entity.Indexcolumns;

public class IndexColumnsBuilder {

	
	public String buildColumns(){
		
		StringBuffer sb=new StringBuffer();
		LanmuBuilder  shangpinbuilder=new LanmuBuilder("box");
		sb.append("<div class=\"page-main-box white-paper\">");
		sb.append("<div class=\"row-flow\">");
		List<Indexcolumns>  textlist=DALBase.getEntity("indexcolumns", "where showstyle='文本'");
		
		for (Indexcolumns indexcolumns : textlist) {
			if(indexcolumns.getLayout()!=null&&indexcolumns.getLayout().equals("2"))
			{
				sb.append(MessageFormat.format("<div class=\"column\" style=\"width:{0}\">",indexcolumns.getWidth()));
			}
			
			if(indexcolumns.getShowstyle().equals("文本")){
			    sb.append(shangpinbuilder.buildTextLanmu(indexcolumns.getColid()));
			}
			sb.append("\r\n");
			
			//结束列
			if(indexcolumns.getLayout()!=null&&indexcolumns.getLayout().equals("2"))
			   sb.append("</div>");
			
			
		}
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
		
		
	}
	
    public String buildImageColumns(){
		
		StringBuffer sb=new StringBuffer();
		
		LanmuBuilder  shangpinbuilder=new LanmuBuilder("box");
		sb.append("<div class=\"page-main-box white-paper\">");
		sb.append("<div class=\"row-flow\">");
		List<Indexcolumns>  textlist=DALBase.getEntity("indexcolumns", "where showstyle='图片'");
		int i=1;
		for (Indexcolumns indexcolumns : textlist) {
			if(indexcolumns.getLayout()!=null&&indexcolumns.getLayout().equals("1"))
			{
				sb.append("<div class=\"clear\"></div>");
				
			}
			if(indexcolumns.getShowstyle().equals("图片")){
				sb.append(shangpinbuilder.buildImageLanmu(indexcolumns.getColid()));
			}
			
			sb.append("\r\n");
			
			
			i++;
			
		}
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
		
		
	}
	
	
}
