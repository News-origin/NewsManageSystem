package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Comment;

public class CommentDao {
	/*
	public List<Comment> listAll(){
		DatabaseDao databaseDao;
		try {
			databaseDao = new DatabaseDao();
			String sql="SELECT * FROM news,user,comment WHERE news.newsId=comment.newsId AND user.userId=comment.userId";
			databaseDao.query(sql);
			List<Comment> comments=new ArrayList<Comment>();
			Comment comment=null;
			while(databaseDao.next()){
				comment=new Comment();
				comment.setCommentId(databaseDao.getInt("commentId"));
				comment.setNewsId(databaseDao.getInt("newsId"));
				comment.setUserId(databaseDao.getInt("userId"));
				comment.setUserName(databaseDao.getString("name"));
				comment.setContent(databaseDao.getString("content"));
				comment.setFloor(databaseDao.getInt("floor"));
				comment.setAppreciate(databaseDao.getInt("appreciate"));
				comment.setCommentDate(databaseDao.getTimestamp("commentDate"));
				comment.setPriorCommentId(databaseDao.getInt("priorCommentId"));
				comments.add(comment);
			}
			return comments;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	public List<Comment> getCommentsById(int newsId){

		DatabaseDao databaseDao;
		try {
			databaseDao = new DatabaseDao();
			String sql="SELECT * FROM news,user,comment WHERE news.newsId=comment.newsId AND user.userId=comment.userId AND comment.newsId="+newsId;
			databaseDao.query(sql);
			List<Comment> comments=new ArrayList<Comment>();
			Comment comment=null;
			while(databaseDao.next()){
				comment=new Comment();
				comment.setCommentId(databaseDao.getInt("commentId"));
				comment.setNewsId(databaseDao.getInt("newsId"));
				comment.setUserId(databaseDao.getInt("userId"));
				comment.setUserName(databaseDao.getString("name"));
				comment.setContent(databaseDao.getString("comment.content"));
				comment.setFloor(databaseDao.getInt("floor"));
				comment.setAppreciate(databaseDao.getInt("appreciate"));
				comment.setCommentDate(databaseDao.getTimestamp("commentDate"));
				comment.setPriorCommentId(databaseDao.getInt("priorCommentId"));
				comments.add(comment);
			}
			databaseDao.close();
			return comments;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void writeComment(Comment comment){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			//先获取已有多少条评论
			int maxFloor;
			String sql="SELECT maxFloor FROM news WHERE newsId="+comment.getNewsId();
			databaseDao.query(sql);
			if(databaseDao.next()){
				maxFloor=databaseDao.getInt("maxFloor");
				maxFloor+=1;
				int newsId=comment.getNewsId();
				int userId=comment.getUserId();
				String content=comment.getContent();
				sql="INSERT INTO comment(newsId,userId,content,floor) VALUES("+newsId+","+userId+",'"+content+"',"+maxFloor+")";
				//System.out.println(sql);
				databaseDao.update(sql);
				sql="UPDATE news SET maxFloor="+maxFloor+" WHERE newsId="+comment.getNewsId();
				//System.out.println(sql);
				databaseDao.update(sql);
			}
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void replyComment(Comment comment){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			//先获取已有多少条评论
			int maxFloor;
			String sql="SELECT maxFloor FROM news WHERE newsId="+comment.getNewsId();
			databaseDao.query(sql);
			if(databaseDao.next()){
				maxFloor=databaseDao.getInt("maxFloor");
				maxFloor+=1;
				int newsId=comment.getNewsId();
				int userId=comment.getUserId();
				String content=comment.getContent();
				int priorCommentId=comment.getPriorCommentId();
				sql="INSERT INTO comment(newsId,userId,content,floor,priorCommentId) VALUES("+newsId+","+userId+",'"+content+"',"+maxFloor+","+priorCommentId+")";
				//System.out.println(sql);
				databaseDao.update(sql);
				sql="UPDATE news SET maxFloor="+maxFloor+" WHERE newsId="+comment.getNewsId();
				//System.out.println(sql);
				databaseDao.update(sql);
			}
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteComment(int commentId) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="DELETE FROM comment WHERE commentId="+commentId;
		databaseDao.update(sql);
		databaseDao.close();
	}
}
