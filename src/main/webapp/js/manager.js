// JavaScript Document
function display(x){
	var div=document.getElementsByClassName("middle_right");
	for(var i=0;i<div.length;i++){
		div[i].setAttribute("class","middle_right");
	}
	div[x].setAttribute("class","middle_right middle_right_show");
}

function valOriginPassword(){
	var str = document.getElementById("originPassword").value;
	var pattern=/^(\w){6,20}$/;
	
	if(document.getElementById("originPassword").value==null || document.getElementById("originPassword").value==""){
		document.getElementById("originPasswordTip").innerHTML="*不能为空";
		return false;
	}else if(str.match(pattern)==null){
		document.getElementById("originPasswordTip").innerHTML="*密码只能输入6-20个字母、数字、下划线";
		return false;
	}else{
		document.getElementById("originPasswordTip").innerHTML="ok";
		return true;
	}
}

function valNewPassword(){
	var str = document.getElementById("newPassword").value;
	var pattern=/^(\w){6,20}$/;
	
	if(document.getElementById("newPassword").value==null || document.getElementById("newPassword").value==""){
		document.getElementById("newPasswordTip").innerHTML="*不能为空";
		return false;
	}else if(str.match(pattern)==null){
		document.getElementById("newPasswordTip").innerHTML="*密码只能输入6-20个字母、数字、下划线";
		return false;
	}else{
		document.getElementById("newPasswordTip").innerHTML="ok";
		return true;
	}
}

function newPasswordSame(){
	var str = document.getElementById("newPassword").value;
	if(document.getElementById("confirmPassword").value==null || document.getElementById("confirmPassword").value==""){
		document.getElementById("confirmPasswordTip").innerHTML="*不能为空";
		return false;
	}else if(document.getElementById("newPassword").value==document.getElementById("confirmPassword").value){			
		document.getElementById("confirmPasswordTip").innerHTML="ok";
		return true ;
	}else{
		document.getElementById("confirmPasswordTip").innerHTML="*密码不一致";
		return false;
	}
			
}
	
function submit1(){
	result1=valOriginPassword();
	result1=valNewPassword() && result1;
	result1=newPasswordSame() && result1;
	if( result1)
		return true;//�ύ
	else 
		return false;//��ֹ�ύ
}