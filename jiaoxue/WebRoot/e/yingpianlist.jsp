
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="import.jsp" %>
<%
   List<Yingpian> listHotyp=DALBase.getTopList("Yingpian"," where clickcount>0 order by clickcount,id desc ",5);
   if(listHotyp!=null){
	   request.setAttribute("listHotyp", listHotyp);
   }
   String[] alpIndexs=new String[]{"A","B","C","D","E","F","G","H","J","K","L","M","N","O","Q","R","S","T","U","V","W","X","Y","Z"};
   request.setAttribute("alpIndexs", alpIndexs);
   String typeid=request.getParameter("typeid");
   String alp=request.getParameter("alp");
  
   String filter="where 1=1 ";
   if(typeid!=null){
   	 filter+=" and ypcateid="+typeid;
   }
   if(alp!=null){
	   filter+=" and alphabetindex='"+alp+"'";
   }
  
   List<Ypcate> listYpcate=DALBase.getEntity("ypcate", "");
   if(listYpcate!=null)
	   request.setAttribute("listYpcate", listYpcate);
   List<Yingpian> listYingpian=DALBase.getEntity("yingpian", filter);
   if(listYingpian!=null)
	   request.setAttribute("listYingpian", listYingpian);

   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>视频</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
<script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
        $(function () {

        	$("#btnGuanzhu").click(function(){
        		
        	   var hyaccountname=$("[name='hyaccount']").val();
        	   var gzname=$("[name='gzname']").val();
        		
        	   if(hyaccountname==""){
                   
                   window.location.href="login.jsp";
                   return false;
                  
                }
        	    if(!confirm("你确定要"+gzname+"关注吗?")){
        	    	
        	    	return false;
        	    }
        	   
        	   
        	});

        });
    </script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="fn-clear"></div>
	
		<div class="wrap round-block">
		    <div style="float:left;width:210px;">
		   
		     
		     <div class="navbar" style="width:200px;">
             <div class="title">今日热播</div>
              <div class="content">
              <ul>
                 <c:forEach var="yingpian" items="${listHotyp}">
	                <li>
		                 <a href="${pageContext.request.contextPath}/e/yingpianinfo.jsp?id=${yingpian.id}" target="self">
						     <img src="${yingpian.tupian }" width="120" height="120"/>
						 </a>
						<div class="name">
						<p>${yingpian.title }</p>
						<div class="price"><em><b>${yingpian.clickcount}次 </b></em></div>
						</div>
					</li>
				  </c:forEach>
                 </ul> 
              </div>
        
        </div>
        
		 </div>
			<div  style="width:980px;min-height:600px;background:#fff;float:left;" >
			  
			  <div class="filter-box">
	              <div class="item">
	                 <div class="label">按类型:</div>
	                 <div class="content-list">
	                    <ul>
	                       <c:forEach var="ypcate" items="${listYpcate}">
	                          <li>
	                             <a href="${pageContext.request.contextPath}/e/yingpianlist.jsp?typeid=${ypcate.id}">${ypcate.name}</a>
	                          </li>
	                       </c:forEach>
	                    </ul>
	                 </div>
	             </div>
	             
	             <div class="item">
	                 <div class="label">按字母索引:</div>
	                 <div class="content-list">
	                    <ul>
	                       <c:forEach var="alp" items="${alpIndexs}">
	                          <li>
	                             <a href="${pageContext.request.contextPath}/e/yingpianlist.jsp?alp=${alp}">${alp}</a>
	                          </li>
	                       </c:forEach>
	                    </ul>
	                 </div>
	             </div>
	             
	             
	             
	             
             </div>
			  
			   <div class="video-cover-fluid">
			   
			   <c:if test="${!empty listYingpian}">
		        <ul>
		           <c:forEach var="yingpian" items="${listYingpian}">
		              
		                <li>
				               <a href="${pageContext.request.contextPath}/e/yingpianinfo.jsp?id=${yingpian.id}" >
				                  <img src="${yingpian.tupian}" width="158" height="108" />
				                  <div class="vedio-play-button"></div>
				                  <span>${yingpian.title}</span>
				               </a>
		              </li>
		           
		           </c:forEach>
		          
		        </ul>
		        </c:if>
		        <c:if test="${empty listYingpian|| fn:length(listYingpian) == 0}">
		            <div class="no-relative">
		                                     暂无相关视频信息
		            </div>                 
		                              
		           
		        </c:if>
		        
		       
		        
		     </div>
				
			</div>
			
		</div>
	
	<div class="fn-clear"></div>
	<jsp:include page="bottom.jsp"></jsp:include>
 </body>
</html>
