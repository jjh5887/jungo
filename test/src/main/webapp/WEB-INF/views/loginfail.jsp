<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Login Fail</title>
	<meta charset = "utf-8">
</head>
<body>
ID : ${account.id}
	<br /> PW : ${account.password}
	<br /> 잔액 : ${account.balance}
	<br />
	<h1> 없는 계정이거나 비밀번호가 일치하지 않습니다! </h1>
	<a href="/test/resources/html/index.html"> 메인페이지로 돌아가기 </a>
</body>
</html>