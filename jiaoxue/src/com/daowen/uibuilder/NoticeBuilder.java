package com.daowen.uibuilder;
import java.text.MessageFormat;
import java.util.*;
import com.daowen.entity.*;
import com.daowen.dal.DALBase;
public class NoticeBuilder {
	
	
	public  String build() {
		StringBuffer sb = new StringBuffer();

		List<Notice> list = DALBase.getTopList("notice", "", 10);
		sb.append("<ul>");
		sb.append("\r\n");
		for (Iterator<Notice> it = list.iterator(); it.hasNext();) {
			Notice n = it.next();
			sb.append(MessageFormat.format(
					"<li ><a href=''noticeinfo.jsp?id={0}''>{1}</a></li>",
					n.getId(), n.getTitle()));
			sb.append("\r\n");
		}
		sb.append("\r\n");
		sb.append("</ul>");
		return sb.toString();

	}
	
	

}
