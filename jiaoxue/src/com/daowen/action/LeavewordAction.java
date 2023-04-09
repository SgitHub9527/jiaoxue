package com.daowen.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.daowen.dal.DALBase;
import com.daowen.entity.Leaveword;
import com.daowen.util.PagerMetal;


/**************************
 * 
 * @author daowen
 * 
 *         留言控制
 * 
 */
public class LeavewordAction extends PageActionBase {
	
	
	
	public void reply() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		
		if (id == null)
			return;
		Leaveword leaveword = (Leaveword) DALBase.load(Leaveword.class,
				new Integer(id));
		if (leaveword == null)
			return;
		
		String replycontent = request.getParameter("replycontent");
		
		
		leaveword.setReplycontent(replycontent);
		leaveword.setStatus(2);
		
		DALBase.update(leaveword);
		if (forwardurl == null) {
			forwardurl = "/admin/leavewordmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("leaveword", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String title = request.getParameter("title");
		String dcontent = request.getParameter("dcontent");
		String createtime = request.getParameter("createtime");
		String replyren = request.getParameter("replyren");
		String teachername=request.getParameter("teachername");
		String replytime = request.getParameter("replytime");
		String replycontent = request.getParameter("replycontent");
		String status = request.getParameter("status");
		String lyren = request.getParameter("lyren");
		String stname = request.getParameter("stname");
		SimpleDateFormat sdfleaveword = new SimpleDateFormat("yyyy-MM-dd");
		Leaveword leaveword = new Leaveword();
		leaveword.setTitle(title == null ? "" : title);
		leaveword.setDcontent(dcontent == null ? "" : dcontent);
		if (createtime != null) {
			try {
				leaveword.setCreatetime(sdfleaveword.parse(createtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			leaveword.setCreatetime(new Date());
		}
		leaveword.setReplyren(replyren == null ? "" : replyren);
		if (replytime != null) {
			try {
				leaveword.setReplytime(sdfleaveword.parse(replytime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			leaveword.setReplytime(new Date());
		}
		leaveword.setTeachername(teachername);
		leaveword.setReplycontent(replycontent == null ? "" : replycontent);
		leaveword.setStatus(1);
		leaveword.setLyren(lyren == null ? "" : lyren);
		leaveword.setStname(stname == null ? "" : stname);
		DALBase.save(leaveword);
		if (forwardurl == null) {
			forwardurl = "/admin/leavewordmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 内部附件支持*********************
	 *******************************************************/
	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Leaveword leaveword = (Leaveword) DALBase.load(Leaveword.class,
				new Integer(id));
		if (leaveword == null)
			return;
		String title = request.getParameter("title");
		String dcontent = request.getParameter("dcontent");
		String createtime = request.getParameter("createtime");
		String replyren = request.getParameter("replyren");
		String replytime = request.getParameter("replytime");
		String replycontent = request.getParameter("replycontent");
		String status = request.getParameter("status");
		String lyren = request.getParameter("lyren");
		String stname = request.getParameter("stname");
		SimpleDateFormat sdfleaveword = new SimpleDateFormat("yyyy-MM-dd");
		leaveword.setTitle(title);
		leaveword.setDcontent(dcontent);
		if (createtime != null) {
			try {
				leaveword.setCreatetime(sdfleaveword.parse(createtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		leaveword.setReplyren(replyren);
		if (replytime != null) {
			try {
				leaveword.setReplytime(sdfleaveword.parse(replytime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		leaveword.setReplycontent(replycontent);
		
		leaveword.setLyren(lyren);
		leaveword.setStname(stname);
		DALBase.update(leaveword);
		if (forwardurl == null) {
			forwardurl = "/admin/leavewordmanager.do?actiontype=get";
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
			Leaveword leaveword = (Leaveword) DALBase.load("leaveword",
					"where id=" + id);
			if (leaveword != null) {
				request.setAttribute("leaveword", leaveword);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/leavewordadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String title = request.getParameter("title");
		String replyren=request.getParameter("replyren");
		String lyren=request.getParameter("lyren");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
		if (replyren != null)
			filter += "  and replyren = '" + replyren + "'  ";
		if (lyren != null)
			filter += "  and lyren = '" + lyren + "'  ";
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
		List<Leaveword> listleaveword = DALBase.getPageEnity("leaveword",
				filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("leaveword",
				filter == null ? "" : filter);
		request.setAttribute("listleaveword", listleaveword);
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
			forwardurl = "/admin/leavewordmanager.jsp";
		}
		forward(forwardurl);
	}
}
