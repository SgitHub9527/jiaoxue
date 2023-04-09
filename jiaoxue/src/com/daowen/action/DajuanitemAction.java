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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.daowen.util.PagerMetal;

public class DajuanitemAction extends PageActionBase {

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("dajuanitem", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String shijuanid = request.getParameter("shijuanid");
		String dajuanid = request.getParameter("dajuanid");
		String shijuantitle = request.getParameter("shijuantitle");
		String defen = request.getParameter("defen");
		String dajuanren = request.getParameter("dajuanren");
		String daan = request.getParameter("daan");
		SimpleDateFormat sdfdajuanitem = new SimpleDateFormat("yyyy-MM-dd");
		Dajuanitem dajuanitem = new Dajuanitem();
		dajuanitem.setShijuanid(shijuanid == null ? 0 : new Integer(shijuanid));
		dajuanitem.setDajuanid(dajuanid == null ? 0 : new Integer(dajuanid));
		dajuanitem.setShijuantitle(shijuantitle == null ? "" : shijuantitle);
		dajuanitem.setDefen(defen == null ? 0 : new Integer(defen));
		dajuanitem.setDajuanren(dajuanren == null ? "" : dajuanren);
		dajuanitem.setDaan(daan == null ? "" : daan);
		DALBase.save(dajuanitem);
		
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanitemmanager.do?actiontype=get";
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
		Dajuanitem dajuanitem = (Dajuanitem) DALBase.load(Dajuanitem.class,
				new Integer(id));
		if (dajuanitem == null)
			return;
		String shijuanid = request.getParameter("shijuanid");
		String dajuanid = request.getParameter("dajuanid");
		String shijuantitle = request.getParameter("shijuantitle");
		String defen = request.getParameter("defen");
		String dajuanren = request.getParameter("dajuanren");
		String daan = request.getParameter("daan");
		SimpleDateFormat sdfdajuanitem = new SimpleDateFormat("yyyy-MM-dd");
		dajuanitem.setShijuanid(shijuanid == null ? 0 : new Integer(shijuanid));
		dajuanitem.setDajuanid(dajuanid == null ? 0 : new Integer(dajuanid));
		dajuanitem.setShijuantitle(shijuantitle);
		dajuanitem.setDefen(defen == null ? 0 : new Integer(defen));
		dajuanitem.setDajuanren(dajuanren);
		dajuanitem.setDaan(daan);
		DALBase.update(dajuanitem);
		
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanitemmanager.do?actiontype=get";
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
			Dajuanitem dajuanitem = (Dajuanitem) DALBase.load("dajuanitem",
					"where id=" + id);
			if (dajuanitem != null) {
				request.setAttribute("dajuanitem", dajuanitem);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanitemadd.jsp";
		}
		forward(forwardurl);
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
		List<Dajuanitem> listdajuanitem = DALBase.getPageEnity("dajuanitem",
				filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("dajuanitem",
				filter == null ? "" : filter);
		request.setAttribute("listdajuanitem", listdajuanitem);
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
			forwardurl = "/admin/dajuanitemmanager.jsp";
		}
		forward(forwardurl);
	}
}
