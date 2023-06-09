﻿package com.daowen.action;

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
 *  收藏夹控制
 * 
 */
public class ShoucangAction extends PageActionBase {
	
	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("shoucang", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String bookid = request.getParameter("bookid");
		String bookname = request.getParameter("bookname");
		String href=request.getParameter("href");
		String xtype=request.getParameter("xtype");
		String tupian = request.getParameter("tupian");
		String scren = request.getParameter("scren");
		String sctime = request.getParameter("sctime");
		SimpleDateFormat sdfshoucang = new SimpleDateFormat("yyyy-MM-dd");
		Shoucang shoucang = new Shoucang();
		shoucang.setBookid(bookid == null ? 0 : new Integer(bookid));
		shoucang.setBookname(bookname == null ? "" : bookname);
		shoucang.setTupian(tupian == null ? "" : tupian);
		shoucang.setScren(scren == null ? "" : scren);
		shoucang.setXtype(xtype==null?"":xtype);
		shoucang.setHref(href==null?"":href);
		if (sctime != null) {
			try {
				shoucang.setSctime(sdfshoucang.parse(sctime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			shoucang.setSctime(new Date());
		}
		// 产生验证
		Boolean validateresult = saveValidate(MessageFormat.format("where bookid=''{0}'' and scren=''{1}'' ",bookid,scren));
		if (validateresult) {
			try {
				request.setAttribute("sctips",
						"<span class='fa fa-check'></span>你已经收藏了");
				request.setAttribute("shoucang", shoucang);
				request.setAttribute("actiontype", "save");
				request.getRequestDispatcher(errorurl).forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		DALBase.save(shoucang);
		
		if (forwardurl == null) {
			forwardurl = "/admin/shoucangmanager.do?actiontype=get";
		}
		
		try {
			
			request.setAttribute("sctips", "<span class='fa fa-check'></span>成功收藏该信息");
			request.getRequestDispatcher(forwardurl).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 新增验证
	private boolean saveValidate(String filter) {
		return DALBase.isExist("shoucang", filter);
	}

	

	

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		String actiontype = "save";
		dispatchParams(request, response);
		if (id != null) {
			Shoucang shoucang = (Shoucang) DALBase.load("shoucang", "where id="
					+ id);
			if (shoucang != null) {
				request.setAttribute("shoucang", shoucang);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/shoucangadd.jsp";
		}
		try {
			request.getRequestDispatcher(forwardurl).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String bookid = request.getParameter("bookid");
		String scren=request.getParameter("scren");
		if (bookid != null)
			filter += "  and bookid like '%" + bookid + "%'  ";
		//
		if (scren != null)
			filter += "  and scren='" + scren + "'  ";
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
		List<Shoucang> listshoucang = DALBase.getPageEnity("shoucang", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("shoucang",
				filter == null ? "" : filter);
		request.setAttribute("listshoucang", listshoucang);
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
			forwardurl = "/admin/shoucangmanager.jsp";
		}
		try {
			request.getRequestDispatcher(forwardurl).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
