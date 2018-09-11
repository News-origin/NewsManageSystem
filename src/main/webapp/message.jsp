<%@ page language="java" import="java.util.*,tools.Message" pageEncoding="UTF-8"%>
<%	
	Message message=(Message) request.getAttribute("message");
	response.setHeader("refresh", "3;URL="+message.getRedirectUrl());
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><title>消息</title></head>
  
  <body>
	<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>${requestScope.message.message}  </td>
			</tr>
			<tr>
				<td>三秒后将跳转页面。<br>
				如果没有跳转,请按 <a href="${requestScope.message.redirectUrl} ">这里</a>!!!</td>
			</tr>
		</tbody>
	</table>    
  </body>
</html>
