package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.News;
import bean.NewsType;
import bean.User;
import service.NewsService;
import service.NewsTypeService;
import tools.PageInformation;
import tools.Tool;

public class NewsServlet extends HttpServlet {

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
		//根据newsId展示某一条新闻
		if("showANews".equals(type)){
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			NewsService newsService=new NewsService();
			News news=newsService.getNews(newsId);
			request.setAttribute("news", news);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/showNews.jsp");
			dispatcher .forward(request, response);
		}
		//添加一条新闻
		else if("addNews".equals(type)){
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			int authorId=user.getUserId();
			String caption=request.getParameter("caption");
			String newsType=request.getParameter("newsType");
			String content=request.getParameter("editorValue");
			String a=request.getParameter("newsTime");
			//DateTimeFormatter用于将字符串解析成LocalDateTime类型的对象，或者反之
			LocalDateTime localDateTime=LocalDateTime.parse(a, DateTimeFormatter.ISO_LOCAL_DATE_TIME);		
			News news=new News();
			news.setAuthorId(authorId);
			news.setCaption(caption);
			news.setNewsType(newsType);
			news.setContent(content);
			news.setNewsTime(localDateTime);
			NewsService newsService=new NewsService();
			newsService.addNews(news, authorId);
			response.sendRedirect("/NewsManageSystem/index.jsp");
		}
		//从数据库种获取所有新闻并保存在context中
		else if("getAllNews".equals(type)){
			ServletContext context=this.getServletContext();
			List<News> allNews=new ArrayList<News>();
			NewsService newsService=new NewsService();
			allNews=newsService.listAll();
			context.setAttribute("allNews", allNews);
			//response.sendRedirect("/NewsManageSystem/newsList.jsp");
		}
		//获取一页新闻
		else if("getOnePageNews".equals(type)){
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			NewsService newsService=new NewsService();
			List<News> newses=newsService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			getServletContext().getRequestDispatcher("/manageNews.jsp").forward(request,response);
		}
		//获取所有新闻类型
		else if("getNewsType".equals(type)){
			NewsTypeService newsTypeService=new NewsTypeService();
			List<NewsType> newsTypes=newsTypeService.listAll();
			ServletContext context=this.getServletContext();
			context.setAttribute("newsTypes", newsTypes);
		}
		//获取需要被修改的新闻并在editNews.jsp中显示
		else if("editNews".equals(type)){
			NewsService newsService=new NewsService();
			News editNews=newsService.getNews(Integer.parseInt(request.getParameter("newsId")));
			NewsTypeService newsTypeService=new NewsTypeService();
			List<NewsType> newsTypes=newsTypeService.listAll();
			request.setAttribute("editNews", editNews);
			request.setAttribute("newsTypes", newsTypes);
			getServletContext().getRequestDispatcher("/editNews.jsp").forward(request,response);
		}
		else if("newsAfterEdit".equals(type)){
			String caption=request.getParameter("caption");
			String newsType=request.getParameter("newsType");
			String content=request.getParameter("editorValue");
			String a=request.getParameter("newsTime");
			//DateTimeFormatter用于将字符串解析成LocalDateTime类型的对象，或者反之
			LocalDateTime localDateTime=LocalDateTime.parse(a, DateTimeFormatter.ISO_LOCAL_DATE_TIME);		
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			int authorId=user.getUserId();
			int newsId=Integer.parseInt(request.getParameter("newsId"));	//被编辑新闻编号
			News news=new News();
			news.setNewsId(newsId);
			news.setAuthorId(authorId);
			news.setCaption(caption);
			news.setNewsType(newsType);
			news.setContent(content);
			news.setNewsTime(localDateTime);
			NewsService newsService=new NewsService();
			newsService.editNews(news, authorId, newsId);
			response.sendRedirect("/NewsManageSystem/index.jsp");
		}
		else if("deleteNews".equals(type)){
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			NewsService newsService=new NewsService();
			newsService.deleteNews(newsId);
			//重新获取一页新闻
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("news", request, pageInformation);
			newsService=new NewsService();
			List<News> newses=newsService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("newses", newses);
			getServletContext().getRequestDispatcher("/manageNews.jsp").forward(request,response);
		}
		else if("searchNews".equals(type)){
			String keyWord=request.getParameter("keyWord");
			List<News> searchNews=new ArrayList<News>();
			NewsService newsService=new NewsService();
			searchNews=newsService.searchNews(keyWord);
			request.setAttribute("searchNews", searchNews);
			getServletContext().getRequestDispatcher("/searchNews.jsp").forward(request,response);
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
