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
	<link href="css/index.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <jsp:include page="/top.jsp" />
    	<table width="500" border="1" style="margin-left:auto; margin-right:auto;">
					<tbody>
				        <tr>
				          <td width="50">id</td>
				          <td width="100">用户类型</td>
				          <td width="100">用户名</td>
				          <td width="100">注册日期</td>
				          <td width="100">账户可用性</td>
				          <td width="100">切换可用性</td>
				        </tr>
				        <c:forEach items="${applicationScope.searchUsers}" var="user">
				        	<tr>
							    <td width="50">${user.userId}</td>
						        <td width="100">${user.type}</td>
						        <td width="100">${user.name}</td>
						        <td width="100">${user.registerDate}</td>
						        <td width="100">${user.enable}</td>
						        <c:if test="${user.enable=='use'}">
						        	<td width="100"><a href="/NewsManageSystem/servlet/UserServlet?type=changeEnable&userId=${user.userId}">禁用</a></td>
						        </c:if>
						        <c:if test="${user.enable=='stop'}">
						        	<td width="100"><a href="/NewsManageSystem/servlet/UserServlet?type=changeEnable&userId=${user.userId}">恢复</a></td>
						        </c:if>
					        </tr>
				        </c:forEach>
					</tbody>
				</table>
    <jsp:include page="/bottom.jsp" />
  </body>
</html>
