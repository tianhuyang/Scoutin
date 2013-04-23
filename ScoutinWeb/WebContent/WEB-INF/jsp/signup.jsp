<%@ page language="java" contentType="application/json;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.opensymphony.xwork2.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
{
<s:iterator value="actionErrors">
	<s:property escape="false" />
</s:iterator>
<s:if test="fieldErrors.size()">,
   "fieldErrors":{
<s:iterator value="fieldErrors" status="s">
	"<s:property value="key" />":[
    <s:iterator value="value" status="ss">
		"<s:property />"
		<s:if test="!#ss.last">,</s:if>
		</s:iterator>
    ]
    <s:if test="!#s.last">,</s:if>
	</s:iterator>
   }
</s:if>
<s:if test="#session.user != null">,
   "user":{
   <s:iterator value="#session.user">
        "<s:property value="key" />":"<s:property value="value" />"
   </s:iterator>
   }
   </s:if>
}
