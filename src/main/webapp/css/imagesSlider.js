// JavaScript Document
var t;
 var nowImage=0;	//储存现在的img对象
 var nowImage1=0;	//储存现在第几张图
t=setTimeout(changeImages,2000);	//这句在寒假时改过，原本为changeImages(0)
 function toRearImage(){
	nowImage1++;
	nowImage1%=5;
	nowImage=document.getElementById("middle_left_img");
	nowLink=document.getElementById("link");
	switch(nowImage1){
		case 0:
			nowLink.innerHTML="<a href='#'>省领导率林慰问团到我市送温暖 林应武参加慰问</a>";
			nowImage.setAttribute("src","../images/middle1.jpg");break;
		case 1:
			nowLink.innerHTML="<a href='#'>刘毅慰问困难党员、困难职工及企业 致新春祝福</a>";
			nowImage.setAttribute("src","../images/middle2.jpg");break;
		case 2:
			nowLink.innerHTML="<a href='#'>林应武检查安全生产和春运工作 要确保安全有序</a>";
			nowImage.setAttribute("src","../images/middle3.jpg");break;
		case 3:
			nowLink.innerHTML="<a href='#'>林应武慰问高层次人才</a>";
			nowImage.setAttribute("src","../images/middle4.jpg");break;
		case 4:
			nowLink.innerHTML="<a href='#'>十三届市纪委三次全会召开 林应武讲话 刘毅出席</a>";
			nowImage.setAttribute("src","../images/middle5.jpg");break;
	}
}
function toPreImage(){
	if(nowImage1==0)
	{
		nowImage1=4;	
	}
	else
	{
		nowImage1--;	
	}
	nowImage=document.getElementById("middle_left_img");
	nowLink=document.getElementById("link");
	switch(nowImage1){
		case 0:
			nowLink.innerHTML="<a href='#'>省领导率林慰问团到我市送温暖 林应武参加慰问</a>";
			nowImage.setAttribute("src","../images/middle1.jpg");break;
		case 1:
			nowLink.innerHTML="<a href='#'>刘毅慰问困难党员、困难职工及企业 致新春祝福</a>";
			nowImage.setAttribute("src","../images/middle2.jpg");break;
		case 2:
			nowLink.innerHTML="<a href='#'>林应武检查安全生产和春运工作 要确保安全有序</a>";
			nowImage.setAttribute("src","../images/middle3.jpg");break;
		case 3:
			nowLink.innerHTML="<a href='#'>林应武慰问高层次人才</a>";
			nowImage.setAttribute("src","../images/middle4.jpg");break;
		case 4:
			nowLink.innerHTML="<a href='#'>十三届市纪委三次全会召开 林应武讲话 刘毅出席</a>";
			nowImage.setAttribute("src","../images/middle5.jpg");break;
	}
}
function PreimageClick(){
		clearTimeout(t);
		toPreImage();
		t=setTimeout(changeImages,2000);
}
function RearimageClick(){
		clearTimeout(t);
		toRearImage();
		t=setTimeout(changeImages,2000);
}
/*	function imageMoveOut(nowImage1){
		t=setTimeout(changeImages,2000,nowImage1);
}
*/
function changeImages(nowImage1){
		toRearImage();
		t=setTimeout(changeImages,2000);
}