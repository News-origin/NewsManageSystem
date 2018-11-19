// JavaScript Document

function valName(){
	//var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
	var str1=document.getElementById("userName").value;//获取文本框的内容
	
	if(document.getElementById("userName").value==null || document.getElementById("userName").value==""){
		document.getElementById("userNameTip").innerHTML="*不能为空";
		return false;
	}else if(str1.length>=8){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
		document.getElementById("userNameTip").innerHTML="ok";
		return true;
	}else{
		document.getElementById("userNameTip").innerHTML="*用户名至少需要8个字符";
		return false;
	}
}

function valPassword(){
	var str = document.getElementById("password").value;
	var pattern=/^(\w){6,20}$/;
	
	if(document.getElementById("password").value==null || document.getElementById("password").value==""){
		document.getElementById("passwordTip").innerHTML="*不能为空";
		return false;
	}else if(str.match(pattern)==null){
		document.getElementById("passwordTip").innerHTML="*密码只能输入6-20个字母、数字、下划线";
		return false;
	}else{
		document.getElementById("passwordTip").innerHTML="ok";
		return true;
	}
}

function valCheckCode(){
	if(document.getElementById("checkCode").value==null || document.getElementById("checkCode").value==""){
		document.getElementById("checkCodeTip").innerHTML="*不能为空";
		return false;
	}
	else{
		document.getElementById("checkCodeTip").innerHTML="ok";
		return true;
	}
}

function getNewCheckCode(){
	document.getElementById("checkImg").src="/NewsManageSystem/servlet/ImageCheckCodeServlet?"+Math.random();
}

$(document).ready(function(){
	//设置失去焦点事件的处理函数
	$("#userName").blur(function(){
		valName();
	});
	$("#password").blur(function(){
		valPassword();
	});
	$("#checkCode").blur(function(){
		valCheckCode();
	});
	//设置点击事件的处理函数
	$("#checkImg").click(function(){
		$("#checkImg").attr("src","/NewsManageSystem/servlet/ImageCheckCodeServlet?rand="+Math.random());
	});
	$("#submitForm").click(function(){
		var canSubmit=false;
		canSubmit=valName();
		canSubmit=canSubmit&valPassword();
		canSubmit=canSubmit&valCheckCode();
		if(canSubmit){
			$.ajax({
				url:"/NewsManageSystem/servlet/UserServlet?type=login",
				type:"post",
				data:$("#loginForm").serialize(),
				dataType:"json",
				cache:false,
				error:function(textStatus, errorThrown){
					alert("系统ajax交互错误: " + textStatus);
				},
				success:function(data,textStatus){
					if(data.result==1){
						window.location.href = '/NewsManageSystem/index.jsp';
					}
					else if(data.result==0){
						alert("用户/电子邮箱/手机号不存在");
					}
					else if(data.result==-1){
						alert("密码错误");
					}
					else if(data.result==-2){
						alert("该用户被禁用，请联系客服解封");
					}
					else if(data.result==-3){
						alert("对数据库操作失败");
					}
					else if(data.result==-5){
						alert("验证码错误");
					}
					else if(data.result==-4){
						alert("session中没有验证码，请刷新页面重新操作");
					}
				}
			});
		}
		else{
			alert("请按提示信息正确输入信息");
		}
	});
});