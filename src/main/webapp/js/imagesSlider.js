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
			nowLink.innerHTML="<a href='http://www.xinhuanet.com/politics/2018lh/2018-03/17/c_1122552094_2.htm'>习近平当选国家主席、中央军委主席</a>";
			nowImage.setAttribute("src","images/middle1.jpg");break;
		case 1:
			nowLink.innerHTML="<a href='http://top.china.com.cn/2018-03/20/content_50724315.htm'>NBA常规赛：火箭胜森林狼</a>";
			nowImage.setAttribute("src","images/middle2.jpg");break;
		case 2:
			nowLink.innerHTML="<a href='http://www.tmtpost.com/3139550.html'>Uber路测致死事故，过错在谁？ </a>";
			nowImage.setAttribute("src","images/middle3.jpg");break;
		case 3:
			nowLink.innerHTML="<a href='http://news.cheshi.com/20180320/2729321.shtml?from=baidu_news'>颜值、操控两大“杀器” 十代雅阁会动谁的奶酪？</a>";
			nowImage.setAttribute("src","images/middle4.jpg");break;
		case 4:
			nowLink.innerHTML="<a href='http://www.sohu.com/a/225876022_119598'>玩家都懵了！魔兽作为暴雪最重要游戏，居然每个版本换不同团队做 </a>";
			nowImage.setAttribute("src","images/middle5.jpg");break;
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
			nowLink.innerHTML="<a href='http://www.xinhuanet.com/politics/2018lh/2018-03/17/c_1122552094_2.htm'>习近平当选国家主席、中央军委主席</a>";
			nowImage.setAttribute("src","images/middle1.jpg");break;
		case 1:
			nowLink.innerHTML="<a href='http://top.china.com.cn/2018-03/20/content_50724315.htm'>NBA常规赛：火箭胜森林狼</a>";
			nowImage.setAttribute("src","images/middle2.jpg");break;
		case 2:
			nowLink.innerHTML="<a href='http://www.tmtpost.com/3139550.html'>Uber路测致死事故，过错在谁？ </a>";
			nowImage.setAttribute("src","images/middle3.jpg");break;
		case 3:
			nowLink.innerHTML="<a href='http://news.cheshi.com/20180320/2729321.shtml?from=baidu_news'>颜值、操控两大“杀器” 十代雅阁会动谁的奶酪？</a>";
			nowImage.setAttribute("src","images/middle4.jpg");break;
		case 4:
			nowLink.innerHTML="<a href='http://www.sohu.com/a/225876022_119598'>玩家都懵了！魔兽作为暴雪最重要游戏，居然每个版本换不同团队做 </a>";
			nowImage.setAttribute("src","images/middle5.jpg");break;
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