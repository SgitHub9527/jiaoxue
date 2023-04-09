<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="law.jsp"%>
<%
    String  id=request.getParameter("id");
    String command="add";
    if( id!=null)
    {
      command="update";
      Zydownload temobjzydownload=(Zydownload)DALBase.load("zydownload"," where id="+ id);
      request.setAttribute("zydownload",temobjzydownload);
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
  <title>下载资源信息查看</title>
    <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/admin/css/layout.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/admin/css/menu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.9.0.js"></script>
</head>
<body >
	   
             <div class="search-title">
	                <h2>
	                       下载资源管理-> 查看下载资源
	                </h2>
                <div class="description">
                </div>
              </div>
				        <table cellpadding="0" cellspacing="1" class="grid" width="100%" >
											   <tr>
											   <td align="right" >标题:</td>
											   <td>
												   ${requestScope.zydownload.title}
												</td>
											   </tr>
											   <tr>
											   <td align="right" >发布人:</td>
											   <td>
												   ${requestScope.zydownload.pubren}
												</td>
											   </tr>
											   <tr>
											   <td align="right">提交时间:</td>
											   <td>
												   ${requestScope.zydownload.pubtime}
												</td>
											   </tr>
											   <tr>
											    <td align="right" >资源分类:</td>
											   <td>
												   ${requestScope.zydownload.fenlei}
												</td>
											   </tr>
											   <tr>
											   <td align="right" >下载次数:</td>
											   <td>
												   ${requestScope.zydownload.dlcount}
												</td>
											   </tr>
											   <tr>
											    <td align="right">图片:</td>
											   <td>
												   <img id="imgTupian" width="200px" height="200px" src="${requestScope.zydownload.tupian}"/>
												</td>
											   </tr>
											 <tr>
											   <td align="right">描述:</td>
											   <td colspan="3">
												${requestScope.zydownload.des}
											   </td>
											 </tr>
						        <tr>
						           <td align="right">
						              附件
						           </td>
						           <td colspan="3">
									   <%
									    if( id!=null)
									    {
									    %>
									    <div class="uploadify-queue">
									        <ul>
									           <%
									                List<Attachement> zydownloadattachementlist=DALBase.getEntity("attachement",MessageFormat.format(" where belongtable=''zydownload'' and belongid=''{0}''",id));
									                for(Attachement tema : zydownloadattachementlist)
									                {
									            %>
									                 <li ><input type='hidden' name='fileuploaded' value='<%=tema.getFilename() %>'  > <%=tema.getFilename() %></li>
									            <%
									            } %>
									        </ul>
									    </div>
									   <%} %>
						           </td>
						        </tr>
				        </table>

</body>
</html>
