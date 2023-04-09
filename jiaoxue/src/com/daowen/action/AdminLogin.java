package com.daowen.action;

import javax.servlet.http.HttpSession;

import com.daowen.dal.DALBase;
import com.daowen.entity.Users;

public class AdminLogin extends PageActionBase {

	

	private void login() {

		String usertype = request.getParameter("usertype");

		String validcode = (String) request.getSession().getAttribute(
				"validcode");

		String inputvalidcode = request.getParameter("validcode");

		if (validcode != null && !validcode.equals(inputvalidcode)) {

			System.out.println("系统验证错误");
			request.setAttribute("errmsg",
					"<img src=\"images/error.gif\"/>系统验证码错误");

			// 分发请求参数
			dispatchParams(request, response);
			forward("/admin/login.jsp");
			return;

		}

		System.out.println("验证码=" + validcode);

		if (usertype != null && usertype.equals("0")) {
			adminLogin();

		}

		

	}

	private void adminLogin() {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Users u = (Users) DALBase.load("users", " where username='" + username
				+ "' and password='" + password + "'");

		if (u != null) {

			HttpSession session = request.getSession();
			u.setLogtimes(u.getLogtimes() + 1);
			DALBase.update(u);
			session.setAttribute("users", u);

			redirect("/admin/index.jsp");
			
		} else {

			// 分发
			dispatchParams(request, response);
			request.setAttribute("errmsg",
					"<img src=\"images/error.gif\"/>用户与密码不匹配");

			System.out.println("系统用户登录失败");
			forward("/admin/login.jsp");

		}

	}


	

}
