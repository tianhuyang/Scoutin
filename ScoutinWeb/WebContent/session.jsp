<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.scoutin.entities.Account" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  
	Account account = new Account();
	account.setAccountId(1);
	account.setEmail("haocai@usc.edu");
	session.setAttribute("user",account);
	//Account account = (Account)session.getAttribute("user"); out.print(account.getEmail());
%>
</body>
</html>