<%@page import="java.util.ArrayList"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO2"%>
<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

/* 	BoardVO vo = new BoardVO();
	BoardDAO2 boardDao = new BoardDAO2();
	List<BoardVO> lists = new ArrayList<BoardVO>();
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
	} */
	List<BoardVO> lists =(List<BoardVO>)request.getAttribute("lists");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board list</title>
</head>
<body>
	<center>
		<h1>board list</h1>
		<h3><%=request.getParameter("user")%></h3>

		<form action="getBoardList.do" method="get">
			<input type="hidden" name="user" value="<%=request.getParameter("user")%>"/>
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right"><select name="seachCondition">
							<option value="Title">제목
							<option value="Content">내용
					</select> <input name="searchKeyword" type="text" /> <input type="submit"
						value="검색" /></td>
				</tr>

			</table>
		</form>

		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="100">제목</th>
				<th bgcolor="orange" width="100">작성자</th>
				<th bgcolor="orange" width="100">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>

			<%
			int count = lists.size();
			for (BoardVO user : lists) {
			%>
			<tr>
				<td><%=count%></td>
				<td><a href="getBoard.do?seq=<%=user.getSeq()%>&user=<%=request.getParameter("user")%>"><%=user.getTitle()%></a></td>
				<td><%=user.getWriter()%></td>
				<td><%=user.getRegDate()%></td>
				<td><%=user.getCnt()%></td>
			</tr>
			<%
			count--;
			}
			%>
		</table>
		<br /> <a href="insertBoard.jsp?user=<%=request.getParameter("user")%>">새글 등록</a>
		</cneter>
</body>
</html>