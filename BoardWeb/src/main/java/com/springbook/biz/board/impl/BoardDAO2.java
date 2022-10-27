package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;


@Repository("boardDAO2")
public class BoardDAO2 {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	//SQL 명령들
	private final String Board_insert = 
			"insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
			//max()함수는 컬럼 중 최고 큰 값을 가지고온다.
			//nvl("왼쪽","오른쪽")함수는 "왼쪽"값이 null이면 "오른쪽"값이 지정되고 null이 아니면"왼쪽"값 그대로 나온다.
			//seq컬럼에서 최고값가져와서 1를 더한값을 seq에 insert한다란 뜻이다.
	private final String Board_update = 
			"update board set title= ?, content = ? where seq =?";
	
	private final String Board_delete = "delete board where seq=?";
	private final String Board_get = "select * from board where seq=?";
	private final String Board_list = "select * from board order by seq desc";
	
	
	
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(Board_insert);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getWriter());
			ps.setString(3,vo.getContent());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
	}
	
	//글 수정
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(Board_update);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3,vo.getSeq());
			i=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
		return i;
	}
	
	
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(Board_delete);
			ps.setInt(1, vo.getSeq());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
	}
	
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		BoardVO v = new BoardVO();
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(Board_get);
			ps.setInt(1,vo.getSeq());
			rs = ps.executeQuery();
			while(rs.next()) {
				v.setSeq(rs.getInt("seq"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setWriter(rs.getString("writer"));
				v.setCnt(rs.getInt("cnt"));
				v.setRegDate(rs.getDate("regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
		return v;
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(Board_list);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO v = new BoardVO();
				v.setSeq(rs.getInt("seq"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setWriter(rs.getString("writer"));
				v.setCnt(rs.getInt("cnt"));
				v.setRegDate(rs.getDate("regdate"));
				list.add(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
		return list;
	}
	
	public List<BoardVO> getBoardByTitle(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardByTitle() 기능 처리");
		
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement("select * from board where title like ?");
			ps.setString(1, "%"+vo.getTitle()+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO v = new BoardVO();
				v.setSeq(rs.getInt("seq"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setWriter(rs.getString("writer"));
				v.setRegDate(rs.getDate("regdate"));
				v.setCnt(rs.getInt("cnt"));
				list.add(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
		return list;
	}
	
	public List<BoardVO> getBoardByContent(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardByContent() 기능 처리");
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement("select * from board where content like ?");
			ps.setString(1, "%"+vo.getContent()+"%");
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO v = new BoardVO();
				v.setSeq(rs.getInt("seq"));
				v.setTitle(rs.getString("title"));
				v.setContent(rs.getString("content"));
				v.setWriter(rs.getString("writer"));
				v.setCnt(rs.getInt("cnt"));
				v.setRegDate(rs.getDate("regdate"));
				list.add(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,ps,conn);
		}
		return list;
	}
	
}
