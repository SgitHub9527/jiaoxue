<%@page import="com.daowen.bll.FriendlinkBLL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="fn-clear"></div>
		<div class="frendlink wrap">
			<strong>友情链接：</strong>
			
			  <%=FriendlinkBLL.getFriendLink() %>

		</div>


	<div class="fn-clear"></div>
	<div>
		<div id="footer_bg">
			<div id="footer">教学平台</div>
		</div>
	</div>
	
	
