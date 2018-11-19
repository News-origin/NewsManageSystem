<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.*"%>
<table class="top" width="1024" border="0" cellspacing="0"
			cellpadding="0">
			<tbody>
				<tr>
					<td width="349"><img src="/NewsManageSystem/images/logo.jpg" width="246"
						height="96" alt="" /></td>
					<form action="/NewsManageSystem/servlet/NewsServlet?type=searchNews" method="post">
						<td width="110"><input type="text" id="searchText"
							name="keyWord" size="30" placeholder="请输入搜索内容"></td>
						<td width="139"><input type="submit" /></td>
					</form>
					<td width="139" align="center" valign="middle"><a href="/NewsManageSystem/index.jsp">首页</a></td>
					<td width="139" align="center" valign="middle">
						<c:if test="${sessionScope.user==null}">
							<a href="/NewsManageSystem/login.jsp">登录</a>
						</c:if>
						<c:if test="${sessionScope.user!=null}">
							<a href="/NewsManageSystem/userMain.jsp"><c:out value="${sessionScope.user.name}" /></a>
						</c:if>
					</td>
					<td width="148" align="center" valign="middle">
						<c:if test="${sessionScope.user==null}">
							<a href="/NewsManageSystem/usersRegistration.jsp">注册</a>
						</c:if>
						<c:if test="${sessionScope.user!=null}">
							<a href="/NewsManageSystem/servlet/UserServlet?type=cancel">注销</a>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>