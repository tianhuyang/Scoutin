<%@ page language="java" contentType="text/plain;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="org.apache.struts.action.ActionErrors"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="org.apache.struts.action.ActionMessage" %>
<%
    ActionErrors errors = (ActionErrors)request.getAttribute("actionErrors");
    Iterator<ActionMessage> it = errors.get();
    String message = "";
    while(it.hasNext())
    {
    	message += it.next().getValues()[0];	
    }
    int status = (message=="")?1:0;
    int user_id=0;
    if(message.length()==0)
    	user_id = (Integer)request.getAttribute("user_id");
%>

{status:<%=status%>,message:"<%=message%>",username:<%=user_id%>}
	
