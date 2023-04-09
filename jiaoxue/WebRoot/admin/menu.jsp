<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">

    $(function () {

        $("#divAccordion dl dt").click(function () {



            $("#divAccordion dl").removeClass("current").addClass("bitem2");
            $("#divAccordion dl dd").hide();

            $(this).siblings("dd").show();
            $(this).parent().addClass("current");


        });

        $(".sitemu li").click(function () {

            $(".sitemu li").removeClass("selected");
            $(this).addClass("selected");

        });

        var menuid = '<%=request.getParameter("menuid")%>';

		if (menuid != "") {
			$("#" + menuid).addClass("selected");
			$("#divAccordion dl dd").hide();
			$("#" + menuid).closest("dd").show().parent().addClass("current");
		}

	})
</script>
<div class="left">
	<div style="width: 145px; height: 480px; padding: 0; margin: 0"
		id="divAccordion">
		<div class="bgtop">
			<b>系统菜单</b>
		</div>



		<dl class="bitem2">
			<dt>
				<i class="fa fa-user"></i> <b>在线学习</b>
			</dt>
			<dd class="current">
				<ul class="sitemu">


					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/bookinfo.jsp">课程章节信息</a>
					</li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/zydownloadmanager.do?actiontype=get">
							<i class="fa fa-navicon"></i>下载资料管理
					</a></li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/zydownloadmanager.do?actiontype=load">
							<i class="fa fa-navicon"></i>发布下载资料
					</a></li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/yingpianmanager.do?actiontype=get"><i
							class="fa fa-navicon"></i>学习视频管理</a></li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/yingpianmanager.do?actiontype=load"><i
							class="fa fa-plus"></i>发布视频</a></li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/ypcatemanager.do?actiontype=get"><i
							class="fa fa-navicon"></i>讲座分类管理</a></li>


				</ul>
			</dd>
		</dl>
		
		
		<dl class="bitem2">
			<dt>
				<i class="fa fa-user"></i> <b>题库试卷</b>
			</dt>
			<dd class="current">
				<ul class="sitemu">


				 <li>
					<a target="main" href="${pageContext.request.contextPath}/admin/ceshitimanager.do?actiontype=get">
					<i class="fa fa-navicon"></i>
					测试题库管理</a>
				</li>
	             <li>
					<a target="main" href="${pageContext.request.contextPath}/admin/kechengmanager.do?actiontype=get">
					<i class="fa fa-plus"></i>
					科目分类</a>
				</li>
				 <li>
					<a target="main" href="${pageContext.request.contextPath}/admin/shijuanmanager.do?actiontype=get">
					<i class="fa fa-navicon"></i>
					测试卷管理</a>
				</li>
				
				<li>
					<a target="main" href="${pageContext.request.contextPath}/admin/shijuanmanager.do?actiontype=load">
					<i class="fa fa-plus"></i>
					新建测试卷</a>
				</li>

				</ul>
			</dd>
		</dl>
		
		<dl class="bitem2">
			<dt>
				<i class="fa fa-cog"></i> <b>平台信息 </b>
			</dt>
			<dd class="current">
				<ul class="sitemu">

					
					<li >
				<a target="main" href="${pageContext.request.contextPath}/admin/xinximanager.do?actiontype=get">新闻资讯管理</a>
			</li>
			<li>
				<a   target="main" href="${pageContext.request.contextPath}/admin/xinximanager.do?actiontype=load">发布新闻资讯</a>
			</li>
			 <li>
				<a target="main" href="${pageContext.request.contextPath}/admin/lanmumanager.do?actiontype=get">系统栏目管理</a>
			</li>
			
				<li>
				<a target="main" href="${pageContext.request.contextPath}/admin/friendlinkmanager.do?actiontype=get&seedid=306">友情链接管理</a>
			</li>
			
			
		    <li>
				<a target="main" href="${pageContext.request.contextPath}/admin/commentmanager.do?actiontype=get">会员评论管理</a>
			</li>
			

			  </ul>
				
			</dd>
		</dl>

		<dl class="bitem2">
			<dt>
				<i class="fa fa-cog"></i> <b>平台设置 </b>
			</dt>
			<dd class="current">
				<ul class="sitemu">

					
					<li >
				<a target="main" href="${pageContext.request.contextPath}/admin/sitenavmanager.do?actiontype=get&seedid=303">首页导航管理</a>
			</li>
			<li>
				<a   target="main" href="${pageContext.request.contextPath}/admin/indexcolumnsmanager.do?actiontype=get">首页栏目管理</a>
			</li>
			 <li>
				<a target="main" href="${pageContext.request.contextPath}/admin/noticemanager.do?actiontype=get&seedid=304">系统公告管理</a>
			</li>
			
			 <li>
				<a target="main" href="${pageContext.request.contextPath}/admin/jiaodiantumanager.do?actiontype=get&seedid=305">首页轮播图管理</a>
			</li>
			 <li>
				<a target="main" href="${pageContext.request.contextPath}/admin/sysconfigmanager.do?actiontype=get">系统介绍设置</a>
			</li>
			
		
			

			  </ul>
				
			</dd>
		</dl>
		<dl class="bitem2">
			<dt>
				<i class="fa fa-info"></i>  <b>系统用户</b>
			</dt>
			<dd class="current">
				<ul class="sitemu">

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/huiyuanmanager.do?actiontype=get">
							学生管理 </a>
					</li>
					
					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/teachermanager.do?actiontype=get">
							教师管理 </a>
					</li>

					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/usersmanager.do?actiontype=get&forwardurl=/admin/usersmanager.jsp">
							后台用户管理 </a>
					</li>


				</ul>
			</dd>
		</dl>
		
		
		<dl class="bitem2">
			<dt>
				<i class="fa fa-user"></i>  <b>账户管理</b>
			</dt>
			<dd class="current">
				<ul class="sitemu">
					
					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/modifypw.jsp">修改密码</a>
					</li>
					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/accountinfo.jsp">账户信息</a>
					</li>
					<li><a target="main"
						href="${pageContext.request.contextPath}/admin/modifyinfo.jsp">编辑信息</a>
					</li>
				</ul>
			</dd>
		</dl>

		<div class="clear"></div>
	</div>
</div>

