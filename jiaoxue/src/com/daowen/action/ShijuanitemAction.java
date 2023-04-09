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

public class ShijuanitemAction extends PageActionBase {
	
	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("shijuanitem", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String shijuanid = request.getParameter("shijuanid");
		String tihao = request.getParameter("tihao");
		String ceshititle = request.getParameter("ceshititle");
		String shijuantitle = request.getParameter("shijuantitle");
		String fenshu = request.getParameter("fenshu");
		SimpleDateFormat sdfshijuanitem = new SimpleDateFormat("yyyy-MM-dd");
		Shijuanitem shijuanitem = new Shijuanitem();
		shijuanitem
				.setShijuanid(shijuanid == null ? 0 : new Integer(shijuanid));
		shijuanitem.setTihao(tihao == null ? 0 : new Integer(tihao));
		
		shijuanitem.setFenshu(fenshu == null ? 0 : new Integer(fenshu));
		DALBase.save(shijuanitem);
		
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanitemmanager.do?actiontype=get";
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
		Shijuanitem shijuanitem = (Shijuanitem) DALBase.load(Shijuanitem.class,
				new Integer(id));
		if (shijuanitem == null)
			return;
		String shijuanid = request.getParameter("shijuanid");
		String tihao = request.getParameter("tihao");
		String ceshititle = request.getParameter("ceshititle");
		String shijuantitle = request.getParameter("shijuantitle");
		String fenshu = request.getParameter("fenshu");
		SimpleDateFormat sdfshijuanitem = new SimpleDateFormat("yyyy-MM-dd");
		shijuanitem
				.setShijuanid(shijuanid == null ? 0 : new Integer(shijuanid));
		shijuanitem.setTihao(tihao == null ? 0 : new Integer(tihao));
		
		shijuanitem.setFenshu(fenshu == null ? 0 : new Integer(fenshu));
		DALBase.update(shijuanitem);
		
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanitemmanager.do?actiontype=get";
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
			Shijuanitem shijuanitem = (Shijuanitem) DALBase.load("shijuanitem",
					"where id=" + id);
			if (shijuanitem != null) {
				request.setAttribute("shijuanitem", shijuanitem);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/shijuanitemadd.jsp";
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
		List<Shijuanitem> listshijuanitem = DALBase.getPageEnity("shijuanitem",
				filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("shijuanitem",
				filter == null ? "" : filter);
		request.setAttribute("listshijuanitem", listshijuanitem);
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
			forwardurl = "/admin/shijuanitemmanager.jsp";
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
