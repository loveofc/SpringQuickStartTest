package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO implements UserService{

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs ;
	
	String insert =	"insert into users values(?,?,?,?)";
	String update = "update users set id=?, password=?, name =?, role=?";
	String delete ="delete users where id=?, password=?";
	String getUser = "select * from user where id=? or name = ?";
	String getUserList = "select * from users order by name desc";

	@Override
	public void insertUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(insert);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt,conn);
		}		
	}

	@Override
	public void updateUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(update);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt,conn);
		}		
		
	}

	@Override
	public void deleteUser(UserVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(delete);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt,conn);
		}
		
	}

	@Override
	public UserVO getUser(UserVO vo) {
		UserVO userVo = new UserVO();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(getUser);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getName());
			rs=stmt.executeQuery();		
			if(rs != null) {
				userVo.setId(rs.getString("id"));
				userVo.setPassword(rs.getString("password"));
				userVo.setName(rs.getString("name"));
				userVo.setRole(rs.getString("role"));
			}else {
				userVo = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt,conn);
		}
		return userVo;
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		List<UserVO> lists = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(getUserList);
			rs=stmt.executeQuery();		
			while(rs.next()) {
				UserVO userVo = new UserVO();
				userVo.setId(rs.getString("id"));
				userVo.setPassword(rs.getString("password"));
				userVo.setName(rs.getString("name"));
				userVo.setRole(rs.getString("role"));
				lists.add(userVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt,conn);
		}
		return lists;
	}

}
