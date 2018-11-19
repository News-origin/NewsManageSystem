package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpException;

//import tools.PageInformation;
import bean.User;
import dao.DatabaseDao;
import dao.UserDao;
import tools.EMailTool;
import tools.Encryption;
import tools.MessageTool;
import tools.PageInformation;

public class UserService {
	//返回-1代表手机已被注册，返回1代表发送成功，返回0代表发送失败
	public int messageForRegister(String phoneNum,Integer messageCode) throws Exception{
		UserDao userDao=new UserDao();
		if(userDao.hasStringValue("phoneNum", phoneNum)==1){
			return -1;
		}
		if(MessageTool.messageForRegister(phoneNum, messageCode)==1){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	//返回-1表示用户名已存在，返回-10表示手机号已被注册,返回1表示成功，返回0表示失败
	public int phoneRegister(User user) throws Exception{	
		UserDao UserDao=new UserDao();
		if(UserDao.hasStringValue("name", user.getName())==1){
			//有同名用户，不可以注册
			return -1;
		}
		if(UserDao.hasStringValue("phoneNum", user.getPhoneNum())==1){
			//手机号已被注册
			return -10;
		}
		Encryption.encryptPasswd(user);
		if(UserDao.phoneRegister(user)){
			return 1;	//成功
		}
		else{
			return 0;
		}
	}
	
	
	//返回-1代表邮箱已被注册，返回1代表发送成功，返回0代表发送失败
	public int emailForRegister(User user,Integer rand) throws Exception{
		UserDao userDao=new UserDao();
		if(userDao.hasStringValue("email", user.getEmail())==1){
			return -1;
		}
		if(EMailTool.emailForRegister(user.getEmail(), rand)==1){
			return 1;
		}
		else{
			return 0;
		}
	}
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
	//返回0表示数据库操作错误，1表示成功创建用户，-1表示有重名用户，-10表示emai已被注册，-11表示重名用户且email被注册
	public Integer emailRegister(User user) throws Exception{
		int result=0;//数据库操作失败	
		UserDao UserDao=new UserDao();
		if(UserDao.hasStringValue("name", user.getName())==1){
			//有同名用户，不可以注册
			result=-1;
		}
		if(UserDao.hasStringValue("email", user.getEmail())==1){
			//有同名用户，不可以注册
			result+=-10;
		}
		if(result<0){
			//用户名已被注册或email被注册过
			return result;
		}
		Encryption.encryptPasswd(user);
		if(UserDao.emailRegister(user)){
			return 1;	//成功
		}
		else{
			return 0;
		}
	}
	//0表示不存在该用户/电子邮箱/手机号,1表示成功，-1表示密码错误,-2表示账号被禁用
	//注意分清楚这个函数的变量user和user1
	public Integer login(User user,HttpSession session) throws Exception{
		UserDao userDao=new UserDao();
		User user1=null;
		if(userDao.hasStringValue("name", user.getName())==1){
			//存在这个用户名
			user1=userDao.getUserByName(user.getName());
		}
		else if(userDao.hasStringValue("email", user.getName())==1){
			//存在这个电子邮箱
			user1=userDao.getUserByEmail(user.getName());
		}
		else if(userDao.hasStringValue("phoneNum", user.getName())==1){
			//存在这个手机号
			user1=userDao.getUserByPhoneNum(user.getName());
		}
		else{
			return 0;
		}
		user.setSalt(user1.getSalt());
		if("stop".equals(user1.getEnable())){
			//如果账户被禁用，返回-2
			return -2;
		}
		//检查密码是否正确
		if(Encryption.checkPassword(user, user1.getPassword())){
			//把用户存进session中
			session.setAttribute("user", user1);
			return 1;
		}
		else{
			//密码错误
			return -1;
		}
	}
	//修改密码,返回1则更改成功，返回0则更改失败
	public Integer changePassword(User user,String newPassword) throws Exception {
		UserDao userDao=new UserDao();
		//根据用户名获取用户信息，需要其中的盐和密码
		User user1=userDao.getUserByName(user.getName());
		user.setSalt(user1.getSalt());	//设置盐
		if(Encryption.checkPassword(user, user1.getPassword())){
			//验证通过
			user.setPassword(newPassword);
			Encryption.encryptPasswd(user);	//根据新的密码重新加密
			userDao.changePassword(user);
			return 1;
		}
		else{
			//由于输入的原密码错误,验证错误
			return 0;
		}
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
	//返回值：1成功发送邮件，-1发送邮件失败，-2邮箱未注册过
	public Integer findPasswordByEmail(User user,Integer rand) throws Exception{
		UserDao userDao=new UserDao();
		Integer result=0;
		if(userDao.hasStringValue("email",user.getEmail())==1){
			//该email存在
			result=EMailTool.sendReturnPassword(user.getEmail(),rand);//发送邮件
		}
		else{
			//该email不存在
			result=-2;
		}
		return result;
	}
	//返回1代表更改密码成功，返回0代表更新失败
	public Integer changePasswordByEmail(User user) throws Exception{
		//加密生成密码
		Encryption.encryptPasswd(user);
		UserDao userDao=new UserDao();
		if(userDao.changePasswordByEamil(user)){
			return 1;
		}
		else{
			return 0;
		}
	}
}
