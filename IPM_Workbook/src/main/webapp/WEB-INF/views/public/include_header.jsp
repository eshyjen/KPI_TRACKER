
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@include file="/WEB-INF/views/public/taglib.jsp"%>
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
			
			
			<td>
			<table>
 <tr>
  <td>
   Example of 			
  </td>
  <td>
   <ul class="dropv">
   <li><a href="#">drop-down links</a> in imbedded text.	
    <ul>
    	<c:forEach items="${sessionScope.kpiDisplayNames}" var="kdn">
    	<li><a href="${kdn.id}">${kdn.name}</a></li>
    	</c:forEach>
     </ul>
    </li>
   </ul>			
  </td>	
 </tr>
</table>
			
			
			</td>
			
			
			<td width="50%" style="padding-right:12px"><% if (request.getRemoteUser() != null) {%><div align="right">
			<a class="logout" href='<c:url value="${rootURL}/v1/public/logout.html"/>'>Logout</a></div><% } %></td>
		</tr>
	</table>
	
</div>

