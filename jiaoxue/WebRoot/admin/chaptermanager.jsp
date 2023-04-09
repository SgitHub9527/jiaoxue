<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="law.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="daowen" uri="/daowenpager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>章信息</title>
    <script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/webui/artDialog/skins/default.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/artDialog/jquery.artDialog.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/combo/combo.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/webui/treetable/skin/jquery.treetable.theme.default.css" rel="stylesheet"
        type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/treetable/js/jquery.treetable.js" type="text/javascript"></script>
     <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			$(function() {
			    $("#btnDelete").click(function(){
			        if($(".check:checked").length<1)
			        {
			           top.$.dialog.alert("请选择需要删除的记录");
			           return;
			        } 
			        $(".check:checked").each(function(index,domEle){
			             var id=$(domEle).val();
			             top.$.dialog.confirm("你确定要注销章信息?", function(){
				             window.location.href=encodeURI('${pageContext.request.contextPath}/admin/chaptermanager.do?forwardurl=/admin/chaptermanager.jsp&actiontype=delete&id='+id);
				         });
			        });
			    });
			    $("#btnCheckAll").click(function(){
			           var ischeck=false;
			           $(".check").each(function(){
			               if($(this).is(":checked"))
			               {
			                  $(this).prop("checked","");
			                  ischeck=false;
			                }
			               else
			               {
			                  $(this).prop("checked","true");
			                  ischeck=true;
			                }
			           });
			           if($(this).text()=="选择记录")
			              $(this).text("取消选择");
			           else
			              $(this).text("选择记录");
			    })
			});
       </script>
	</head>
	 <body >
		<div class="search-title">
			<h2>章管理</h2>
			<div class="description">
				
			</div>
		</div>
					<!-- 搜索控件开始 -->
		          <div class="search-options">
					  <form id="searchForm"  action="${pageContext.request.contextPath}/admin/chaptermanager.do" method="post" >
					   <table  cellspacing="0" width="100%">
					        <tbody>
					          <tr>
					             <td>
					                标题
                                            <input name="title"  value="${title}" class="input-txt" type="text" id="title"  />
					                  <input type="hidden"   name="actiontype" value="search"/>
					                  <input type="hidden"   name="seedid" value="${seedid}"/>
					                  <input type="hidden"   name="forwardurl" value="/admin/chaptermanager.jsp"/>
					                   <div class="ui-button">
							       <button class="ui-button-text">
									  搜索
								   </button>
							   </div>
					             </td>
					          </tr>
					        </tbody>
					   </table>
					   </form>
					</div>
					<!-- 搜索控件结束 -->
					<div class="clear">
					</div>
		 <div class="action-details">
					 <a href="#" class="btn btn-success"  id="btnCheckAll" class="action-button">选择</a>
					<a id="btnDelete" class="action-btn"
					href="#"> <em class="icon-delete"></em> <b>删除</b>
			                     </a>
                </div>
	      <table id="chapter" width="100%" border="0" cellspacing="0" cellpadding="0" class="ui-record-table">
					<thead>
		                    <tr >
							    <th >
							       选择
							    </th>
					<th><b>标题</b></th>
													         <th><b>是节</b></th>
													         <th><b>父节点</b></th>
								<th>
								   操作
								</th>
							 </tr>
							 </thead>
							 <tbody>
							 <c:if test="${listchapter== null || fn:length(listchapter) == 0}">
						        <tr>
						          <td colspan="20">
						              没有相关章信息
						          </td>
						        </tr>
						    </c:if>
								<%	
									SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
									if(request.getAttribute("listchapter")!=null)
								      {
									  List<Chapter> listchapter=( List<Chapter>)request.getAttribute("listchapter");
								     for(Chapter  temchapter  :  listchapter)
								      {
							%>	
							<tr>
							    <td>
											 &nbsp<input id="chk<%=temchapter.getId()%>"  class="check"   name="chk<%=temchapter.getId()%>" type="checkbox"  value='<%=temchapter.getId()%>' >
							    </td>
							<td ><%=temchapter.getTitle()%></td>
																     <td ><%=temchapter.getSection()%></td>
																     <td ><%=temchapter.getParentid()%></td>
								<td >
								     <a class="orange-href"   href="${pageContext.request.contextPath}/admin/chaptermanager.do?actiontype=load&id=<%=temchapter.getId()%>&forwardurl=/admin/chapteradd.jsp"><i class="icon-edit icon-white"></i>修改</a>
							<a class="orange-href"   href="${pageContext.request.contextPath}/admin/chapterdetails.jsp?id=<%=temchapter.getId()%>"><i class="icon-zoom-in icon-white"></i>查看</a>
								</td>
							</tr>
							<%}}%>
						</tbody>
						</table>
						<div class="clear"></div>
						<daowen:pager id="pager1"  attcheform="searchForm" />
	</body>
</html>
