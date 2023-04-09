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


public class YingpianAction extends PageActionBase {
	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("yingpian", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String title = request.getParameter("title");
		String ypcatename = request.getParameter("ypcatename");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String clickcount = request.getParameter("clickcount");
		String des = request.getParameter("des");
		String xshowtype=request.getParameter("xshowtype");
		String remoteurl=request.getParameter("remoteurl");
		String tupian = request.getParameter("tupian");
		String ypcateid=request.getParameter("ypcateid");
		String alphabetindex=request.getParameter("alphabetindex");
		SimpleDateFormat sdfyingpian = new SimpleDateFormat("yyyy-MM-dd");
		Yingpian yingpian = new Yingpian();
		
		yingpian.setTitle(title == null ? "" : title);
		yingpian.setYpcatename(ypcatename == null ? "" : ypcatename);
		yingpian.setPubren(pubren == null ? "" : pubren);
		if (pubtime != null) {
			try {
				yingpian.setPubtime(sdfyingpian.parse(pubtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			yingpian.setPubtime(new Date());
		}
		yingpian.setClickcount(clickcount == null ? 0 : new Integer(clickcount));
		yingpian.setDes(des == null ? "" : des);
		yingpian.setXshowtype(xshowtype==null?0: new Integer(xshowtype));
		yingpian.setRemoteurl(remoteurl==null?"":remoteurl);
		yingpian.setTupian(tupian == null ? "" : tupian);
		yingpian.setYpcateid(ypcateid==null?0:new Integer(ypcateid));
		yingpian.setAlphabetindex(alphabetindex==null?"":alphabetindex);
		DALBase.save(yingpian);
		// 保存附件
		attachements(new Integer(yingpian.getId()).toString());
		if (forwardurl == null) {
			forwardurl = "/admin/yingpianmanager.do?actiontype=get";
		}
        redirect(forwardurl);
	}

	/******************************************************
	 *********************** 内部附件支持*********************
	 *******************************************************/
	public void attachements( String belongid) {
		DALBase.delete("attachement", MessageFormat.format(
				" where belongid=''{0}'' and belongtable=''yingpian'' ",
				belongid));
		String[] photos = request.getParameterValues("fileuploaded");
		if (photos == null)
			return;
		for (int i = 0; i < photos.length; i++) {
			Attachement a = new Attachement();
			a.setType("images");
			a.setPubtime(new Date());
			a.setBelongfileldname("id");
			a.setFilename(photos[i]);
			a.setBelongid(belongid);
			a.setBelongtable("yingpian");
			a.setUrl(request.getContextPath() + "/upload/temp/"
					+ a.getFilename());
			a.setTitle(a.getFilename());
			DALBase.save(a);
		}
	}

	

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Yingpian yingpian = (Yingpian) DALBase.load(Yingpian.class,new Integer(id));
		if (yingpian == null)
			return;
		String xshowtype=request.getParameter("xshowtype");
		String remoteurl=request.getParameter("remoteurl");
		String title = request.getParameter("title");
		String ypcatename = request.getParameter("ypcatename");
		String ypcateid=request.getParameter("ypcateid");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String clickcount = request.getParameter("clickcount");
		String des = request.getParameter("des");
		String alphabetindex=request.getParameter("alphabetindex");
		String tupian = request.getParameter("tupian");
		SimpleDateFormat sdfyingpian = new SimpleDateFormat("yyyy-MM-dd");
		yingpian.setTitle(title);
		yingpian.setXshowtype(xshowtype==null?0: new Integer(xshowtype));
		yingpian.setRemoteurl(remoteurl==null?"":remoteurl);
		yingpian.setYpcatename(ypcatename);
		yingpian.setYpcateid(ypcateid==null?0:new Integer(ypcateid));
		
		yingpian.setPubren(pubren);
		if (pubtime != null) {
			try {
				yingpian.setPubtime(sdfyingpian.parse(pubtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		yingpian.setClickcount(clickcount == null ? 0 : new Integer(clickcount));
		yingpian.setDes(des);
		yingpian.setTupian(tupian);
		yingpian.setAlphabetindex(alphabetindex==null?"":alphabetindex);
		DALBase.update(yingpian);
		attachements(new Integer(yingpian.getId()).toString());
		if (forwardurl == null) {
			forwardurl = "/admin/yingpianmanager.do?actiontype=get";
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
			Yingpian yingpian = (Yingpian) DALBase.load("yingpian", "where id="
					+ id);
			if (yingpian != null) {
				request.setAttribute("yingpian", yingpian);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		List<Object> ypcatename_datasource = DALBase.getEntity("ypcate", "");
		request.setAttribute("ypcatename_datasource", ypcatename_datasource);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/yingpianadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String pubren =request.getParameter("pubren");
		String title = request.getParameter("title");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		//
		if(pubren !=null)
			  filter+=" and pubren='"+pubren+"'";
		
		
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
		
		
		List<Yingpian> listyingpian = DALBase.getPageEnity("yingpian", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("yingpian",
				filter == null ? "" : filter);
		request.setAttribute("listyingpian", listyingpian);
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
			forwardurl = "/admin/yingpianmanager.jsp";
		}
		forward(forwardurl);
	}
}
