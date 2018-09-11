package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class News {
	private int newsId;	//新闻编号
	private int authorId;	//小编编号
	private String name;	//小编名字
	private String newsType;	//新闻类型
	private String caption;	//新闻标题
	private String content;	//新闻内容
	private LocalDateTime newsTime;	//新闻发生时间
	private Timestamp publishTime;	//新闻发布时间
	private boolean check;	//是否可被阅读
	private int maxFloor;	//最高楼层
	private List<Comment> comments;	//该新闻的评论
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(LocalDateTime localDateTime) {
		this.newsTime = localDateTime;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public int getMaxFloor() {
		return maxFloor;
	}
	public void setMaxFloor(int maxFloor) {
		this.maxFloor = maxFloor;
	}
}
