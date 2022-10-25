package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO baordDao;
	
	@Override
	public void insertBoard(BoardVO vo) {
		baordDao.insertBoard(vo);		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		baordDao.updateBoard(vo);		
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		baordDao.deleteBoard(vo);	
	}

	@Override
	public void getBoard(BoardVO vo) {
		baordDao.getBoard(vo);	
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> lists = baordDao.getBoardList(vo);
		return lists;
	}

}
