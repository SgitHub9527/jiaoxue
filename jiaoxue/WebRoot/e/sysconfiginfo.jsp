<%@page import="com.daowen.bll.*"%>
<%@page import="com.daowen.entity.*,com.daowen.dal.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
  
            String id=request.getParameter("id");
            if(id!=null){
            
                Sysconfig sysconfig=(Sysconfig)DALBase.load("sysconfig","where id="+id);
                
                if(sysconfig!=null)
                   request.setAttribute("sysconfig",sysconfig);
                
            }
  
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>

<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>

    <script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    
            $(function(){
               
            })
    
    
    </script>
    
    
    
   

    

</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	
	
	<div class="fn-clear"></div>
	
	<div class="wrap round-block" >
		
		  <div style="width:280px;float:left;min-height:600px;background:#008c8c;">
		      <div class="simple-category" >
		          <div class="title">
		                                         教学大纲
		          </div>
		          <div class="content">
		              <ul>
		                 <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=1">教学主要内容</a>
		                 </li>     
		                 <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=2">教学目的、要求</a>
		                 </li> 
		                  <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=3">学时分配</a>
		                 </li> 
		                 <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=4">教学环节安排</a>
		                 </li>
		                  <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=5">课程内实验内容及要求</a>
		                 </li>
		                  <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=6">考核和成绩评定方法</a>
		                 </li>
		                  <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=7">教材及参考书</a>
		                 </li>
		                 
		                  <li>
		                    <a href="${pageContext.request.contextPath}/e/sysconfiginfo.jsp?id=8">《内科护理学（1）》课程课内实验教学大纲</a>
		                 </li>
		              </ul>
		          </div>
		          
		      </div>
		  </div>
		   <div style="min-height:600px;width:800px;float:left;" class="info">
                    <h1>
                           ${sysconfig.title}
                    </h1>
                    <h5>
                                                    
                    </h5>
                    <div class="news-content">
                       
                            ${sysconfig.dcontent}  
                        
                       
                    </div>
                    
                        
                </div>
                
               
	
		

	</div>
	
	<div class="fn-clear"></div>


	<jsp:include page="bottom.jsp"></jsp:include>



</body>
</html>