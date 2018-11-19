<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${requestScope.news.caption}</title>
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
	<script type="text/javascript" src="/NewsManageSystem/js/jquery-3.3.1.js"></script>
	
	<link href="/NewsManageSystem/css/newsText.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
	<div class="frame">
	  <jsp:include page="/top.jsp" />
	    <div class="Text">
	   	  <div class="TextHead">
	      	<h1>${requestScope.news.caption}</h1>
	        <table width="500" border="0" cellspacing="0" cellpadding="0">
	          <tbody>
	            <tr>
	              <td align="center">作者：${requestScope.news.name}</td>
	              <td align="center">${requestScope.news.publishTime}</td>
	            </tr>
	          </tbody>
	        </table>
	      </div>
	      <div class="TextBody">
	          ${requestScope.news.content}
	      </div>
	      <div class="TextComment" id="TextComment">
	      	<jsp:include page="/showComments.jsp" />
	      </div>
	    </div>
	    <jsp:include page="/bottom.jsp" />
	</div>
  </body>
</html>
