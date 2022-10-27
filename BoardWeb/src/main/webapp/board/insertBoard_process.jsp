<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	String user = request.getParameter("user");

	BoardVO vo = new BoardVO();
	BoardDAO2 dao = new BoardDAO2();
	
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	
	dao.insertBoard(vo);
	
	response.sendRedirect("getBoardList.jsp?user="+user);

%>