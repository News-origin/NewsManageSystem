package bean;

import java.sql.Timestamp;

public class Comment {
	public int commentId;
	public int newsId;
	public int userId;
	public String userName;	//评论的用户名
	public String content;
	public int floor;	//楼层数
	public int appreciate;	//点赞数
	public Timestamp commentDate;	//评论时间（默认是插入表中的时间）
	public int priorCommentId;	//回复的评论的编号
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getAppreciate() {
		return appreciate;
	}
	public void setAppreciate(int appreciate) {
		this.appreciate = appreciate;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public int getPriorCommentId() {
		return priorCommentId;
	}
	public void setPriorCommentId(int priorCommentId) {
		this.priorCommentId = priorCommentId;
	}
}