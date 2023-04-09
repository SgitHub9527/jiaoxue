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

public class DajuanAction extends PageActionBase {

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("dajuan", " where id=" + id);
		//删除答卷记录
	    DALBase.delete("dajuanitem","where dajuanid="+id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String title = request.getParameter("title");
		String zujuanren = request.getParameter("zujuanren");
		String kemu = request.getParameter("kemu");
		String dajuanren = request.getParameter("dajuanren");
		String shijuanid = request.getParameter("shijuanid");
		String defen = request.getParameter("defen");
		SimpleDateFormat sdfdajuan = new SimpleDateFormat("yyyy-MM-dd");
		Dajuan dajuan = new Dajuan();
		dajuan.setTitle(title == null ? "" : title);
		dajuan.setZujuanren(zujuanren == null ? "" : zujuanren);
		dajuan.setKemu(kemu == null ? "" : kemu);
		dajuan.setDajuanren(dajuanren == null ? "" : dajuanren);
		dajuan.setShijuanid(shijuanid == null ? "" : shijuanid);
		dajuan.setDefen(defen == null ? 0 : new Integer(defen));
		DALBase.save(dajuan);
		
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanmanager.do?actiontype=get";
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
		Dajuan dajuan = (Dajuan) DALBase.load(Dajuan.class, new Integer(id));
		if (dajuan == null)
			return;
		String title = request.getParameter("title");
		String zujuanren = request.getParameter("zujuanren");
		String kemu = request.getParameter("kemu");
		String dajuanren = request.getParameter("dajuanren");
		String shijuanid = request.getParameter("shijuanid");
		String defen = request.getParameter("defen");
		SimpleDateFormat sdfdajuan = new SimpleDateFormat("yyyy-MM-dd");
		dajuan.setTitle(title);
		dajuan.setZujuanren(zujuanren);
		dajuan.setKemu(kemu);
		dajuan.setDajuanren(dajuanren);
		dajuan.setShijuanid(shijuanid);
		dajuan.setDefen(defen == null ? 0 : new Integer(defen));
		DALBase.update(dajuan);
	
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanmanager.do?actiontype=get";
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
			Dajuan dajuan = (Dajuan) DALBase.load("dajuan", "where id=" + id);
			if (dajuan != null) {
				request.setAttribute("dajuan", dajuan);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/dajuanadd.jsp";
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
		String title = request.getParameter("title");
		String  dajuanren=request.getParameter("dajuanren");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		
		if (dajuanren != null)
			filter += "  and dajuanren='" + dajuanren + "'  ";
		
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
		List<Dajuan> listdajuan = DALBase.getPageEnity("dajuan", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("dajuan", filter == null ? ""
				: filter);
		request.setAttribute("listdajuan", listdajuan);
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
			forwardurl = "/admin/dajuanmanager.jsp";
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
