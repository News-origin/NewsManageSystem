// JavaScript Document

function E_valName(){
	var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
	var str1=document.getElementById("E_userName").value;//获取文本框的内容
	
	if(document.getElementById("E_userName").value==null || document.getElementById("E_userName").value==""){
		document.getElementById("E_userNameTip").innerHTML="*不能为空";
		return false;
	}else if(str1.length>=8 && pattern.test(str1)){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
		document.getElementById("E_userNameTip").innerHTML="ok";
		return true;
	}else{
		document.getElementById("E_userNameTip").innerHTML="*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_";
		return false;
	}
}

function E_valPassword(){
	var str = document.getElementById("E_password").value;
	var pattern=/^(\w){6,20}$/;
	
	if(document.getElementById("E_password").value==null || document.getElementById("E_password").value==""){
		document.getElementById("E_passwordTip").innerHTML="*不能为空";
		return false;
	}else if(str.match(pattern)==null){
		document.getElementById("E_passwordTip").innerHTML="*密码只能输入6-20个字母、数字、下划线";
		return false;
	}else{
		document.getElementById("E_passwordTip").innerHTML="ok";
		return true;
	}
}

function E_passwordSame(){
	var str = document.getElementById("E_password").value;
	if(document.getElementById("E_confirmPassword").value==null || document.getElementById("E_confirmPassword").value==""){
		document.getElementById("E_confirmPasswordTip").innerHTML="*不能为空";
		return false;
	}else if(document.getElementById("E_password").value==document.getElementById("E_confirmPassword").value){			
		document.getElementById("E_confirmPasswordTip").innerHTML="ok";
		return true ;
	}else{
		document.getElementById("E_confirmPasswordTip").innerHTML="*两次密码不一样";
		return false;
	}
			
}

function E_valCheckCode(){
	if(document.getElementById("E_checkCode").value==null || document.getElementById("E_checkCode").value==""){
		document.getElementById("E_checkCodeTip").innerHTML="*不能为空";
		return false;
	}
	else{
		document.getElementById("E_checkCodeTip").innerHTML="ok";
		return true;
	}
}

function valEmail(){
	var str=document.getElementById("email").value;
	var pattern = new RegExp("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$");//创建模式对象
	if(document.getElementById("email").value==null || document.getElementById("email").value==""){
		document.getElementById("emailTip").innerHTML="*不能为空";
		return false;
	}
	else if(pattern.test(str)){
		document.getElementById("emailTip").innerHTML="ok";
		return true;
	}
	else{
		document.getElementById("emailTip").innerHTML="*邮箱格式不对";
		return false;
	}
}

function P_valName(){
	var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
	var str1=document.getElementById("P_userName").value;//获取文本框的内容
	
	if(document.getElementById("P_userName").value==null || document.getElementById("P_userName").value==""){
		document.getElementById("P_userNameTip").innerHTML="*不能为空";
		return false;
	}else if(str1.length>=8 && pattern.test(str1)){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
		document.getElementById("P_userNameTip").innerHTML="ok";
		return true;
	}else{
		document.getElementById("P_userNameTip").innerHTML="*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_";
		return false;
	}
}

function P_valPassword(){
	var str = document.getElementById("P_password").value;
	var pattern=/^(\w){6,20}$/;
	
	if(document.getElementById("P_password").value==null || document.getElementById("P_password").value==""){
		document.getElementById("P_passwordTip").innerHTML="*不能为空";
		return false;
	}else if(str.match(pattern)==null){
		document.getElementById("P_passwordTip").innerHTML="*密码只能输入6-20个字母、数字、下划线";
		return false;
	}else{
		document.getElementById("P_passwordTip").innerHTML="ok";
		return true;
	}
}

function P_passwordSame(){
	var str = document.getElementById("P_password").value;
	if(document.getElementById("P_confirmPassword").value==null || document.getElementById("P_confirmPassword").value==""){
		document.getElementById("P_confirmPasswordTip").innerHTML="*不能为空";
		return false;
	}else if(document.getElementById("P_password").value==document.getElementById("P_confirmPassword").value){			
		document.getElementById("P_confirmPasswordTip").innerHTML="ok";
		return true ;
	}else{
		document.getElementById("P_confirmPasswordTip").innerHTML="*两次密码不一样";
		return false;
	}
			
}

