
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
  //栏目编码
	 String lanmuId=request.getParameter("lanmuid");
     int nlanmuId=0;
     XinxiService xinxiSrv=new XinxiService();
     LanmuService lanmuSrv=new LanmuService();
	 String parentId=request.getParameter("parentid");
	 List<Lanmu> listLanmu=null;
	 if(lanmuId!=null){
	     nlanmuId=Integer.parseInt(lanmuId);
		 Lanmu lanmu=(Lanmu)DALBase.load("lanmu","where id="+lanmuId);
	     if(lanmu!=null)
	        request.setAttribute("lanmu",lanmu);
	     if(lanmu.getIsleaf()!=1){
	    	 listLanmu=lanmuSrv.getSublanmus(nlanmuId);
	        parentId=lanmuId;
	     }else
	     {
	     	if(parentId!=null)
	     		listLanmu=lanmuSrv.getSublanmus(Integer.parseInt(parentId));
	     }
	    
	 }
	 
	request.setAttribute("listLanmu", listLanmu);
	
    int pageindex = 1;
	int pagesize = 10;
	// 获取当前分页
	String currentpageindex = request.getParameter("currentpageindex");
	// 当前页面尺寸
	String currentpagesize = request.getParameter("pagesize");
	// 设置当前页
	if (currentpageindex != null)
		pageindex = new Integer(currentpageindex);
	// 设置当前页尺寸
	if (currentpagesize != null)
		pagesize = new Integer(currentpagesize);
	List<Xinxi> xinxilist =xinxiSrv.findXinxi(nlanmuId,pageindex, pagesize);
	int recordscount =xinxiSrv.getCount(nlanmuId);
	request.setAttribute("listXinxi", xinxilist);
	PagerMetal pm = new PagerMetal(recordscount);
	// 设置尺寸
	pm.setPagesize(pagesize);
	// 设置当前显示页
	pm.setCurpageindex(pageindex);
	// 设置分页信息
	request.setAttribute("pagermetal", pm);
            
  
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${lanmu.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/index.css" type="text/css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/e/css/box.all.css" type="text/css"></link>
    <script src="${pageContext.request.contextPath}/webui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>

</head>
<body>

		<jsp:include page="head.jsp"></jsp:include>
	
	<div class="fn-clear"></div>
	<div class="wrap round-block" >
	     <div class="filter-box">
            
              <div class="item">
                 <div class="label">${lanmu.title }:</div>
                 <div class="content-list">
                    <ul>
                        
                       <c:forEach var="lanmu" items="${listLanmu}">
                          <li>
                             <a href="${pageContext.request.contextPath}/e/lanmuinfo.jsp?lanmuid=${lanmu.id}&parentid=<%=parentId%>">${lanmu.title}</a>
                          </li>
                       </c:forEach>
                    </ul>
                 </div>
             </div>
             
          
          </div>
		<div class="common-picture-list" style="min-height:500px;">
		  
		     <div class="title">
		          <i class="fa fa-thumbs-up"></i>${lanmu.title}
     	     </div>
     	     
     	         <ul>
     	            <% 
     	              for(Iterator it=xinxilist.iterator();it.hasNext();){ 
     	              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     	                  Xinxi temxinxi=(Xinxi)it.next();
     	            
     	            %>
     	                 
     	            <li>
     	                <h3>
							<a
								href="${pageContext.request.contextPath}/e/xinxiinfo.jsp?id=<%=temxinxi.getId()%>"><%=temxinxi.getTitle() %></a>
						</h3>
     	                <div class="picture-area">
     	                  <img src="<%=temxinxi.getTupian2() %>"/>
     	                </div>
     	                <div class="text-area">
     	                    
     	                   <div class="list-show-item">
							<em> <i class="fa fa-list-alt"></i>:</em> <span><%=sdf.format(temxinxi.getPubtime()) %></span>
						   </div>
						   <div class="list-show-item">
							   <i class="fa fa-user"></i>:<span><%=temxinxi.getPubren() %></span>  
						   </div>
						   <div class="list-show-item">
							   <i class="fa fa-eye"></i>: <span><%=temxinxi.getClickcount() %>次</span> 
						   </div>
     	                  <div class="abstract">
							 <%=HTMLUtil.subStringInFilter(temxinxi.getDcontent(),0, 300) %>                     
							[<a
								href="${pageContext.request.contextPath}/e/xinxiinfo.jsp?id=<%=temxinxi.getId()%>">详细</a>]
						   </div>
     	                 
     	                </div>
     	            </li>
     	            <%} %>
     	         </ul>
     	        
     	     </div>
     	     
     	     
     	     <form id="xinxisearchForm" method="post" action="${pageContext.request.contextPath}/e/lanmuinfo.jsp">
                         <input type="hidden" name="actiontype" value="get" />
                      <input type="hidden" name="lanmuid" value="<%=lanmuId %>" />
                      <%if(parentId!=null){ %>
                      <input type="hidden" name="parentid" value="<%=parentId %>" />
                      <%} %>
                 </form>
		<daowen:pager id="pager1"  attcheform="xinxisearchForm" />
		
		  
		</div>
		
			

	
	<div class="fn-clear"></div>


	<jsp:include page="bottom.jsp"></jsp:include>


</body>
</html>