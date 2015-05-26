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


<link href="${rootURL}/resources/bootstrap/css/bootstrap.css"
				media="screen" rel="stylesheet" type="text/css" />
				<link rel="stylesheet" href="${pageContext.request.contextPath }/css/layout.css" type="text/css" />
				<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript"
				src="${rootURL}/resources/jquery/jquery-1.10.2.js"></script>
			<script type="text/javascript"
				src="${rootURL}/resources/bootstrap/js/bootstrap.js"></script>
			<script type="text/javascript" src="${rootURL}/resources/js/app.js"></script>


			<script type="text/javascript">
				var datefield = document.createElement("input")
				datefield.setAttribute("type", "date")
				if (datefield.type != "date") { //if browser doesn't support input type="date", load files for jQuery UI Date Picker
					document
							.write('<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />\n')
					document
							.write('<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"><\/script>\n')
					document
							.write('<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"><\/script>\n')
				}
			</script>
			<script>
				if (datefield.type != "date") { //if browser doesn't support input type="date", initialize date picker widget:
					jQuery(function($) { //on document.ready
						$('#creationDate').datepicker();
					})
				}
			</script>

			<script type="text/javascript">

				function addformChanged() {
					//var checkflag = checkDisTable();
					//alert("checkflag "+checkflag);
					//if (checkflag == true) {
						showBlank();
						setDefaultvalues();
					//}
				}

				function setDefaultvalues() {
					for (var i = 0; i < document.forms[1].elements.length; i++) {
						document.forms[1].elements[i].defaultValue = document.forms[1].elements[i].value;
					}
				}

				function formChanged() {
					var flag = true;
					for (var i = 0; i < document.forms[1].elements.length; i++) {
						if (document.forms[1].elements[i].value != document.forms[1].elements[i].defaultValue) {
							flag = false;
							break;
						}
					}

					if (flag == false) {
						var confirmed = confirm("You have unsaved changes in the form. Do you want to save the changes now?");
					}
					if (confirmed) {
						flag = false;
						setDefaultvalues();
						//save();
					} else {
						flag = true;
						setDefaultvalues();
					}
					return flag;
				}
				function formDiv() {
					var checkdiv = false;
					if (document.getElementById('2') != null) {
						if (document.getElementById('2').style.display == "block") {
							checkdiv = true;
						}
					}
					return checkdiv;
				}

				function checkDisTable() {
					//alert("checkDisTable");
					var formreturn = true;
					var checkdiv2 = formDiv();
					//alert("checkdiv2 "+checkdiv2);
					if (checkdiv2 == true) {
						formreturn = formChanged();
					}
					return formreturn;
				}

				function findPosX(obj) {
					var curleft = 0;
					if (obj.offsetParent)
						while (1) {
							curleft += obj.offsetLeft;
							if (!obj.offsetParent)
								break;
							obj = obj.offsetParent;
						}
					else if (obj.x)
						curleft += obj.x;
					return curleft;
				}

				function findPosY(obj) {
					var curtop = 0;
					if (obj.offsetParent)
						while (1) {
							curtop += obj.offsetTop;
							if (!obj.offsetParent)
								break;
							obj = obj.offsetParent;
						}
					else if (obj.y)
						curtop += obj.y;
					return curtop;
				}

				function showDiv2(obj, divid) {

					//alert("in showDiv2 obj is "+obj);
					//alert("in showDiv2 divid is "+divid);
					//var screenMid = screen.width / 2;
					//var curleftObj = findPosX(obj);
					//var curtopObj = findPosY(obj);
					//alert("curtopObj------>"+curtopObj);
					//alert("curtopObj<screen.height/2------>"+(curtopObj<screen.height/2));
					//if (curtopObj < screen.height / 2) {
						//curtopObj = curtopObj + 200;
					//} else {
						//curtopObj + 200;
					//}

					//var myDiv = document.getElementById(divid);
					//var childDiv1 = document.getElementById("layer2");
					//myDiv.style.position = "relative";
					//myDiv.style.left = curleftObj + "px";
					//myDiv.style.top = curtopObj + "px";

				}

				function showBlank() {
					document.getElementById("assetName").value = "";
					document.getElementById("assetShortDescription").value = "";
					document.getElementById("projectName").value = "";
					document.getElementById("registeredInAssetPortal").value = "";
					document.getElementById("reusedInOtherProjectsName").value = "";
					document.getElementById("effortSave").value = "";
					document.getElementById("creationDate").value = "";

					document.forms[1].elements["assetId"].value = "";
					//window.document.getElementById("resultDiv").innerHTML = "";
					//window.document.getElementById("responseDiv").innerHTML = "";
					document.getElementById('1').style.display = "none";
					document.getElementById('2').style.display = "block";

					var addbtnobj = document.getElementById("add");
					//showDiv2(addbtnobj, "2");
				}

				function closeForm() {
					document.getElementById('2').style.display = 'none';
				}
			</script>

			<script type="text/javascript">

				function IsValid() {
					//if (isEmpty()) {
						//showSaveDiv();
						document.saveUpdateOperationalDisciplineForm.submit();
					//}
				}

				/* function isEmpty() {
					var formfield = "";
					var str = document.getElementById("assetName").value;
					if (document.getElementById("assetName").value == "") {
						formfield = "Asset Name";
					}
					if (document.getElementById("assetShortDescription").value == "") {
						formfield = formfield + "\n" + "Asset Short Description";
					}
					if (document.getElementById("projectName").value == "")
						formfield = formfield + "\n" + "Project Name";
					if (document.getElementById("registeredInAssetPortal").value == "")
						formfield = formfield + "\n" + "Registered In AssetPortal";
					if (document.getElementById("reusedInOtherProjectsName").value == "")
						formfield = formfield + "\n" + "Reused In Other Projects Name";
					if (document.getElementById("effortSave").value == "")
						formfield = formfield + "\n" + "Effort Save";
					if (document.getElementById("creationDate").value == "")
						formfield = formfield + "\n" + "Asset creation Date";
					if (formfield == "") {
						return true;
					} else {
						alert("Required Fields :  \n" + formfield);
						return false;
					}
				}
			 */
			</script>

			<script type="text/javascript">
				function overlay() {
					el = document.getElementById("overlay");
					el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
				}
			</script>



			<script type="text/javascript">
				var xmlHttp;
				function createXMLHttpRequest(reqName) {
					alert("reqName : "+reqName);
					try {
						reqName = new XMLHttpRequest();
					} catch (e) {
						try {
							reqName = new ActiveXObject("Msxml2.XMLHTTP");
						} catch (e) {
							reqName = new ActiveXObject("Microsoft.XMLHTTP");
						}
					}

					return reqName;
				}
			</script>

			<script type="text/javascript">
				var xmlHttpRequest2;
				var xmlResponseTxt2;

				function SaveupdateUserAJAX() {
					xmlHttpRequest2 = createXMLHttpRequest(xmlHttpRequest2);
					alert("xmlHttpRequest2 : "+xmlHttpRequest2);
					var actionSave = "saveAssetDetails.html?op=saveUpdateAsset&";
					var qString = createGETQueryString();
					alert("qString : "+qString);
					var FinalQString = createQueryString(actionSave, qString);
					alert("FinalQString : "+FinalQString);
					xmlHttpRequest2.open("GET", FinalQString, true);
					xmlHttpRequest2.onreadystatechange = updateUser;
					xmlHttpRequest2.send(null);
				}

				function updateUser() {
					if (xmlHttpRequest2.readyState == 4) {
						if (xmlHttpRequest2.status == 200) {

							xmlResponseTxt2 = xmlHttpRequest2.responseText;
							window.document.getElementById("resultDiv").innerHTML = xmlResponseTxt2;
							xmlResponseTxt2 = null;
							setDefaultvalues();
						}
					}
				}
				function createGETQueryString() {

					//document.getElementById("assetName").value = "";
					//document.getElementById("assetShortDescription").value = "";
					//document.getElementById("projectName").value = "";
					//document.getElementById("registeredInAssetPortal").value = "";
					//document.getElementById("reusedInOtherProjectsName").value = "";
					//document.getElementById("effortSave").value = "";

					var assetId = document.getElementById("assetId").value;
					var assetName = document.getElementById("assetName").value;
					var assetShortDescription = document.getElementById("assetShortDescription").value;
					var projectName = document.getElementById("projectName").value;
					var registeredInAssetPortal = document.getElementById("registeredInAssetPortal").value;
					var reusedInOtherProjectsName = document.getElementById("reusedInOtherProjectsName").value;
					var effortSave = document.getElementById("effortSave").value;

					//creating the Query String
					var qryString = "assetId=" + assetId;
					qryString = qryString + "&assetName=" + assetName;
					qryString = qryString + "&assetShortDescription=" + assetShortDescription;
					qryString = qryString + "&projectName=" + projectName;
					qryString = qryString + "&registeredInAssetPortal=" + registeredInAssetPortal;
					qryString = qryString + "&reusedInOtherProjectsName=" + reusedInOtherProjectsName;
					qryString = qryString + "&effortSave=" + effortSave;
					return qryString;
				}

				function createQueryString(action, param) {
					var qString = action + param;
					return qString;
				}
			</script>



			<script type="text/javascript">


				function showDivDetails(obj) {
					//var screenMid = screen.width / 2;
					//alert("The screenMid is "+screenMid);
					//var curleftObj = screenMid - 512;
					//var curtopObj = findPosY(obj);
					//alert("curtopObj="+curtopObj+"curleftObj="+curleftObj);
					//curtopObj = curtopObj + 25;
					var myDiv = document.getElementById("2");
					//myDiv.style.position = "relative";
					//myDiv.style.left = curleftObj + "px";
					//myDiv.style.top = curtopObj + "px";
					myDiv.style.display = "block";
				}

				function checkRow(rowid) {
					// var pageNum=document.getElementById("recordsperpage").value ;
					alert("value---->"+rowid);
					var pageNum = 5;
					var id = rowid;

					while (id > pageNum) {
						id = id - pageNum;
					}
					var table = document.getElementById("usertable");
					if (table != null) {
						var tbody = table.getElementsByTagName("tbody")[0];
						var rows = tbody.getElementsByTagName("tr");

						var flag1 = checkDisTable();
						if (flag1 == true) {
							var cell = rows[id - 1].getElementsByTagName("td")[1];

							document.getElementById('1').style.display = "none";
							//document.getElementById('2').style.display="block";

							//if (document.getElementById('myalert').style.display == "block") {
								//document.getElementById('myalert').style.display = "none";
							//}
							showDivDetails(cell);

							//action = "userListAction.do?method=ajaxedit&userid="  ;
							action = "operationalDiscipline.html?op=getOperationalDiscipline&opdId=";
							//var ProductID=rowid;
							var opdId = cell.innerHTML;
							fetchAsset(action, opdId);

						}
					}
				}
				function checkDelete(rowNum) {
					//var pageNum=document.getElementById("recordsperpage").value ;
					//alert("rowNum---->"+rowNum);
					var pageNum = 5;
					var id = rowNum;
					var table = document.getElementById("usertable");
					var tbody = table.getElementsByTagName("tbody")[0];
					var rows = table.getElementsByTagName("tr");
					while (id > pageNum) {
						id = id - pageNum;
					}

					var cell = rows[id].getElementsByTagName("td")[1];
					//alert("cell---->"+cell);
					//var cell1 = rows[id - 1].getElementsByTagName("td")[1];
					//alert("cell1---->"+cell1);
					var id = cell.innerHTML;
					//alert("id---->"+id);
					var flag1 = checkDisTable();
					//alert("flag1---->"+flag1);
					if (flag1 == true) {
						//showDiv2(rows[id], 'delalert');
						//document.getElementById('delalert').style.display = "block";
						//document.getElementById('usdelid').innerHTML = cell.innerHTML;
						//document.getElementById('usid').value = cell.innerHTML;
						document.getElementById('2').style.display = "none";
						//document.getElementById('myalert').style.display = "none";
						setDefaultvalues();
						var action = "removeAssetDetail.html?op=deleteAsset&id="+ rowNum;
						//alert("action---->"+action);
						document.forms[1].action = action;
						document.forms[1].method = "POST"
						document.forms[1].submit();

					}
				}
			</script>

			<script type="text/javascript">
				var resTxt;
				function fetchAsset(action, assetId) {
					var action1 = action;
					xmlHttp = createXMLHttpRequest(xmlHttp);
					var url = createQueryString(action1, assetId);

					xmlHttp.open("GET", url, true);
					xmlHttp.onreadystatechange = callback;
					xmlHttp.send(null);
				}

				function callback() {
					if (xmlHttp.readyState == 4) {
						if (xmlHttp.status == 200) {
							if (window.document.getElementById("resultDiv") != null)
								window.document.getElementById("resultDiv").innerHTML = "";
							if (window.document.getElementById("responseDiv") != null)
								window.document.getElementById("responseDiv").innerHTML = "";
							resTxt = xmlHttp.responseText;

							setValues();
							setDefaultvalues();
							resTxt = null;
						}
					}
				}

				function setValues() {
					var entry = resTxt.split("###");
					for (var i = 0; i < entry.length; i++) {
						var values = entry[i].split("***");
						if (document.getElementById(values[0]) != null)
							document.getElementById(values[0]).value = values[1];
					}
				}
			</script>


			<script type="text/javascript">
				function assetDelete() {
					var assetId = document.getElementById('usid');
					var action = "removeAssetDetail.html?op=deleteAsset&id="+ assetId.value;
					document.forms[1].action = action;
					document.forms[1].method = "get"
					document.forms[1].submit();
				}

				function notDelete() {
					document.getElementById('delalert').style.display = "none";
				}
			</script>


