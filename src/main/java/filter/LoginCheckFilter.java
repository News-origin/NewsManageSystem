package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/LoginCheckFilter")
public class LoginCheckFilter implements Filter {

	private String includedPage;
	private String[] includedPages; // 需要限制访问的网页

	/**
	 * Default constructor.
	 */
	public LoginCheckFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println("正在访问LoginCheckFilter");
		// 获取req,res,session
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		// 检查当前访问网页是否是受限制网页
		boolean flag = false;
		for (String page : includedPages) {
			if (req.getServletPath().equals(page)) {
				flag = true;
			}
		}
		// 访问的是受限制网页且是login.jsp
		if (flag && (req.getServletPath().equals("/login.jsp"))) {
			// 用户未登录时
			if (user == null) {
				chain.doFilter(request, response);
			}
			// 用户已登录时，跳转到index.jsp
			else {
				res.sendRedirect("/index.jsp");
			}
		}
		// 访问的是受限制网页且是normalUsersManagement.jsp
		else if (flag && (req.getServletPath().equals("/normalUsersManagement.jsp"))) {
			if (user == null) {
				res.sendRedirect("/NewsManageSystem/login.jsp");
			} else if ("newsAuthor".equals(user.getType())) {
				res.sendRedirect("/NewsManageSystem/index.jsp");
			} else if ("manager".equals(user.getType())) {
				res.sendRedirect("/NewsManageSystem/index.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}
		// 访问的是受限制网页且是newsAuthor.jsp
		else if (flag && (req.getServletPath().equals("/newsAuthor.jsp"))) {
			if (user == null) {
				res.sendRedirect("/NewsManageSystem/login.jsp");
			} else if (!("newsAuthor".equals(user.getType()))) {
				if ("user".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				} else if ("manager".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				}
			} else {
				chain.doFilter(request, response);
			}
		}
		// 访问的是受限制网页且是addNews.jsp
		else if (flag && (req.getServletPath().equals("/addNews.jsp"))) {
			if (user == null) {
				res.sendRedirect("/NewsManageSystem/login.jsp");
			} else if (!("newsAuthor".equals(user.getType()))) {
				if ("user".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				} else if ("manager".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				}
			} else {
				chain.doFilter(request, response);
			}
		}
		// 访问的是受限制网页且是manager.jsp
		else if (flag && (req.getServletPath().equals("/manager.jsp"))) {
			if (user == null) {
				res.sendRedirect("/NewsManageSystem/login.jsp");
			} else if (!("manager".equals(user.getType()))) {
				if ("user".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				} else if ("newsAuthor".equals(user.getType())) {
					res.sendRedirect("/NewsManageSystem/index.jsp");
				}
			} else {
				chain.doFilter(request, response);
			}
		}
		// 访问的不是受限制网页
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		includedPage = fConfig.getInitParameter("includedPages");
		if (includedPage != null && includedPage.length() > 0) {
			includedPages = includedPage.split(","); // 按“，”划分
		}
	}

}
