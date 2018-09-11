<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>编辑新闻</title>
    <script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.config.js' type="text/javascript"></script>
	<script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.all.min.js' type="text/javascript"></script>
	<script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/lang/zh-cn/zh-cn.js' type="text/javascript"></script>  
  </head>
  
  <body>
    <form action="/NewsManageSystem/servlet/NewsServlet?type=newsAfterEdit&newsId=${requestScope.editNews.newsId}" method="post">
    	标题:<input type="text" name="caption" value="${requestScope.editNews.caption}"/>
    	<select  style="display:block;" name="newsType" id="newsType">
			<c:forEach items="${requestScope.newsTypes}"  var="newsType">
				<c:choose>
					<c:when test="${newsType.newsType == requestScope.editNews.newsType}">
						<option value="${newsType.newsType}" selected>${newsType.newsType}</option>
					</c:when>
					<c:otherwise>
						<option value="${newsType.newsType}">${newsType.newsType}</option>
					</c:otherwise>			    
				</c:choose>			
			</c:forEach>								
		</select>
    	新闻日期:<input type="datetime-local" name="newsTime" value="${requestScope.editNews.newsTime}" />
      	<div>
 			<script id="container" type="text/plain" style="width:800px;height:600px;"></script>
		</div>
		<input type="submit" value="submit">
	</form>
  </body>
  <script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('container');
     //设置编辑器的内容
 		ue.ready(function() {
    		ue.setContent('${requestScope.editNews.content}');
		});
		/*
		function cancel(){//取消修改 
			ids1=document.getElementById("ids"); 
		  	ids1.value="";
		  	//提交
		  	document.getElementById('myform').action="/news/servlet/NewsServlet?type1=newsManage";
			document.getElementById('myform').submit();			
		}
		*/
  </script>
</html>
