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
	<link href="css/login.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<div class="frame">
		<jsp:include page="/top.jsp" />
	    <div class="loginDiv">
	        <form id="loginForm">
				<table class="loginDiv_table" width="800" border="0" cellspacing="3" cellpadding="0">
		          <tbody>
		            <tr>
		              <td width="120">&nbsp;</td>
		              <td width="280">登录</td>
		            </tr>
		            <tr>
		              <td style="text-align: right;">用户名/电子邮箱/手机号：</td>
		              <td>
		                <input type="text" id="userName" name="userName">
		                <p id="userNameTip" style="text-align: left;"></p>
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
						<td style="text-align: right;">验证码：</td>
						<td>
							<input name="checkCode" type="text" id="checkCode" style="height: 50px;" />
							<p id="checkCodeTip" style="color: red;text-align: left;"></p>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><img  id="checkImg" src="/NewsManageSystem/servlet/ImageCheckCodeServlet?" class="hand" /></td>
					</tr>
		            <tr>
		              <td style="text-align: right;"></td>
		              <td>
		                <input id="submitForm" type="button" value="登录" style="width: 100px;height: 20px;">
		              </td>
		            </tr>
		            <tr>
		              <td style="text-align: right;"></td>
		              <td>
		              	<a href="findPassword.jsp">忘记密码？请点击这里</a>
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
