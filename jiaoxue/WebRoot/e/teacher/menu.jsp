
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script  type="text/javascript">
     
   $(function(){

      $("#side-menu .menu-group li").removeClass("current");
     
      var seedid='<%=request.getParameter("seedid")%>';
      
      if(seedid!="null")
         $("#"+seedid).addClass("current");
      else
        $("#m1").addClass("current");
      
   })

</script>

<div id="side-menu">

  
  
  
    <div class="menu-group">
		<h2>
		    <i class="fa fa-cog"></i>题库管理
		</h2>
		<ul>
		    
		      <li id="206" class="current">
				<a  href="${pageContext.request.contextPath}/admin/ceshitimanager.do?actiontype=get&tno=${teacher.tno}&forwardurl=/e/teacher/ceshitimanager.jsp&seedid=206">题库管理</a>
			</li>
			
			  
		      <li id="207" >
				<a  href="${pageContext.request.contextPath}/admin/ceshitimanager.do?actiontype=load&tno=${teacher.tno}&forwardurl=/e/teacher/ceshitiadd.jsp&seedid=207">添加题库</a>
			</li>
		    
		     <li id="208" >
				<a  href="${pageContext.request.contextPath}/admin/shijuanmanager.do?actiontype=get&zujuanren=${teacher.tno}&forwardurl=/e/teacher/shijuanmanager.jsp&seedid=208">我的试卷</a>
			</li>
			 <li id="209" >
				<a  href="${pageContext.request.contextPath}/admin/shijuanmanager.do?actiontype=load&tno=${teacher.tno}&forwardurl=/e/teacher/shijuanadd.jsp&seedid=209">试卷入库</a>
			</li>
			
		    
		     
		     
			
			
		</ul>
	</div>
	
	 <div class="menu-group">
		<h2>
		    <i class="fa fa-cog"></i>资源管理
		</h2>
		<ul>
		    
		     
		    <li id="301" class="current">
				<a  href="${pageContext.request.contextPath}/admin/yingpianmanager.do?actiontype=load&tno=${teacher.tno}&forwardurl=/e/teacher/yingpianadd.jsp&seedid=301">发布视频</a>
			</li>
			
			 <li id="302" >
				<a  href="${pageContext.request.contextPath}/admin/yingpianmanager.do?actiontype=get&pubren=${teacher.tno}&forwardurl=/e/teacher/yingpianmanager.jsp&seedid=302">视频管理</a>
			</li>
			
		     
		     <li id="304"><a href="${pageContext.request.contextPath}/admin/zydownloadmanager.do?actiontype=get&pubren=${teacher.tno}&forwardurl=/e/teacher/zydownloadmanager.jsp&seedid=304">
					下载资源管理 </a>
		     </li>
		      <li id="305"><a href="${pageContext.request.contextPath}/admin/zydownloadmanager.do?actiontype=load&pubren=${teacher.tno}&forwardurl=/e/teacher/zydownloadadd.jsp&seedid=305">
					发布下载资源 </a>
		     </li>
		       <li id="306"><a href="${pageContext.request.contextPath}/admin/leavewordmanager.do?actiontype=get&replyren=${teacher.tno}&forwardurl=/e/teacher/leavewordmanager.jsp&seedid=306">
					课程回复 </a>
		     </li>
		     
			
			
		</ul>
	</div>
    
	<div class="menu-group">
		<h2>
			账户信息
		</h2>
		<ul>
			<li id="101" class="current">
				<a  href="${pageContext.request.contextPath}/e/teacher/accountinfo.jsp?seedid=101">账户信息</a>
			</li>
			<li id="102">
				<a href="${pageContext.request.contextPath}/e/teacher/modifypw.jsp?seedid=102" target="_self">密码修改</a>
			</li>
			<li id="103">
				<a href="${pageContext.request.contextPath}/e/teacher/modifyinfo.jsp?seedid=103" target="_self">信息修改</a>
			</li>
       

		</ul>
	</div>




</div>