<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="daowen" uri="/daowenpager"%>
<%@ page import="com.daowen.uibuilder.*" %>
<%@ page import="com.daowen.util.*" %>
<%@page import="com.daowen.bll.*,java.util.*"%>
<%@page import="com.daowen.entity.*,com.daowen.dal.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <%
    String  filter="";
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
	List<Zydownload> zydownloadlist = DALBase.getPageEnity("zydownload", filter,
			pageindex, pagesize);
	int recordscount = DALBase.getRecordCount("zydownload", filter == null ? ""
			: filter);
	request.setAttribute("zydownloadlist", zydownloadlist);
	PagerMetal pm = new PagerMetal(recordscount);
	// 设置尺寸
	pm.setPagesize(pagesize);
	// 设置当前显示页
	pm.setCurpageindex(pageindex);
	// 设置分页信息
	request.setAttribute("pagermetal", pm);
  %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
<script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="wrap round-block">
		<div class="line-title">
			当前位置：<a href="${pageContext.request.contextPath}/e/index.jsp">首页</a>
			&gt;&gt; 下载查询
		</div>
		<div style="min-height:600px;" class="common-picture-list">
			<ul>
				<% 
                    SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
                     if(zydownloadlist!=null){
     	              for(Iterator it=zydownloadlist.iterator();it.hasNext();){ 
     	                  Zydownload temzydownload=(Zydownload)it.next();
     	            %>
				<li class="clearfix">
					<h3>
						<a
							href="${pageContext.request.contextPath}/e/zydownloadinfo.jsp?id=<%=temzydownload.getId()%>"><%=temzydownload.getTitle() %></a>
					</h3>
					<div class="picture-area">
						<img src="<%=temzydownload.getTupian()%>" width="150" height="95" />
					</div>
					<div class="text-area">
						
						<div class="list-show-item">
							<em> 发布人:</em><%=temzydownload.getPubren()%></div>
						<div class="list-show-item">
							<em> 发布时间:</em><%=temzydownload.getPubtime()%></div>
						<div class="list-show-item">
							<em> 下载次数:</em><%=temzydownload.getDlcount()%></div>
						<div class="list-show-item">
							<em> 分类:</em><%=temzydownload.getFenlei()%></div>
						<div class="abstract">
							<%=HTMLUtil.subStringInFilter(temzydownload.getDes(), 0, 200) %>
							[<a
								href="${pageContext.request.contextPath}/e/zydownloadinfo.jsp?id=<%=temzydownload.getId()%>">详细</a>]
						</div>
					</div>
				</li>
				<%}} %>
			</ul>
			<form id="zydownloadsearchForm" method="post"
				action="${pageContext.request.contextPath}/e/zydownloadlist.jsp">
				<input type="hidden" name="actiontype" value="get" /> <input
					type="hidden" name="forwardurl" value="/e/zydownloadlist.jsp" />
			</form>
			<daowen:pager id="pager1" attcheform="zydownloadsearchForm" />
		</div>
	</div>
	<div class="fn-clear"></div>
	<jsp:include page="bottom.jsp"></jsp:include>
 </body>
</html>
