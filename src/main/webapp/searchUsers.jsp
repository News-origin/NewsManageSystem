<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'searchUsers.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
      function getOnePage(){
    	  	//提交
		  	$( "#middle_right" ).load( "/NewsManageSystem/servlet/UserServlet?type=searchUsers&page=1&pageSize=1",$("#searchUsersForm").serialize());
      	}
	</script>
</head>

<body>
	<div id="searchUsers">
		<form id="searchUsersForm" method="post">
			<table width="500" style="margin-left:auto; margin-right:auto;">
				<tbody>
					<tr>
						<td width="250"></td>
						<td width="250">查询条件</td>
					</tr>
					<tr>
						<td width="250">用户类型:</td>
						<td width="250"><select name="userType">
								<option value="all">全部</option>
								<option value="user">普通用户</option>
								<option value="newsAuthor">新闻编辑员</option>
								<option value="manager">管理员</option>
						</select></td>
					</tr>
					<tr>
						<td width="250">用户名:</td>
						<td width="250"><input name="name" type="text" /></td>
					</tr>
					<tr>
						<td width="250">账号可用性:</td>
						<td width="250"><select name="enable">
								<option value="all">全部</option>
								<option value="use">可用</option>
								<option value="stop">不可用</option>
						</select></td>
					</tr>
					<tr>
						<td width="250"></td>
						<td width="250"><input type="button" onclick="getOnePage()" value="开始查询" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
