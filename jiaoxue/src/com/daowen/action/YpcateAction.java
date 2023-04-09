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

public class YpcateAction extends PageActionBase {
	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("ypcate", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String name = request.getParameter("name");
		String des = request.getParameter("des");
		SimpleDateFormat sdfypcate = new SimpleDateFormat("yyyy-MM-dd");
		Ypcate ypcate = new Ypcate();
		ypcate.setName(name == null ? "" : name);
		ypcate.setDes(des == null ? "" : des);
		DALBase.save(ypcate);
		// 保存附件

		if (forwardurl == null) {
			forwardurl = "/admin/ypcatemanager.do?actiontype=get";
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
		Ypcate ypcate = (Ypcate) DALBase.load(Ypcate.class, new Integer(id));
		if (ypcate == null)
			return;
		String name = request.getParameter("name");
		String des = request.getParameter("des");
		SimpleDateFormat sdfypcate = new SimpleDateFormat("yyyy-MM-dd");
		ypcate.setName(name);
		ypcate.setDes(des);
		DALBase.update(ypcate);

		if (forwardurl == null) {
			forwardurl = "/admin/ypcatemanager.do?actiontype=get";
		}
		 redirect(forwardurl);
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
			Ypcate ypcate = (Ypcate) DALBase.load("ypcate", "where id=" + id);
			if (ypcate != null) {
				request.setAttribute("ypcate", ypcate);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/ypcateadd.jsp";
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
		String name = request.getParameter("name");
		if (name != null)
			filter += "  and name like '%" + name + "%'  ";
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
		List<Ypcate> listypcate = DALBase.getPageEnity("ypcate", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("ypcate", filter == null ? ""
				: filter);
		request.setAttribute("listypcate", listypcate);
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
			forwardurl = "/admin/ypcatemanager.jsp";
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
