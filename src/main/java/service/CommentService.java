package service;

import java.util.List;

import bean.Comment;
import dao.CommentDao;

public class CommentService {
	/*
	public List<Comment> listAll(){
		CommentDao commentDao=new CommentDao();
		return commentDao.listAll();
	}
	*/
	public List<Comment> getCommentsById(int newsId){
		CommentDao commentDao=new CommentDao();
		return commentDao.getCommentsById(newsId);
	}
	public void writeComment(Comment comment){
		CommentDao commentDao=new CommentDao();
		commentDao.writeComment(comment);
	}
	public void replyComment(Comment comment){
		CommentDao commentDao=new CommentDao();
		commentDao.replyComment(comment);
	}
	public void deleteComment(int commentId){
		CommentDao commentDao=new CommentDao();
		try {
			commentDao.deleteComment(commentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
