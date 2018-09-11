package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//import tools.PageInformation;
import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.PageInformation;

public class UserService {
	public Integer count(){
		UserDao userDao=new UserDao();
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			return userDao.count(databaseDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//获取一页用户
	public List<User> getOnePage(PageInformation pageInformation){
		UserDao userDao=new UserDao();
		try {
			return userDao.getOnePage(pageInformation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<User> listAll() throws Exception{
		UserDao userDao=new UserDao();
		return userDao.listAll();
	}
	public Integer register(User user){
		int result=-1;//数据库操作失败	
		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			UserDao UserDao=new UserDao();
			
			if(UserDao.hasUser(user, databaseDao)==0){//没有同名用户，可以注册
				UserDao.register(user, databaseDao);
				System.out.println("注册成功！！！");
				databaseDao.close();
				return 1;	//成功
			}else{
				databaseDao.close();
				return 0;//失败，用户已存在
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer login(User user){
		int result=-2;	//数据库操作失败	
		try {
			UserDao UserDao=new UserDao();
			return UserDao.login(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return result;
	}	
	/*
	public List<User> getOnePage(PageInformation pageInformation) {	
		List<User> users=new ArrayList<User>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			UserDao userDao=new UserDao();		
			users=userDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return users;
	}

	public List<User> check(PageInformation pageInformation,String id) {	
		List<User> users=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			UserDao userDao=new UserDao();
			
			if(id!=null && !id.isEmpty())
				userDao.changeEnable(id,databaseDao);
			
			users=userDao.getOnePage(pageInformation,databaseDao);
			
		} catch (SQLException e) {
			users=null;
			e.printStackTrace();
		} catch (Exception e) {
			users=null;
			e.printStackTrace();
		}		
		return users;
	}
	
	//删除多条记录
	public List<User> deletes(PageInformation pageInformation) {	
		List<User> users=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			UserDao userDao=new UserDao();
			userDao.deletes(pageInformation.getIds(),databaseDao);
			pageInformation.setIds(null);
			users=userDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			users=null;
			e.printStackTrace();
		} catch (Exception e) {
			users=null;
			e.printStackTrace();
		}		
		return users;
	}
	*/
	
	//修改密码
	public Integer changePassword(User user, String originPassword,String newPassword) throws Exception {		
		UserDao userDao=new UserDao();
		return userDao.changePassword(user, originPassword, newPassword);
	}
	
	public void changePersonalData(User user){
		UserDao userDao=new UserDao();
		try {
			userDao.changePersonalData(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void changeEnable(int userId){
		UserDao userDao=new UserDao();
		userDao.changeEnable(userId);
	}
	public void deleteUser(int userId){
		UserDao userDao=new UserDao();
		userDao.deleteUser(userId);
	}
	//获取记录数
	public Integer count(DatabaseDao databaseDao){
		UserDao userDao=new UserDao();
		try {
			return userDao.count(databaseDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
