<%@page import="com.daowen.bll.*"%>
<%@page import="com.daowen.entity.*,com.daowen.dal.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="law.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/web2table.css" type="text/css"></link>
 
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/register.css" type="text/css"></link>

<script
	src="${pageContext.request.contextPath}/webui/jquery/jquery-1.5.2.min.js"
	type="text/javascript"></script>





</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
    <div class="fn-clear"></div>
	<div class="wrap">
		<div class="cover-title">
			当前位置：<a href="index.jsp">首页</a> &gt;&gt; <a href="myindex.jsp">会员中心</a>
		</div>
	</div>


	<div class="fn-clear"></div>

		<div class="wrap">
		
		   <div class="main">
		   
		       <jsp:include  page="menu.jsp"></jsp:include>
		       <div class="main-content">
					
					
					<div class="search-title">
					<h2>账户余额:${huiyuan.yue}￥
					   <span class="ui-button"><a href="${pageContext.request.contextPath}/e/huiyuan/chongzhi.jsp" class="ui-button-text">我要充值</a></span>
					</h2>
					<div class="description"></div>
				     </div>
			
					
		   </div>
			
		</div>
		


	<div class="fn-clear"></div>


	<jsp:include page="bottom.jsp"></jsp:include>



</body>
</html>