package com.springbook.view.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO2;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		BoardDAO2 boardDAO = new BoardDAO2();
		
		if(path.equals("/login.do")) {
			String id= request.getParameter("id");
			String pwd = request.getParameter("password");
			
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(pwd);
			UserDAO userDao = new UserDAO();	
			UserVO user = userDao.getUser(vo);
			
			if(user != null){
				response.sendRedirect("getBoardList.do?user="+user.getId());
			}else{
				response.sendRedirect("/login.jsp");
			}
		}else if(path.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/login.jsp");
		}else if(path.equals("/insertBoard.do")) {
			request.setCharacterEncoding("utf-8");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			String user = request.getParameter("user");

			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriter(writer);
			
			boardDAO.insertBoard(vo);
			
			response.sendRedirect("getBoardList.do?user="+user);
		}else if(path.equals("/updateBoard.do")) {
			request.setCharacterEncoding("utf-8");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			String user = request.getParameter("user");
			BoardVO vo = new BoardVO();
			
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSeq(Integer.parseInt(seq));
			
			int i = boardDAO.updateBoard(vo);
			if(i>0){
				response.sendRedirect("getBoard.jsp?seq="+vo.getSeq()+"&user="+user);
			}else{
				response.sendRedirect("getBoardList.jsp?user="+user);
			} 
		}else if(path.equals("/deleteBoard.do")) {
			request.setCharacterEncoding("utf-8");
			String sqe = request.getParameter("sqe");
			String user = request.getParameter("user");

			BoardVO vo = new BoardVO();			
			vo.setSeq(Integer.parseInt(sqe));	
			boardDAO.deleteBoard(vo);
			
			response.sendRedirect("getBoardList.jsp?user"+user);

		}else if(path.equals("/getBoard.do")) {
			String seq = request.getParameter("seq");
			String user = request.getParameter("user");
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardVO board= boardDAO.getBoard(vo);
			request.setAttribute("board", board);
			request.getRequestDispatcher("getBoard.jsp?user="+user).forward(request, response);
		}else if(path.equals("/getBoardList.do")) {
			BoardVO vo = new BoardVO();
			BoardDAO2 boardDao = new BoardDAO2();
			List<BoardVO> lists = new ArrayList<BoardVO>();
			String user = request.getParameter("user");
			String seachCondition = request.getParameter("seachCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			if (seachCondition != null && seachCondition.equalsIgnoreCase("Content") && !searchKeyword.equals("")) {
				
					vo.setContent(searchKeyword);
					lists = boardDao.getBoardByContent(vo);
					
			} else if (seachCondition != null && seachCondition.equalsIgnoreCase("Title") && !searchKeyword.equals("")) {
				
					vo.setTitle(searchKeyword);
					lists = boardDao.getBoardByTitle(vo);
						
			} else {
				lists = boardDao.getBoardList(vo);
			}
			request.setAttribute("lists", lists);
			request.getRequestDispatcher("getBoard.jsp?user="+user).forward(request, response);
		}
	}

}
