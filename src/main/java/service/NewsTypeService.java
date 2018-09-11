package service;

import java.util.List;

import bean.NewsType;
import dao.NewsTypeDao;

public class NewsTypeService {
	public List<NewsType> listAll(){
		NewsTypeDao newsTypeDao=new NewsTypeDao();
		return newsTypeDao.listAll();
	}
}
