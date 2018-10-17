package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.dataBean;
import Bean.yfJshjBean;
import util.DBUtil;

public class dataDao {
	
	/*
	 * 根据企业id，找到每个企业每个月的销售额
	 */
	public List<yfJshjBean> yfjshjX(String id) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select xf_id,kpyf,sum(jshj) jshj from test where xf_id=7010 GROUP BY xf_id,kpyf" ;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<yfJshjBean> list = new ArrayList<yfJshjBean>();
		while(rs.next())
		{
			String nsr_id = rs.getString("xf_id");
			String kpyf = rs.getString("kpyf");
			double jshj = rs.getDouble("jshj");
			yfJshjBean bean = new yfJshjBean(nsr_id,jshj,kpyf);
			System.out.println(bean);
			list.add(bean);
		}
		return list;
	}
	
	/**
	 * 根据企业id，找到每个企业每个月的购项额
	 */
	public List<yfJshjBean> yfjshjG(String id) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "select gf_id,kpyf,sum(jshj) jshj from test where xf_id=7010 GROUP BY gf_id,kpyf;" ;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<yfJshjBean> list = new ArrayList<yfJshjBean>();
		double[] jshj1 = new double[12]; 
		while(rs.next())
		{
			String nsr_id = rs.getString("gf_id");
			String kpyf = rs.getString("kpyf");
			double jshj = rs.getDouble("jshj");
			yfJshjBean bean = new yfJshjBean(nsr_id,jshj,kpyf);
			list.add(bean);
		}
		return list;
	}
	/***
	 * 某个行业的平均进项金额
	 */
	
}
