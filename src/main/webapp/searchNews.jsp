<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searhNews.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/NewsManageSystem/css/searchNews.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <jsp:include page="/top.jsp" />
    <div class="searchNews">
    	<p>搜索结果</p>
	    <ul>
	    	<c:forEach items="${requestScope.searchNews}" var="news">
	    		<li>
		    		<a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>
		    	</li>
	    	</c:forEach>
	    </ul>
    </div>
  </body>
</html>
