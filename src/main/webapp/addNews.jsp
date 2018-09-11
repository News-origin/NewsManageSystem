<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>添加新闻</title>
  	<link href="/NewsManageSystem/css/addNews.css" rel="stylesheet" type="text/css">
    <script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.config.js' type="text/javascript"></script>
	<script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.all.min.js' type="text/javascript"></script>
	<script src='/NewsManageSystem/plugin/ueditor1_4_3_3-utf8-jsp/utf8-jsp/lang/zh-cn/zh-cn.js' type="text/javascript"></script>  
  </head>
  
  <body>
  	<jsp:include page="/top.jsp" />
    <form action="/NewsManageSystem/servlet/NewsServlet?type=addNews" method="post">
    	<p>标题:<input type="text" name="caption" /></p>
    	<p>
	    	新闻类型：<select  style="display:block;" name="newsType" id="newsType">
				<c:forEach items="${applicationScope.newsTypes}"  var="newsType">
					<option value="${newsType.newsType}">${newsType.newsType}</option>	
				</c:forEach>								
			</select>
		</p>
    	<p>新闻日期:<input type="datetime-local" name="newsTime" /></p>
      	<div>
 			<script id="container" type="text/plain" style="width:800px;height:600px;"></script>
		</div>
		<input type="submit" value="submit">
	</form>
	<jsp:include page="/bottom.jsp" />
  </body>
  <script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('container');
  </script>
</html>
