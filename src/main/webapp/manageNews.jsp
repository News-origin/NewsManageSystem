<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.*"%>
<%@ page import="tools.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manageNews.jsp' starting page</title>
    
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
		  	$( "#middle_right").load( "/NewsManageSystem/servlet/NewsServlet?type=getOnePageNews&page="+page.value
		  		+"&pageSize="+pageSize.value,$("#listOnePageNews").serialize());
      	}
	</script>
  </head>
  
  <body>
	<form
		action="/NewsManageSystem/servlet/NewsServlet?type=getOnePageNews"
		id="listOnePageNews" method="post">
		<table align="center" border='1'>
			<tr bgcolor='#FFACAC'>
				<td><a href='javascript:void(0);'
					onclick="getOnePage('','newsId');">Id</a></td>
				<td>标题</td>
				<td>作者</td>
				<td>日期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.newses}" var="news">
				<c:if test="${sessionScope.user.type=='newsAuthor'}">
					<c:if test="${news.authorId==sessionScope.user.userId}">
						<tr>
							<td><c:out value="${news.newsId}" /></td>
							<td><c:out value="${news.caption}" /></td>
							<td><c:out value="${news.name}" /></td>
							<td><c:out value="${news.publishTime}" /></td>
							<td><input type="button" value="编辑" onclick="window.location.href='/NewsManageSystem/servlet/NewsServlet?type=editNews&newsId=${news.newsId}'" /></td>
						</tr>
					</c:if>
				</c:if>
				<c:if test="${sessionScope.user.type=='manager'}">
					<tr>
						<td><c:out value="${news.newsId}" /></td>
						<td><c:out value="${news.caption}" /></td>
						<td><c:out value="${news.name}" /></td>
						<td><c:out value="${news.publishTime}" /></td>
						<td><input type="button" onclick="deleteANews(${news.newsId})" value="删除" /></td>
					</tr>
				</c:if>
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
  </body>
  <script type="text/javascript" src="js/manageNews.js"></script>
</html>
