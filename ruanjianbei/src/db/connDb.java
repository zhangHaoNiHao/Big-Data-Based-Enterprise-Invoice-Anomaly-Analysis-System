package db;
import java.sql.*;
import java.util.ArrayList;

import util.DBUtil;

 
public class connDb {
	private static Connection con;
	private static Statement state;
	private static ResultSet rs;
 
   
    public static ArrayList<String> index() throws SQLException{
        ArrayList<String> list = new ArrayList<String>();
        con = DBUtil.getConnection();
        state = con.createStatement();
        rs = state.executeQuery("SELECT  COUNT(*) as num FROM `temp2` WHERE label !=0 ");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        rs = state.executeQuery("SELECT  COUNT(*) as num FROM `temp2` WHERE label = 0 ");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        rs = state.executeQuery("SELECT  COUNT(*) FROM `temp2` WHERE prediction BETWEEN '0.002863430980373005' AND '0.011463756102660334' ");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        rs = state.executeQuery("SELECT  COUNT(*) FROM `temp2` WHERE prediction BETWEEN '0.005' AND '0.008' ");
        while(rs.next()){
            list.add(rs.getString(1));
        }
        DBUtil.close(rs, state, con);
        return list;
    }
   
}
