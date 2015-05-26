

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>


<%@include file="taglib.jsp" %>
<%@ page session="false"%>
<<html>
<head>

<link href="${rootURL}/resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title><spring:message code="label.form.title"></spring:message></title>

<script>

function validateForm() {
  alert('in fname function');  
var x = document.forms["myForm"]["fname"].value;
    if (x == null || x == "") {
        alert('First name must be filled out');
        return false;
    }
	else
	{
	if(!(/^[a-zA-Z]+$/).test(x))
	{
		alert('Enter letters only');
		return false;
	}
	return true;
}
}
 


function validate1Form() {
    	alert('in lname function'); 
	var x = document.forms["myForm"]["lname"].value;
    if (x == null || x == "") {
        alert('Last name must be filled out');
        return false;
    }
	else
	{
	if(!(/^[a-zA-Z]+$/).test(x))
	{
		alert('Enter letters only');
		return false;
	}
	return true;
}
}
function validateEmail() {
    var x = document.forms["myForm"]["email"].value;
	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
     if(!(re.test(x)))
	{	alert('enter valid email id');
   		return false;
	}
     else
		return true;
}
 
</script>


</head>


<body>
<div class="container">
		<div class="span12">
		<h1 align="center"><font face="THE TIMES NEW ROMAN"color="#0B2F3A">
	<spring:message code="label.form.title"></spring:message></font></h1>
<center>
<form:form modelAttribute="registrationDTO" method="POST" action="registration.html" enctype="utf8" name="myForm" >

	
		<br>
				 <table>
				<tr>
					<td><label><spring:message code="label.user.firstName"></spring:message></label></td>
					<td><form:input path="userFristName" value="" size="26"/></td>
					<td><form:errors path="userFristName" cssClass="alert alert-error"/></td>
				</tr>
				<tr>
					<td><label><spring:message code="label.user.lastName"></spring:message></label></td>
					<td><form:input path="userLastName" value="" size="26"/></td>
					<td><form:errors path="userLastName" cssClass="alert alert-error"/></td>
				</tr>
				<tr>
					<td><label><spring:message code="label.user.email"></spring:message></label></td>
					<td><form:input path="emailId" value="" size="26"/></td>
					<td><form:errors path="emailId" cssClass="alert alert-error"/></td>
				</tr>
				
				<tr>
         	<td><label><spring:message code="label.form.loginRole" /></label></td>
         	<td>
			<form:select path="userSelectedRole">
				<c:forEach items="${roles}" var="rol">
					<option value="${rol.id}">${rol.name}</option>
				</c:forEach>
			</form:select>
			</td>
			<td><form:errors path="userSelectedRole" cssClass="alert alert-error" /></td>
		</tr>
				
				<tr>
					<td><label><spring:message code="label.user.password"></spring:message></label></td>
					<td><form:input path="password" value="" type="password" size="26"/></td>
					<td><form:errors path="password" cssClass="alert alert-error"/></td>
				</tr>
				<tr>
					<td><label><spring:message code="label.user.confirmPass"></spring:message></label></td>
					<td><form:input path="matchingPassword" value="" type="password" size="26"/></td>
					<td><form:errors cssClass="alert alert-error" /></td>
				</tr>
				<tr>
					<td><label><spring:message code="label.user.form.captchaCode"></spring:message></label>
					</td>
					<td><img
					src='<%=request.getContextPath()%>/<c:url value="simpleCaptcha.png" />' /></td>
					<td></td>
				</tr>
				<tr>
					<td><label><spring:message code="label.user.captchaCode"></spring:message></label></td>
					<td><form:input path="captchaCode" size="26"/></td>
					<td><form:errors path="captchaCode" cssClass="alert alert-error" /></td>
				</tr>
				
				<tr>
				<td><button type="submit" class="btn btn-primary">
					<spring:message code="label.form.submit"></spring:message>
				</button></td>
				<td><button type="Reset" class="btn btn-primary">
					<spring:message code="label.form.reset"></spring:message>
				</button></td>
				<td></td>
				</tr>
				</table>
			</form:form>
			</center>
			<br> <a href="<c:url value="login.html" />"><spring:message code="label.form.loginLink"></spring:message></a>
		</div>
	</div>

</body>
</html>













