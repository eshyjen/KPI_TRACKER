
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%
		response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	%>
	
	
	<%@include file="/WEB-INF/views/public/taglib.jsp"%>
	<fmt:setBundle basename="messages" />
<html>
	<head>
		<title>Home</title>
			<link href="${rootURL}/resources/bootstrap/css/bootstrap.css"
				media="screen" rel="stylesheet" type="text/css" />
				<link rel="stylesheet"
				href="${pageContext.request.contextPath }/css/layout.css" type="text/css" />
				<link href="${pageContext.request.contextPath }/css/style.css"
				rel="stylesheet" type="text/css" />
			<script type="text/javascript"
				src="${rootURL}/resources/jquery/jquery-1.10.2.js"></script>
			<script type="text/javascript"
				src="${rootURL}/resources/bootstrap/js/bootstrap.js"></script>
			<script type="text/javascript" src="${rootURL}/resources/js/app.js"></script>
	</head>
<body>
	<jsp:include page="/WEB-INF/views/public/include_header.jsp" />
		<div id="breadcrumb">
		    <ul>
		      <li class="first"><fmt:message key="breadcrumb.tag"/></li>
		      <li>&#187;</li>
		      <li><a href='<c:out value="${homeUrl}"/>'><fmt:message key="breadcrumb.tag.home"/></a></li>
		      <li>&#187;</li>
		      <li class="current"><fmt:message key="breadcrumb.tag.kpi.msg"/></li>
		    </ul>
		</div>
			<div class="container">
				<div class="row" style="height: 20px !important;"></div>
				<div class="row">
		
					<div id="userrights">
						<table class="table  table-hover" id="right_table">
							<thead>
								<tr>
									<th>#</th>
									<th>KPI Name</th>
									<!-- <th>Enabled</th> -->
									<th>KPI Value</th>
									<!-- <th>Roles</th>
									<th>Add Role</th>
									<th>Delete user</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${kpis}" var="kpi">
									<tr>
										<td>${kpi.id}</td>
										<td>${kpi.kpiName}</td>
										<%-- <td><input type="checkbox" checked="${user.enabled}"></input></td> --%>
										<td>${kpi.kpiValue}</td>
										<%-- <td><c:forEach items="${user.roles}" var="role">
												<c:url value="/removerole/${user.id}/${role.name}" var="url" />
												<form action="${url}" method="POST">
													<input type="submit" class="label" id="${role.name}"
														value="${role.name}" />
												</form>
											</c:forEach></td>
										 <td><c:forEach items="${roles}" var="role">
												<c:url value="/addrole/${user.id}/${role.name}" var="url" />
												<form action="${url}" method="POST">
													<input type="submit" class="label" id="${role.name}"
														value="${role.name}" />
												</form>
											</c:forEach></td>
		
		
										<td><c:url value="/delete/${user.id}" var="url"></c:url>
											<form action="${url}" method="POST">
												<button class="btn btn-primary" type="submit">Delete
													User</button>
											</form>
										</td> --%>
									</tr>
								</c:forEach>
							</tbody>
		
						</table>
					</div>
					
				</div>
		
			</div>
		</body>
	</html>