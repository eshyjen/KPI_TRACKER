
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



		
		<div id="userDetails">
		<ul>
			<li>
			<fmt:message key="label.user.userFristName"/>: ${userProfile.userFristName}
			</li>
			<li>
			<fmt:message key="label.user.userLastName"/>: ${userProfile.userLastName}
			</li>
			<li>
			<fmt:message key="label.user.costCenter"/>: ${userProfile.costCenter}
			</li>
			<li>
			<fmt:message key="label.user.role"/>: ${userProfile.role}
			</li>
			<li>
			<fmt:message key="label.user.currentLineManager"/>: ${userProfile.currentLineManager}
			</li>
			<li>
			<fmt:message key="label.user.currentLineManager"/>: ${userProfile.currentLineManager}
			</li>
			<li>
			<fmt:message key="label.user.dateOfJoinInMediaAccount"/>: ${userProfile.dateOfJoinInMediaAccount}
			</li>
			<li>
			<fmt:message key="label.user.educationalQualification"/>: ${userProfile.educationalQualification}
			</li>
			<li>
			<fmt:message key="label.user.emailId"/>: ${userProfile.emailId}
			</li>
			<li>
			<fmt:message key="label.user.employeeId"/>: ${userProfile.employeeId}
			</li>
			<li>
			<fmt:message key="label.user.jobRole"/>: ${userProfile.jobRole}
			</li>
			<li>
			<fmt:message key="label.user.jobStage"/>: ${userProfile.jobStage}
			</li>
			<li>
			<fmt:message key="label.user.lastYearIPMRating"/>: ${userProfile.lastYearIPMRating}
			</li>
			<li>
			<fmt:message key="label.user.manHourRate"/>: ${userProfile.manHourRate}
			</li>
			<li>
			<fmt:message key="label.user.modifiedDate"/>: ${userProfile.modifiedDate}
			</li>
			<li>
			<fmt:message key="label.user.previousLineManeger"/>: ${userProfile.previousLineManeger}
			</li>
			<li>
			<fmt:message key="label.user.previousOrganisation"/>: ${userProfile.previousOrganisation}
			</li>
			<li>
			<fmt:message key="label.user.signunId"/>: ${userProfile.signunId}
			</li>
			<li>
			<fmt:message key="label.user.totalEricssonExperienceInMonths"/>: ${userProfile.totalEricssonExperienceInMonths}
			</li>
			<li>
			<fmt:message key="label.user.totalITExperience"/>: ${userProfile.totalITExperience}
			</li>
			<li>
			<fmt:message key="label.user.totalYearsOfExperience"/>: ${userProfile.totalYearsOfExperience}
			</li>
			<li>
			<fmt:message key="label.user.yearOfIPM"/>: ${userProfile.yearOfIPM}
			</li>
			<li>
			<fmt:message key="label.user.yearOfLastPromotion"/>: ${userProfile.yearOfLastPromotion}
			</li>
			
			
			<%-- <li>
				<fmt:message key="label.Account.receiveNewsletter"/>: 
				<fmt:message key="label.${account.owner.receiveNewsletter}"/>
			</li> --%>
			
			<%-- <li>
				<fmt:message key="label.Account.dateOfBirth"/>: <fmt:formatDate value="${account.owner.dateOfBirth}"/>
			</li> --%>
			
			<%-- <li>
				<fmt:message key="label.Account.creditCardNumber"/>:
				<c:choose>
					<c:when test="${!empty account.creditCardNumber}">
						${account.creditCardNumber}
					</c:when>
					<c:otherwise>N/A</c:otherwise>
				</c:choose>
			</li> --%>
		</ul>
	</div>
		
		


		</body>
	</html>