package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



/**
 * 数据库工具类，用来连接数据库，执行SQL语句，关闭数据库连接等。
 * @version 1
 */
public class DBUtil {

	public DBUtil() {
	}
	
	 private static String driverName="com.mysql.jdbc.Driver";
	 private  static String dbURL="jdbc:MySQL://localhost:3306/hive?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	 private  static String userName="root";
	 private  static String userPwd="123456";
	
	
	/**
	 * 返回一个数据库的连接对象的应用
	 * @return
	 */
	public static Connection getConnection(){
		 Connection dbConn=null;
		 try
		{
			Class.forName(driverName);
			//System.out.println("加载驱动成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}
		try{
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				//System.out.println("连接数据库成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
			JOptionPane.showMessageDialog(null, "数据库连接失败！请检查连接！","错误",  JOptionPane.ERROR_MESSAGE);
		}
		return dbConn;	
	}
	
	/**
	 * 关闭数据库的连接对象，SQL语句对象
	 * @param state sql语句对象
	 * @param con	数据库连接对象
	 */
	public static void close(Statement state,Connection con){
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
			}
		}
		
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
			}
	}
	
	/**
	 * 关闭数据库的连接对象，SQL语句对象，查询结果集对象
	 * 
	 * @param rs		结果集对象
	 * @param state		Statement对象
	 * @param con		Connection对象
	 */
	public static void close(ResultSet rs,java.sql.Statement state,Connection con){
		
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if(state!=null)
			try {
				state.close();
			} catch (SQLException e) {
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		
	}
	
}
