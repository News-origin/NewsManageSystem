<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>欢迎访问人民网</title>
		<jsp:include page="/style.jsp" />
		<jsp:include  page='<%="/servlet/NewsServlet?type=getAllNews"%>' flush="true" />
		<jsp:include  page='<%="/servlet/NewsServlet?type=getNewsType"%>' flush="true" />
		<link href="/NewsManageSystem/css/index.css" rel="stylesheet"
	type="text/css">
		<script type="text/javascript"
			src="/NewsManageSystem/js/imagesSlider.js"></script>
		<link href="/NewsManageSystem/css/imagesSlider.css" rel="stylesheet"
			type="text/css">
		<script type="text/javascript" src="/NewsManageSystem/js/tab.js"></script>
		<link href="/NewsManageSystem/css/tab.css" rel="stylesheet"
			type="text/css">
	</head>
	<body>
		<div class="frame">
			<jsp:include page="/top.jsp" />
			<div class="middle">
				<div id="myTab">
					<div>
						<div>要闻速递</div>
						<div>互联网</div>
						<div>体育</div>
						<div>游戏</div>
					</div>
					<div>
						<div>
							<ul>
								<c:forEach items="${applicationScope.allNews}" var="news">
                    				<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a></li>
                    			</c:forEach>
                    			<li><a href="/NewsManageSystem/newsList.jsp">>>想看更多新闻请点击这里</a></li>
							</ul>
						</div>
						<div>
							<ul>
								<c:forEach items="${applicationScope.allNews}" var="news">
			                    	<c:if test="${news.newsType=='互联网'}">
			                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a></li>
			                    	</c:if>
                    			</c:forEach>
							</ul>
						</div>
						<div>
							<ul>
								<c:forEach items="${applicationScope.allNews}" var="news">
			                    	<c:if test="${news.newsType=='体育'}">
			                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a></li>
			                    	</c:if>
                    			</c:forEach>
							</ul>
						</div>
						<div>
							<ul>
								<c:forEach items="${applicationScope.allNews}" var="news">
			                    	<c:if test="${news.newsType=='游戏'}">
			                    		<li><a href="/NewsManageSystem/servlet/NewsServlet?type=showANews&newsId=${news.newsId}">${news.caption}</a></li>
			                    	</c:if>
                    			</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="/bottom.jsp" />
		</div>
		<script type="text/javascript">
			var params = {
				"id" : "myTab",
				"width" : 1024,
				"height" : 366
			};
			var tab = new Tab(params);
		</script>
	</body>
</html>
