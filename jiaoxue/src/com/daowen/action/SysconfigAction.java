package com.daowen.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;

import com.daowen.dal.DALBase;
import com.daowen.entity.Sysconfig;
import com.daowen.util.PagerMetal;


public class SysconfigAction extends PageActionBase {
	


	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("sysconfig", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String title = request.getParameter("title");
		String dcontent = request.getParameter("dcontent");
		SimpleDateFormat sdfsysconfig = new SimpleDateFormat("yyyy-MM-dd");
		Sysconfig sysconfig = new Sysconfig();
		sysconfig.setTitle(title == null ? "" : title);
		sysconfig.setDcontent(dcontent == null ? "" : dcontent);
		DALBase.save(sysconfig);

		if(forwardurl==null)
			forwardurl="/admin/sysconfigmanager.do?actiontype=get";
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
		Sysconfig sysconfig = (Sysconfig) DALBase.load(Sysconfig.class,
				new Integer(id));
		if (sysconfig == null)
			return;
		String title = request.getParameter("title");
		String dcontent = request.getParameter("dcontent");
		SimpleDateFormat sdfsysconfig = new SimpleDateFormat("yyyy-MM-dd");
		sysconfig.setTitle(title);
		sysconfig.setDcontent(dcontent);
		DALBase.update(sysconfig);
		if(forwardurl==null)
			forwardurl="/admin/sysconfigmanager.do?actiontype=get";
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
			Sysconfig sysconfig = (Sysconfig) DALBase.load("sysconfig",
					"where id=" + id);
			if (sysconfig != null) {
				request.setAttribute("sysconfig", sysconfig);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/sysconfigadd.jsp";
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
		List<Sysconfig> listsysconfig = DALBase.getPageEnity("sysconfig",
				filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("sysconfig",
				filter == null ? "" : filter);
		request.setAttribute("listsysconfig", listsysconfig);
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
			forwardurl = "/admin/sysconfigmanager.jsp";
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
