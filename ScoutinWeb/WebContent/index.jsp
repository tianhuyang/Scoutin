<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="test.*"%>
<%@ page import="com.scoutin.utilities.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<%
		TestRemote tr = (TestRemote)EJBUtils.obtainBean("ScoutinApplication/Scoutin/Test!test.TestRemote");
	    if(tr != null){
	    	//out.println(tr.getResult());
	    }
	    %>
	</h1>
</body>
</html>