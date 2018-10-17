package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



/**
 * ���ݿ⹤���࣬�����������ݿ⣬ִ��SQL��䣬�ر����ݿ����ӵȡ�
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
	 * ����һ�����ݿ�����Ӷ����Ӧ��
	 * @return
	 */
	public static Connection getConnection(){
		 Connection dbConn=null;
		 try
		{
			Class.forName(driverName);
			//System.out.println("���������ɹ���");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}
		try{
			dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
				//System.out.println("�������ݿ�ɹ���");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ��������ӣ�","����",  JOptionPane.ERROR_MESSAGE);
		}
		return dbConn;	
	}
	
	/**
	 * �ر����ݿ�����Ӷ���SQL������
	 * @param state sql������
	 * @param con	���ݿ����Ӷ���
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
	 * �ر����ݿ�����Ӷ���SQL�����󣬲�ѯ���������
	 * 
	 * @param rs		���������
	 * @param state		Statement����
	 * @param con		Connection����
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
