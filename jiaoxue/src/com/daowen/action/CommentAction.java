package com.daowen.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.daowen.dal.DALBase;
import com.daowen.entity.Comment;
import com.daowen.util.PagerMetal;

public class CommentAction extends PageActionBase {

	

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("comment", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String photo = request.getParameter("photo");

		String commentren = request.getParameter("currenthy");
		String commentcontent = request.getParameter("commentcontent");
		String xtype = request.getParameter("xtype");
		String belongid = request.getParameter("belongid");
        String istopic=request.getParameter("istopic");
        String topicid=request.getParameter("topicid");
		Comment comment = new Comment();
		comment.setPhoto(photo);
		comment.setCommenttime(new Date());
        if(istopic!=null)
        	comment.setTopicid(new Integer(topicid));
        else 
        	comment.setTopicid(0);
        if(topicid!=null)
        	comment.setIstopic(new Integer(istopic));
        else
        	comment.setTopicid(0);
		comment.setCommentren(commentren == null ? "" : commentren);
		comment.setCommentcontent(commentcontent == null ? "" : commentcontent);
		comment.setXtype(xtype == null ? "" : xtype);
		comment.setBelongid(belongid == null ? "" : belongid);
		DALBase.save(comment);
		String forwardurl = request.getParameter("forwardurl");
		if (forwardurl == null) {
			forwardurl = "/admin/commentmmanager.do?actiontype=get";
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
		if (id != null) {
			Comment comment = (Comment) DALBase.load("comment", "where id="
					+ id);
			if (comment != null) {
				request.setAttribute("comment", comment);
			}
			actiontype = "update";
		}
		request.setAttribute("id", id);
		request.setAttribute("actiontype", actiontype);
		try {
			request.getRequestDispatcher("commentadd.jsp").forward(request,
					response);
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
		String filter = "";
		//
		String commentren = request.getParameter("commentren");
		if (commentren != null)
			filter = "  where commentren like '%" + commentren + "%'  ";
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
		List<Comment> listcomment = DALBase.getPageEnity("comment", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("comment",
				filter == null ? "" : filter);
		request.setAttribute("listcomment", listcomment);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		try {
			request.getRequestDispatcher("/admin/commentmanager.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
