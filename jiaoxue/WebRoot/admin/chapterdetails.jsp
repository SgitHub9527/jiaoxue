<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="law.jsp"%>
<%
    String  id=request.getParameter("id");
    if( id!=null)
    {
      Chapter temobjchapter=(Chapter)DALBase.load("chapter"," where id="+ id);
      request.setAttribute("chapter",temobjchapter);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
  <title>章信息查看</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js"></script>
     <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
</head>
<body >
	 	 <div class="search-title">
<h2>
	                      查看章
	                </h2>
	                <div class="description">
	                </div>
              </div>
				        <table cellpadding="0" cellspacing="1" class="grid" width="100%" >
											   <tr>
											   <td width="10%" align="right" >标题:</td>
											   <td>
												   ${requestScope.chapter.title}
												</td>
											   </tr>
											   <tr>
											   <td width="10%" align="right" >是节:</td>
											   <td>
												   ${requestScope.chapter.section}
												</td>
											   </tr>
											   <tr>
											   <td width="10%" align="right" >父节点:</td>
											   <td>
												   ${requestScope.chapter.parentid}
												</td>
											   </tr>
											 <tr>
											   <td align="right">备注:</td>
											   <td colspan="3">
												${requestScope.chapter.dcontent}
											   </td>
											 </tr>
				        </table>
</body>
</html>
