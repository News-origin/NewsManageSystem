<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
	<link href="css/normalUsersManagement.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/normalUsersManagement.js"></script>
	<title>普通用户界面</title>
  </head>
  
  <body>
	<div class="frame">
			<jsp:include page="/top.jsp" />
		    <div class="middle">
		        <div class="middle_left">
		            <ul>
			            <li id="toShowPersonalData">显示个人信息</li>
			            <li id="toChangePersonalData">修改个人信息</li>
			            <li id="toChangePassword">修改密码</li>
		            </ul>
		        </div>
		        <div id="middle_right" class="middle_right">
		        	<jsp:include page="/showPersonalData.jsp" />
		        </div>
		    </div>
		    <jsp:include page="/bottom.jsp" />
		</div>
	</body>
</html>
