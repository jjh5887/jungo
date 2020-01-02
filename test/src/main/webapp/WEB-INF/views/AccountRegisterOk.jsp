<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1> AccountRegisterOk </h1>
	 
	ID : ${account.id}<br />
	PW : ${account.password}<br />
	잔액 : ${account.balance} <br />
	
	
	<a href="/lec18/resources/html/memJoin.html"> Go MemberJoin </a>
</body>
</html>