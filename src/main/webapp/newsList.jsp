<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新闻列表</title>
	<style type="text/css">
	a:link {
		color: #000000;
		text-decoration: none;
	}
	a:visited {
		text-decoration: none;
		color: #000000;
	}
	a:hover {
		text-decoration: none;
	}
	a:active {
		text-decoration: none;
	}
	</style>
	<link href="css/newsList.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/newsList.js"></script>
  </head>
  
  <body>
    <div class="frame">
      <jsp:include page="/top.jsp" />
        <div class="middle">
            <div class="middle_left">
                <ul>
                    <li onClick="changeNewsBoard(0)">全部</li>
                    <li onClick="changeNewsBoard(1)">互联网</li>
                    <li onClick="changeNewsBoard(2)">体育</li>
                    <li onClick="changeNewsBoard(3)">游戏</li>
                    <li onClick="changeNewsBoard(4)">其它</li>
                </ul>
            </div>
            <div class="middle_right middle_right_show">
                <ul>
                    <c:forEach items="${applicationScope.allNews}" var="news">
                    	<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>&nbsp;——${news.publishTime}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="middle_right">
                <ul>
                    <c:forEach items="${applicationScope.allNews}" var="news">
                    	<c:if test="${news.newsType=='互联网'}">
                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>&nbsp;——${news.publishTime}</li>
                    	</c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="middle_right">
                <ul>
                    <c:forEach items="${applicationScope.allNews}" var="news">
                    	<c:if test="${news.newsType=='体育'}">
                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>&nbsp;——${news.publishTime}</li>
                    	</c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="middle_right">
                <ul>
                    <c:forEach items="${applicationScope.allNews}" var="news">
                    	<c:if test="${news.newsType=='游戏'}">
                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>&nbsp;——${news.publishTime}</li>
                    	</c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="middle_right">
                <ul>
                    <c:forEach items="${applicationScope.allNews}" var="news">
                    	<c:if test="${news.newsType=='其它'}">
                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a>&nbsp;——${news.publishTime}</li>
                    	</c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <jsp:include page="/bottom.jsp" />
    </div>
  </body>
</html>
