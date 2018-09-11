package service;
import java.util.List;

import bean.News;
import dao.NewsDao;
import tools.PageInformation;

public class NewsService {
	public List<News> searchNews(String keyWord){
		NewsDao newsDao=new NewsDao();
		try {
			return newsDao.searchNews(keyWord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//获取一页新闻
	public List<News> getOnePage(PageInformation pageInformation){
		NewsDao newsDao=new NewsDao();
		try {
			return newsDao.getOnePage(pageInformation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//获取所有新闻
	public List<News> listAll(){
		NewsDao newsDao=new NewsDao();
		return newsDao.listAll();
	}
	//根据新闻编号获取某条新闻
	public News getNews(int newsId){
		NewsDao newsDao=new NewsDao();
		return newsDao.getNews(newsId);
	}
	public void addNews(News news,int authorId){
		NewsDao newsDao=new NewsDao();
		newsDao.addNews(news, authorId);
	}
	public void editNews(News news,int authorId,int newsId){
		NewsDao newsDao=new NewsDao();
		newsDao.editNews(news, authorId, newsId);
	}
	public void deleteNews(int newsId){
		NewsDao newsDao=new NewsDao();
		newsDao.deleteNews(newsId);
	}
}
