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
  	<title>管理员界面</title>
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
	<link href="css/manager.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/manager.js"></script>
  </head>
  
  <body>
	<div class="frame">
			<jsp:include page="/top.jsp" />
		    <div class="middle">
		        <div class="middle_left">
		            <ul>
			            <li onClick="display(0)">显示个人信息</li>
			            <li onClick="display(1)">修改个人信息</li>
			            <li onClick="display(2)">修改密码</li>
			            <li><a href="/NewsManageSystem/servlet/UserServlet?type=listOnePageUsers&page=1&pageSize=2">浏览用户</a></li>
			            <li onClick="display(5)">查询用户</li>
			            <li><a href="/NewsManageSystem/servlet/NewsServlet?type=getOnePageNews&page=1&pageSize=4">编辑新闻</a></li>
		            </ul>
		        </div>
		        <div id="showPersonalData" class="middle_right middle_right_show">
		          <table width="500" border="0" cellspacing="0" cellpadding="0">
		          	<tbody>
						<tr>
			              	<td width="239" align="center">
					              <table width="400" border="0" cellspacing="0" cellpadding="0">
					                <tbody>
					                  <tr>
					                  	<td width="168" align="center" style="text-align: center;">个人信息</td>
					                  </tr>
					                  <tr>
					                    <td height="44" align="center">用户类型：</td>
					                    <td width="232"><c:out value="${sessionScope.user.type}" /></td>
					                  </tr>
					                  <tr>
					                    <td align="center">用户名：</td>
					                    <td><c:out value="${sessionScope.user.name}" /></td>
					                  </tr>
					                  <tr>
					                    <td height="129" align="center">头像：</td>
					                    <td valign="middle"><img src="${sessionScope.user.imgUrl}" width="120" height="100" alt=""/></td>
					                  </tr>
					                  <tr>
					                    <td align="center">注册日期：</td>
					                    <td><c:out value="${sessionScope.user.registerDate}" /></td>
					                  </tr>
					                  <tr>
					                    <td align="center">性别：</td>
					                    <td><c:out value="${sessionScope.user.sex}" /></td>
					                  </tr>
					                  <tr>
					                    <td align="center">爱好：</td>
					                    <td><c:out value="${sessionScope.user.hobby}" /></td>
					                  </tr>
					                </tbody>
					              </table>
				              </td>
			            </tr>
			          </tbody>
		          </table>
		      </div>
		      <div id="changePersonalData" class="middle_right">
				<form action="servlet/UserServlet?type=changePersonalData" enctype="multipart/form-data" method="post">
					<table width="500" border="0" cellspacing="0" cellpadding="0">
						<tbody>
					    	<tr>
					        	<td width="165" align="center" style="text-align: center;">修改个人信息</td>
					        </tr>
					        <tr>
					        	<td height="129" align="center">头像：</td>
					        	<td align="center" valign="middle"><img src="${sessionScope.user.imgUrl}" width="120" height="100" alt=""/><input type="file" name="headImg"></td>
					    	</tr>
						    <tr>
						    	<td align="center">&nbsp;</td>
						    	<td>预览</td>
						    </tr>
						    <tr>
						    	<td align="center">性别</td>
						    	<td>
						        <select name="sex">
						          <option value ="男">男</option>
						          <option value ="女">女</option>
						          <option value ="未知">未知</option>
						        </select>
						    </tr>
						    <tr>
						    	<td align="center">爱好：</td>
							   	<td>
							      <input name="hobby" type="text" value="${sessionScope.user.hobby}">
							      <input type="submit">
							    </td>
						    </tr>
						</tbody>
					</table>
				</form>
		      </div>
		      <div id="changePassword" class="middle_right">
		          <form action="servlet/UserServlet?type=changePassword" method="post" onsubmit="return submit1()">
		          	<table>
		          		<tr>
		          			<td>原密码：</td>
		          			<td><input type="password" name="originPassword" id="originPassword" /></td>
		          			<td id="originPasswordTip"></td>
		          		</tr>
		          		<tr>
		          			<td>新密码：</td>
		          			<td><input type="password" name="newPassword" id="newPassword" /></td>
		          			<td id="newPasswordTip"></td>
		          		</tr>
		          		<tr>
		          			<td>确认新密码：</td>
		          			<td><input type="password" name="confirmPassword" id="confirmPassword" /></td>
		          			<td id="confirmPasswordTip"></td>
		          		</tr>
		          		<tr>
		          			<td></td>
		          			<td><input type="submit" value="确定"></td>
		          		</tr>
		          	</table>
		          </form>
		      </div>
		      <div id="listOnePageUsers" class="middle_right">
				
		      </div>
		      <div id="checkUsers" class="middle_right">

		      </div>
		      <div id="searchUsers" class="middle_right">
		      	<form action="servlet/UserServlet?type=searchUsers&page=1&pageSize=2" method="post">
					<table width="500"  style="margin-left:auto; margin-right:auto;">
						<tbody>
					        <tr>
					          <td width="250"></td>
					          <td width="250">查询条件</td>
					        </tr>
					        <tr>
					          <td width="250">用户类型:</td>
					          <td width="250">
					          	<select name="userType">
					          		<option value="all">全部</option>
					          		<option value="user">普通用户</option>
					          		<option value="newsAuthor">新闻编辑员</option>
					          		<option value="manager">管理员</option>
					          	</select>
					          </td>
					        </tr>
					        <tr>
					          <td width="250">用户名:</td>
					          <td width="250"><input name="name" type="text" /></td>
					        </tr>
					        <tr>
					          <td width="250">账号可用性:</td>
					          <td width="250">
						          <select name="enable">
						          		<option value="all">全部</option>
						          		<option value="use">可用</option>
						          		<option value="stop">不可用</option>
						          </select>
					          </td>
					        </tr>
					        <tr>
					          <td width="250"></td>
					          <td width="250"><input type="submit" value="开始查询" /></td>
					        </tr>
						</tbody>
					</table>
				</form>
		      </div>
		      <div id="deleteUsers" class="middle_right">
				
		      </div>
		      <div id="deleteNews" class="middle_right">
	                
            	</div>
		    </div>
		    <jsp:include page="/bottom.jsp" />
		</div>
	</body>
</html>
