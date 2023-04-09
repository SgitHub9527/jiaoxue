package com.daowen.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.hibernate.Query;
import com.daowen.dal.*;
import com.daowen.bll.*;
import com.daowen.entity.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.MessageFormat;
import com.daowen.util.PagerMetal;

/**************************
 * 
 * @author daowen
 * 
 *         章控制
 * 
 */
public class ChapterAction extends PageActionBase {
	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("chapter", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String title = request.getParameter("title");
		String section = request.getParameter("section");
		String dcontent = request.getParameter("dcontent");
		String parentid = request.getParameter("parentid");
		SimpleDateFormat sdfchapter = new SimpleDateFormat("yyyy-MM-dd");
		Chapter chapter = new Chapter();
		chapter.setTitle(title == null ? "" : title);
		chapter.setSection(section == null ? 0 : new Integer(section));
		chapter.setDcontent(dcontent == null ? "" : dcontent);
		chapter.setParentid(parentid == null ? 0 : new Integer(parentid));
		DALBase.save(chapter);
		
		if (forwardurl == null) {
			forwardurl = "/admin/chaptermanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	
	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Chapter chapter = (Chapter) DALBase
				.load(Chapter.class, new Integer(id));
		if (chapter == null)
			return;
		String title = request.getParameter("title");
		String section = request.getParameter("section");
		String dcontent = request.getParameter("dcontent");
		String parentid = request.getParameter("parentid");
		SimpleDateFormat sdfchapter = new SimpleDateFormat("yyyy-MM-dd");
		chapter.setTitle(title);
		chapter.setSection(section == null ? 0 : new Integer(section));
		chapter.setDcontent(dcontent);
		chapter.setParentid(parentid == null ? 0 : new Integer(parentid));
		DALBase.update(chapter);
	
		if (forwardurl == null) {
			forwardurl = "/admin/chaptermanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		String parentid=request.getParameter("parentid");
		String actiontype = "save";
		dispatchParams(request, response);
		if(parentid!=null){
			Chapter chapter = (Chapter) DALBase.load("chapter", "where id="
					+ parentid);
			if (chapter != null) {
				request.setAttribute("parent", chapter);
			}
		}
		if (id != null) {
			Chapter chapter = (Chapter) DALBase.load("chapter", "where id="
					+ id);
			if (chapter != null) {
				request.setAttribute("chapter", chapter);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		
		
		if (forwardurl == null) {
			forwardurl = "/admin/chapteradd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String title = request.getParameter("title");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		//
		int pageindex = 1;
		int pagesize = 10;
		// 获取当前分页
		String currentpageindex = request.getParameter("currentpageindex");
		// 当前页面尺寸
		String currentpagesize = request.getParameter("pagesize");
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Chapter> listchapter = DALBase.getPageEnity("chapter", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("chapter",
				filter == null ? "" : filter);
		request.setAttribute("listchapter", listchapter);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/chaptermanager.jsp";
		}
		forward(forwardurl);
	}
}
