<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>
<%@ page import="com.daowen.bll.*"%>
<%@ page import="com.daowen.entity.*" %>
<%@page import="com.daowen.dal.DALBase"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="web" uri="/WEB-INF/webcontrol.tld"%>
<%@ include file="law.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
  <title>测试题</title>
    <link href="${pageContext.request.contextPath}/e/css/index.css" rel="stylesheet" type="text/css" />
     <link href="${pageContext.request.contextPath}/e/css/box.all.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js"></script>
     <link href="${pageContext.request.contextPath}/webui/artDialog/skins/default.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/artDialog/jquery.artDialog.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/jquery.metadata.js" ></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/messages_cn.js" ></script>
       <link href="${pageContext.request.contextPath}/webui/easydropdown/themes/easydropdown.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/easydropdown/jquery.easydropdown.js" type="text/javascript"></script>    
    <link href="${pageContext.request.contextPath}/uploadifyv3.1/uploadify.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/uploadifyv3.1/jquery.uploadify-3.1.js" type="text/javascript"></script>
   
   <script type="text/javascript">
        function initControl(){
        }
        $(function ()
        {
        	
           $("#kmid").change(function(){
		  	      $("[name=kemu]").val($("#kmid option:selected").text());
		   });
       	 $("[name=kemu]").val($("#kmid option:selected").text());
            initControl();
            $.metadata.setType("attr","validate");
            $("#ceshitiForm").validate();
           
            
        });  
    </script>
</head>
<body>
	<jsp:include page="/e/head.jsp"></jsp:include>
	<div class="wrap round-block">
		<div class="line-title">
			当前位置：<a href="index.jsp">首页</a> &gt;&gt; <a href="ceshitimanager.jsp">试题集管理</a>
		</div>
	
		<div class="main">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="main-content">

				<form name="ceshitiform" method="post"
					action="${pageContext.request.contextPath}/admin/ceshitimanager.do"
					id="ceshitiForm">
					<table class="grid" cellspacing="1" width="100%">
						<input type="hidden" name="id" value="${id}" />
						<input type="hidden" name="actiontype" value="${actiontype}" />
						<input type="hidden" name="seedid" value="${seedid}" />
                        <input type="hidden" name="erorurl" value="/e/teacher/ceshitiadd.jsp" />
                        <input type="hidden" name="forwardurl" value="/admin/ceshitimanager.do?actiontype=ge&forwardurl=/e/teacher/ceshitimanager.jsp" />
                        


						<tr>
							<td align="right">题目:</td>
							<td><input name="title"
								validate="{required:true,messages:{required:'请输入题目'}}"
								value="${requestScope.ceshiti.title}" class="input-txt"
								type="text" id="txtTitle" /></td>
						</tr>
						<tr>
							<td align="right">测试分类:</td>
							<td><web:dropdownlist name="kmid" id="kmid"
									cssclass="dropdown" value="${requestScope.panduan.kemu}"
									datasource="${kemu_datasource}" textfieldname="mingcheng"
									valuefieldname="id">
								</web:dropdownlist> <input id="hidkemu" name="kemu" type="hidden"
								vlaue="${requestScope.shijuan.kemu}" /></td>
						</tr>
						<tr>
							<td align="right">选项A:</td>
							<td><input name="choicea"
								validate="{required:true,messages:{required:'请输入选项A'}}"
								value="${requestScope.ceshiti.choicea}" class="input-txt"
								type="text" id="txtChoicea" /></td>
						</tr>
						<tr>
							<td align="right">选项B:</td>
							<td><input name="choiceb"
								validate="{required:true,messages:{required:'请输入选项B'}}"
								value="${requestScope.ceshiti.choiceb}" class="input-txt"
								type="text" id="txtChoiceb" /></td>
						</tr>
						<tr>
							<td align="right">选项C:</td>
							<td><input name="choicec"
								validate="{required:true,messages:{required:'请输入选项C'}}"
								value="${requestScope.ceshiti.choicec}" class="input-txt"
								type="text" id="txtChoicec" /></td>
						</tr>
						<tr>
							<td align="right">选项D:</td>
							<td><input name="choiced"
								validate="{required:true,messages:{required:'请输入选项D'}}"
								value="${requestScope.ceshiti.choiced}" class="input-txt"
								type="text" id="txtChoiced" /></td>
						</tr>
						<tr>
							<td align="right">答案:</td>
							<td><input name="daan"
								validate="{required:true,messages:{required:'请输入答案'}}" value="A"
								checked="checked" type="radio" />A <input name="daan"
								validate="{required:true,messages:{required:'请输入答案'}}" value="B"
								type="radio" />B <input name="daan"
								validate="{required:true,messages:{required:'请输入答案'}}" value="C"
								type="radio" />C <input name="daan"
								validate="{required:true,messages:{required:'请输入答案'}}" value="D"
								type="radio" />D</td>
						</tr>


						<tr>
							<td colspan="2"><input type="submit" value="提交" id="Button1"
								class="orange-button" /></td>
						</tr>

					</table>

				</form>

			</div>

		</div>

	</div>


</body>
</html>
