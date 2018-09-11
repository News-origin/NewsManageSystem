<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>回复评论界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
	<link href="/NewsManageSystem/css/replyComment.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  	<jsp:include page="/top.jsp" />
  	<form action="servlet/CommentServlet?type=replyComment&newsId=${param.newsId}&userId=${sessionScope.user.userId}&priorCommentId=${param.priorCommentId}" method="post" style="width:800px; margin-left:auto; margin-right:auto;">
	    	<input style="width: 600px; height:200px;" type="text" name="content" />
	    	<input type="submit" />
	</form>
    <jsp:include page="/bottom.jsp" />
  </body>
</html>