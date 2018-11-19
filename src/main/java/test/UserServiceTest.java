package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import service.UserService;
import tools.PageInformation;

public class UserServiceTest {
	/*
	static protected UserService userService;
	
	@BeforeClass
	static public void beforeClass() throws Exception{
		userService=new UserService();
	}
	
	@AfterClass
	static public void afterClass(){
		userService=null;
	}

	@Test
	public void testCount(){
		int count=userService.count();
		assertEquals(count,4);	
	}

	@Test
	public void testListAll() throws Exception{
		List<User> userList=null;
		userList=userService.listAll();
		assertEquals(userList.size(),4);
	}
	
	@Test
	public void testGetOnePage(){
		PageInformation pageInformation=new PageInformation();
		pageInformation.setTableName("user");
		pageInformation.setPage(1);
		pageInformation.setPageSize(2);
		//假设有一页有两条数据
		List<User> userList=null;
		userList=userService.getOnePage(pageInformation);
		assertEquals(new Integer(userList.size()),pageInformation.getPageSize());
	}
	
	@Test
	public void testLogin(){
		User user=new User();
		user.setName("wuyaopeng");
		user.setPassword("123456");
		int result=userService.login(user);
		assertEquals(result,1);
	}
	
	@Test
	public void testChangePassword() throws Exception{
		User user=new User();
		user.setUserId(31);
		user.setName("wuyaopeng");
		int result=userService.changePassword(user, "123456", "123456");
		assertEquals(result,1);
	}
	*/
}
