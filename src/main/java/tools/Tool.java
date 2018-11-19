package tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Tool {
	static public Random random=new Random();
	//从客户端获取分页、排序、删除等的参数
	public static void getPageInformation(String tableName,HttpServletRequest request,PageInformation pageInformation){
		pageInformation.setTableName(tableName);
		
		String param=request.getParameter("pageSize");
		if(param==null)
			pageInformation.setPageSize(null);
		else
			pageInformation.setPageSize(Integer.parseInt(param));
		
		param=request.getParameter("totalPageCount");
		if(param==null)
			pageInformation.setTotalPageCount(null);
		else
			pageInformation.setTotalPageCount(Integer.parseInt(param));	

		param=request.getParameter("allRecordCount");
		if(param==null)
			pageInformation.setAllRecordCount(null);
		else
			pageInformation.setAllRecordCount(Integer.parseInt(param));
		
		param=request.getParameter("page");
		if(param==null)
			pageInformation.setPage(null);
		else
			pageInformation.setPage(Integer.parseInt(param));
		
		pageInformation.setOrderField(request.getParameter("orderField"));
		pageInformation.setOrder(request.getParameter("order"));
		pageInformation.setIds(request.getParameter("ids"));
		pageInformation.setSearchSql(request.getParameter("searchSql"));
	}	
	
	//生成表的查询语句
	public static String getSql(PageInformation pageInformation, String type){
		String sql="";
		
		//删除
		if(pageInformation.getIds()!=null &&  !pageInformation.getIds().isEmpty()){			
			sql+="delete * from"+pageInformation.getTableName().toLowerCase()
				+" where " + pageInformation.getTableName().toLowerCase()+"Id in ("
					+" "+pageInformation.getIds()+") ";
		}else if("count".equals(type)){//查询：只查符合条件的记录数目
			sql+=""+" select count(*) as count1 from " + pageInformation.getTableName().toLowerCase()+" ";
			//查询条件
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()){
				sql+=" where "+pageInformation.getSearchSql()+" ";
			}	
		}else if("select".equals(type)){//一般查询
			sql+=""+" select * from " + pageInformation.getTableName().toLowerCase()+" ";
			//查询条件
			if(pageInformation.getSearchSql()!=null && !pageInformation.getSearchSql().isEmpty()){
				sql+=" where "+pageInformation.getSearchSql()+" ";
			}
			//排序,默认按主键的降序排列
			if(pageInformation.getOrderField()==null || pageInformation.getOrderField().isEmpty()){
				sql+=" ORDER BY "+pageInformation.getTableName()+"Id "+" desc ";
			}else{			
				sql+=" ORDER BY "+pageInformation.getOrderField()+" "+pageInformation.getOrder()+" ";
			}	
			//分页
			if(pageInformation.getPage()!=null && pageInformation.getPage()>-100){	
				Integer start= (pageInformation.getPage()-1) *
						pageInformation.getPageSize();
				
				sql+=" limit "+start.toString()+","+pageInformation.getPageSize();
				System.out.println(sql);
			}	
		}
		
		return sql;
	}
	
	////更新pageInformation的总页数等
	public static void setPageInformation(Integer allRecordCount,PageInformation pageInformation){
		pageInformation.setAllRecordCount(allRecordCount);
		Integer totalPageCount=(int) Math.ceil(1.0* allRecordCount / pageInformation.getPageSize());//总页数
		pageInformation.setTotalPageCount(totalPageCount);
		
		//防止页码越界  删除时有可能页码越界
		if(pageInformation.getPage()<1)
			pageInformation.setPage(1);
		if(pageInformation.getPage()>totalPageCount)
			pageInformation.setPage(totalPageCount);		
	}
	

	
	public static void dispatch(HttpServlet servlet, String localUrl, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletContext context = servlet.getServletContext();//ServletContext存储全局数据
		RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");//设置分派的网址
		rd.forward(request,response);	
	}
		
	//给ajax请求返回json格式的数据
	public static void returnIntResult(HttpServletResponse response, Integer result) throws ServletException, IOException{
		//response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("{\"result\":"+result.toString()+"}");
		out.flush();
	}
	
	public static Integer getRandomInteger(){
		Integer rand=random.nextInt();
		return rand;		
	}
	
	public static Integer getRandomInRangeInteger(Integer min, Integer max){
		int rand = min + (int)(Math.random() * ((max - min) + 1));
		return rand;		
	}
	
	public static Long getSecondFromNow(Date old){//当前时间领先old多少秒
		Date now=new Date();
		long between = (now.getTime()-old.getTime())/1000;//得到间隔秒数
		return between;		
	}
}
