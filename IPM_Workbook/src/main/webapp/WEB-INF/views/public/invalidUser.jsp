<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>


<%@include file="taglib.jsp" %>
<fmt:setBundle basename="messages" />
<%@ page session="true"%>
<html>
<head>
<link href="${rootURL}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
	<title><spring:message code="label.badUser.title"></spring:message></title>
</head>
<body>
<h1>
<div class="alert alert-error">
				 ${param.message}
				 </div>
</h1>
<br>
<a href="<c:url value="/v1/public/registration.html" />"><spring:message
code="label.form.loginSignUp"></spring:message></a>

<c:if test="${param.expired}">
<br>
<h1>${label.form.resendRegistrationToken}</h1>
<button onclick="resendToken()">
	<spring:message code="label.form.resendRegistrationToken"></spring:message>
</button>


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
function resendToken(){
	$.get("<c:url value="/resendRegistrationToken.html"><c:param name="token" value="${param.token}"/></c:url>", function(data){
		window.location.href = "<c:url value="/login.html"></c:url>" + "?message=" + data.message;
    })
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1)
        {
            window.location.href = "<c:url value="/emailError.html"></c:url>";
        }
        else{
            window.location.href = "<c:url value="/login.html"></c:url>" + "?message=" + data.responseJSON.message;
        }
    });
}

$(document).ajaxStart(function() {
    $("title").html("LOADING ...");
});
</script>
</c:if>
</body>
</html>
