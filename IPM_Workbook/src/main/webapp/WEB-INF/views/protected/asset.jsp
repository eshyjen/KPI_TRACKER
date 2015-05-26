
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
					if (isEmpty()) {
						//showSaveDiv();
						document.assetDetailsForm.submit();
					}
				}
				
				function isEmpty() {
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
							action = "assetDetail.html?op=getAsset&assetId=";
							//var ProductID=rowid;
							var assetId = cell.innerHTML;
							fetchAsset(action, assetId);

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
		<%-- <div id="breadcrumb">
		    <ul>
		      <li class="first"><fmt:message key="breadcrumb.tag"/></li>
		      <li>&#187;</li>
		      <li><a href='<c:out value="${homeUrl}"/>'><fmt:message key="breadcrumb.tag.home"/></a></li>
		      <li>&#187;</li>
		      <li class="current"><fmt:message key="breadcrumb.tag.asset.msg"/></li>
		    </ul>
		</div> --%>

	<div id="dataview" style="position: relative; float: left width :   100%; height: 20%; background-color: #ffffff; padding: 12px 12px 12px 0px">
			<form name="myform" id="myform">
			<FONT face="Calibri" size="2">
				<h3 align="center">Asset Details Page</h3>
			</FONT> 
			<a id="add" onclick="addformChanged();">
				<img src="<%=request.getContextPath()%>/skin/images/add_button.gif" />
			</a>

				<div id="tablediv">	
	
					 <display:table id="usertable" name="<%=Constants.ASSET_LIST %>" class="list" sort="page" pagesize="5" export="true" requestURI="assetDetails.html"  >
				   	 <display:column title="Edit/Delete"  paramProperty="id" paramId="id"><a href="javascript:checkRow(${usertable_rowNum});">
				   	 <img src="<%=request.getContextPath()%>/skin/images/edit_icon.gif" title="Edit" border="0">
				   	 </a><img src="<%=request.getContextPath()%>/skin/images/spacer.gif" width="4" height="1">
				   			<a href="javascript:checkDelete(${usertable_rowNum});">
				   			<img src="<%=request.getContextPath()%>/skin/images/remove_icon.gif" title="Remove" border="0"></a>
				   	 </display:column>
					 <display:column property="id" sortable="true" title="Id"/>
				     <display:column property="assetName" title="Asset Name" sortable="true" sortProperty="assetName"/>
				    <%--  <display:column property="assetShortDescription" sortable="true" title="Asset Short Description"/> --%>
				     <display:column property="projectName" title="Project Name" sortable="true" />
				     <display:column property="registeredInAssetPortal" title="Registered In Asset Portal" sortable="true" />
				     <display:column property="reusedInOtherProjectsName" title="Reused In Other Projects Name" sortable="true" />
				     <display:column property="effortSave" title="Effort Save" sortable="true" />
				     </display:table>
				</div>
			</form>
			<p></p>
		</div>
			<!-- 1st div-->
			<div id="1" style="display:block;height:300px;">
			
			</div>
			
			<!-- 1st div ends-->
			<!-- 2nd div start here-->
	<div id="2"
		style="position: relative; float: left; background-color: #ffffff; BORDER-RIGHT: #999999 2px solid; BORDER-TOP: #999999 2px solid; DISPLAY: none; Z-INDEX: 10; BORDER-LEFT: #999999 2px solid; WIDTH: 80%; BORDER-BOTTOM: #999999 2px solid">
		<form method="POST" action="saveAssetDetails.html" name="assetDetailsForm">
			<h1 align="left">
				<b><font face="THE TIMES NEW ROMAN" size="4">Asset Details:</font></b>
			</h1>
			<table width=100% frame="box">
				<tr>
					<td align="right"><nobr>
							<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Asset
								Name*:</font>
						</nobr></td>
					<td width="4">&nbsp;</td>
					<td><nobr>
							<input type="text" size="20" Id="assetName" name="assetName"/>
							<td width="40%">&nbsp;</td>


							<td align="right"><nobr>
									<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Asset
										Creation*:</font>
								</nobr></td>
							<td width="4">&nbsp;</td>

							<td><nobr>
									<input type="date" size="20" Id="creationDate" name="creationDate" />
									<td width="40%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><nobr>
							<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Project
								Name*:</font>
						</nobr></td>
					<td width="4">&nbsp;</td>
					<td><nobr>

							<input type="text" size="20" Id="projectName" name="projectName"/>
							<td width="40%">&nbsp;</td>
							<td align="right"><nobr>
									<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Reused
										in other projects name*:</font>
								</nobr></td>
							<td width="4">&nbsp;</td>
							<td><nobr>
									<input type="text" size="20" Id="reusedInOtherProjectsName" name="reusedInOtherProjectsName"/>
									<td width="40%">&nbsp;</td>
				</tr>
				<tr>
					<td align="right"><nobr>
							<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Asset
								Description*:</font>
						</nobr></td>
					<td width="4">&nbsp;</td>
					<td><nobr>
							<textarea wrap="virtual" name="assetShortDescription" id="assetShortDescription" rows=2 cols=31></textarea>
							<br>
							<td width="40%">&nbsp;</td>
							<td align="right"><nobr>
									<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Effort
										Save*:</font>
								</nobr></td>
							<td width="4">&nbsp;</td>
							<td><nobr>
									<input type="text" size="20" Id="effortSave" name="effortSave"/>
									<td width="40%">&nbsp;</td>
				</tr>
				<tr>


					<td align="right"><nobr>
							<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2"></font>
						</nobr></td>
					<td width="4">&nbsp;</td>
					<td><nobr>

							<td width="40%">&nbsp;</td>

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<td align="right"><nobr>
									<font face="THE TIMES NEW ROMAN" color="#0B2F3A" size="2">Registered
										in Asset Portal*:</font>
								</nobr></td>
							<td width="4">&nbsp;</td>
							<td><nobr>
									<input type="text" size="20" Id="registeredInAssetPortal" name="registeredInAssetPortal"/>
									<td width="40%">&nbsp;</td>
				</tr>



				<tr>
					<td><input type="button" value="Save/Update" style="margin-left: 350%;" onclick="javascript:IsValid();"></td>
				</tr>
			</table>
			<input type="hidden" Id="assetId" name="assetId"/>
	</form>
	</div>
</body>
 </html>