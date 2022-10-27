<%@page import="com.springbook.biz.board.impl.BoardDAO2"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String sqe = request.getParameter("sqe");
	String user = request.getParameter("user");

	BoardVO vo = new BoardVO();
	BoardDAO2 dao = new BoardDAO2();
	
	vo.setSeq(Integer.parseInt(sqe));	
	dao.deleteBoard(vo);
	
	response.sendRedirect("getBoardList.jsp?user"+user);

%>