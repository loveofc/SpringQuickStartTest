<%@page import="com.springbook.biz.board.impl.BoardDAO2"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	/* String seq = "";
	if(request.getParameter("seq") != null){
		seq = request.getParameter("seq");
	}else if(request.getAttribute("seq") !=null){
		seq =(String)request.getAttribute("seq");
	}
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	
	BoardDAO2 boardDAO = new BoardDAO2(); */
	BoardVO board= (BoardVO)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
</head>
<body>
<center>
	<h1>글 상세</h1>
	<a href="../login/logout_process.jsp">Log-out</a>
	<hr/>
	<form action="updateBoard_process.jsp" method="post">
		<input type="hidden" name="seq" value="<%=board.getSeq() %>"/>
		<input type="hidden" name="user" value="<%=request.getParameter("user")%>"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					<input name="title" type="text" value="<%=board.getTitle()%>"/>
				</td>
			</tr>
				<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left">
					<%=board.getWriter() %>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" >내용</td>
				<td align="left">
					<textarea name="content" cols="40" rows="10"><%=board.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" >등록일</td>
				<td align="left">
					<%=board.getRegDate() %>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">조회수</td>
				<td align="left">
					<%=board.getCnt() %>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 수정"/>
				</td>
			</tr>		
		</table>	
	</form>
	<hr/>
	<a href="insertBoard.jsp?user=<%=request.getParameter("user")%>">글등록</a><br/>
	<a href="deleteBoard_process.jsp?sqe=<%=board.getSeq() %>">글삭제</a><br/>
	<a href="getBoardList.jsp?user=<%=request.getParameter("user")%>">글목록</a>

</center>
</body>
</html>