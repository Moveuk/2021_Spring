<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange"><b>기본 에러 화면입니다.</b></td>
		</tr>
	</table>
	<br>
	<table width="100%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange">
				Message : ${exception.message }
			</td>
		</tr>
	</table>
	
</body>
</html>