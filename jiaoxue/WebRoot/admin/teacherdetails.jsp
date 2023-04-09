<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="law.jsp"%>
<%
	String id = request.getParameter("id");
	
	if (id != null) {
		
		Teacher temobjteacher = (Teacher) DALBase.load("teacher",
				" where id=" + id);
		request.setAttribute("teacher", temobjteacher);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>教师信息查看</title>

<link href="${pageContext.request.contextPath}/admin/css/web2table.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webui/jquery/jquery-1.9.0.js"></script>
</head>
<body>

	<div class="search-title">
		<h2>教师管理-> 查看教师</h2>
		<div class="description"></div>
	</div>
	<table cellpadding="0" cellspacing="1" class="grid" width="100%">
		<tr>
			<td align="right" width="10%" class="title">教工号:</td>
			<td width="20%">${requestScope.teacher.tno}</td>
			<td colspan="2" rowspan="5"><img id="imgXiangpian" width="200px"
				height="200px" src="${requestScope.teacher.xiangpian}" />
			</td>
		</tr>
		<tr>
			<td align="right" class="title">教师姓名:</td>
			<td>${requestScope.teacher.name}</td>
		</tr>
		<tr>
			<td align="right" class="title">性别:</td>
			<td>${requestScope.teacher.sex}</td>
		</tr>

		<tr>
			<td align="right" class="title">登录次数:</td>
			<td>${requestScope.teacher.logintimes}</td>
		</tr>

		<tr>
			<td align="right" class="title">职称:</td>
			<td>${requestScope.teacher.zhicheng}</td>
		</tr>
		<tr>
			<td align="right" class="title">学历:</td>
			<td>${requestScope.teacher.xueli}</td>


			<td align="right" class="title">邮箱:</td>
			<td>${requestScope.teacher.email}</td>
		</tr>
		<tr>
			<td align="right" class="title">联系电话:</td>
			<td colspan="3">${requestScope.teacher.mobile}</td>

			
		</tr>
		<tr>
			<td align="right" class="title">履历:</td>
			<td colspan="3">${requestScope.teacher.lvli}</td>
		</tr>
	</table>

</body>
</html>
