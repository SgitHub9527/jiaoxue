package com.daowen.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.daowen.dal.DALBase;
import com.daowen.entity.Indexcolumns;
import com.daowen.entity.Lanmu;
import com.daowen.util.PagerMetal;

/**************************
 * 
 * @author daowen
 * 
 *         首页栏目控制
 * 
 */
public class IndexcolumnsAction extends PageActionBase {
	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("indexcolumns", " where id=" + id);
		binding();
	}

	
	
	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String suzuid=request.getParameter("suzuid");
		String errorurl = request.getParameter("errorurl");
		
		String xtype=request.getParameter("xtype");
		if(suzuid!=null)
		{
			Lanmu temspc=(Lanmu)DALBase.load("lanmu", "where id="+suzuid);
			if(temspc!=null&&xtype!=null&&xtype.equals("1"))
			{
				Indexcolumns indexcolumns = new Indexcolumns();
				indexcolumns.setColdes(temspc.getTitle());
				indexcolumns.setColid(temspc.getId());
				indexcolumns.setXtype("资讯");
				indexcolumns.setShowstyle("图片");
				indexcolumns.setLayout("1");
				indexcolumns.setWidth("100%");
				
				DALBase.save(indexcolumns);
			}
		}
		if (forwardurl == null) {
			forwardurl = "/admin/indexcolumnsmanager.do?actiontype=get";
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
		Indexcolumns indexcolumns = (Indexcolumns) DALBase.load(
				Indexcolumns.class, new Integer(id));
		if (indexcolumns == null)
			return;
		String coldes = request.getParameter("coldes");
		String colid = request.getParameter("colid");
		String xtype = request.getParameter("xtype");
		String showstyle = request.getParameter("showstyle");
		String layout=request.getParameter("layout");
		String width=request.getParameter("width");
		
		indexcolumns.setColdes(coldes);
		indexcolumns.setColid(colid == null ? 0 : new Integer(colid));
		indexcolumns.setXtype(xtype);
		indexcolumns.setShowstyle(showstyle);
		indexcolumns.setLayout(layout);
		indexcolumns.setWidth(width);
		DALBase.update(indexcolumns);
		
		if (forwardurl == null) {
			forwardurl = "/admin/indexcolumnsmanager.do?actiontype=get";
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
			Indexcolumns indexcolumns = (Indexcolumns) DALBase.load(
					"indexcolumns", "where id=" + id);
			if (indexcolumns != null) {
				request.setAttribute("indexcolumns", indexcolumns);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/indexcolumnsadd.jsp";
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
		String coldes = request.getParameter("coldes");
		if (coldes != null)
			filter += "  and coldes like '%" + coldes + "%'  ";
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
		List<Indexcolumns> listindexcolumns = DALBase.getPageEnity(
				"indexcolumns", filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("indexcolumns",
				filter == null ? "" : filter);
		request.setAttribute("listindexcolumns", listindexcolumns);
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
			forwardurl = "/admin/indexcolumnsmanager.jsp";
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
