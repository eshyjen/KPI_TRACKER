<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main.css">
<script type="text/javascript">
function back2PublicHome() {
	document.location.href = "<%=request.getContextPath()%>/index.action";
	return true;
}
function back2ProtectedHome() {
	document.location.href = "<%=request.getContextPath()%>/protected/welcome.action";
	return true;
}

</script>
<!-- commit to github -->
</head>
<div class="header">
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td class="header1"></td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td width="50%" class="welcome"><div align="left">Welcome <%
				if (request.getRemoteUser() != null) {
					out.print(request.getRemoteUser());
				} else {
					out.print("Anonymous");
				}
			%>
			</div></td>
			<td width="50%" style="padding-right:12px"><% if (request.getRemoteUser() != null) {%><div align="right">
			<a class="logout" href='<c:url value="${rootURL}/v1/public/logout.html"/>'>Logout</a></div><% } %></td>
		</tr>
	</table>

</div>

