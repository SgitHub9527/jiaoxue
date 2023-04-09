<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="web" uri="/WEB-INF/webcontrol.tld"%>
<%@ include file="law.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
  <title>影片</title>
    <link href="${pageContext.request.contextPath}/admin/css/web2table.css" rel="stylesheet" type="text/css" />
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery-1.8.3.min.js"></script>
     <link href="${pageContext.request.contextPath}/webui/artDialog/skins/default.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/artDialog/jquery.artDialog.source.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webui/artDialog/iframeTools.source.js" type="text/javascript"></script>
    <script  type="text/javascript" src="${pageContext.request.contextPath}/webui/jquery/jquery.validate.min.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/jquery.validateex.js" ></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/jquery.metadata.js" ></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery/messages_cn.js" ></script>
        <link href="${pageContext.request.contextPath}/webui/easydropdown/themes/easydropdown.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/webui/easydropdown/jquery.easydropdown.js" type="text/javascript"></script>    
    <link href="${pageContext.request.contextPath}/uploadifyv3.1/uploadify.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/uploadifyv3.1/jquery.uploadify-3.1.js" type="text/javascript"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/webui/jquery-form/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/editor/lang/zh_CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webui/jqueryui/themes/base/jquery.ui.all.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jqueryui/ui/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webui/jqueryui/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/webui/jqueryui/ui/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
   <script type="text/javascript">
        function initControl(){
	  //开始绑定
                    //结束绑定
                     
			            editor = KindEditor.create('textarea[name="des"]', {
			            uploadJson : '../plusin/upload_json.jsp',
				        fileManagerJson : '../plusin/file_manager_json.jsp',
			            resizeType: 1,
				        allowFileManager: true
				       });
			           $('#btnulTupian').uploadify({  
			                'formData': { 'folder': '${pageContext.request.contextPath}/Upload' },  
			                'buttonText': '视频图片',  
			                'buttonClass': 'browser',  
			                'removeCompleted': true,  
			                'swf': '${pageContext.request.contextPath}/uploadifyv3.1/uploadify.swf', 
			                'fileTypeExts':"*.jpg;*.gif;*.png;",
			                'auto':true, 
			                'removeTimeout':0,
			                'debug': false,  
			                'height': 15,  
			                'width':90,  
			                'uploader': '${pageContext.request.contextPath}/admin/uploadmanager.do',
			                 'fileSizelimit':'2048KB',
			                 'queueSizelimit':'5',
			                 'onUploadSuccess':function(file, data, response){
			                     $("#filelist").show();
			                     $("#imgTupian").attr("src","${pageContext.request.contextPath}/upload/temp/"+file.name);
                                 $("#hidTupian").val("${pageContext.request.contextPath}/upload/temp/"+file.name);
			                  }
			             }); 
			          var imgtupiansrc="${requestScope.yingpian.tupian}";
				       if(imgtupiansrc==""){
				         var url="${pageContext.request.contextPath}/upload/nopic.jpg";
				         $("#imgTupian").attr("src",url);
				         $("#hidTupian").val(url);
				       }else
				       {
				          $("#imgTupian").attr("src",imgtupiansrc);
				          $("#hidTupian").val(imgtupiansrc); 
				       }
	        //初始化附件
	            initUpload();
        }
        //初始化附件
         function  initUpload(){
             $('#upload').uploadify({  
                'formData': { 'folder': '${pageContext.request.contextPath}/Upload' },  
                'buttonText': '上传视频',  
                'buttonClass': 'browser',  
                'removeCompleted': true,  
                'swf': '${pageContext.request.contextPath}/uploadifyv3.1/uploadify.swf', 
                'fileTypeExts':"*.avi;*.rmbv;*.wmv;*.rm;*.mp4;*.m4v;*.flv",
                'auto':true, 
                'removeTimeout':0,
                'debug': false,  
                'height': 15,  
                'width':90,  
                'uploader': '${pageContext.request.contextPath}/admin/uploadmanager.do',
                 'fileSizelimit':'2048KB',
                 'queueSizelimit':'5',
                 'onUploadSuccess':function(file, data, response){
                     $("#upload-video-fluid").show();
                     var filesize=Math.round(file.size/1024);
                      $(".uploadify-queue-item ul").append("<li ><input type='hidden' name='fileuploaded' value='"+file.name+"'  > "+file.name+"["+filesize+"KB] <a href=\"#\" onclick=\"$(this).parent().remove();\">移除</a> </li> ");
                  },
                  'onQueueComplete':function(){
                  }
             }); 
          }
        $(function ()
        {
            initControl();
            
            var xshowtype = $("#hidXshowtype").val();
            if (xshowtype == 1) {
                $("[name=xshowtype][value=1]").attr("checked", true);
                $("#remote-video-fluid").show();
                $("#upload-video-fluid").hide();
            } else
                if (xshowtype == 2) {
                    $("[name=xshowtype][value=2]").attr("checked", true);
                    $("#remote-video-fluid").hide();
                    $("#upload-video-fluid").show();
                }
            $("[name=xshowtype]").click(function () {
               
                var value = $(this).val();
                if (value == "1") {
                    $("#remote-video-fluid").show();
                    $("#upload-video-fluid").hide();
                }
                if (value == "2") {

                    $("#remote-video-fluid").hide();
                    $("#upload-video-fluid").show();
                }


            });
            
            $("#ypcateid").change(function(){
      	      $("[name=ypcatename]").val($("#ypcateid option:selected").text());
             });
            $("[name=ypcatename]").val($("#ypcateid option:selected").text());
            
            $.metadata.setType("attr","validate");
            $("#yingpianForm").validate();
        });  
    </script>
