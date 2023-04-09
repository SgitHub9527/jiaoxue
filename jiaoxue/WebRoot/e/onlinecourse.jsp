
<%@page import="com.daowen.bll.*,java.util.*"%>
<%@page import="com.daowen.entity.*,com.daowen.dal.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
  
          
  
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络课程知识点</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/catalog.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
<script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

  
    

</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="wrap round-block">
	  <div class="line-title">
                       当前位置：<a href="index.jsp">首页</a> &gt;&gt; 网络课程
      </div>
	
	
	    <div class="row-flow" >
	     <div class="column" >

				<div class="book-catalog" style="width:300px;">
					<%
						List<Chapter> listchapter = DALBase.getEntity("chapter",
								" where section=0 ");
						if (listchapter != null) {

							for (Chapter temchapter : listchapter) {
					%>
					<div class="chapter">
						<div class="title">
							<span class="catbanner">&nbsp;<img height="11" width="9"
								src="${pageContext.request.contextPath}/e/images/line.gif"> </span>
							<%=temchapter.getTitle()%>
							
						</div>
						<div class="section">

							<%
								List<Chapter> listsection = DALBase.getEntity("chapter",
												" where parentid=" + temchapter.getId());
							%>
							<ul>
								<%
									for (Chapter temsection : listsection) {
								%>
								<li><a
									href="${pageContext.request.contextPath}/e/onlinecourse.jsp?id=<%=temsection.getId()%>">-<%=temsection.getTitle()%>
								</a>
								</li>

								<%
									}
								%>

							</ul>

						</div>
					</div>
					<%}} %>

				</div>


			</div>
		 <div  class="column" style="width:815px;">
		  
		       <div class="knowledge-content" style="min-height:600px;">
		       
		                       <%
		                          String id=request.getParameter("id");
		                          if(id!=null)
		                          {
		                        	  Chapter  sectioninfo=(Chapter)DALBase.load("chapter","where id="+id);
		                        	  if(sectioninfo!=null){
		                        		  request.setAttribute("sectioninfo", sectioninfo);
		                        	  }
		                          }else
		                          {
		                        	  Chapter  sectioninfo=(Chapter)DALBase.load("chapter","where section=1");
		                        	  if(sectioninfo!=null){
		                        		  request.setAttribute("sectioninfo", sectioninfo);
		                        	  }  
		                          }
		                       %>
		           ${sectioninfo.dcontent }
		       </div>
		  
		   </div>
		</div>
		

	</div>
	
	<div class="fn-clear"></div>


	<jsp:include page="bottom.jsp"></jsp:include>



</body>
</html>