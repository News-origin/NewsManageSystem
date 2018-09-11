package servlet;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.User;
import service.UserService;
import tools.Message;
import tools.PageInformation;
import tools.SearchTool;
import tools.Tool;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		if("register".equals(type)){
			//根据客户端提交的信息，创建一个User对象并为其设置信息
			User user=new User();
			String role=request.getParameter("role");	//设置用户类型
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			if("user".equals(role)){
				user.setEnable("use");
			}
			else{
				user.setEnable("stop");
			}
			user.setName(userName);
			user.setPassword(password);
			user.setType(role);
			UserService userService=new UserService();
			Message message=new Message();
			int result=userService.register(user);
			message.setResult(result);
			if(result==1){
				message.setMessage("注册成功！");
				message.setRedirectUrl("/NewsManageSystem/login.jsp");
			}else if(result==0){
				message.setMessage("同名用户已存在，请改名重新注册！");
				message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
			}else{
				message.setMessage("注册失败！");
				message.setRedirectUrl("/NewsManageSystem/usersRegistration.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if("login".equals(type)){
			User user=new User();
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			user.setName(userName);
			user.setPassword(password);
			UserService userService=new UserService();
			Message message=new Message();
			int result=userService.login(user);
			message.setResult(result);
			if(result==1){
				message.setMessage("登录成功！");
				message.setRedirectUrl("/NewsManageSystem/index.jsp");
				System.out.println(user.getUserId());
				System.out.println(user.getName());
				System.out.println(user.getPassword());
				System.out.println(user.getSex());
				System.out.println(user.getEnable());
				System.out.println(user.getHobby());
				System.out.println(user.getImgUrl());
				System.out.println(user.getType());
				System.out.println(user.getRegisterDate());
				HttpSession session=request.getSession();
				session.setAttribute("user", user);
			}
			else if(result==0){
				message.setMessage("由于该用户被禁用，登录失败！");
				message.setRedirectUrl("/NewsManageSystem/login.jsp");
			}
			else{
				message.setMessage("由于该用户该用户不存在或密码错误，登录失败！");
				message.setRedirectUrl("/NewsManageSystem/login.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if("changePassword".equals(type)){
			User user=new User();
			HttpSession session=request.getSession();
			user=(User) session.getAttribute("user");
			String originPassword=request.getParameter("originPassword");
			String newPassword=request.getParameter("newPassword");
			UserService userService=new UserService();
			int result = -1;
			Message message=new Message();
			try {
				result=userService.changePassword(user, originPassword, newPassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result==0){
				message.setMessage("由于输入的原密码错误，修改密码失败！");
				message.setRedirectUrl("/NewsManageSystem/normalUsersManagement.jsp");
			}
			else if(result==1){
				message.setMessage("修改密码成功！");
				message.setRedirectUrl("/NewsManageSystem/normalUsersManagement.jsp");
				session=request.getSession();
				session.setAttribute("user", user);
				/*
				System.out.println(user.getUserId());
				System.out.println(user.getName());
				System.out.println(user.getPassword());
				System.out.println(user.getType());
				*/
			}
			else{
				message.setMessage("由于未知原因，修改密码失败！");
				message.setRedirectUrl("/NewsManageSystem/normalUsersManagement.jsp");
			}
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if("changePersonalData".equals(type)){
			HttpSession session=request.getSession();
			User user=new User();
			user=(User) session.getAttribute("user");
			String sex;
			String hobby;
			String imgUrl;
			//以下为上传图片等操作
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
						//由于enctype="multipart/form-data",取表单中的值要这样取
						String fieldName = item.getFieldName();
						String valueOfField = new String(item.getString().getBytes("iso-8859-1"), "utf-8");
						if("sex".equals(fieldName)){
							sex=valueOfField;
							user.setSex(sex);
						}
						else if("hobby".equals(fieldName)){
							hobby=valueOfField;
							user.setHobby(hobby);
						}
					} 
					else {// 上传文件
						File uploadImgFolder=new File(request.getServletContext().getRealPath("//upload/images/"));
						System.out.println(request.getServletContext().getRealPath("//upload/images/"));
						//factory.setRepository(uploadImgFolder);// 设置存放文件的文件夹
						String str=item.getName();
						if("".equals(item.getName())||item.getName()==null){
							continue;
						}
						File uploadedFile = new File(
								request.getServletContext().getRealPath("/upload/images/" + item.getName()));
						item.write(uploadedFile);
						imgUrl="/NewsManageSystem/upload/images/"+item.getName();	//保存上传文件路径
						user.setImgUrl(imgUrl);
						System.out.println("文件上传成功！！！");
						item.delete();// 删除临时文件
					}
				}
				/*
				System.out.println(user.getSex());
				System.out.println(user.getImgUrl());
				System.out.println(user.getHobby());
				*/
				UserService userService=new UserService();
				userService.changePersonalData(user);
				Message message=new Message();
				message.setMessage("修改个人信息成功！！！");
				message.setRedirectUrl("/NewsManageSystem/normalUsersManagement.jsp");
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if("cancel".equals(type)){
			//从session中移除user
			request.getSession().removeAttribute("user");
			Message message=new Message();
			message.setMessage("注销成功！！！");
			message.setRedirectUrl("/NewsManageSystem/login.jsp");
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/message.jsp").forward(request,response);
		}
		else if("listOnePageUsers".equals(type)){
			PageInformation pageInformation=new PageInformation();
			//获取分页信息
			Tool.getPageInformation("user", request, pageInformation);
			UserService userService=new UserService();
			//获取一页的用户
			List<User> users=userService.getOnePage(pageInformation);
			request.setAttribute("users", users);
			request.setAttribute("pageInformation", pageInformation);
			getServletContext().getRequestDispatcher("/showUsers.jsp").forward(request,response);
		}
		/*
		else if("listUsers".equals(type)){
			ServletContext context=this.getServletContext();
			UserService userService=new UserService();
			List<User> users = new ArrayList<User>();
			try {
				users = userService.listAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.setAttribute("users", users);
		}
		*/
		else if("changeEnable".equals(type)){
			UserService userService=new UserService();
			int userId=Integer.parseInt(request.getParameter("userId"));
			userService.changeEnable(userId);
			response.sendRedirect("/NewsManageSystem/manager.jsp");
		}
		else if("searchUsers".equals(type)){
			/*
			String userType=(String) request.getParameter("userType");
			String name=(String) request.getParameter("name");
			String enable=(String) request.getParameter("enable");
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			UserService userService=new UserService();
			List<User> users=userService.searchUsers(pageInformation, userType, name, enable);
			request.setAttribute("users", users);
			request.setAttribute("pageInformation", pageInformation);
			getServletContext().getRequestDispatcher("/showUsers.jsp").forward(request,response);
			*/
			PageInformation pageInformation=new PageInformation();
			Tool.getPageInformation("user", request, pageInformation);
			pageInformation.setSearchSql(SearchTool.user(request));
			UserService userService=new UserService();
			List<User> users=userService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/showUsers.jsp").forward(request,response);
		}
		else if("deleteUsers".equals(type)){
			UserService userService=new UserService();
			int userId=Integer.parseInt(request.getParameter("userId"));
			userService.deleteUser(userId);
			response.sendRedirect("/NewsManageSystem/manager.jsp");
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}
}
