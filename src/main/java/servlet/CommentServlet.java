package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import bean.Comment;
import bean.News;
import bean.User;
import service.CommentService;
import service.NewsService;

public class CommentServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		if("writeComment".equals(type)){
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			Comment comment=new Comment();
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			int userId=user.getUserId();
			String content=request.getParameter("content");
			comment.setNewsId(newsId);
			comment.setUserId(userId);
			comment.setContent(content);
			CommentService commentService=new CommentService();
			commentService.writeComment(comment);
			//重新展示新闻
			NewsService newsService=new NewsService();
			News news=newsService.getNews(newsId);
			request.setAttribute("news", news);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/showNews.jsp");
			dispatcher .forward(request, response);
		}
		else if("goToReply".equals(type)){
			int priorCommentId=Integer.parseInt(request.getParameter("priorCommentId"));
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			int userId=user.getUserId();
			request.setAttribute("priorCommentId", priorCommentId);
			request.setAttribute("newsId", newsId);
			request.setAttribute("userId", userId);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/replyComment.jsp");
			dispatcher .forward(request, response);
		}
		else if("replyComment".equals(type)){
			Comment comment=new Comment();
			int priorCommentId=Integer.parseInt(request.getParameter("priorCommentId"));
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			int userId=Integer.parseInt(request.getParameter("userId"));
			String content=request.getParameter("content");
			comment.setNewsId(newsId);
			comment.setUserId(userId);
			comment.setContent(content);
			comment.setPriorCommentId(priorCommentId);
			CommentService commentService=new CommentService();
			commentService.replyComment(comment);
			//重新展示新闻
			NewsService newsService=new NewsService();
			News news=newsService.getNews(newsId);
			request.setAttribute("news", news);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/showNews.jsp");
			dispatcher .forward(request, response);
		}
		else if("deleteComment".equals(type)){
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			int commentId=Integer.parseInt(request.getParameter("commentId"));
			CommentService commentService=new CommentService();
			commentService.deleteComment(commentId);
			//重新展示新闻
			NewsService newsService=new NewsService();
			News news=newsService.getNews(newsId);
			request.setAttribute("news", news);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/showNews.jsp");
			dispatcher .forward(request, response);
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}

}
