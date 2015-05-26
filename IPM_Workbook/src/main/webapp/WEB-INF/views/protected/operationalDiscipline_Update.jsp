<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%
		response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0
		response.setDateHeader("Expires", 0); //prevents caching at the proxy server
	%>

	<%@page import="com.ericsson.v1.util.Constants"%>
	<%@include file="/WEB-INF/views/public/taglib.jsp"%>
	<%@include file="/WEB-INF/views/public/include_header.jsp" %>
	<fmt:setBundle basename="messages" />
<html>
	<head>
	<link rel="shortcut icon" type="image/x-icon" href="http://eriteamtracker.egi.ericsson.com/img/favicon.ico">
	<link rel="shortcut icon" type="image/png" href="http://eriteamtracker.egi.ericsson.com/img/favicon.png">
	<link rel="stylesheet" href="http://eriteamtracker.egi.ericsson.com/css/style.css">
	<link rel="stylesheet" type="text/css" href="http://eriteamtracker.egi.ericsson.com/css/common_style.css" />
	<script src="http://eriteamtracker.egi.ericsson.com/js/jquery-1.2.6.min.js" type="text/javascript"></script>

	<style type='text/css'>


	 /* body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; } */
    fieldset { padding:0; border:0; margin-top:25px; }
   /*  h1 { font-size: 1.2em; margin: .6em 0; } */
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }







            #loginbox {
      width: 600px;
      height: 350px;

      background-color: #7B68F2;
      background: linear-gradient(to bottom, #CFCFCF, #FFFFFF);
      background:-webkit-linear-gradient(top, #CFCFCF, #FFFFFF);
      background:-moz-linear-gradient(top, #CFCFCF, #FFFFFF);
      background-clip: padding-box;
      border-radius: 12px 12px 12px 12px;

      box-shadow:10px 10px 15px #272727;
    }

    .box_text_style {
      color: #FFFFFF;
      text-shadow: 1px 1px 3px #888888;
    }

    .logintitle {
      font-size: 18px;
      font-weight: 300;
      height: 60px;
      line-height: 60px;
      width: 100%;
      text-align: center;
    }

    .separator {
       border-bottom: 1px solid #FFFFFF;
       width:90%;
       margin:auto;
       clear:both;
    }

        </style>



<link rel="stylesheet" type="text/css" href="../resources/stylesheets/commonStyle.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<link rel="stylesheet" type="text/css" href="../resources/stylesheets/jquery.autocomplete.css" />


<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script type="text/javascript" src="../resources/js/jquery.autocomplete.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.0.0.js"></script>
<script>
	function myFunction() {
	  		var x = document.getElementById("operationalDisciplineName").value;
	  		if(x == "Competence Assesment completion status in ESS"){
	                  	document.getElementById("fqId").innerHTML="quarterly";
	                  	document.getElementById("frequency").value="quarterly";
	  		}
	                  else if (x == "Mandatory training completion status"){
	                          document.getElementById("fqId").innerHTML="quarterly";
	                          document.getElementById("frequency").value="quarterly";
	  		}
	                  else if (x == "CV update status"){
	                          document.getElementById("fqId").innerHTML ="quarterly";
	                          document.getElementById("frequency").value="quarterly";
	  		}
	                  else if(x== "Time Booking completion status"){
	                          document.getElementById("fqId").innerHTML ="weekly";
	                          document.getElementById("frequency").value="weekly";
	  		}
	                 /*  else if(x== "Expense booking status"){
                          document.getElementById("frequency").innerHTML ="weekly";
  			} */
	                  else{
	                          document.getElementById("fqId").innerHTML ="";
	                          document.getElementById("frequency").value ="";
	  		}
	   }
  </script>

</head>
<body>
		<div class="container" align="center">
		<div id="loginbox">
        <div class='logintitle ericssonfont'> Operational Discipline Details Page</div>
        <div class='separator'></div>
   <form:form method="post" action="saveUpdateOperationalDiscipline.html" modelAttribute="operationalDisciplineDTO" name="saveUpdateOperationalDisciplineForm">

		<font face="THE TIMES NEW ROMAN"color="#0B2F3A" size="5">Operational Discipline Name:</font>
		<form:select path="operationalDisciplineName" id="operationalDisciplineName" style="width:137px" onchange="myFunction()" required="required">
			<form:option value="" selected="selected">-------Select-----</form:option>
			<form:option value="Competence Assesment completion status in ESS">Competence Assesment completion status in ESS</form:option>
			<form:option value="Mandatory training completion status">Mandatory training completion status</form:option>
			<form:option value="CV update status">CV update status</form:option>
			<form:option value="Time Booking completion status">Time Booking completion status</form:option>
		</form:select>
	<br><br>
	<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="5" style="float:left; margin-left:40%">frequency:</font>
	<font style="float:left; margin-right:25%">
	<label id="fqId"></label>
	<form:hidden path="frequency" id="frequency"></form:hidden>
	</font>
	<br>
	<br>
	<font face="THE TIMES NEW ROMAN"color="#0B2F3A" size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;quarter:</font>
	<form:select path="quarter" id="quarter" style="width:137px" required="required">
		<form:option value="" selected="selected">------Select------</form:option>
		<form:option value="Q1">Q1</form:option>
		<form:option value="Q2">Q2</form:option>
		<form:option value="Q3">Q3</form:option>
		<form:option value="Q4">Q4</form:option>
	</form:select>
	<br>
	<br>

<font face="THE TIMES NEW ROMAN"color="#0B2F3A" size="5" style="float:left; margin-left:35%" >non-Compliance:</font>
 <!-- <font style="float:left; margin-right:25%"> -->
 <form:radiobutton  path="nonCompliance" id="nonCompliance" value="yes" />yes
<form:radiobutton  path="nonCompliance" id="nonCompliance" value="no" />no

<form:hidden path="operationalDisciplineId" id="operationalDisciplineId"></form:hidden>
<br>
<br>
<input type="submit" value="Save/Update" />
</form:form>

</body>
</html>