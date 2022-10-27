package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;


@Repository("boardDAOSpirng")
public class BoardDAOSpring{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//SQL 명령들
	private final String Board_insert = 
			"insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
			//max()함수는 컬럼 중 최고 큰 값을 가지고온다.(select nvl(max(seq),0)+1 from board)
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
		jdbcTemplate.update(Board_insert,vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(Board_update,vo.getTitle(),vo.getContent(),vo.getSeq());
	}
	
	
	
	//글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(Board_delete,vo.getSeq());
	}
	
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(Board_get, args, new BoardRowMapper());
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		
		return jdbcTemplate.query(Board_list,  new BoardRowMapper());
	}
	
	class BoardRowMapper implements RowMapper<BoardVO>{
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegDate(rs.getDate("regdate"));
			board.setCnt(rs.getInt("cnt"));			
			return board;
		}
	}
	
}
