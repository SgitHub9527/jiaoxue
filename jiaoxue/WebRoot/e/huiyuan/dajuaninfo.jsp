﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>
<%@ page import="com.daowen.bll.*"%>
<%@ page import="com.daowen.entity.*" %>
<%@page import="com.daowen.dal.DALBase"%>
<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="law.jsp"%>
<%
    String  djid=request.getParameter("djid");
    Dajuan temobjdajuan=null;
    
    if( djid!=null)
    {
     
    	temobjdajuan=(Dajuan)DALBase.load("dajuan"," where id="+ djid);
       request.setAttribute("dajuan",temobjdajuan);
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <title>答卷信息查看</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
    <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
     <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.9.0.js"></script>
     
    <script  type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/jquery.metadata.js" ></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/messages_cn.js" ></script>
   <script type="text/javascript">
   
   
     
        $(function ()
        {
        	
        	  $.metadata.setType("attr", "validate");
              
              $("#form1").validate();
              
            
        });  
    </script>
</head>
<body >
	 <jsp:include page="head.jsp"></jsp:include>
		  <div class="wrap round-block">
			<div class="line-title">
				  当前位置：<a href="${pageContext.request.contextPath}/e/index.jsp">首页</a>>> 测试结果
			</div>
    	
		   <div class="main">
		     <jsp:include  page="menu.jsp"></jsp:include>
	  <div  class="main-content">	
	          <div class="search-title">
	                <h2>
	                      ${requestScope.dajuan.title}  得分:(${dajuan.defen}分)  
	                </h2>
                <div class="description">
                                                        
	               
                </div>
              </div>
            
				<table border="0" cellspacing="1" class="grid" cellpadding="0"
					width="100%">

				      <tr>
				         <td>一.单选题</td>
				      </tr>
					   <%
                            
                          int i=1;
					     
                          List<Dajuanitem> dajuanitemlist=(List<Dajuanitem>)DALBase.getEntity("dajuanitem", MessageFormat.format("where shijuanid={0} and tixing=''单选题'' and dajuanren=''{1}'' and dajuanid={2}",temobjdajuan.getShijuanid(),temobjdajuan.getDajuanren(),djid));
                          for(Iterator<Dajuanitem> it=dajuanitemlist.iterator();it.hasNext();i++)
                           {
                              
                               Dajuanitem dji=it.next();
                               Ceshiti temdanxuan=(Ceshiti) DALBase.load("ceshiti","where id="+dji.getTihao());
                               if(temdanxuan!=null){
                        %>
                        
                        
                           <tr>
                        
                          <td>
                            
                            <%=i %> 
                             <%=temdanxuan.getTitle() %>
                          </td>
                       </tr>
                       
                        <tr>
                            <td >
                               
                                 A:
                                 <%=temdanxuan.getChoicea() %>
                                
                                
                                 
                                 
                                   
                                
                               
                            </td>
                       </tr>
                        <tr>
                            <td >
                               
                                
                                 B: <input name="<%=temdanxuan.getId() %>" type="radio" value="B"/><%=temdanxuan.getChoiceb() %>
                                
                                
                               
                            </td>
                       </tr>
                        <tr>
                            <td >
                               
                               
                                 C: <input name="<%=temdanxuan.getId() %>"  type="radio" value="C"/><%=temdanxuan.getChoicec() %>
                                 
                                
                               
                            </td>
                       </tr>
                        <tr>
                            <td >
                               
                                
                                 D: <input name="<%=temdanxuan.getId() %>" type="radio" value="D"/><%=temdanxuan.getChoiced() %>
                                 
                                
                               
                            </td>
                       </tr>
                     
                          <tr>
                            <td >
                               
                                                                                            填写答案:<%=dji.getDaan()%>
                                   所得分:<%=dji.getDefen()%>
                                
                               
                            </td>
                       </tr>
                      
                       
                       <%}//end if %>
                       <%}//end for %>
                       
                  
					
				</table>
			
			</div>
	   </div>
</body>
</html>
