package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;


@Repository("boardDAO")
public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//SQL 명령들
	private final String Board_insert = 
			"insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
			//max()함수는 컬럼 중 최고 큰 값을 가지고온다.
			//nvl("왼쪽","오른쪽")함수는 "왼쪽"값이 null이면 "오른쪽"값이 지정되고 null이 아니면"왼쪽"값 그대로 나온다.
			//seq컬럼에서 최고값가져와서 1를 더한값을 seq에 insert한다란 뜻이다.
	private final String Board_update = 
			"update board set title=?, content =? where seq =?";
	
	private final String Board_delete = "delete board where seq=?";
	private final String Board_get = "select * from board where seq=?";
	private final String Board_list = "select * from board order by seq desc";
	
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn =JDBCUtil.getConnection();
			stmt = conn.prepareStatement(Board_insert);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn =JDBCUtil.getConnection();
			stmt = conn.prepareStatement(Board_update);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 Board_delete() 기능 처리");
		try {
			conn =JDBCUtil.getConnection();
			stmt = conn.prepareStatement(Board_delete);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
	
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 Board_get() 기능 처리");
		BoardVO board = null;
		try {
			conn =JDBCUtil.getConnection();
			stmt = conn.prepareStatement(Board_get);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();	
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 Board_get() 기능 처리");
		List<BoardVO> lists = new ArrayList<BoardVO>();
		try {
			conn =JDBCUtil.getConnection();
			stmt = conn.prepareStatement(Board_list);
			rs = stmt.executeQuery();	
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
				lists.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return lists;
	}
	
}