</head>
<body>
	<div class="search-title">
		<h2>发布课程信息</h2>
		<div class="description"></div>
	</div>
	<form name="yingpianform" method="post"
		action="${pageContext.request.contextPath}/admin/yingpianmanager.do"
		id="yingpianForm">
		<table class="grid" cellspacing="1" width="100%">
			<input type="hidden" name="id" value="${id}" />
			<input name="pubren" validate="{required:true,messages:{required:'请输入发布人'}}"
					value="${users.username}" class="input-txt"
					type="hidden" id="txtPubren" />
			<input type="hidden" name="actiontype" value="${actiontype}" />
			<input type="hidden" name="seedid" value="${seedid}" />
			<input type="hidden" name="errorurl" value="/admin/yingpianadd.jsp" />
			<tr>
				<td width="15%" align="right">名称:</td>
				<td width="*"><input name="title"
					validate="{required:true,messages:{required:'请输入影片标题'}}"
					value="${requestScope.yingpian.title}" class="input-txt"
					type="text" id="txtTitle" />
					
				</td>
				
			</tr>
			<tr>
				<td align="right">视频类别:</td>
				<td><web:dropdownlist  cssclass="dropdown" name="ypcateid" id="ypcateid"
						value="${requestScope.yingpian.ypcateid}"
						datasource="${ypcatename_datasource}" textfieldname="name"
						valuefieldname="id">
					</web:dropdownlist>
					<input type="hidden" name="ypcatename"  value="${yingpian.ypcatename}" />
				</td>
			</tr>
			<tr>
			 
			     <td align="right">视频快照:</td>
			      <td><img id="imgTupian" width="200px" height="200px"
					src="${requestScope.yingpian.tupian}" />
					<div>
						<input type="file" name="upload" id="btnulTupian" />
					</div> <input type="hidden" id="hidTupian" name="tupian"
					value="${requestScope.yingpian.tupian}" /></td>
			</tr>
			<tr>
			   <td align="right">字母索引</td>
			   <td >
			        <select class="dropdown"  name="alphabetindex">
			              <option value="A">A</option>
			              <option value="B">B</option>
			              <option value="C">C</option>
			              <option value="D">D</option>
			              <option value="E">E</option>
			              <option value="F">F</option>
			              <option value="G">G</option>
			              <option value="H">H</option>
			              <option value="I">I</option>
			               <option value="J">J</option>
			              <option value="K">K</option>
			              <option value="L">L</option>
			              <option value="M">M</option>
			              <option value="N">N</option>
			              <option value="O">O</option>
			              <option value="P">P</option>
			              <option value="Q">Q</option>
			              <option value="R">R</option>
			              <option value="S">S</option>
			              <option value="T">T</option>
			              <option value="U">U</option>
			              <option value="V">V</option>
			              <option value="W">W</option>
			              <option value="X">X</option>
			              <option value="Y">Y</option>
			              <option value="Z">Z</option>
			        </select>
			   </td>
			</tr>
			
		    <tr>
				<td align="right">视频地址</td>
				<td colspan="3">
				   <input  type="hidden" id="hidXshowtype" value="1"/> 
                   <input type="radio" name="xshowtype" value="1"  checked="true"  /> 外部视频 
                   <input type="radio" name="xshowtype" value="2"   /> 上传视频 
                   <div id="remote-video-fluid">
                       
                         <textarea name="remoteurl" id="txtDes" style="width:54%;height:50px;">${requestScope.yingpian.remoteurl}
						</textarea>
                       
                   </div>
					
					<div  id="upload-video-fluid" style="display:none;" class="uploadify-queue">
						
						<div>
						   <input type="file" name="upload" id="upload" />
					    </div>
						<div class="uploadify-queue-item">
							<ul>
							</ul>
						</div>
					 <%
									     String id= request.getParameter("id");
									    if( id!=null)
									    {
									    %>
					<div class="uploadify-queue">
						<ul>
							<%
									                List<Attachement> yingpianattachementlist=DALBase.getEntity("attachement",MessageFormat.format(" where belongtable=''yingpian'' and belongid=''{0}''",id));
									                for(Attachement tema : yingpianattachementlist)
									                {
									            %>
							<li><input type='hidden' name='fileuploaded'
								value='<%=tema.getFilename() %>'> <%=tema.getFilename() %>
									<a href="#" onclick="$(this).parent().remove();">移除</a>
							</li>
							<%
									            } %>
						</ul>
					</div> <%} %>
					
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">介绍:</td>
				<td colspan="3"><textarea name="des" id="txtDes"
						style="width:98%;height:200px;">${requestScope.yingpian.des}</textarea>
				</td>
			</tr>
			<tr>
			    <td colspan="4">
			       <input type="submit" value="提交" id="Button1" class="orange-button" />
			    </td>
			</tr>
		
		</table>
		
	</form>
</body>
</html>
