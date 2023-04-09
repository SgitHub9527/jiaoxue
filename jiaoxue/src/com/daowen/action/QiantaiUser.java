package com.daowen.action;

import java.text.MessageFormat;

import com.daowen.dal.DALBase;
import com.daowen.entity.Huiyuan;
import com.daowen.entity.Teacher;

public class QiantaiUser extends PageActionBase {

	
	private void exit() {

		String usertype=request.getParameter("usertype");
		if (usertype!= null&&usertype.equals("0")) {

			System.out.println("系统退出");
			request.getSession().removeAttribute("huiyuan");

		}
		if (usertype!= null&&usertype.equals("1")) {

			System.out.println("教师退出");
			request.getSession().removeAttribute("teacher");

		}
		

	}
	private  void login(){
		
		String usertype = request.getParameter("usertype");

		if (usertype != null && usertype.equals("0")) {
			huiyuanLogin();
		}
		
		if (usertype != null && usertype.equals("1")) {
			teacherLogin();
		}
		
	}

	private void huiyuanLogin() {

		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");

		String filter = MessageFormat.format(
				"where accountname=''{0}'' and password=''{1}''", accountname,
				password);

		Huiyuan huiyuan = (Huiyuan) DALBase.load("huiyuan", filter);
		String errorurl=request.getParameter("errorurl");

		if (huiyuan != null && huiyuan.getPassword().equals(password)) {
				huiyuan.setLogtimes(huiyuan.getLogtimes() + 1);
				DALBase.update(huiyuan);
				request.getSession().setAttribute("huiyuan", huiyuan);
			    redirect("/e/huiyuan/accountinfo.jsp");
			
		} else {

			dispatchParams(request, response);
			request.setAttribute("errormsg", "<label class='error'>会员账户和密码不匹配</label>");
			forward(errorurl);

		}

	}
	
	
	private void teacherLogin() {

		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");

		String filter = MessageFormat.format("where tno=''{0}'' and password=''{1}''", accountname,
						password);

		Teacher teacher = (Teacher) DALBase.load("teacher", filter);
		String errorurl = request.getParameter("errorurl");

		if (teacher != null && teacher.getPassword().equals(password)) {

			
				teacher.setLogintimes(teacher.getLogintimes() + 1);
				DALBase.update(teacher);
				request.getSession().setAttribute("teacher", teacher);
				redirect("/e/teacher/accountinfo.jsp");

		} else {

			dispatchParams(request, response);
			request.setAttribute("message", "系统账户和密码不匹配");
			forward(errorurl);

		}

	}


}
