<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>


<%@include file="taglib.jsp" %>
<%@ page session="true"%>

<html>

<head>
<link href="${rootURL}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title><spring:message code="label.pages.home.title"></spring:message></title>
</head>
<body>
	<div class="container">
		<div class="span12">
			<div id="success">
				<spring:message code="message.regSucc"></spring:message>
			</div>
			<a href="<c:url value="login.html" />"><spring:message
					code="label.login"></spring:message></a>
		</div>
	</div>
</body>
</html>