var symbol = "select";
var searchType = "provider";
var httpRequest;
var provider;
var assignShortcode;
function searchProvider(label) {
    if (window.XMLHttpRequest) {
        httpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    var searchStr;
    if(label == 'query'){
      	httpRequest.onreadystatechange = searchCallback;
      	searchStr = document.getElementById('searchStr').value;
    }
    else{
      	httpRequest.onreadystatechange = searchCallbackAssign;
      	searchStr = document.getElementById('searchStr2').value;
    }
    httpRequest.open('GET', '/tpim/am/wizard/searchProvider.jsp?searchType=service&search=' + searchStr, true);
    httpRequest.send(null);
}
    
function searchCallback() {
    if (httpRequest.readyState == 4) {
      if (httpRequest.status == 200) {
        var obj = eval(httpRequest.responseText);
        var htmlStr = '';
        for (var i = 0; i < obj.length; i++) {
			 htmlStr += '<input name="radio" value="' + obj[i].id + 
            '" type="radio" id="radio_' + obj[i].id + '" > ' + 
            '<label for="radio_' + obj[i].id + '">' + obj[i].name + ' (' + obj[i].id + ')</label><br>';
        }
        if (obj.length == 0) {
          htmlStr = 'No matching providers';
        }
        document.getElementById('providerResult').innerHTML = htmlStr;
      } else {
        alert('Search failed: ' + httpRequest.statusText);
      }
    }
}
  
function searchCallbackAssign() {
    if (httpRequest.readyState == 4) {
      if (httpRequest.status == 200) {
        var obj = eval(httpRequest.responseText);
        var htmlStr = '';
        for (var i = 0; i < obj.length; i++) {
			 htmlStr += '<input onclick="onRadioClickAssign(this);" name="radioAssign" value="' + obj[i].id + 
            '" type="radio" id="radio_' + obj[i].id + '" > ' + 
            '<label for="radio_' + obj[i].id + '">' + obj[i].name + ' (' + obj[i].id + ')</label><br>';
        }
        if (obj.length == 0) {
          htmlStr = 'No matching providers';
        }
        document.getElementById('providerResult2').innerHTML = htmlStr;
      } else {
        alert('Search failed: ' + httpRequest.statusText);
      }
    }
}
  
function showError(errorInfo, tipDiv){
	$(tipDiv).innerHTML = "" + errorInfo + "";
  	$(tipDiv).style.display = '';
  	resizeFrame();
}
        
function searchAssignment() {

   if(document.getElementById("AMShortcodeAssignment_Edit") != null){
    document.getElementById("AMShortcodeAssignment_Edit").disabled = true
    }
   if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
    document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
    }
	if(searchType == "provider"){
		searchAssignmentByProvider();
	} else if(searchType == "shortcode") {
		searchAssignmentByShortcode();
	}
}
    
function searchAssignmentByProvider() {
	var pid;
	if($("selectDiv").style.display != "none"){//selection
		var currentDiv = document.getElementById("selectDiv").getElementsByTagName("select")[0];
		if(currentDiv){
			for(var i = 0; i < currentDiv.options.length; i++){
			  if(currentDiv.options[i].selected == true)
				  pid = currentDiv.options[i].value;
				  if(pid == 'default') return;
			}
			listAssignment(pid, null, "provider");
		}
	}else{//radio
		var radios = document.getElementsByName("radio");
		if(radios){
			for(var i = 0; i < radios.length; i++){
				if(radios[i].checked){
					pid = radios[i].value;
					listAssignment(pid, null, "provider");
					break;
				}
			}
		}
	}
	$("searchTip").style.display = "none";
}
    
function searchAssignmentByShortcode(){
	var range = $("searchRange").value;
	if(range){
		if(!validateAssignmentShortcode(range, "searchTip")) return;
	}
	listAssignment("all", range, "shortcode");
	$("searchTip").style.display = "none";
}
 
function listAssignment(pid, range, type){
	var hitsNumber = $("itemNumber_assignment").value;
	if (hitsNumber == null || hitsNumber.length == 0) {
		hitsNumber = 0;
	} else {
		if(!isInteger(hitsNumber)) {
			return false;
		} else if(hitsNumber <= 0){
			return false;
		}
	}
	getAssignmentData(pid, range, hitsNumber, type);
}
 
function listAssignmentWithPageNum(num){
 	var hitsNumber = $("itemNumber_assignment").value;
	if (hitsNumber == null || hitsNumber.length == 0) {
		hitsNumber = 0;
	} else {
		if(!isInteger(hitsNumber)) {
			return false;
		} else if(hitsNumber <= 0){
			return false;
		}
	}
	var pid;
   	if(symbol == "radio"){
   		pid = document.getElementById('radioValue').value;
   	}
   	else{
   		var selection = document.getElementById("providerAssign").options
   		for(var i = 0; i < selection.length; i++){
   			if(selection[i].selected == true)
			pid = selection[i].value;
   		}
   	}
	var pageNum = 1;
	if(hitsNumber != 0){
		pageNum = Math.ceil(parseInt(num) / hitsNumber);
	}
	setSearchProvider("providerSearch", pid);
	getAssignmentDataWithPageNum(pid, null, hitsNumber, pageNum, assignShortcode, "provider");
}
 
function setSearchProvider(selectId, pid){
	var selection = document.getElementById(selectId).options;
	for(var i = 0; i < selection.length; i++){
		if(selection[i].value == pid){
			selection[i].selected = true;
			break;
		}
	}
}
 
function isInteger(inputString) {
    var reg =/^(-?)[0-9]+$/; 
	if (reg.test(inputString)) {
		return true;
	} else {
		return false;
	}
}
    
function onRadioClickAssign(field){
	document.getElementById('radioValue').value = field.value; 
}
    
