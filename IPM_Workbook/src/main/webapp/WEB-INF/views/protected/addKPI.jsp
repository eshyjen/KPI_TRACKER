
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
	href="${pageContext.request.contextPath }/css/layout.css"
	type="text/css" />
<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${rootURL}/resources/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${rootURL}/resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${rootURL}/resources/js/app.js"></script>


<script>
	function displayVals() {
		//var singleValues = $("#single").val();
		var multipleValues = $("#userSelectedRoles").val() || [];
		var selectedRoles = multipleValues.join(", "));
		  alert("selectedRoles : "+selectedRoles);
		  
		    //var multipleValues1 = $("#userSelectedRoles option:selected").text() || [];
			//var selectedRoles1 = multipleValues1.join(", "));
			 // alert("selectedRoles1 : "+selectedRoles1);
	}
	$("select").change(displayVals);
	displayVals();
</script>

<script type="text/javascript">
 
$(document).ready(function(){
 
    var counter = 1;
 
    //$("#addButton").click(function () {
 
	//if(counter>10){
            //alert("Only 10 textboxes allow");
            //return false;
	//}   
 
	//var newTextBoxDiv = $(document.createElement('div'))
	     //.attr("id", 'TextBoxDiv' + counter);
 
	//newTextBoxDiv.after().html('<label>Textbox #'+ counter + ' : </label>' +
	      //'<input type="text" name="textbox' + counter + 
	     // '" id="textbox' + counter + '" value="" >');
 
	//newTextBoxDiv.appendTo("#TextBoxesGroup");
 
 
	//counter++;
    // });
    
    
    
       
	$('input[type="checkbox"]')
								.click(
										function() {

											if ($(this).prop("checked") == false) {

												alert("Checkbox is unchecked.");
												//document.getElementById('2').style.display = 'none';
												$('#2').hide();
												var multipleValues = $( "#userSelectedRoles").val() || [];
												 
												 var multipleValues1 = $("#userSelectedRoles option:selected" ).text() || [];
													//var selectedRoles1 = multipleValues1.join(", "));
													 //alert("selectedRoles1 : "+selectedRoles1);
													 
													 
													 var foo = []; 
													$('#userSelectedRoles :selected').each(function(i, selected){ 
 													 foo[i] = $(selected).text(); 
													});
													
													
													//private String kpiValueSWD;
													//private String kpiValueSSWD;
													//private String kpiValueSA;
													//private String kpiValueSSA;
													//private String kpiValueSPM;
													//private String kpiValueCA;
													//private String kpiValueRPM;
													//private String kpiValueSDL;
													//private String kpiValueDL;
													
													for (i = 0; i < foo.length; i++) {
													    var roleName = foo[i] ;
													    var roleNameText;
													    var kpiValue;
													    if(roleName == 'SW Developer'){
													    	roleNameText = 'SWD';
													    	//kpiValue = ${kpiDTO.kpiRoleDTO.kpiValueSWD}
													    	
													    }
													    if(roleName == 'Senior SW Developer'){
													    	roleNameText = 'SSWD';
													    	//kpiValue = ${kpiDTO.kpiRoleDTO.kpiValueSSWD}
													    }
													    if(roleName == 'SW Architect'){
													    	roleNameText = 'SA';
													    }
													    if(roleName == 'Senior SW Architect'){
													    	roleNameText = 'SSA';
													    }
													    if(roleName == 'SPM/People Manager'){
													    	roleNameText = 'SPM';
													    	//kpiValue = ${kpiDTO.kpiRoleDTO.kpiValueSPM}
													    }
													    if(roleName == 'Design Authority/Chief Architect'){
													    	roleNameText = 'CA';
													    }
													    if(roleName == 'Regional CAC Program Manager'){
													    	roleNameText = 'RPM';
													    }
													    if(roleName == 'Sub-domain Lead'){
													    	roleNameText = 'SDL';
													    }
													    if(roleName == 'Domain Lead'){
													    	roleNameText = 'DL';
													    }
													    
												var newTextBoxDiv = $(
														document
																.createElement('div'))
														.attr(
																"id",
																'TextBoxDiv'
																		+ roleNameText);
												
												
												var TextAreaBoxDiv = $(
														document
																.createElement('div'))
														.attr(
																"id",
																'TextAreaBoxDiv'
																		+ roleNameText);
												

												newTextBoxDiv
														.after()
														.html(
																'<tr><td><label>KPI Value '
																		+ roleName
																		+ ' : </label></td>'
																		+ '<td><input type="text" name="kpiValue' + roleNameText + 
       	      '" id="kpiValue' + roleNameText + '" value="'+ kpiValue +'" ></td><td></td></tr>');
												
												// for text area
												
												TextAreaBoxDiv
														.after()
														.html(
																'<label>KPI Comment '
																		+ roleName
																		+ ' : </label>'
																		+ '<textarea name="comment' + roleNameText + 
       	      '" id="comment' + roleNameText + '" value="" >');

												newTextBoxDiv
														.appendTo("#TextBoxesGroup");
												
												TextAreaBoxDiv
												.appendTo("#TextBoxesGroup");

												//counter++;
													}

											}

											else if ($(this).prop("checked") == true) {

												alert("Checkbox is checked.");
												var multipleValues1 = $("#userSelectedRoles option:selected" ).text() || [];
												var foo = []; 
												$('#userSelectedRoles :selected').each(function(i, selected){ 
													 foo[i] = $(selected).text(); 
												});
												
												//$("#TextBoxesGroup").remove();
												$("#roleSenior SW Developer").remove();
												//var newTextBoxDiv = $(
														//document
																//.createElement('div'))
														//.attr(
																//"id",
																//'TextBoxesGroup');
												for (i = 0; i < foo.length; i++) {
												    var roleName = foo[i] ;
												    var roleNameText;
												    
												    if(roleName == 'SW Developer'){
												    	roleNameText = 'SWD';
												    }
												    if(roleName == 'Senior SW Developer'){
												    	roleNameText = 'SSWD';
												    }
												    if(roleName == 'SW Architect'){
												    	roleNameText = 'SA';
												    }
												    if(roleName == 'Senior SW Architect'){
												    	roleNameText = 'SSA';
												    }
												    if(roleName == 'SPM/People Manager'){
												    	roleNameText = 'SPM';
												    }
												    if(roleName == 'Design Authority/Chief Architect'){
												    	roleNameText = 'CA';
												    }
												    if(roleName == 'Regional CAC Program Manager'){
												    	roleNameText = 'RPM';
												    }
												    if(roleName == 'Sub-domain Lead'){
												    	roleNameText = 'SDL';
												    }
												    if(roleName == 'Domain Lead'){
												    	roleNameText = 'DL';
												    }
												    
												    //alert("roleNameText : "+roleNameText);
												    //alert("TextBoxDiv : "+ $("#TextBoxDiv") + roleNameText);
												   $("#TextBoxDiv" + roleNameText).remove();
												   $("#TextAreaBoxDiv" + roleNameText).remove();
												   
												}
											}

										});
										
										
										
										
										////////////////////// for edit ////////////////////
										//private String kpiValueSWD;
										//private String kpiValueSSWD;
										//private String kpiValueSA;
										//private String kpiValueSSA;
										//private String kpiValueSPM;
										//private String kpiValueCA;
										//private String kpiValueRPM;
										//private String kpiValueSDL;
										//private String kpiValueDL;
										
										//<c:if test="${not empty kpiDTO.kpiRoleDTO.kpiValueSWD}">
												//${kpiDTO.kpiRoleDTO.kpiValueSWD}
												
												//var newTextBoxDiv1 = $(
														//document
																//.createElement('div'))
														//.attr(
																//"id",
																//'TextBoxDivSWD');
												
												//newTextBoxDiv1
												//.after()
												//.html(
														//'<tr><td><label>KPI Value : </label></td>'
																//+ '<td><input type="text" name="kpiValueSWD" id="kpiValueSWD" value="${kpiDTO.kpiRoleDTO.kpiValueSWD}" ></td><td></td></tr>');
												
												//newTextBoxDiv1
												//.appendTo("#TextBoxesGroup");
										//</c:if>

						//$("#getButtonValue").click(
								//function() {

									//var msg = '';
									//for (i = 1; i < counter; i++) {
										//msg += "\n Textbox #" + i + " : "
												//+ $('#textbox' + i).val();
									//}
									//alert(msg);
								//});
					});
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/views/public/include_header.jsp" />
	<div id="breadcrumb">
		<ul>
			<li class="first"><fmt:message key="breadcrumb.tag" /></li>
			<li>&#187;</li>
			<li><a href='<c:out value="${homeUrl}"/>'><fmt:message
						key="breadcrumb.tag.home" /></a></li>
			<li>&#187;</li>
			<li class="current"><fmt:message key="breadcrumb.tag.kpi.msg" /></li>
		</ul>
	</div>
	
	
	
	<form:form method="post" modelAttribute="kpiDTO" action="addKPIDetails.html">
        <table>
            <tr>
                <td><spring:message code="label.kpi.kpiName" text="KPI Name" /></td>
                <td><form:input path="kpiName" value="${kpiDTO.kpiName}"/></td>
                <td><form:errors path="kpiName" cssClass="error" /></td>
            </tr>
            <tr>
                <td><spring:message code="label.kpi.kpiDisplayName" text="KPI Display Name" /></td>
                <td><form:input path="kpiDisplayName"  value="${kpiDTO.kpiDisplayName}"/></td>
                <td><form:errors path="kpiDisplayName" cssClass="error" /></td>
            </tr>
            
         <tr>
         	<td><spring:message code="label.kpi.role" text="Select role(s)" /></td>
         	<td>
			<%-- <form:select multiple="true" path="userSelectedRoles">
				<c:forEach items="${roles}" var="rol">
					<c:set var="isSelected" value="false" />
					<c:forEach items="${kpiDTO.userSelectedRoles}" var="rolUsr">
						<c:if test="${rolUsr.id == rol.id}">
							<c:set var="isSelected" value="true" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isSelected}">
							<option value="${rol.id}" selected="selected">${rol.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${rol.id}">${rol.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select> --%>
			
			<form:select multiple="true" path="userSelectedRoles">
				<c:forEach items="${roles}" var="rol">
					<c:set var="isSelected" value="false" />
					<c:forEach items="${kpiDTO.userSelectedRoles}" var="rolUsr">
						<c:if test="${rolUsr == rol.id}">
							<c:set var="isSelected" value="true" />
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isSelected}">
							<option value="${rol.id}" selected="selected">${rol.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${rol.id}">${rol.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select>
			
			
			</td>
			<td><form:errors path="userSelectedRoles" cssClass="error" /></td>
		</tr>
		
		 	<tr> 
                <td><spring:message code="label.kpi.kpisame.for.all.role" text="KPI value same for all role(s)" /></td>
                <td><form:checkbox path="kpiVaueSameForAllRoles"  value="${kpiDTO.kpiVaueSameForAllRoles}"/></td>
                <td></td>
            </tr>
            <div id="2">
             <tr>
                <td><spring:message code="label.kpi.kpiValue" text="KPI Value" /></td>
                <td><form:input path="kpiValue"  value="${kpiDTO.kpiValue}"/></td>
                <td><form:errors path="kpiValue" cssClass="error" /></td>
            </tr>
            
             <tr>
                <td><spring:message code="label.kpi.kpiDescription" text="KPI Description" /></td>
                <td><form:textarea path="kpiDescription"  value="${kpiDTO.kpiDescription}"/></td>
                <td><form:errors path="kpiDescription" cssClass="error" /></td>
            </tr>
			</div>
            <tr>
                <td colspan="3"><input type="submit" value="Add KPI"/></td>
            </tr>
             <table>
            <div id='TextBoxesGroup'>
            
			</div>
			</table>
			<input type='button' value='Add Button' id='addButton'>
			<input type='button' value='Get TextBox Value' id='getButtonValue'>
            
        </table>
    </form:form> 
	
</body>

</html>
