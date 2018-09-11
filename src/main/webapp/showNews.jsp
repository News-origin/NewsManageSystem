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
    <title>${applicationScope.news.caption}</title>
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
	      <div class="TextComment">
	      	<form action="/NewsManageSystem/servlet/CommentServlet?type=writeComment&newsId=${requestScope.news.newsId}" method="post">
	      	<input style="width: 400px; height:100px;" type="text" name="content">
	      	<input style="width: 100px;" type="submit" value="提交评论">
	        <div class="UsersComment">
	        	<h3 style="font-family: '微软雅黑';text-align: left; margin-bottom:5px">最新评论</h3>
	            <c:forEach items="${requestScope.news.comments}" var="comment">
		            <table width="700" border="0" cellspacing="0" cellpadding="0">
		              <tbody>
		                <tr>
		                  <td width="76"><img style="float: left" src="/NewsManageSystem/images/headPortrait.jpg" width="60" alt=""/></td>
		                  <td width="384">
						  	<p class="userName">${comment.userName}</p>
						  	<p class="reviewTime">${comment.commentDate}</p>
						  </td>
						  <td style="text-align: right;" width="240">
						  	<c:out value="第${comment.floor}楼" />
						  	<a href="#" target="_self"><img style="opacity: 0.6" src="/NewsManageSystem/images/good.png" width="15" height="15" alt=""/></a>
						  	<c:out value="${comment.appreciate}" />
						  	<a href="/NewsManageSystem/servlet/CommentServlet?type=goToReply&priorCommentId=${comment.commentId}&newsId=${comment.newsId}" target="_self" class="review">回复</a>
						  	<c:if test="${sessionScope.user.type=='manager'}">
						  		<a href="/NewsManageSystem/servlet/CommentServlet?type=deleteComment&newsId=${comment.newsId}&commentId=${comment.commentId}">删除</a>
						  	</c:if>
					  	  </td>
		                </tr>
						<c:forEach items="${requestScope.news.comments}" var="comment1">
			            	<c:if test="${comment.priorCommentId==comment1.commentId}">
								<tr>
							    	<td width="76"><img style="float: left" src="/NewsManageSystem/images/headPortrait.jpg" width="40" alt=""/></td>
							        <td width="384">
										<p class="userName">&nbsp;&nbsp;&nbsp;${comment1.userName}</p>
										<p class="reviewTime">&nbsp;&nbsp;&nbsp;${comment1.commentDate}</p>
									</td>
									<td style="text-align: left;" width="240">
										<c:out value="第${comment1.floor}楼" />
										<a href="#" target="_self"><img style="opacity: 0.6" src="/NewsManageSystem/images/good.png" width="15" height="15" alt=""/></a>
										<c:out value="${comment1.appreciate}" />
										<a href="/NewsManageSystem/servlet/CommentServlet?type=goToReply&newsId=${comment1.newsId}&priorCommentId=${comment1.commentId}" target="_self" class="review">回复</a>
										<c:if test="${sessionScope.user.type=='manager'}">
						  					<a href="/NewsManageSystem/servlet/CommentServlet?type=deleteComment&newsId=${comment1.newsId}&commentId=${comment.commentId}">删除</a>
						  				</c:if>
									</td>
							    </tr>
							    <tr>
							    	<td>&nbsp;</td>
									<td colspan="2" style="text-align: left; font-weight: bold;">
										&nbsp;&nbsp;&nbsp;<c:out value="${comment1.content}" />
									</td>
								</tr>
							</c:if>
					    </c:forEach>
		                <tr>
		                  <td>
		                  	&nbsp;
		                  </td>
						  <td colspan="2" style="text-align: left; font-weight: bold;">
						  	<c:out value="${comment.content}" />
						  </td>
		                </tr>
		              </tbody>
		            </table>
	            </c:forEach>
	        </div>
	        </form>
	      </div>
	    </div>
	    <jsp:include page="/bottom.jsp" />
	</div>
  </body>
</html>
