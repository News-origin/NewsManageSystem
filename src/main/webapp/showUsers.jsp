<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.*"%>
<%@ page import="tools.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'showUsers.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
      function getOnePage(type,orderFieldName){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
    	  	
  			var order=document.getElementById("order");
  			var orderField=document.getElementById("orderField");
			
			if(orderFieldName!=""){//切换排序
				orderField.value=orderFieldName;//设置排序字段名
				if(order.value == "asc")//切换排序
					order.value="desc";
				else
					order.value="asc";	
					
				page.value=1;//排序后从第一页开始显示												
			}
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="first")
    	  		page.value="1";
    	  	else if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="last"){
    	  		page.value=totalPageCount.value;
    	  	}
    	  	//提交
    	  	document.getElementById('listOnePageUsers').submit();
      	}
	</script>
	<link href="css/manager.css" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:include page="/top.jsp" />
	<form
		action="/NewsManageSystem/servlet/UserServlet?type=listOnePageUsers"
		id="listOnePageUsers" method="post">
		<table align="center" border='1'>
			<tr bgcolor='#FFACAC'>
				<td><a href='javascript:void(0);'
					onclick="getOnePage('','userId');">Id</a></td>
				<td>用户类型</td>
				<td>用户名</td>
				<td>注册日期</td>
				<td>切换可用性</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${requestScope.users}" var="user">
				<tr>
					<td><c:out value="${user.userId}" /></td>
					<td><c:out value="${user.type}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.registerDate}" /></td>
					<c:if test="${user.enable=='use'}">
						<td width="100"><a href="/NewsManageSystem/servlet/UserServlet?type=changeEnable&userId=${user.userId}">禁用</a></td>
					</c:if>
					<c:if test="${user.enable=='stop'}">
						<td width="100"><a href="/NewsManageSystem/servlet/UserServlet?type=changeEnable&userId=${user.userId}">恢复</a></td>
					</c:if>
					<td width="100"><a href="/NewsManageSystem/servlet/UserServlet?type=deleteUsers&userId=${user.userId}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<table align="center" border='1'>
			<tr>
				<c:if test="${requestScope.pageInformation.page > 1}">
					<td><a href="javascript:void(0);"
						onclick="getOnePage('first','');">首页</a></td>
				</c:if>

				<c:if test="${requestScope.pageInformation.page > 1}">
					<td><a href="javascript:void(0);"
						onclick="getOnePage('pre','');">上一页</a></td>
				</c:if>

				<c:if
					test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
					<td><a href="javascript:void(0);"
						onclick="getOnePage('next','');">下一页</a></td>
				</c:if>
				<c:if
					test="${requestScope.pageInformation.page < requestScope.pageInformation.totalPageCount}">
					<td><a href="javascript:void(0);"
						onclick="getOnePage('last','');">尾页</a></td>
				</c:if>
				<td>共${requestScope.pageInformation.totalPageCount}页</td>
			</tr>
		</table>
		<input type="hidden" name="page" id="page"
			value="${requestScope.pageInformation.page}"> <input
			type="hidden" name="pageSize" id="pageSize"
			value="${requestScope.pageInformation.pageSize}"> <input
			type="hidden" name="totalPageCount" id="totalPageCount"
			value="${requestScope.pageInformation.totalPageCount}"> <input
			type="hidden" name="allRecordCount" id="allRecordCount"
			value="${requestScope.pageInformation.allRecordCount}"> <input
			type="hidden" name="orderField" id="orderField"
			value="${requestScope.pageInformation.orderField}"> <input
			type="hidden" name="order" id="order"
			value="${requestScope.pageInformation.order}"> <input
			type="hidden" name="ids" id="ids"
			value="${requestScope.pageInformation.ids}"> <input
			type="hidden" name="searchSql" id="searchSql"
			value="${requestScope.pageInformation.searchSql}"> <input
			type="hidden" name="result" id="result"
			value="${requestScope.pageInformation.result}">
	</form>
	<jsp:include page="/bottom.jsp" />
</body>
</html>
