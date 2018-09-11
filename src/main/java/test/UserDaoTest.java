package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.PageInformation;

public class UserDaoTest {
	@Test
	public void testCount() throws Exception {
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao=new DatabaseDao();
		
		int count=userDao.count(databaseDao);
		databaseDao.close();
		//假设数据库中有4条数据，assertEquals是junit提供的用于判断 实际值 与 期望值 是否相等的断言，junit还有其他断言，参见附录2或百度
		assertEquals(count,4);				
	}
	
	@Test
	public void testListAll() throws Exception {
		UserDao userDao=new UserDao();
		List<User> userList=userDao.listAll();
		//假设数据库中有12条数据
		assertNotNull(userList);				
	}
	
	@Test
	public void testGetOnePage(){
		PageInformation pageInformation=new PageInformation();
		pageInformation.setTableName("user");
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		//假设有一页有两条数据
		UserDao userDao=new UserDao();
		List<User> userList=null;
		try {
			userList = userDao.getOnePage(pageInformation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new Integer(userList.size()),pageInformation.getPageSize());			
	}
	
	@Test
	public void testHasUser() throws Exception{
		User user=new User();
		user.setName("wuyaopeng");
		UserDao userDao=new UserDao();
		DatabaseDao databaseDao=new DatabaseDao();
		int result=userDao.hasUser(user, databaseDao);
		assertEquals(result,1);
	}
	
	@Test
	public void testLogin() throws SQLException, Exception{
		User user=new User();
		user.setName("wuyaopeng");
		user.setPassword("123456");
		UserDao userDao=new UserDao();
		int result=userDao.login(user);
		assertEquals(result,1);
	}
	
	@Test
	public void testChangePassword() throws Exception{
		User user=new User();
		user.setUserId(31);
		user.setName("wuyaopeng");
		UserDao userDao=new UserDao();
		int result=userDao.changePassword(user, "123456", "123456");
		assertEquals(result,1);
	}
	
	@Test
	public void testChangeEnable() throws Exception{
		String id="31";
		DatabaseDao databaseDao=new DatabaseDao();
		UserDao userDao=new UserDao();
		int result=userDao.changeEnable(id,databaseDao);
		assertEquals(result,1);
	}
}
