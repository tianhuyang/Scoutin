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
%>

{status:<%=status%>,message:"<%=message%>",username:"haocai"}
	
