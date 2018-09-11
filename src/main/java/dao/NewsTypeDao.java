package dao;

import java.util.ArrayList;
import java.util.List;

import bean.NewsType;

public class NewsTypeDao {
	public List<NewsType> listAll(){
		try {
			List<NewsType> newsTypes=new ArrayList<NewsType>();
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="SELECT * FROM newstype";
			databaseDao.query(sql);
			NewsType newsType=null;
			while(databaseDao.next()){
				newsType=new NewsType();
				newsType.setNewsTypeId(databaseDao.getInt("newsTypeId"));
				newsType.setNewsType(databaseDao.getString("newsType"));
				newsTypes.add(newsType);
			}
			databaseDao.close();
			return newsTypes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
