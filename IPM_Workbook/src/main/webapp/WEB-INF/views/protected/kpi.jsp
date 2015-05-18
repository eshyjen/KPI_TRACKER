
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%
		response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	%>
	
	<%@page import="com.ericsson.v1.util.Constants"%>
	<%@include file="/WEB-INF/views/public/taglib.jsp"%>
	<fmt:setBundle basename="messages" />
<html>
	<head>
		<title>Home</title>
			<link href="${rootURL}/resources/bootstrap/css/bootstrap.css"
				media="screen" rel="stylesheet" type="text/css" />
				<link rel="stylesheet" href="${pageContext.request.contextPath }/css/layout.css" type="text/css" />
				<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript"
				src="${rootURL}/resources/jquery/jquery-1.10.2.js"></script>
			<script type="text/javascript"
				src="${rootURL}/resources/bootstrap/js/bootstrap.js"></script>
			<script type="text/javascript" src="${rootURL}/resources/js/app.js"></script>
			
	</head>
	
	
		<div id="dataview" style="position: relative; float: left width :   100%; height: 20%; background-color: #ffffff; padding: 12px 12px 12px 0px">
			<form name="myform" id="myform">
			<FONT face="Calibri" size="2">
				<h3 align="center">Asset Details Page</h3>
			</FONT> 
			<a id="add" onclick="addformChanged();">
				<img src="<%=request.getContextPath()%>/skin/images/add_button.gif" />
			</a>

				<%-- <div id="tablediv">
				
	
					 <display:table id="usertable" name="<%=Constants.KPI_LIST %>" class="list" sort="page" pagesize="5" 
					 	export="true" requestURI="kpiDetails.html"  >
				   	 <display:column title="Edit/Delete"  paramProperty="id" paramId="id">
				   	 <a href="javascript:checkRow(${usertable_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/edit_icon.gif" title="Edit" border="0"></a>
				   	 <img src="<%=request.getContextPath()%>/skin/images/spacer.gif">
				   	 <a href="javascript:checkDelete(${usertable_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/remove_icon.gif" title="Remove" border="0"></a>
				   	 </display:column>
					 <display:column property="id" sortable="true" title="Id"/>
				     <display:column property="kpiDisplayName" title="Kpi Display Name" sortable="true" sortProperty="kpiDisplayName"/>
				     <display:column property="kpiName" sortable="true" title="Kpi Name"/>
				     <display:column property="role" title="Role" sortable="true" />
				     <display:column property="kpiDescription" title="Kpi Description" sortable="true" />
				     </display:table>
				</div> --%>
				
				
				<div id="tablediv1">	
	
					 <display:table id="usertable1" name="<%=Constants.KPI_LIST1 %>" class="list" sort="page" pagesize="5" 
					 	export="true" requestURI="kpisDetails.html"  >
				   	 <display:column title="Edit/Delete"  paramProperty="id" paramId="id">
				   	 <a href="javascript:checkRow(${usertable1_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/edit_icon.gif" title="Edit" border="0"></a>
				   	 <img src="<%=request.getContextPath()%>/skin/images/spacer.gif">
				   	 <a href="javascript:checkDelete(${usertable1_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/remove_icon.gif" title="Remove" border="0"></a>
				   	 </display:column>
					 <display:column property="id" sortable="true" title="Id" />
				     <display:column property="kpiDisplayName" title="Kpi Display Name" sortable="true"/>
				    <%--  <display:column property="kpiName" sortable="true" title="Kpi Name"/> --%>
				     <display:column property="kpiValueSWD" title="SW Developer" sortable="true" />
				     <display:column property="kpiValueSSWD" title="Senior SW Developer" sortable="true" />
				     <display:column property="kpiValueSA" sortable="true" title="SW Architect"/>
				     <display:column property="kpiValueSSA" title="Senior SW Architect" sortable="true"/>
				     <display:column property="kpiValueSPM" sortable="true" title="SPM/People Manager"/>
				     <display:column property="kpiValueCA" sortable="true" title="Design Authority/Chief Architect"/>
				     <display:column property="kpiValueRPM" title="Regional CAC Program Manager" sortable="true" />
				     <display:column property="kpiValueSDL" sortable="true" title="Sub-domain Lead"/>
				     <display:column property="kpiValueDL" title="Domain Lead" sortable="true" />
				     </display:table>
				</div>
				
			</form>
			<p></p>
		</div>
	
	
	<!-- 2nd div start here-->
			<div id="2" style="position: relative;float: left;background-color:#ffffff;BORDER-RIGHT: #999999 2px solid; BORDER-TOP: #999999 2px solid; DISPLAY: none; Z-INDEX: 10;  BORDER-LEFT: #999999 2px solid; WIDTH: 80%; BORDER-BOTTOM: #999999 2px solid">
				<h2><spring:message code="lbl.page" text="Add New Employee" /></h2>
    <br/>
		</div>
	
</html>