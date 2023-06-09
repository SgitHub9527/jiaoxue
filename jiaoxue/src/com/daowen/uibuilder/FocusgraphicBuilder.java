package com.daowen.uibuilder;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import com.daowen.dal.DALBase;
import com.daowen.entity.Jiaodiantu;
import com.daowen.entity.Notice;

public class FocusgraphicBuilder {

	public  String build()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"slide_c\">");

		List<Jiaodiantu> list = DALBase.getTopList("jiaodiantu", "", 10);
		
		for (Iterator<Jiaodiantu> it = list.iterator(); it.hasNext();) {
			Jiaodiantu n = it.next();
			sb.append(MessageFormat.format(
					"<a target=\"_blank\" href=''{0}''><img alt=\"\" title=\"\" src=\"{1}\"></a>",
					n.getHref(), n.getTupian()));
			sb.append("\r\n");
		}
		sb.append(" <div class=\"ico_c\">");
		sb.append("\r\n");
		sb.append("</div>");
		sb.append("\r\n");
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
}
