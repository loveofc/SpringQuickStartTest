<%@page import="com.springbook.biz.user.impl.UserDAO"%>
<%@page import="com.springbook.biz.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String id= request.getParameter("id");
	String pwd = request.getParameter("password");
	
	UserVO vo = new UserVO();
	vo.setId(id);
	vo.setPassword(pwd);
	UserDAO userDao = new UserDAO();	
	UserVO user = userDao.getUser(vo);
	
	if(user != null){
		response.sendRedirect("../board/getBoardList.jsp?user="+user.getId());
	}else{
		response.sendRedirect("login.jsp");
	}

%>