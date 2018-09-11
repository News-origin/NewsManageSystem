// JavaScript Document
/*
function loginCheck(){
	var x=checkUsersName() && checkUsersPassword();
	if(x){
		alert("登录成功！！！");
		return true;
	}
	else{
		alert("请正确输入账号和密码");
		return false;
	}
}

function checkUsersName() {
	var cun = true;
	var usersNameTip=document.getElementById("usersNameTip");
 	var usersName = document.getElementById("usersName").value;
 	if (usersName.length==0) {
 		cun = false;
		usersNameTip.setAttribute("class","noShowed");
 	}else{
		usersNameTip.setAttribute("class","showed");
	}
 	return cun;
}

function checkUsersPassword() {
	var cup = true;
	var passwordTip=document.getElementById("passwordTip");
 	var password = document.getElementById("password").value;
 	if (password.length==0) {
 		cup = false;
		passwordTip.setAttribute("class","showed");
 	}else{
		passwordTip.setAttribute("class","noShowed");
	}
 	return cup;
}
*/
function valName(){
	var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
	var str1=document.getElementById("usersName").value;//获取文本框的内容
	
	if(document.getElementById("usersName").value==null || document.getElementById("usersName").value==""){
		document.getElementById("usersNameTip").innerHTML="*不能为空";
		return false;
	}else if(str1.length>=8 && pattern.test(str1)){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
		document.getElementById("usersNameTip").innerHTML="ok";
		return true;
	}else{
		document.getElementById("usersNameTip").innerHTML="*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_";
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

	
function submit1(){
	result1=valName();
	result1=valPassword() && result1;
	if( result1)
		return true;//提交
	else 
		return false;//阻止提交
}