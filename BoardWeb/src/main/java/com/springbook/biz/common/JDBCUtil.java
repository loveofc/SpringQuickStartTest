package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		String driver ="org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/test";
		String id="sa";
		String pwd="";
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,id,pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection con) {
		try {
			if(rs != null) {
				if(!rs.isClosed()) rs.close();
			}
			
			if(stmt != null) {
				if(!stmt.isClosed()) stmt.close();
			}
			
			if(con != null) {
				if(!con.isClosed()) con.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
