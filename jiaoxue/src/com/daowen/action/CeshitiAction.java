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

public class CeshitiAction extends PageActionBase {

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("ceshiti", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String title = request.getParameter("title");
		String choicea = request.getParameter("choicea");
		String choiceb = request.getParameter("choiceb");
		String choicec = request.getParameter("choicec");
		String choiced = request.getParameter("choiced");
		String kmid = request.getParameter("kmid");
		String daans = request.getParameter("daan");
		System.out.println(daans.toString()+"sadddddddddd");
		String kemu = request.getParameter("kemu");
		SimpleDateFormat sdfceshiti = new SimpleDateFormat("yyyy-MM-dd");
		Ceshiti ceshiti = new Ceshiti();
		ceshiti.setTitle(title == null ? "" : title);
		ceshiti.setChoicea(choicea == null ? "" : choicea);
		ceshiti.setChoiceb(choiceb == null ? "" : choiceb);
		ceshiti.setChoicec(choicec == null ? "" : choicec);
		ceshiti.setChoiced(choiced == null ? "" : choiced);
		ceshiti.setDaan(daans == null ? "" : daans);
		ceshiti.setKmid(kmid == null ? 0 : Integer.parseInt(kmid));
		ceshiti.setKemu(kemu);
		DALBase.save(ceshiti);
		System.out.println("asss------");
		if (forwardurl == null) {
			forwardurl = "/admin/ceshitimanager.do?actiontype=get";
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
		Ceshiti ceshiti = (Ceshiti) DALBase
				.load(Ceshiti.class, new Integer(id));
		if (ceshiti == null)
			return;
		String title = request.getParameter("title");
		String choicea = request.getParameter("choicea");
		String choiceb = request.getParameter("choiceb");
		String choicec = request.getParameter("choicec");
		String choiced = request.getParameter("choiced");
		String kmid = request.getParameter("kmid");
		String daan = request.getParameter("daan");
		String kemu = request.getParameter("kemu");
		SimpleDateFormat sdfceshiti = new SimpleDateFormat("yyyy-MM-dd");
		ceshiti.setTitle(title);
		ceshiti.setChoicea(choicea);
		ceshiti.setChoiceb(choiceb);
		ceshiti.setChoicec(choicec);
		ceshiti.setChoiced(choiced);
		ceshiti.setDaan(daan);
		ceshiti.setKmid(kmid == null ? 0 : Integer.parseInt(kmid));
		ceshiti.setKemu(kemu);
		DALBase.update(ceshiti);

		if (forwardurl == null) {
			forwardurl = "/admin/ceshitimanager.do?actiontype=get";
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
			Ceshiti ceshiti = (Ceshiti) DALBase.load("ceshiti", "where id="
					+ id);
			if (ceshiti != null) {
				request.setAttribute("ceshiti", ceshiti);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		List<Object> kemu_datasource = DALBase.getEntity("kecheng", "");
		request.setAttribute("kemu_datasource", kemu_datasource);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/ceshitiadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		
		String title=request.getParameter("title");
		//
		if(title!=null)
			filter+=" and title like '%"+title+"%'";
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
		List<Ceshiti> listceshiti = DALBase.getPageEnity("ceshiti", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("ceshiti",
				filter == null ? "" : filter);
		request.setAttribute("listceshiti", listceshiti);
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
			forwardurl = "/admin/ceshitimanager.jsp";
		}
		forward(forwardurl);
	}
}
