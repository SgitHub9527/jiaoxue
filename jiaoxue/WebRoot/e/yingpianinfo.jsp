<%@page import="com.daowen.bll.*,java.util.*"%>
<%@ page import="java.text.*"%>
<%@page import="com.daowen.entity.*,com.daowen.dal.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
		  String id=request.getParameter("id");
		  Yingpian  yingpian=null;
		  if(id!=null){
		      yingpian=(Yingpian)DALBase.load("yingpian","where id="+id);
		      yingpian.setClickcount(yingpian.getClickcount()+1);
		      DALBase.update(yingpian);
		      if(yingpian!=null)
		         request.setAttribute("yingpian",yingpian);
		  }
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
 <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/leaveword.css" type="text/css"></link>
<link href="${pageContext.request.contextPath}/webui/video-js/video-js.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/webui/video-js/video.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
 <script type="text/javascript">
         
         videojs.options.flash.swf = "video-js.swf";
         
         $(function(){
        	 
        	 $("#btnShoucang").click(function(){
      	    	
      	    	  
      	    	  var temaccountname= $("[name=scren]").val(); 
      	    	  var temreUrl=$("#reUrl").val();
      	    	  if(temaccountname==""){
                        
      	    		  window.location.href="${pageContext.request.contextPath}/e/login.jsp";
                      return false;
                     
                       
                   }
      	    	  
      	    	   return true;
      	      
      	      
      	      });
        	 
        	 
         });
        
 </script>

</head>
<body>
	<jsp:include page="/e/head.jsp"></jsp:include>
	<div class="wrap round-block">
	  <div class="line-title">
                   当前位置：<a href="index.aspx">首页</a> >> 课程信息
      </div>
	
	<input type="hidden" id="reUrl" name="reurl" value="/e/yingpianinfo.jsp?id=<%=id%>"/>
	<input type="hidden" id="commentresurl" value="/e/yingpianinfo.jsp?id=<%=id%>">
	<div class="fn-clear"></div>
	
		<div class="info">
			<h1>${requestScope.yingpian.title}[${requestScope.yingpian.ypcatename}]</h1>
			<h5>发布时间:<fmt:formatDate value="${requestScope.yingpian.pubtime}" pattern="yyyy-MM-dd hh:mm:ss"/>
			
			 
			</h5>
			<div class="news-content">
				<table cellpadding="0" cellspacing="1" class="grid" width="100%">
						
					
					<tr>
						
						<td >
							<%
								if (id != null) {
							%>
							<div class="uploadify-queue">

								<%
									List<Attachement> yingpianattachementlist = DALBase
												.getEntity(
														"attachement",
														MessageFormat
																.format(" where belongtable=''yingpian'' and belongid=''{0}''",
																		id));
										for (Attachement tema : yingpianattachementlist) {

											String url = tema.getUrl();
								%>

								<video id="example_video_1" class="video-js vjs-default-skin"
									controls preload="none" width="1100" height="400"
									poster="${yingpian.tupian}" data-setup="{}"> <source
									src="<%=tema.getUrl()%>" type='video/mp4' /> <track
									kind="captions" src="demo.captions.vtt" srclang="en"
									label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
								<track kind="subtitles" src="demo.captions.vtt" srclang="en"
									label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
								<p class="vjs-no-js">
									To view this video please enable JavaScript, and consider
									upgrading to a web browser that <a
										href="http://videojs.com/html5-video-support/" target="_blank">supports
										HTML5 video</a>
								</p>
								</video>

								<%
									}
								%>

							</div> 
							
							<%} 
							
							   if(yingpian!=null&&yingpian.getXshowtype()==1){
								   
							   
							
							%>
							   <%=yingpian.getRemoteurl() %>
							<%} %>
						</td>
					</tr>
					<tr>
					   <td>
					         <form method="post" action="${pageContext.request.contextPath}/admin/shoucangmanager.do">
                                         <input type="hidden" name="bookid" value="<%=id%>"/>
                                          <input type="hidden" name="bookname" value="${yingpian.title}"/>
                                          <input type="hidden" name="tupian" value="${yingpian.tupian}"/>
                                         <input type="hidden" name="actiontype" value="save"/>
                                         <input type="hidden" name="scren" value="${huiyuan.accountname}"/>
                                         <input type="hidden" name="forwardurl" value="/e/yingpianinfo.jsp?id=<%=id%>"/>
                                          <input type="hidden" name="errorurl" value="/e/yingpianinfo.jsp?id=<%=id%>"/>
		                     <button  id="btnShoucang" class="btn btn-default"><i class="fa fa-plus"></i>收藏</button>
                          </form>
                           ${sctips}
					   </td>
					</tr>
				</table>
			</div>
		</div>
		<jsp:include page="comment.jsp">
		    <jsp:param value="yingpian" name="commenttype"/>
		</jsp:include>
	</div>
	<div class="fn-clear"></div>
	<jsp:include page="/e/bottom.jsp"></jsp:include>
</body>
</html>
