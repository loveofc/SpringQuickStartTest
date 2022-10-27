<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록</title>
</head>
<body>
<center>
	<h1>글 등록</h1>
	<a href="../login/logout_process.jsp">Log-out</a>
	<hr/>
	<form action="insertBoard.do" method="post">
		<input type="hidden" name="user" value="<%=request.getParameter("user")%>"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					<input name="title" type="text" value=""/>
				</td>
			</tr>
				<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><input name="writer" type="text" value=""/></td>
			</tr>
			<tr>
				<td bgcolor="orange" >내용</td>
				<td align="left">
					<textarea name="content" cols="40" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 등록"/>
				</td>
			</tr>		
		</table>	
	</form>
	<hr/>
	<a href="getBoardList.jsp?user=<%=request.getParameter("user")%>">글목록</a>

</center>
</body>
</html>