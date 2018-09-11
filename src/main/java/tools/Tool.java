package tools;

import javax.servlet.http.HttpServletRequest;

public class Tool {
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
	

	
}
