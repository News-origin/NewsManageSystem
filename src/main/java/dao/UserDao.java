package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
import tools.PageInformation;
import tools.Tool;
*/
import bean.User;
import tools.PageInformation;
import tools.Tool;

public class UserDao {
	//获取一页用户
	public List<User> getOnePage(PageInformation pageInformation) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		List<User> users=new ArrayList<User>(); 
		String sqlCount=Tool.getSql(pageInformation,"count");
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		
		String sqlSelect=Tool.getSql(pageInformation,"select");
		databaseDao.query(sqlSelect);
		while (databaseDao.next()) {
			User user=new User();
			user.setEnable(databaseDao.getString("enable"));
			user.setName(databaseDao.getString("name"));
			user.setPassword(databaseDao.getString("password"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setType(databaseDao.getString("type"));
			user.setUserId(databaseDao.getInt("userId"));
			users.add(user);	
		}
		databaseDao.close();
		return users;
	}
	//获取所有用户
	public List<User> listAll() throws Exception{
		try {
			List<User> users=new ArrayList<User>();
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="SELECT * FROM user,userinformation WHERE user.userId=userinformation.userId";
			databaseDao.query(sql);
			User user=null;
			while(databaseDao.next()){
				user=new User();
				user.setUserId(databaseDao.getInt("userId"));
				user.setType(databaseDao.getString("type"));
				user.setName(databaseDao.getString("name"));
				user.setPassword(databaseDao.getString("password"));
				user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
				user.setEnable(databaseDao.getString("enable"));
				user.setSex(databaseDao.getString("sex"));
				user.setHobby(databaseDao.getString("hobby"));
				user.setImgUrl(databaseDao.getString("imgUrl"));
				users.add(user);
			}
			databaseDao.close();
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//是否已存在这个用户名
	public Integer hasUser(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where name='"+user.getName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return 1;
		}
		return 0;
	}
	
	//注册
	public boolean emailRegister(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		boolean returnValue=false;
		String sql="insert into user(type,name,email,password,salt,enable) values('"+
				user.getType()+"','"+user.getName()+"','"+user.getEmail()+"','"+
				user.getPassword()+"','"+user.getSalt()+"','"+user.getEnable()+"')";
		if(databaseDao.update(sql)==1){
			//如果成功插入user表，再进行以下操作
			sql="SELECT userId FROM user WHERE name LIKE '"+user.getName()+"'";
			databaseDao.query(sql);
			int userId = -1;	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
			if(databaseDao.next()){
				userId=databaseDao.getInt("userId");	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
			}
			sql="insert into userinformation(userId) values("+userId+")";	//插入该用户的信息
			if(databaseDao.update(sql)==1){
				//如果成功插入userinformation表，再进行以下操作
				returnValue=true;
			}
		}
		databaseDao.close();
		return returnValue;
	}
	//更改密码
	public void changePassword(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		//先检查原密码是否正确
		String sql="UPDATE user"+" SET password='"+user.getPassword()+"',salt='"+user.getSalt()+"' WHERE user.userId="+user.getUserId();
		databaseDao.update(sql);
		databaseDao.close();
	}
	
	public void changePersonalData(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="DELETE FROM userinformation WHERE userId="+user.getUserId();
		databaseDao.update(sql);
		sql="INSERT INTO userinformation(userId,sex,hobby,imgUrl) VALUES("+user.getUserId()+",'"+user.getSex()+"','"+user.getHobby()+"','"+user.getImgUrl()+"')";
		System.out.println(sql);
		databaseDao.update(sql);
		databaseDao.close();
	}
	//切换账户可用性
	public void changeEnable(int userId){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="SELECT enable From user WHERE userId="+userId;
			databaseDao.query(sql);
			if(databaseDao.next()){
				String enable=databaseDao.getString("enable");
				if("use".equals(enable)){
					sql="UPDATE user SET enable='stop' WHERE userId="+userId;
					databaseDao.update(sql);
				}
				else{
					sql="UPDATE user SET enable='use' WHERE userId="+userId;
					databaseDao.update(sql);
				}
			}
			else{
				
			}
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除一个用户
	public void deleteUser(int userId){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			String sql="DELETE FROM user WHERE userId="+userId;
			databaseDao.update(sql);
			sql="DELETE FROM userinformation WHERE userId="+userId;
			databaseDao.update(sql);
			databaseDao.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取记录数
	public Integer count(DatabaseDao databaseDao) throws SQLException{
		String strSQL = "select count(*) as recordcount from user";
		databaseDao.query(strSQL);
		if (databaseDao.next()) 
			return databaseDao.getInt("recordcount");		
		return 0;
	}
	//根据用户名获取一个用户的所有信息
	public User getUserByName(String name) throws Exception{
		User user=new User();
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="SELECT *"+" FROM user u1,userinformation u2"+" WHERE u1.userId=u2.userId AND u1.name LIKE '"+name+"'";
		System.out.println(sql);
		databaseDao.query(sql);
		if(databaseDao.next()){
			user.setUserId(databaseDao.getInt("userId"));
			user.setName(databaseDao.getString("name"));
			user.setEmail(databaseDao.getString("email"));
			user.setPhoneNum(databaseDao.getString("phoneNum"));
			user.setType(databaseDao.getString("type"));
			user.setEnable(databaseDao.getString("enable"));
			user.setPassword(databaseDao.getString("password"));
			user.setSalt(databaseDao.getString("salt"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setSex(databaseDao.getString("sex"));
			user.setHobby(databaseDao.getString("hobby"));
			user.setImgUrl(databaseDao.getString("imgUrl"));
			databaseDao.close();
			return user;
		}
		else{
			databaseDao.close();
			return user;
		}
	}
	//根据电子邮箱获取一个用户的所有信息
	public User getUserByEmail(String email) throws Exception{
		User user=new User();
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="SELECT *"+" FROM user u1,userinformation u2"+" WHERE u1.userId=u2.userId AND u1.email LIKE '"+email+"'";
		System.out.println(sql);
		databaseDao.query(sql);
		if(databaseDao.next()){
			user.setUserId(databaseDao.getInt("userId"));
			user.setName(databaseDao.getString("name"));
			user.setEmail(databaseDao.getString("email"));
			user.setPhoneNum(databaseDao.getString("phoneNum"));
			user.setType(databaseDao.getString("type"));
			user.setEnable(databaseDao.getString("enable"));
			user.setPassword(databaseDao.getString("password"));
			user.setSalt(databaseDao.getString("salt"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setSex(databaseDao.getString("sex"));
			user.setHobby(databaseDao.getString("hobby"));
			user.setImgUrl(databaseDao.getString("imgUrl"));
			databaseDao.close();
			return user;
		}
		else{
			databaseDao.close();
			return user;
		}
	}
	//根据手机号获取一个用户的所有信息
	public User getUserByPhoneNum(String phoneNum) throws Exception{
		User user=new User();
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="SELECT *"+" FROM user u1,userinformation u2"+" WHERE u1.userId=u2.userId AND u1.phoneNum LIKE '"+phoneNum+"'";
		System.out.println(sql);
		databaseDao.query(sql);
		if(databaseDao.next()){
			user.setUserId(databaseDao.getInt("userId"));
			user.setName(databaseDao.getString("name"));
			user.setEmail(databaseDao.getString("email"));
			user.setPhoneNum(databaseDao.getString("phoneNum"));
			user.setType(databaseDao.getString("type"));
			user.setEnable(databaseDao.getString("enable"));
			user.setPassword(databaseDao.getString("password"));
			user.setSalt(databaseDao.getString("salt"));
			user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
			user.setSex(databaseDao.getString("sex"));
			user.setHobby(databaseDao.getString("hobby"));
			user.setImgUrl(databaseDao.getString("imgUrl"));
			databaseDao.close();
			return user;
		}
		else{
			databaseDao.close();
			return user;
		}
	}
	//根据字段名，查是否有字段值为value的记录
	//返回值：1表示有相同值、-1表示没有相同值
	public int hasStringValue(String fieldName, String value) throws Exception{
		int result = -1;
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="select * from user where "+fieldName+"='"+value+"'";
		databaseDao.query(sql);
		if (databaseDao.next()) {
			result=1;
		}
		databaseDao.close();
		return result;
	}
	
	public boolean changePasswordByEamil(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		boolean result=false;
		String sql="UPDATE user SET password='"+user.getPassword()+"',salt='"+user.getSalt()+"' WHERE email='"+user.getEmail()+"'";
		if(databaseDao.update(sql)==1){
			//若更新影响一行，则代表更新成功
			result=true;
		}
		databaseDao.close();
		return result;
	}
	
	public boolean phoneRegister(User user) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		boolean returnValue=false;
		String sql="insert into user(type,name,phoneNum,password,salt,enable) values('"+
				user.getType()+"','"+user.getName()+"','"+user.getPhoneNum()+"','"+
				user.getPassword()+"','"+user.getSalt()+"','"+user.getEnable()+"')";
		if(databaseDao.update(sql)==1){
			//如果成功插入user表，再进行以下操作
			sql="SELECT userId FROM user WHERE name LIKE '"+user.getName()+"'";
			databaseDao.query(sql);
			int userId = -1;	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
			if(databaseDao.next()){
				userId=databaseDao.getInt("userId");	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
			}
			sql="insert into userinformation(userId) values("+userId+")";	//插入该用户的信息
			if(databaseDao.update(sql)==1){
				//如果成功插入userinformation表，再进行以下操作
				returnValue=true;
			}
		}
		databaseDao.close();
		return returnValue;
	}
}
