<%@ page import="com.daowen.entity.*" %>
<%@ page import="com.daowen.bll.*" %>
<%@ page import="com.daowen.util.*" %>
<%@ page import="com.daowen.uibuilder.*" %>
<%@ page import="com.daowen.dal.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%
      Object o=  request.getSession().getAttribute("users");
      if(o==null)
          response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
%>
