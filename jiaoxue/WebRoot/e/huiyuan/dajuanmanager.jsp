﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.daowen.bll.*,com.daowen.util.PagerMetal"%>
<%@ page import="com.daowen.entity.*" %>
<%@page import="com.daowen.dal.DALBase"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="law.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="daowen" uri="/daowenpager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>答卷信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
    <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js"></script>
     <link href="${pageContext.request.contextPath}/webui/artDialog/skins/default.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/artDialog/jquery.artDialog.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.theme.default.css" rel="stylesheet"
        type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/treetable/js/jquery.treetable.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
			    $("#btnDelete").click(function(){
			        if($(".check:checked").length<1)
			        {
			           $.dialog.alert("请选择需要删除的记录");
			           return;
			        } 
			        $(".check:checked").each(function(index,domEle){
			             var id=$(domEle).val();
			             $.dialog.confirm("你确定要注销答卷信息?", function(){
				             window.location.href=encodeURI('${pageContext.request.contextPath}/admin/dajuanmanager.do?&forwardurl=/e/huiyuan/dajuanmanager.jsp&actiontype=delete&id='+id);
				          });
			        });
			    });
			    $("#btnCheckAll").click(function(){
			           var ischeck=false;
			           $(".check").each(function(){
			               if($(this).is(":checked"))
			               {
			                  $(this).prop("checked","");
			                  ischeck=false;
			                }
			               else
			               {
			                  $(this).prop("checked","true");
			                  ischeck=true;
			                }
			           });
			           if($(this).text()=="选择记录")
			              $(this).text("取消选择");
			           else
			              $(this).text("选择记录");
			    })
			});
       </script>
	</head>
	  <body >
	<jsp:include page="head.jsp"></jsp:include>
	<div class="wrap round-block">
		<div class="line-title">
			当前位置：<a href="index.jsp">首页</a> &gt;&gt; 测试结果
		</div>

		<div class="main">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="main-content">
				<div class="search-title">
					<h2>我的测试结果</h2>
					<div class="description"></div>
				</div>
				<!-- 搜索控件开始 -->
				<div class="search-options">
					<form id="searchForm"
						action="${pageContext.request.contextPath}/admin/dajuanmanager.do"
						method="post">
						<table cellspacing="1" width="100%">
							<tbody>
								<tr>
									<td>试卷名 <input name="title" value="${title}"
										class="input-txt" type="text" id="title" /> <input
										type="hidden" name="actiontype" value="search" /> <input
										type="hidden" name="dajuanren" value="${huiyuan.accountname}" /> <input
										type="hidden" name="forwardurl"
										value="/e/huiyuan/dajuanmanager.jsp" />
										<div class="ui-button">
											<input type="submit" value="搜索" id="btnSearch"
												class="ui-button-text" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<!-- 搜索控件结束 -->
				<div class="clear"></div>
				<div class="action-details">
					<span id="btnCheckAll" class="orange-href">选择</span> <span
						id="btnDelete" class="orange-href">删除</span>

				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="ui-record-table">
					<thead>
						<tr>
							<th>选择</th>
							<th><b>试卷名</b></th>
							
							<th><b>科目</b></th>
							<th><b>答卷人</b></th>
							<th><b>障碍指数</b></th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${listdajuan== null || fn:length(listdajuan) == 0}">
							<tr>
								<td colspan="20">没有相关答卷信息</td>
							</tr>
						</c:if>
						<%	
									if(request.getAttribute("listdajuan")!=null)
								      {
									  List<Dajuan> listdajuan=( List<Dajuan>)request.getAttribute("listdajuan");
								     for(Dajuan  temdajuan  :  listdajuan)
								      {
							%>
						<tr>
							<td>&nbsp<input id="chk<%=temdajuan.getId()%>" class="check"
								name="chk<%=temdajuan.getId()%>" type="checkbox"
								value='<%=temdajuan.getId()%>'></td>
							<td><%=temdajuan.getTitle()%></td>
						
							<td><%=temdajuan.getKemu()%></td>
							<td><%=temdajuan.getDajuanren()%></td>
							<td><%=temdajuan.getDefen()%></td>
							<td><a class="orange-href"
								href="${pageContext.request.contextPath}/e/huiyuan/dajuaninfo.jsp?djid=<%=temdajuan.getId()%>">查看</a>
							</td>
						</tr>
						<%}}%>
					</tbody>
				</table>
				<div class="clear"></div>
				<daowen:pager id="pager1" attcheform="searchForm" />
			</div>
		</div>
	</div>
	<jsp:include page="/e/bottom.jsp"></jsp:include>
</body>
</html>