function enableSearch() {
    document.getElementById('selectDiv').style.display = 'none';
    document.getElementById('searchDiv').style.display = '';
	document.getElementById('previous').style.display = '';
	resizeFrame();
}
    
function enableSearchAssign() {
    document.getElementById('selectDiv2').style.display = 'none';
    document.getElementById('searchDiv2').style.display = '';
	document.getElementById('previous2').style.display = '';
	symbol = 'radio';
	//document.getElementById('shortcodeRange').value = '';
	resizeFrame();
}
    
function onBackClick(field) {
	document.getElementById('searchDiv').style.display = 'none';
	document.getElementById('previous').style.display = 'none';
    document.getElementById('selectDiv').style.display = '';
	var radios = document.getElementsByName("radio");
	var pid = null;
	for(var i = 0; i < radios.length; i++){
		if(radios[i].checked) pid = radios[i].value;
		radios[i].checked = false;
	}
	symbol = 'select';
	if(pid != null) setSearchProvider("providerSearch", pid);
	resizeFrame();
}
    
function onBackClickAssign(field) {
	document.getElementById('searchDiv2').style.display = 'none';
	document.getElementById('previous2').style.display = 'none';
    document.getElementById('selectDiv2').style.display = '';
    var radios = document.getElementsByName("radioAssign");
	var pid = null;
	for(var i = 0; i < radios.length; i++){
		if(radios[i].checked) pid = radios[i].value;
		radios[i].checked = false;
	}
	symbol = 'select';
	if(pid != null) setSearchProvider("providerAssign", pid);
	resizeFrame();
}
     

document.onkeydown = function(eve)
{
	var e;
	var code;
	if(!eve){//ie
	   code = event.keyCode; 
	   e = event.srcElement;
	}else{//firefox
	   code=eve.which;
	   e = eve.target;
	}
	if(code==13)
	{
		if(e == document.getElementById("searchStr")){
			document.getElementById("selectSearch").click();
			return false;
		}else if(e == document.getElementById("searchStr2")){
			document.getElementById("assignSearch").click();
			return false;
		}else if(e == document.getElementById("shortcodeRange")){
			document.getElementById("assignButton").click();
			return false;
		}else if(e == document.getElementById("searchRange") || e == document.getElementById("itemNumber_assignment")){
			document.getElementById("shortcode-assignment-search-btn").click();
			return false;
		}
	}
}

function setDefault(){
	var defaultSelection = document.getElementById("defaultValue");
	defaultSelection.selected = true;
}

function changeAssignment(){
	var options = $("searchType");
	for(var i = 0; i < options.length; i++){
		if(options[i].selected){
			searchType = options[i].value;
			break;
		}
	}
	if(searchType == "provider"){
		$("shortcodeCondition").style.display = "none";
		$("providerCondition").style.display = "";
		$("selectDiv").style.display = "";
		$("searchDiv").style.display = "none";
		$("searchTip").style.display = "none";
	} else if(searchType == "shortcode"){
		$("shortcodeCondition").style.display = "";
		$("providerCondition").style.display = "none";
		$("previous").style.display = "none";
	}
	resizeFrame();
}

function revokeShortcode(args){
	var pid = args[0];
	var confirmInfo = args[1];
	var shortcode = (args[2] == args[3])? args[2] : args[2] + "-" + args[3];
	var continued = confirm(confirmInfo + ' ' + shortcode + '?');
  	if(!continued) return;
  	sendRevokeRequest(pid,shortcode);
}

function sendRevokeRequest(pid,shortcode){
	if (window.XMLHttpRequest) {
        httpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    httpRequest.onreadystatechange = revokeCallback;
    httpRequest.open('GET', '/tpim/protected/sms/shortcodeAssignment.do?action=revoke&pid=' + pid + '&shortcodeRange=' + shortcode, true);
    httpRequest.send(null);
}

function revokeCallback(){
	if (httpRequest.readyState == 4) {
	    if (httpRequest.status == 200) {
	        var obj = eval(httpRequest.responseText);
	        if(obj[0].result == "success"){
	        	if($("AMShortcodeAssignment").rows.length == 2 && top.list_assignment.currentPage > 1){
	        		top.list_assignment.currentPage--;
	        	}
	        	top.list_assignment.refresh();
	        	hide("revokeTip");
	        	hide("assignmentTip");
	        	showError(obj[0].msg, "revokeTip");
	        }else{
	        	showError(obj[0].msg, "revokeTip");
	        	//alert('Revoke failed: ' + obj[0].msg);
	        }
	    } else {
	      	alert('Revoke failed: ' + httpRequest.statusText);
	    	
	    }
    }
}

function composeAssignmentUrl(isAll, pid, range, type){
	var url = "";
	if(isAll){
		url = url + "&all=y";
	}else{
		url = url + "&all=n";
	}
	url = url + "&pid=" + pid;
	url = url + "&shortcodeRange=" + range;
	url = url + "&type=" + type;
	return url;
}

function setShortcodeAssignment(displayType, condition, number){
	setSelectByValue("searchType", displayType);
	if(displayType == "provider"){
		setSelectByValue("providerSearch", condition);
		$("shortcodeCondition").style.display = "none";
		$("providerCondition").style.display = "";
	}else{
		$("searchRange").value = condition;
		$("shortcodeCondition").style.display = "";
		$("providerCondition").style.display = "none";
	}
	$("itemNumber_assignment").value = number;
}


/**
 * Shows an element
 * @param id element id
 */
function show(id) {
	var element = document.getElementById(id);
	if(element != null) {
		element.style.display = 'block';
	}
}

/**
 * Hides an element
 * @param id element id
 */
function hide(id) {
    var element = document.getElementById(id);
	if(element != null) {
		element.style.display = 'none';
	}
}