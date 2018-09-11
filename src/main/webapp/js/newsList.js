// JavaScript Document
function changeNewsBoard(x){
	var divObjs=document.getElementsByClassName("middle_right");
	for(var i=0;i<divObjs.length;i++){
		divObjs[i].setAttribute("class","middle_right");
	}
	divObjs[x].setAttribute("class","middle_right middle_right_show");
}