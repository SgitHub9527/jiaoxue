<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="law.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="daowen" uri="/daowenpager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>章信息</title>
    <script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/webui/artDialog/skins/default.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/artDialog/jquery.artDialog.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/combo/combo.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.theme.default.css" rel="stylesheet"
        type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/treetable/js/jquery.treetable.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/e/css/catalog.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(function() {
			    $(".delete-button").click(function(){
			        var id=$(this).attr("nodeid");
			       
			             top.$.dialog.confirm("你确定要注销章信息?", function(){
				             window.location.href=encodeURI('${pageContext.request.contextPath}/admin/chaptermanager.do?forwardurl=/admin/bookinfo.jsp&actiontype=delete&id='+id);
				         });
			       
			    });
			    
			});
       </script>
	</head>
	 <body >
	<div class="search-title">
		<h2>课程知识点管理</h2>
		<div class="description">
		
		    <a class="orange-href" href="${pageContext.request.contextPath}/admin/chaptermanager.do?actiontype=load&forwardurl=/admin/chapteradd.jsp">添加章节</a>
		</div>
	</div>

    <%	
									List<Chapter> listchapter=DALBase.getEntity("chapter", " where section=0 ");
									if(listchapter!=null)
								      {
									 
								     for(Chapter  temchapter  :  listchapter)
								      {
							%>
	<div class="chapter">
		<div class="title">
			<span class="catbanner">&nbsp;<img height="11" width="9"
				src="${pageContext.request.contextPath}/e/images/line.gif">
			</span>
		    <a  href="${pageContext.request.contextPath}/admin/chaptermanager.do?actiontype=load&id=<%=temchapter.getId()%>&forwardurl=/admin/chapteradd.jsp">	<%=temchapter.getTitle() %></a>
            <a class="orange-href" href="${pageContext.request.contextPath}/admin/chaptermanager.do?actiontype=load&parentid=<%=temchapter.getId()%>&forwardurl=/admin/sectionadd.jsp">添加知识点</a> <span class="delete-button orange-href" nodeid="<%=temchapter.getId() %>">删除</span>
		</div>
		<div class="section">

           <%
               List<Chapter> listsection=DALBase.getEntity("chapter", " where parentid="+temchapter.getId());
           %>
			<ul>
               <% for(Chapter  temsection  :  listsection)
					{
			  %>
				<li><a    href="${pageContext.request.contextPath}/admin/chaptermanager.do?actiontype=load&parentid=<%=temchapter.getId() %>&id=<%=temsection.getId() %>&forwardurl=/admin/sectionadd.jsp">-<%=temsection.getTitle() %> </a>
				    <span class="delete-button orange-href" nodeid="<%=temsection.getId() %>">删除</span>
				</li>

			  <%} %>

			</ul>

		</div>
	</div>
   <%}} %>
	<div class="clear"></div>


</body>
</html>
