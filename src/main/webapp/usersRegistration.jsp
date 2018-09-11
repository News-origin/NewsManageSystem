<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'usersRegistration.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<title>注册</title>
	<jsp:include page="/style.jsp" />
	<link href="css/index.css" rel="stylesheet" type="text/css">
	<link href="css/usersRegistration.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/usersRegistration.js"></script>
</head>

<body>
	<div class="frame">
		<jsp:include page="/top.jsp" />
		<div class="registrationDiv">
			<form action="servlet/UserServlet?type=register" method="post"  onsubmit="return submit1()">
				<table width="600" border="0" cellspacing="3" cellpadding="0">
					<tbody>
						<tr>
							<td width="120">&nbsp;</td>
							<td width="480">注册</td>
						</tr>
						<tr>
							<td style="text-align: right;">用户类型：</td>
							<td>
								<select name="role">
										<option value="user">普通用户</option>
										<option value="manager">管理员</option>
										<option value="newsAuthor">新闻编辑员</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">用户名：</td>
							<td><input name="userName" type="text" id="usersName">
								<p id="usersNameTip" style="color: red;text-align: left;"></p>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">密码：</td>
							<td><input name="password" type="password" id="password">
								<p id="passwordTip" style="color: red;text-align: left;"></p></td>
						</tr>
						<tr>
							<td style="text-align: right;">重新输入密码：</td>
							<td><input type="password" id="confirmPassword">
								<p id="confirmPasswordTip" style="color: red;text-align: left;"></p></td>
						</tr>
						<tr>
							<td style="text-align: right;">&nbsp;</td>
							<td><input type="submit" value="注册"
								style="width: 100px;height: 20px;"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<jsp:include page="/bottom.jsp" />
	</div>
</body>
</html>
