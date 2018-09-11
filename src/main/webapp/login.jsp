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
	<jsp:include page="/style.jsp" />
	<title>登录</title>
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/login.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<div class="frame">
		<jsp:include page="/top.jsp" />
	    <div class="loginDiv">
	        <form action="servlet/UserServlet?type=login" method="post" onsubmit="return submit1()">
				<table width="400" border="0" cellspacing="3" cellpadding="0">
		          <tbody>
		            <tr>
		              <td width="120">&nbsp;</td>
		              <td width="280">登录</td>
		            </tr>
		            <tr>
		              <td style="text-align: right;">用户名：</td>
		              <td>
		                <input type="text" id="usersName" name="userName">
		                <p id="usersNameTip" style="text-align: left;"></p>
		              </td>
		            </tr>
		            <tr>
		              <td style="text-align: right;">密码：</td>
		              <td>
		                <input type="password" id="password" name="password">
		                <p id="passwordTip" style="text-align: left;"></p>
		              </td>
		            </tr>
		            <tr>
		              <td style="text-align: right;">&nbsp;</td>
		              <td>
		                <input type="submit" value="登录" style="width: 100px;height: 20px;">
		              </td>
		            </tr>
		          </tbody>
		        </table>
	        </form>
	    </div>
	    <jsp:include page="/bottom.jsp" />
	</div>
  </body>
</html>
