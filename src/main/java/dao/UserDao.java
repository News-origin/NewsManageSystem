package dao;

import java.sql.ResultSet;

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
	public Integer hasUser(User user,DatabaseDao databaseDao) throws SQLException{
		String sql="select * from user where name='"+user.getName()+"'";
		databaseDao.query(sql);
		while(databaseDao.next()){
			return 1;
		}
		return 0;
	}
	
	//注册
	public void register(User user,DatabaseDao databaseDao) throws SQLException{
		String sql="insert into user(type,name,password,enable) values('"+
				user.getType()+"','"+user.getName()+"','"+
				user.getPassword()+"','"+user.getEnable()+"')";
		databaseDao.update(sql);
		sql="SELECT userId FROM user WHERE name LIKE '"+user.getName()+"'";
		databaseDao.query(sql);
		int userId = -1;	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
		while(databaseDao.next()){
			userId=databaseDao.getInt("userId");	//获取新建用户的userId,用来存储该用户的信息(性别，爱好等);
		}
		sql="insert into userinformation(userId) values("+userId+")";	//插入该用户的信息
		databaseDao.update(sql);
		//databaseDao.close();
	}
	
	//登录
	public Integer login(User user) throws SQLException, Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sql="SELECT *"+" FROM user u1,userinformation u2"+" WHERE u1.userId=u2.userId AND u1.name LIKE '"+user.getName()+"' AND u1.password LIKE '"+user.getPassword()+"'";
		System.out.println(sql);
		databaseDao.query(sql);
		while(databaseDao.next()){
			String enable=databaseDao.getString("enable");
			//检查账户可用性是因为，如果账户没被禁用，则取出数据库中的数据为User对象user设值，并保存在session中
			if( ("use").equals(enable)  ){
				user.setUserId(databaseDao.getInt("userId"));
				user.setType(databaseDao.getString("type"));
				user.setEnable(enable);
				user.setRegisterDate(databaseDao.getTimestamp("registerDate"));
				user.setSex(databaseDao.getString("sex"));
				user.setHobby(databaseDao.getString("hobby"));
				user.setImgUrl(databaseDao.getString("imgUrl"));
				databaseDao.close();
				return 1;//可以登录
			}
			databaseDao.close();
			return 0;//用户存在，但被停用
		}
		databaseDao.close();
		return -1;//该用户不存在或密码错误
	}	
	
	//更改密码
	public Integer changePassword(User user,String originPassword,String newPassword) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		//先检查原密码是否正确
		String sql="SELECT *"+" FROM user"+" WHERE user.userId="+user.getUserId()+" AND user.password LIKE '"+originPassword+"'";
		databaseDao.query(sql);
		if(!databaseDao.next()){
			databaseDao.close();
			return 0;	//原密码错误
		}
		//原码正确，开始修改密码
		else{
			sql="UPDATE user"+" SET password='"+newPassword+"'"+" WHERE user.userId="+user.getUserId();
			databaseDao.update(sql);
			user.setPassword(newPassword);	//为User对象设置新密码
			databaseDao.close();
			return 1;	//成功修改密码
		}
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
	
	//切换用户的可用性
	public Integer changeEnable(String id,DatabaseDao databaseDao)throws SQLException{//查询失败返回-1
		String sql = "select * from user where userId in ("+id+")";
		databaseDao.query(sql);
		while (databaseDao.next()) {
			String enable=databaseDao.getString("enable");
			if("use".equals(enable))
				enable="stop";
			else
				enable="use";
			sql = "update user set enable='"+enable+"' where userId in ("+id+")";
			databaseDao.update(sql);
			databaseDao.close();
			return 1;
		}
		databaseDao.close();
		return 0;
	}
	/*
	//删除多个用户
	public Integer deletes(String ids,DatabaseDao databaseDao)throws SQLException{//查询失败返回-1
		if(ids!=null && ids.length()>0){
			String sql = "delete from user where userId in ("+ids+")";
			return databaseDao.update(sql);
		}else
			return -1;
	}
	*/
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
	/*
	//按条件搜索用户
	public List<User> searchUsers(PageInformation pageInformation,String type,String name,String enable) throws Exception{
		DatabaseDao databaseDao=new DatabaseDao();
		String sqlCount="SELECT COUNT(*) AS count1 FROM user WHERE type LIKE '"+type+"' AND name LIKE '%"+name+"%' AND enable LIKE '"+enable+"'";
		Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
		Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
		String sqlSelect="SELECT * FROM user WHERE type LIKE '"+type+"' AND name LIKE '%"+name+"%' AND enable LIKE '"+enable+"' GROUP BY userId";
		databaseDao.query(sqlSelect);
		List<User> searchUsers=new ArrayList<User>();
		while(databaseDao.next()){
			User user=new User();
			user.setUserId(databaseDao.getInt("userId"));
			user.setType(databaseDao.getString("type"));
			user.setName(databaseDao.getString("name"));
			user.setPassword(databaseDao.getString("password"));
			user.setEnable(databaseDao.getString("enable"));
			searchUsers.add(user);
		}
		return searchUsers;
	}
	*/
	//获取记录数
	public Integer count(DatabaseDao databaseDao) throws SQLException{
		String strSQL = "select count(*) as recordcount from user";
		databaseDao.query(strSQL);
		if (databaseDao.next()) 
			return databaseDao.getInt("recordcount");		
		return 0;
	}
}
