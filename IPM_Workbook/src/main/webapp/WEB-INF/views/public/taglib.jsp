<%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!-- Get context path using JSTL -->

<c:set var="rootURL" value="${pageContext.servletContext.contextPath}" />

<c:set var="protectedURL" value="/v1/protected/" />

<c:url var="homeUrl" value="${protectedURL}ipmDashboard.html"/>

<c:url var="monthlyShortcodeUrl" value="${protectedURL}assetDetails.html"/>

<c:url var="periodicReportUrl" value="${protectedURL}ipmDashboard.html"/>


