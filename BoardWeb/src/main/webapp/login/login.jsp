<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login </title>
</head>
<body>
<center>
	<h1>로그인</h1>
	<hr>
	<form action="../login.do" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange">아이디</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">아이디</td>
				<td><input type="text" name="password"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인"/>
				</td>
			</tr>
		</table>	
	</form>
<hr/>
</center>
</body>
</html>