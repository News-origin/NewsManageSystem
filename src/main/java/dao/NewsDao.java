package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;
import bean.News;
import bean.User;
import service.CommentService;
import tools.PageInformation;
import tools.Tool;

public class NewsDao {
	//获取一页新闻
	public List<News> getOnePage(PageInformation pageInformation) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		List<News> newses=new ArrayList<News>(); 
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			News news=new News();
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setAuthorId(databaseDao.getInt("authorId"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setCaption(databaseDao.getString("caption"));
			news.setContent(databaseDao.getString("content"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			news.setCheck(databaseDao.getBoolean("check"));
			news.setMaxFloor(databaseDao.getInt("maxFloor"));
			CommentService commentService=new CommentService();
			//根据newsId获取评论
			List<Comment> comments=commentService.getCommentsById(databaseDao.getInt("newsId"));
			news.setComments(comments);
			newses.add(news);
		}
		databaseDao.close();
		return newses;
	}
	//获取所有新闻
	public List<News> listAll(){
		try {
			List<News> allNews=new ArrayList<News>();
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="SELECT * FROM news,user WHERE news.authorId=user.userId";
			databaseDao.query(sql);
			News news=null;
			while(databaseDao.next()){
				news=new News();
				news.setNewsId(databaseDao.getInt("newsId"));
				news.setAuthorId(databaseDao.getInt("authorId"));
				news.setName(databaseDao.getString("name"));
				news.setNewsType(databaseDao.getString("newsType"));
				news.setCaption(databaseDao.getString("caption"));
				news.setContent(databaseDao.getString("content"));
				news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
				news.setPublishTime(databaseDao.getTimestamp("publishTime"));
				news.setCheck(databaseDao.getBoolean("check"));
				news.setMaxFloor(databaseDao.getInt("maxFloor"));
				CommentService commentService=new CommentService();
				//根据newsId获取评论
				List<Comment> comments=commentService.getCommentsById(databaseDao.getInt("newsId"));
				news.setComments(comments);
				allNews.add(news);
			}
			databaseDao.close();
			return allNews;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public News getNews(int newsId){
		try {
			News news=new News();
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="SELECT * FROM news,user WHERE news.authorId=user.userId AND news.newsId="+newsId;
			databaseDao.query(sql);
			if(databaseDao.next()){
				news.setNewsId(databaseDao.getInt("newsId"));
				news.setAuthorId(databaseDao.getInt("authorId"));
				news.setName(databaseDao.getString("name"));
				news.setNewsType(databaseDao.getString("newsType"));
				news.setCaption(databaseDao.getString("caption"));
				news.setContent(databaseDao.getString("content"));
				news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
				news.setPublishTime(databaseDao.getTimestamp("publishTime"));
				news.setCheck(databaseDao.getBoolean("check"));
				news.setMaxFloor(databaseDao.getInt("maxFloor"));
				CommentService commentService=new CommentService();
				//根据newsId获取评论
				List<Comment> comments=commentService.getCommentsById(databaseDao.getInt("newsId"));
				news.setComments(comments);
			}
			databaseDao.close();
			return news;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void addNews(News news,int authorId){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			String newsType=news.getNewsType();
			String caption=news.getCaption();
			String content=news.getContent();
			LocalDateTime newsTime=news.getNewsTime();
			String sql="INSERT INTO news (authorId,newsType,caption,content,newsTime) VALUES("+authorId+",'"+newsType+"','"+caption+"','"+content+"','"+newsTime+"')";
			System.out.println(sql);
			databaseDao.update(sql);
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editNews(News news,int authorId,int newsId){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			String newsType=news.getNewsType();
			String caption=news.getCaption();
			String content=news.getContent();
			LocalDateTime newsTime=news.getNewsTime();
			String sql="UPDATE news SET newsType='"+newsType+"' WHERE newsId="+newsId;
			databaseDao.update(sql);
			sql="UPDATE news SET caption='"+caption+"' WHERE newsId="+newsId;
			databaseDao.update(sql);
			sql="UPDATE news SET content='"+content+"' WHERE newsId="+newsId;
			databaseDao.update(sql);
			sql="UPDATE news SET newsTime="+newsTime+" WHERE newsId="+newsId;
			databaseDao.update(sql);
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteNews(int newsId){
		try {
			DatabaseDao databaseDao = new DatabaseDao();
			String sql="DELETE FROM news WHERE newsId="+newsId;
			databaseDao.update(sql);
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<News> searchNews(String keyWord) throws Exception{
		DatabaseDao databaseDao = new DatabaseDao();
		String sql;
		if(keyWord==null||"".equals(keyWord)){
			sql="SELECT * FROM news";
		}
		else{
			sql="SELECT * FROM news WHERE caption LIKE '%"+keyWord+"%'";
		}
		databaseDao.query(sql);
		List<News> searchNews=new ArrayList<News>();
		News news=null;
		while(databaseDao.next()){
			news=new News();
			news.setNewsId(databaseDao.getInt("newsId"));
			news.setNewsType(databaseDao.getString("newsType"));
			news.setCaption(databaseDao.getString("caption"));
			news.setContent(databaseDao.getString("content"));
			news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
			news.setPublishTime(databaseDao.getTimestamp("publishTime"));
			news.setMaxFloor(databaseDao.getInt("maxFloor"));
			CommentService commentService=new CommentService();
			//根据newsId获取评论
			List<Comment> comments=commentService.getCommentsById(databaseDao.getInt("newsId"));
			news.setComments(comments);
			searchNews.add(news);
		}
		return searchNews;
	}
}
