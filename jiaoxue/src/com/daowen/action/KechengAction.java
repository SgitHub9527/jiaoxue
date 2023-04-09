package com.daowen.action;

import javax.servlet.ServletException;

import com.daowen.dal.*;
import com.daowen.bll.*;
import com.daowen.entity.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.daowen.util.PagerMetal;


public class KechengAction extends PageActionBase {
	
	

	
	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("kecheng", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String mingcheng = request.getParameter("mingcheng");
		
		SimpleDateFormat sdfkecheng = new SimpleDateFormat("yyyy-MM-dd");
		Kecheng kecheng = new Kecheng();
		kecheng.setMingcheng(mingcheng == null ? "" : mingcheng);
	
		DALBase.save(kecheng);
		
		if (forwardurl == null) {
			forwardurl = "/admin/kechengmanager.do?actiontype=get";
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
		Kecheng kecheng = (Kecheng) DALBase.load(Kecheng.class, new Integer(id));
		if (kecheng == null)
			return;
		String mingcheng = request.getParameter("mingcheng");
	
		SimpleDateFormat sdfkecheng = new SimpleDateFormat("yyyy-MM-dd");
		kecheng.setMingcheng(mingcheng);
		
		DALBase.update(kecheng);
		
		if (forwardurl == null) {
			forwardurl = "/admin/kechengmanager.do?actiontype=get";
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
			Kecheng kecheng = (Kecheng) DALBase.load("kecheng", "where id="
					+ id);
			if (kecheng != null) {
				request.setAttribute("kecheng", kecheng);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/kechengadd.jsp";
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
		
		String tno=request.getParameter("tno");
		
		if(tno!=null)
			filter+=" and tno='"+tno+"'";
		
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Kecheng> listkecheng = DALBase.getPageEnity("kecheng", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("kecheng",
				filter == null ? "" : filter);
		request.setAttribute("listkecheng", listkecheng);
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
			forwardurl = "/admin/kechengmanager.jsp";
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
