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
	<link href="css/usersRegistration.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/usersRegistration.js"></script>
</head>

<body>
	<div class="frame">
		<jsp:include page="/top.jsp" />
		<input id="emailMethod" type="button" value="通过邮箱注册" />
		<input id="phoneMethod" type="button" value="通过手机注册" />
		<div class="registrationDiv" id="registrationDiv">
			<jsp:include page="/emailRegister.jsp" />
			<jsp:include page="/phoneRegister.jsp" />
		</div>
		<jsp:include page="/bottom.jsp" />
	</div>
</body>
</html>
