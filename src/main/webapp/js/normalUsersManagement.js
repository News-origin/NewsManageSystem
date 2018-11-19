// JavaScript Document
$(document).ready(function(){
	$("#toShowPersonalData").click(function(){
		$("#middle_right").html("");
		$("#middle_right").load("/NewsManageSystem/showPersonalData.jsp");
	});
	$("#toChangePersonalData").click(function(){
		$("#middle_right").html("");
		$("#middle_right").load("/NewsManageSystem/changePersonalData.jsp");
	});
	$("#toChangePassword").click(function(){
		$("#middle_right").html("");
		$("#middle_right").load("/NewsManageSystem/changePassword.jsp");
	});
});