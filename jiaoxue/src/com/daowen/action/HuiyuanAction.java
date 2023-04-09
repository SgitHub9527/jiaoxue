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

public class HuiyuanAction extends PageActionBase {


	private void chongzhi() {
		String jine = request.getParameter("jine");
		String forwardurl = request.getParameter("forwardurl");

		String id = request.getParameter("id");
		if (id == null || id == "")
			return;
		Huiyuan huiyuan = (Huiyuan) DALBase.load(Huiyuan.class, new Integer(id));
		if (huiyuan != null) {
			huiyuan.setYue(huiyuan.getYue() + Float.parseFloat(jine));
			DALBase.update(huiyuan);
			request.getSession().setAttribute("huiyuan", huiyuan);
			
			redirect(forwardurl);
		}

	}

	private void modifyPw() {

		String password = request.getParameter("password");
		String forwardurl = request.getParameter("forwardurl");
		String repassword1 = request.getParameter("repassword1");
		String errorurl=request.getParameter("errorurl");
		String id = request.getParameter("id");
		if (id == null || id == "")
			return;
		Huiyuan huiyuan = (Huiyuan) DALBase
				.load(Huiyuan.class, new Integer(id));

		if (huiyuan == null)
			return;
		
		if(!huiyuan.getPassword().equals(password)){
			request.setAttribute("errormsg",
					"<label class='error'>原始密码不正确</label>");
			
			forward(errorurl);
			return ;
		}

		huiyuan.setPassword(repassword1);
		DALBase.update(huiyuan);
		request.getSession().setAttribute("huiyuan", huiyuan);
		redirect(forwardurl);
	}

	
	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("huiyuan", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String idcardno = request.getParameter("idcardno");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String touxiang = request.getParameter("touxiang");
		String sex = request.getParameter("sex");
		String zhiye = request.getParameter("zhiye");
		String aihao = request.getParameter("aihao");
		String des = request.getParameter("des");
		if (DALBase.isExist("huiyuan", "where accountname='" + accountname
				+ "'")) {
			
				request.setAttribute("errormsg",
						"<label class='error'>用户名已经存在</label>");
				dispatchParams(request, response);
				forward("/e/register.jsp");
				return;
			
		}

		Huiyuan huiyuan = new Huiyuan();
		huiyuan.setAccountname(accountname == null ? "" : accountname);
		huiyuan.setXtype("");

		if (mobile != null)
			huiyuan.setMobile(mobile);
		if (address != null)
			huiyuan.setAddress(address);
		if (sex != null)
			huiyuan.setSex(sex);
		else
			huiyuan.setSex("男");

		huiyuan.setNickname(accountname);
		huiyuan.setName(name);
		huiyuan.setRegdate(new Date());
		huiyuan.setIdcardno(idcardno == null ? "" : idcardno);
		huiyuan.setLogtimes(0);
		huiyuan.setPassword("123456");
		if (touxiang != null)
			huiyuan.setTouxiang(touxiang);
		else
			huiyuan.setTouxiang(request.getContextPath() + "/upload/nopic.jpg");
		huiyuan.setEmail(email == null ? "" : email);
		huiyuan.setJibie("初级");
		huiyuan.setZhiye(zhiye);
		huiyuan.setAihao(aihao);
		huiyuan.setStatus(1);
		huiyuan.setYue(0);
		huiyuan.setXtype("普通会员");
		huiyuan.setDes(des == null ? "" : des);
		DALBase.save(huiyuan);
		String forwardurl = request.getParameter("forwardurl");
		if (forwardurl == null) {
			forwardurl = "/admin/huiyuanmanager.do?actiontype=get";
		}
		redirect(forwardurl);

	}

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String id = request.getParameter("id");
		if (id == null)
			return;
		Huiyuan huiyuan = (Huiyuan) DALBase
				.load(Huiyuan.class, new Integer(id));
		if (huiyuan == null)
			return;
		String accountname = request.getParameter("accountname");

		String nickname = request.getParameter("nickname");
		String forwardurl = request.getParameter("forwardurl");

		String touxiang = request.getParameter("touxiang");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String jibie = request.getParameter("jibie");
		String name = request.getParameter("name");
		String zhiye = request.getParameter("zhiye");
		String aihao = request.getParameter("aihao");
		SimpleDateFormat sdfhuiyuan = new SimpleDateFormat("yyyy-MM-dd");
		huiyuan.setAccountname(accountname);
		huiyuan.setNickname(nickname);
		huiyuan.setTouxiang(touxiang);
		huiyuan.setEmail(email);
		huiyuan.setMobile(mobile);
		huiyuan.setSex(sex);
		huiyuan.setAddress(address);
		huiyuan.setJibie(jibie);
		huiyuan.setName(name);
		huiyuan.setZhiye(zhiye);
		huiyuan.setAihao(aihao);

		DALBase.update(huiyuan);
		request.getSession().setAttribute("huiyuan", huiyuan);
		if (forwardurl == null) {
			forwardurl = "/admin/huiyuanmanager.do?actiontype=get";
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
			Huiyuan huiyuan = (Huiyuan) DALBase.load("huiyuan", "where id="
					+ id);
			if (huiyuan != null) {
				request.setAttribute("huiyuan", huiyuan);
			}
			actiontype = "update";
		}
		request.setAttribute("id", id);
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		if(forwardurl==null)
			forwardurl="/admin/huiyuanadd.jsp";
		
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "";
		//
		int pageindex = 1;
		int pagesize = 10;
		// 获取当前分页

		String accountname = request.getParameter("accountname");

		if (accountname != null)
			filter = "  where accountname like '%" + accountname + "%'  ";

		String currentpageindex = request.getParameter("currentpageindex");
		// 当前页面尺寸
		String currentpagesize = request.getParameter("pagesize");
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Huiyuan> listhuiyuan = DALBase.getPageEnity("huiyuan", filter,
				pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("huiyuan",
				filter == null ? "" : filter);
		request.setAttribute("listhuiyuan", listhuiyuan);
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
			forwardurl = "/admin/huiyuanmanager.jsp";
		}
		forward(forwardurl);
	}
}