function P_valCheckCode(){
	if(document.getElementById("P_checkCode").value==null || document.getElementById("P_checkCode").value==""){
		document.getElementById("P_checkCodeTip").innerHTML="*不能为空";
		return false;
	}
	else{
		document.getElementById("P_checkCodeTip").innerHTML="ok";
		return true;
	}
}

function valPhoneNum(){
	var str=$("#phoneNum").val();
	if($("#phoneNum").val()==null||$("#phoneNum").val()==""){
		$("#phoneTip").html("*不能为空");
		return false;
	}
	else if(str.length!=11){
		$("#phoneTip").html("*电话长度应该为11");
		return false;
	}
	else{
		$("#phoneTip").html("OK");
		return true;
	}
}

function valMessageCode(){
	var str=$("#messageCode").val();
	if(str==null||str==""){
		$("#messageCodeTip").html("*不能为空");
		return false;
	}
	else if(str.length!=6){
		$("#messageCodeTip").html("*短信验证码为6位数字");
		return false;
	}
	else{
		$("#messageCodeTip").html("OK");
		return true;
	}
}

$(document).ready(function(){
	$("#emailMethod").click(function(){
		$("#phoneRegisterForm").attr("class","noShowed");
		$("#emailRegisterForm").attr("class","showed");
	});
	$("#phoneMethod").click(function(){
		$("#phoneRegisterForm").attr("class","showed");
		$("#emailRegisterForm").attr("class","noShowed");
	});
	//提示用户输入
	$("#E_userNameTip").html("*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_");
	$("#E_passwordTip").html("*密码只能输入6-20个字母、数字、下划线");
	$("#E_confirmPasswordTip").html("再次输入密码");
	$("#emailTip").html("请按正确的邮箱格式输入邮箱");
	$("#E_checkCodeTip").html("请输入如图所示的验证码");
	//设置失去焦点后触发的函数
	$("#E_checkImg").click(function(){//为id是checkImg的标签绑定  鼠标单击事件  的处理函数
		//$(selector).attr(attribute,value)  设置被选元素的属性值
		//网址后加如一个随机值rand，表示了不同的网址，防止缓存导致的图片内容不变
		$("#E_checkImg").attr("src","/NewsManageSystem/servlet/ImageCheckCodeServlet?rand="+Math.random());
	});
	$("#E_userName").blur(function(){//为id是userName的标签绑定  失去焦点事件  的处理函数
		E_valName();
		});
	$("#E_password").blur(function(){
		E_valPassword();
		});
	$("#E_confirmPassword").blur(function(){
		E_passwordSame();
		});
	$("#email").blur(function(){
		valEmail();
		});
	$("#E_checkCode").blur(function(){
		E_valCheckCode();
	});
	//通过邮箱注册得验证，提交
	$("#E_submitForm").click(function(){
		var canSubmit=false;	//变量,用于验证是否可以提交数据到服务端
		canSubmit=E_valName();
		canSubmit=canSubmit&E_valPassword();
		canSubmit=canSubmit&E_passwordSame();
		canSubmit=canSubmit&valEmail();
		canSubmit=canSubmit&E_valCheckCode();
		if(canSubmit){
			$.ajax({
				url:"/NewsManageSystem/servlet/UserServlet?type=emailForRegister",
				type:"post",
				data : $("#emailRegisterForm").serialize(),//serialize():搜集表单元素数据，并形成查询字符串
				dataType:"json",	//从服务端返回的数据为json格式
				cache:false,
				error : function(textStatus, errorThrown) {//ajax请求失败时，将会执行此回调函数
					alert("系统ajax交互错误: " + textStatus);
				},
				success:function(data, textStatus){
					if(data.result==1){
						alert("注册邮件发送成功，请注意查收！！！");
					}
					else if(data.result==0){
						alert("邮件发送失败");
					}
					else if(data.result==-1){
						alert("该邮箱已被注册，请填写别的邮箱");
					}
					else if(data.result==-3){
						alert("对数据库操作失败，请联系客服修复");
					}
					else if(data.result==-4){
						alert("验证码错误");
					}
					else if(data.result==-5){
						alert("session中无验证码，请刷新页面重新注册");
					}
				}
			});
		}
		else{
			alert("请按要求填写信息");
		}
	});
	
	//提示用户输入
	$("#P_userNameTip").html("*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_");
	$("#phoneTip").html("输入长度为11的手机号");
	$("#P_passwordTip").html("*密码只能输入6-20个字母、数字、下划线");
	$("#P_confirmPasswordTip").html("再次输入密码");
	$("#P_checkCodeTip").html("请输入如图所示的验证码");
	$("#messageCodeTip").html("输入手机号，获取短信验证码");
	$("#P_checkImg").click(function(){//为id是checkImg的标签绑定  鼠标单击事件  的处理函数
		//$(selector).attr(attribute,value)  设置被选元素的属性值
		//网址后加如一个随机值rand，表示了不同的网址，防止缓存导致的图片内容不变
		$("#P_checkImg").attr("src","/NewsManageSystem/servlet/ImageCheckCodeServlet?rand="+Math.random());
	});
	//设置失去焦点后触发的函数
		$("#P_userName").blur(function(){//为id是userName的标签绑定  失去焦点事件  的处理函数
			P_valName();
			});
		$("#P_password").blur(function(){
			P_valPassword();
			});
		$("#P_confirmPassword").blur(function(){
			P_passwordSame();
			});
		$("#P_checkCode").blur(function(){
			P_valCheckCode();
		});
		$("#phoneNum").blur(function(){
			valPhoneNum();
		});
		$("#messageCode").blur(function(){
			valMessageCode();
		});
		$("#getMessageCode").click(function(){
			var canSubmit=false;
			canSubmit=valPhoneNum();
			var phoneNum=$("#phoneNum").val();
			if(canSubmit){
				$.ajax({
					url:"/NewsManageSystem/servlet/UserServlet?type=messageForRegister",
					type:"post",
					data : {"phoneNum":phoneNum},
					dataType:"json",	//从服务端返回的数据为json格式
					cache:false,
					error : function(textStatus, errorThrown) {//ajax请求失败时，将会执行此回调函数
						alert("系统ajax交互错误: " + textStatus);
					},
					success:function(data, textStatus){
						if(data.result==1){
							alert("短信验证码发送成功，请注意查收！！！");
						}
						else if(data.result==0){
							alert("短信发送失败");
						}
						else if(data.result==-1){
							alert("该手机已被注册，请填写别的邮箱");
						}
						else if(data.result==-3){
							alert("对数据库操作失败，请联系客服修复");
						}
					}
				});
			}
			else{
				alert("请填写正确的手机号");
			}
		});
		//通过邮箱注册得验证，提交
		$("#P_submitForm").click(function(){
			var canSubmit=false;	//变量,用于验证是否可以提交数据到服务端
			canSubmit=P_valName();
			canSubmit=canSubmit&valPhoneNum();
			canSubmit=canSubmit&valMessageCode();
			canSubmit=canSubmit&P_valPassword();
			canSubmit=canSubmit&P_passwordSame();
			canSubmit=canSubmit&P_valCheckCode();
			if(canSubmit){
				$.ajax({
					url:"/NewsManageSystem/servlet/UserServlet?type=phoneRegister",
					type:"post",
					data : $("#phoneRegisterForm").serialize(),//serialize():搜集表单元素数据，并形成查询字符串
					dataType:"json",	//从服务端返回的数据为json格式
					cache:false,
					error : function(textStatus, errorThrown) {//ajax请求失败时，将会执行此回调函数
						alert("系统ajax交互错误: " + textStatus);
					},
					success:function(data, textStatus){
						if(data.result==1){
							alert("注册成功");
						}
						else if(data.result==0){
							alert("注册失败");
						}
						else if(data.result==-1){
							alert("该用户已被注册，请填写别的用户名");
						}
						else if(data.result==-2){
							alert("短信验证码不正确");
						}
						else if(data.result==-3){
							alert("操作超时，请重新刷新页面和获取短信验证码再操作");
						}
						else if(data.result==-4){
							alert("验证码错误");
						}
						else if(data.result==-5){
							alert("session中无验证码，请刷新页面重新注册");
						}
						else if(data.result==-10){
							alert("该手机已被注册，请填写别的手机号");
						}
					}
				});
			}
			else{
				alert("请按要求填写信息");
			}
		});
});