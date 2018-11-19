package servlet;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import bean.User;
import service.UserService;
import tools.Message;
import tools.PageInformation;
import tools.ResponseTools;
import tools.SearchTool;
import tools.Tool;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if ("emailForRegister".equals(type)) {
			Integer result = -5;
			HttpSession session = request.getSession();
			String E_checkCode = request.getParameter("E_checkCode");
			String sessionCheckCode = (String) session.getAttribute("checkCode");
			if (sessionCheckCode == null) {
				result = -5;
			} else if (sessionCheckCode.equals(E_checkCode)) {
				Integer registerRand = Tool.getRandomInRangeInteger(10, 100000);
				User registerUser = new User();
				if ("user".equals(request.getParameter("E_role"))) {
					registerUser.setEnable("use");
				} else {
					registerUser.setEnable("stop");
				}
				registerUser.setType(request.getParameter("E_role"));
				registerUser.setName(request.getParameter("E_userName"));
				registerUser.setEmail(request.getParameter("email"));
				registerUser.setPassword(request.getParameter("E_password"));
				// 发邮件，验证注册
				UserService userService = new UserService();
				try {
					result = userService.emailForRegister(registerUser, registerRand);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result = -3;
					e.printStackTrace();
				}
				if (result == 1) {
					// 发送注册邮件成功
					session.setAttribute("registerUser", registerUser);
					session.setAttribute("registerRand", registerRand);
					session.setAttribute("registerTime", new Date());
				}
			} else {
				// 客户端提交的验证码与服务端中的不一致
				result = -4;
			}
			// result=-5,session中没有验证码
			// result=-4，验证码错误
			// result=-3，数据库操作失败
			// result=-1，邮箱已被注册
			// result=0，邮件发送失败
			// result=1，邮件发送成功
			ResponseTools.responseJSONData(response, result);
		} 
		//发短信验证码
		else if ("messageForRegister".equals(type)) {
			Integer result = -5;
			HttpSession session = request.getSession();
			String phoneNum=request.getParameter("phoneNum");
			Integer messageCode = Tool.getRandomInRangeInteger(100000, 999999);
			// 发短信，验证注册
			UserService userService = new UserService();
			try {
				result=userService.messageForRegister(phoneNum, messageCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result = -3;
				e.printStackTrace();
			}
			if (result == 1) {
				// 发送注册短信成功
				session.setAttribute("messageCode", messageCode.toString());
				session.setAttribute("registerTime", new Date());
			}
			// result=-3，数据库操作失败
			// result=-1，手机已被注册
			// result=0，短信发送失败
			// result=1，短信发送成功
			ResponseTools.responseJSONData(response, result);
		}
		else if("phoneRegister".equals(type)){
			HttpSession session = request.getSession();
			String sessionCheckCode=(String) session.getAttribute("checkCode");
			String checkCode=request.getParameter("P_checkCode");
			Date old = (Date) session.getAttribute("registerTime");
			int result=-5;
			if(sessionCheckCode==null){
				//session中无验证码
				result=-5;
			}
			else if(checkCode.equals(sessionCheckCode)){
				//验证码正确
				String messageCode=request.getParameter("messageCode");
				String sessionMessageCode=request.getParameter("messageCode");
				if(old == null || Tool.getSecondFromNow(old) > 600){
					//操作超时
					result=-3;
				}
				else{
					if(messageCode.equals(sessionMessageCode)){
						//短信验证码正确
						//获取把用户信息并插入数据库
						User registerUser=new User();
						registerUser.setName(request.getParameter("P_userName"));
						registerUser.setPhoneNum(request.getParameter("phoneNum"));
						registerUser.setPassword(request.getParameter("P_password"));
						registerUser.setType(request.getParameter("P_role"));
						if("user".equals(request.getParameter("P_role"))){
							registerUser.setEnable("use");
						}
						else{
							registerUser.setEnable("stop");
						}
						//插入数据库
						UserService userService=new UserService();
						try {
							result=userService.phoneRegister(registerUser);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							//数据库操作失败
							result=-6;
						}
						if(result==1){
							session.removeAttribute("registerTime");
							session.removeAttribute("messageCode");
						}
					}
					else{
						//短信验证码不正确
						result=-2;
					}
				}
			}
			else{
				//验证码不正确
				result=-4;
			}
			//result为-5，表示session中无验证码
			//result为-4。表示验证码不正确
			//result为-3，表示操作超时
			//result为-2，表示短信验证码不正确
			//result为-6，表示数据库操作失败
			//result为-1，表示该用户已被注册
			//result为-10，表示该手机已被注册
			//result为0，表示注册失败
			//result为1，表示注册成功
			ResponseTools.responseJSONData(response, result);
		}
		else if ("emailRegister".equals(type)) {
			Message message = new Message();
			Integer result = -2;
			HttpSession session = request.getSession();
			Integer sessionRand = (Integer) session.getAttribute("registerRand");
			String registerRand = request.getParameter("registerRand");
			Date old = (Date) session.getAttribute("registerTime");
			if (!registerRand.equals(sessionRand.toString())) {
				// rand值不一致
				message.setMessage("rand值不一致，请重新操作！");
				message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
			} else if (old == null || Tool.getSecondFromNow(old) > 600) {
				// 操作超时
				message.setMessage("操作超时，请重新操作！");
				message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
			} else {
				User registerUser = (User) session.getAttribute("registerUser");
				// 根据客户端提交的信息，创建一个User对象并为其设置信息
				UserService userService = new UserService();
				try {
					result = userService.emailRegister(registerUser);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result == 1) {
					message.setMessage("注册成功！");
					message.setRedirectUrl("/NewsManageSystem/login.jsp");
				} else if (result == -1) {
					message.setMessage("同名用户已存在，请改名重新注册！");
					message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
				} else if (result == -10) {
					message.setMessage("邮箱已被注册，请改名重新注册！");
					message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
				} else if (result == -11) {
					message.setMessage("同名用户已存在且邮箱已被注册，请改名重新注册！");
					message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
				} else if (result == 0) {
					message.setMessage("数据库操作失败！");
					message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
				} else if (result == -2) {
					message.setMessage("未知错误！！！");
					message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
				}
			}
			session.removeAttribute("registerUser");
			session.removeAttribute("registerRand");
			session.removeAttribute("registerTime");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		} 
		else if ("login".equals(type)) {
			Integer result = -4;
			HttpSession session = request.getSession();
			String checkCode = request.getParameter("checkCode");
			String sessionCheckCode = (String) session.getAttribute("checkCode");
			if (sessionCheckCode == null) {
				// session中无验证码
				result = -4;
			} else if (checkCode.equals(sessionCheckCode)) {
				User user = new User();
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				user.setName(userName);
				user.setPassword(password);
				UserService userService = new UserService();
				session = request.getSession();
				try {
					result = userService.login(user, session);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// 对数据库操作失败
					result = -3;
					e.printStackTrace();
				}
			} else {
				// 客户端输入的验证码与服务端的不一致
				result = -5;
			}
			// result=-5,session中没有验证码
			// result=-4，验证码错误
			// result=-3，数据库操作失败
			// result=-2，该用户被禁用！
			// result=-1，密码错误
			// result=0，不存在该用户/电子邮箱/手机号
			// result=1，登录成功
			ResponseTools.responseJSONData(response, result);
		} 
		else if ("changePassword".equals(type)) {
			User user=null;
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			String newPassword = request.getParameter("newPassword");
			user.setPassword(request.getParameter("originPassword"));	//把用户输入的旧密码存进User对象用于验证
			UserService userService = new UserService();
			int result = -3;
			try {
				result = userService.changePassword(user,newPassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result==1){
				//移除session中的user
				session.removeAttribute("user");
			}
			//result为-3，数据库操作出错
			//result为0，输入原密码错误
			//result为1，修改成功
			ResponseTools.responseJSONData(response, result);
		} else if ("changePersonalData".equals(type)) {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			String sex;
			String hobby;
			String imgUrl;
			// 以下为上传图片等操作
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解析request，将其中各表单元素和上传文件提取出来
			try {
				List<FileItem> items = upload.parseRequest(request);// items存放各表单元素
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {// 遍历表单元素
					FileItem item = iter.next();
					if (item.isFormField()) {// 非上传文件表单元素
						// 由于enctype="multipart/form-data",取表单中的值要这样取
						String fieldName = item.getFieldName();
						String valueOfField = new String(item.getString().getBytes("iso-8859-1"), "utf-8");
						if ("sex".equals(fieldName)) {
							sex = valueOfField;
							user.setSex(sex);
						} else if ("hobby".equals(fieldName)) {
							hobby = valueOfField;
							user.setHobby(hobby);
						}
					} else {// 上传文件
						if ("".equals(item.getName()) || item.getName() == null) {
							continue;
						}
						File uploadedFile = new File(
								request.getServletContext().getRealPath("/upload/images/" + item.getName()));
						item.write(uploadedFile);
						imgUrl = "/NewsManageSystem/upload/images/" + item.getName(); // 保存上传文件路径
						user.setImgUrl(imgUrl);
						System.out.println("文件上传成功！！！");
						item.delete();// 删除临时文件
					}
				}
				UserService userService = new UserService();
				userService.changePersonalData(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ResponseTools.responseJSONData(response, 1);
		} else if ("cancel".equals(type)) {
			// 从session中移除user
			request.getSession().removeAttribute("user");
			Message message = new Message();
			message.setMessage("注销成功！！！");
			message.setRedirectUrl("/NewsManageSystem/login.jsp");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		} else if ("listOnePageUsers".equals(type)) {
			PageInformation pageInformation = new PageInformation();
			// 获取分页信息
			Tool.getPageInformation("user", request, pageInformation);
			UserService userService = new UserService();
			// 获取一页的用户
			List<User> users = userService.getOnePage(pageInformation);
			request.setAttribute("users", users);
			request.setAttribute("pageInformation", pageInformation);
			getServletContext().getRequestDispatcher("/showUsers.jsp").forward(request, response);
		}
		/*
		 * else if("listUsers".equals(type)){ ServletContext
		 * context=this.getServletContext(); UserService userService=new
		 * UserService(); List<User> users = new ArrayList<User>(); try { users
		 * = userService.listAll(); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * context.setAttribute("users", users); }
		 */
		else if ("changeEnable".equals(type)) {
			UserService userService = new UserService();
			int userId = Integer.parseInt(request.getParameter("userId"));
			userService.changeEnable(userId);
			response.sendRedirect("/NewsManageSystem/manager.jsp");
		} else if ("searchUsers".equals(type)) {
			/*
			 * String userType=(String) request.getParameter("userType"); String
			 * name=(String) request.getParameter("name"); String
			 * enable=(String) request.getParameter("enable"); PageInformation
			 * pageInformation=new PageInformation();
			 * Tool.getPageInformation("user", request, pageInformation);
			 * UserService userService=new UserService(); List<User>
			 * users=userService.searchUsers(pageInformation, userType, name,
			 * enable); request.setAttribute("users", users);
			 * request.setAttribute("pageInformation", pageInformation);
			 * getServletContext().getRequestDispatcher("/showUsers.jsp").
			 * forward(request,response);
			 */
			PageInformation pageInformation = new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			UserService userService = new UserService();
			List<User> users = userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/showUsers.jsp").forward(request, response);
		} else if ("deleteUsers".equals(type)) {
			UserService userService = new UserService();
			int userId = Integer.parseInt(request.getParameter("userId"));
			userService.deleteUser(userId);
			response.sendRedirect("/NewsManageSystem/manager.jsp");
		} else if ("findPasswordByEmail".equals(type)) {
			User user = new User();
			String email = request.getParameter("email");
			Integer findPasswordRand = Tool.getRandomInRangeInteger(10, 100000);
			user.setEmail(email);
			UserService userService = new UserService();
			int result = -3;
			try {
				result = userService.findPasswordByEmail(user, findPasswordRand);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message message = new Message();
			if (result == -3) {
				message.setMessage("未知错误！");
				message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
			} else if (result == -1) {
				message.setMessage("发送邮件失败");
				message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
			} else if (result == -2) {
				message.setMessage("邮箱未被注册过！");
				message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
			} else if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("findPasswordRand", findPasswordRand);
				session.setAttribute("time", new Date());
				message.setMessage("发送邮件成功！");
				message.setRedirectUrl("/NewsManageSystem/login.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		} else if ("changePasswordByEmail".equals(type)) {
			// 先检查rand的值
			HttpSession session = request.getSession();
			String findPasswordRand = request.getParameter("findPasswordRand");
			Integer sessionRand = (Integer) session.getAttribute("findPasswordRand");
			Date old = (Date) session.getAttribute("time");
			Message message = new Message();
			if (!findPasswordRand.equals(sessionRand.toString())) {
				// rand值不一致
				message.setMessage("rand值不一致，请重新操作！");
				message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
			} else if (old == null || Tool.getSecondFromNow(old) > 600) {
				// 操作超时
				message.setMessage("操作超时，请重新操作！");
				message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
			} else {
				User user = new User();
				user.setPassword(request.getParameter("password"));
				user.setEmail((String) session.getAttribute("email"));
				UserService userService = new UserService();
				int result = -3;
				try {
					result = userService.changePasswordByEmail(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result == -3) {
					message.setMessage("未知错误！");
					message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
				} else if (result == 1) {
					// 删掉session中无用的数据
					session.removeAttribute("findPasswordRand");
					session.removeAttribute("email");
					session.removeAttribute("time");
					message.setMessage("更改密码成功！！！");
					message.setRedirectUrl("/NewsManageSystem/login.jsp");
				} else if (result == 0) {
					message.setMessage("更改密码失败！！！");
					message.setRedirectUrl("/NewsManageSystem/findPassword.jsp");
				}

			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
