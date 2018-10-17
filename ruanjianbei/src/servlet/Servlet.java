package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import Bean.yfJshjBean;
import Dao.dataDao;
import util.DBUtil;;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static Connection con;
	private static Statement state;
	private static ResultSet rs;
	
	private static final long serialVersionUID = 1L;
       
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if("list".equals(method))
		{
			try {
				list(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if("detail".equals(method))
		{
			try {
				System.out.println("detail");
				detail(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if("x_yf_jshj".equals(method))//每个企业每月的销售额，购项额
		{
			try {
				XyfJshj(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if("g_yf_jshj".equals(method))//每个企业每月的销售额，购项额
		{
			try {
				GyfJshj(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void GyfJshj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		dataDao dao = new dataDao();
		
		List<yfJshjBean> glist = dao.yfjshjG(id);
		
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(glist));
		out.flush();
		out.close();
	}
	private void XyfJshj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		dataDao dao = new dataDao();
		
		List<yfJshjBean> xlist = dao.yfjshjX(id);
		
		PrintWriter out = response.getWriter();
		out.print(JSON.toJSONString(xlist));
		out.flush();
		out.close();
	}
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		con = DBUtil.getConnection();
		state = con.createStatement();
		String sql="";
		ArrayList<String[]> list = new ArrayList<String[]>();
		   
		String i = request.getParameter("i");
		if(i.equals("0")){//0问题 1正常 2可能 3较大
			sql="SELECT  *  FROM `temp2` WHERE label !=0 ";
		}else if(i.equals("1")){
			sql="SELECT  *  FROM `temp2` WHERE label =0 ";
		}else if(i.equals("2")){
			sql="SELECT  *  FROM `temp2` WHERE prediction BETWEEN '0.002863430980373005' AND '0.011463756102660334' ";
		}else if(i.equals("3")){
			sql="SELECT  *  FROM `temp2` WHERE prediction BETWEEN '0.005' AND '0.008' ";
		}
		rs = state.executeQuery(sql);
		
		while (rs.next()) {
			String[] temp={rs.getString("nsr_id"),rs.getString("fpsl"),rs.getString("fpje"),rs.getString("fpse")};
			list.add(temp);
		}
		DBUtil.close(rs, state, con);
		request.setAttribute("datas", list);
		request.setAttribute("name", request.getParameter("name"));
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		con = DBUtil.getConnection();
		state = con.createStatement();
		ArrayList<String[]> list = new ArrayList<String[]>();
		   
		String id = request.getParameter("id");
		System.out.println(id);
		rs = state.executeQuery("SELECT  *  FROM `test` WHERE nsr_id ="+id);
		
		while (rs.next()) {
			String[] temp={rs.getString("nsr_id"),rs.getString("gf_id"),rs.getString("xf_id")};
			list.add(temp);
		}
		
		ObjectMapper mapper = new ObjectMapper(); // 提供java-json相互转换功能的类

		String json = mapper.writeValueAsString(list); // 将list中的对象转换为Json格式的数组

		System.out.println(json);

		// 将json数据返回给客户端
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(json);
		DBUtil.close(rs, state, con);
	}
}