</head>
<body>

<div id="dataview" style="position: relative; float: left width :   100%; height: 20%; background-color: #ffffff; padding: 12px 12px 12px 0px">
			<form name="myform" id="myform">
			<FONT face="Calibri" size="2">
				<h3 align="center">Operational Discipline Page</h3>
			</FONT>
			<a id="add" onclick="addformChanged();">
				<img src="<%=request.getContextPath()%>/skin/images/add_button.gif" />
			</a>

				<div id="tablediv">

					 <display:table id="usertable" name="<%=Constants.OPERATIONAL_DISCIPLINE_LIST %>" class="list" sort="page" pagesize="5"
					 export="true" requestURI="operationalDisciplineDetails.html"  >
				   	 <%-- <display:column title="Edit/Delete"  paramProperty="id" paramId="id">
				   	 <a href="javascript:checkRow(${usertable_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/edit_icon.gif" title="Edit" border="0">
				   	 </a><img src="<%=request.getContextPath()%>/skin/images/spacer.gif" width="4" height="1">
				   			<a href="javascript:checkDelete(${usertable_rowNum});">
				   			<img src="<%=request.getContextPath()%>/skin/images/remove_icon.gif" title="Remove" border="0"></a>
				   	 </display:column> --%>
				   	 <display:column title="Edit/Delete"  paramProperty="id" paramId="id" >
				   	 <a href="operationalDisciplineForUpdate.html">
				   	 <img src="<%=request.getContextPath()%>/skin/images/edit_icon.gif" title="Edit" border="0">
				   	 </a><img src="<%=request.getContextPath()%>/skin/images/spacer.gif" width="4" height="1">
				   			<a href="javascript:checkDelete(${usertable_rowNum});">
				   			<img src="<%=request.getContextPath()%>/skin/images/remove_icon.gif" title="Remove" border="0"></a>
				   	 </display:column>
					 <display:column property="id" sortable="true" title="Id"/>
				     <display:column property="operationalDisciplineName" title="Operational Discipline Name" sortable="true" sortProperty="operationalDisciplineName"/>
				     <display:column property="frequency" title="Frequency" sortable="true" />
				     <display:column property="quarter" title="Quarter" sortable="true" />
				     <display:column property="nonCompliance" title="non-Compliance" sortable="true" />
				     </display:table>
				</div>
			</form>
			<p></p>
		</div>
			<%-- <!-- 1st div-->
			<div id="1" style="display:block;height:300px;">

			</div>

			<!-- 1st div ends-->
			<!-- 2nd div start here-->
	<div id="2"
		style="position: relative; float: left; background-color: #ffffff; BORDER-RIGHT: #999999 2px solid; BORDER-TOP: #999999 2px solid; DISPLAY: none; Z-INDEX: 10; BORDER-LEFT: #999999 2px solid; WIDTH: 80%; BORDER-BOTTOM: #999999 2px solid">
		<div class="container" align="center">
		<div id="loginbox">
        <div class='logintitle ericssonfont'> Operational Discipline</div>
        <div class='separator'></div>
   <form:form method="post" action="saveUpdateOperationalDiscipline.html" modelAttribute="operationalDisciplineDTO" name="saveUpdateOperationalDisciplineForm">

		<font face="THE TIMES NEW ROMAN"color="#0B2F3A" size="5">Operational Discipline Name:</font>
		<form:select path="operationalDisciplineName" id="operationalDisciplineName" style="width:137px" onchange="myFunction()" required="required">
			<form:option value="" selected="selected">-------Select-----</form:option>
			<form:option value="Competence Assesment completion status in ESS">Competence Assesment completion status in ESS</form:option>
			<form:option value="Mandatory training completion status">Mandatory training completion status</form:option>
			<form:option value="CV update status">CV update status</form:option>
			<form:option value="Time Booking completion status">Time Booking completion status</form:option>
			<form:option value="Expense booking status">Expense booking status</form:option>
		</form:select>
	<br><br>
	<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="5" style="float:left; margin-left:40%">frequency:</font>
	<font style="float:left; margin-right:25%">
	<label id="fqId"></label>
	<form:hidden path="frequency" id="frequency"></form:hidden>
	<form:input path="frequency" id="frequency"></form:input>
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

<!-- <input type="button" value="Save/Update" onclick="javascript:IsValid();" /> -->
<input type="submit" value="Save/Update" />
</form:form>


	</div> --%>



</body>
</html>