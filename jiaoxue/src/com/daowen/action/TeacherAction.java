package com.daowen.action;

import javax.servlet.ServletException;

import com.daowen.dal.*;
import com.daowen.bll.*;
import com.daowen.entity.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.MessageFormat;

import com.daowen.util.PagerMetal;

public class TeacherAction extends PageActionBase {

	private void modifyPw() {
		String password1 = request.getParameter("password1");
		String repassword1 = request.getParameter("repassword1");

		String forwardurl = request.getParameter("forwardurl");
		String errorurl = request.getParameter("errorurl");
		String id = request.getParameter("id");
		if (id == null || id == "")
			return;
		Teacher teacher = (Teacher) DALBase
				.load(Teacher.class, new Integer(id));

		if (teacher == null) {
			request.setAttribute("errormsg",
					"<label class='error'>账户不存在，不能修改</label>");
			forward(errorurl);

		}
		if (!teacher.getPassword().equals(password1)) {

			request.setAttribute("errormsg",
					"<label class='error'>原始密码不正确，不能修改</label>");
			forward(errorurl);
		} else {
			teacher.setPassword(repassword1);
			DALBase.update(teacher);
			request.getSession().setAttribute("teacher", teacher);
			redirect(forwardurl);
		}
	}

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("teacher", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String tno = request.getParameter("tno");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String xiangpian = request.getParameter("xiangpian");

		String zhicheng = request.getParameter("zhicheng");
		String xueli = request.getParameter("xueli");
		String jiguan = request.getParameter("jiguan");

		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String jiaoyanshi = request.getParameter("jiaoyanshi");
		String lvli = request.getParameter("lvli");
		SimpleDateFormat sdfteacher = new SimpleDateFormat("yyyy-MM-dd");
		Teacher teacher = new Teacher();
		teacher.setTno(tno == null ? "" : tno);
		teacher.setName(name == null ? "" : name);
		teacher.setSex(sex == null ? "" : sex);
		teacher.setXiangpian(xiangpian == null ? "" : xiangpian);
		teacher.setLogintimes(0);
		teacher.setPassword("123456");
		teacher.setZhicheng(zhicheng == null ? "" : zhicheng);
		teacher.setXueli(xueli == null ? "" : xueli);
		teacher.setJiguan(jiguan == null ? "" : jiguan);
		teacher.setAge(40);
		teacher.setEmail(email == null ? "" : email);
		teacher.setMobile(mobile == null ? "" : mobile);
		teacher.setJiaoyanshi(jiaoyanshi == null ? "" : jiaoyanshi);
		teacher.setLvli(lvli == null ? "" : lvli);
		DALBase.save(teacher);
		String forwardurl = request.getParameter("forwardurl");
		if (forwardurl == null) {
			forwardurl = "/admin/teachermanager.jsp";
		}

		redirect(forwardurl);
	}

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String id = request.getParameter("id");

		String forwardurl = request.getParameter("forwardurl");
		if (id == null)
			return;
		Teacher teacher = (Teacher) DALBase
				.load(Teacher.class, new Integer(id));
		if (teacher == null)
			return;
		String tno = request.getParameter("tno");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String xiangpian = request.getParameter("xiangpian");
		String logintimes = request.getParameter("logintimes");
		String password = request.getParameter("password");
		String zhicheng = request.getParameter("zhicheng");
		String xueli = request.getParameter("xueli");
		String jiguan = request.getParameter("jiguan");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String jiaoyanshi = request.getParameter("jiaoyanshi");
		String lvli = request.getParameter("lvli");
		SimpleDateFormat sdfteacher = new SimpleDateFormat("yyyy-MM-dd");
		teacher.setTno(tno);
		teacher.setName(name);
		teacher.setSex(sex);
		teacher.setXiangpian(xiangpian);

		teacher.setZhicheng(zhicheng);
		teacher.setXueli(xueli);
		teacher.setEmail(email);
		teacher.setMobile(mobile);
		teacher.setLvli(lvli);
		DALBase.update(teacher);

		if (forwardurl == null) {
			forwardurl = "/admin/teachermanager.jsp";
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
		String forwardurl = request.getParameter("forwardurl");
		dispatchParams(request, response);
		if (id != null) {
			Teacher teacher = (Teacher) DALBase.load("teacher", "where id="
					+ id);
			if (teacher != null) {
				request.setAttribute("teacher", teacher);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);

		if (forwardurl == null) {
			forwardurl = "/admin/teacheradd.jsp";
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
		String tno = request.getParameter("tno");
		String name = request.getParameter("name");
		if (tno != null)
			filter += " and tno='" + tno + "'";
		if (name != null)
			filter += " and name like '%" + name + "%'";
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
		List<Teacher> listteacher = DALBase.getPageEnity("teacher", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("teacher",
				filter == null ? "" : filter);
		request.setAttribute("listteacher", listteacher);
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
			forwardurl = "/admin/teachermanager.jsp";
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
