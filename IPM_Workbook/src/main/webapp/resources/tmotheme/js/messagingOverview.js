var type='shortcode';
var searchShortcodeCondition_Overview = null;
var searchServiceCondition_Overview = null;

function displayTooltip(){
	setClass('messagingTip');
	showTooltip(info);
}

function search() {
	var condition;
	var condition = document.getElementById("condition").value;
	var hitsNumber = document.getElementById("itemNumber").value;
	//var currentStartNumber = 1;
	var isAll = false;
	if(type == 'shortcode'){
		if(!validateOverviewShortcode(condition))
			return false;
		//don't search if the hit number is zero.
		if (hitsNumber == null || hitsNumber.length == 0) {
			hitsNumber = 0;
			isAll = true;
		} else {
			if(!isInteger(hitsNumber)) {
				return false;
			} else if(hitsNumber <= 0){
				return false;
			}
		}
		getShortcodeData(condition, hitsNumber, isAll);
	}else{//messaging service
		//don't search if the hit number is zero.
		if (hitsNumber == null || hitsNumber.length == 0) {
			hitsNumber = 0;
			isAll = true;
		} else {
			if(!isInteger(hitsNumber)) {
				return false;
			} else if(hitsNumber <= 0){
				return false;
			}
		}
		$("overviewTip").style.display = "none";
		getServiceData(condition, hitsNumber, isAll);
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
		document.getElementById("sms-overview-search-btn").click();
		return false;
	}
}

function caculatePageNumber(startRow, sectionSize){
	return Math.ceil(startRow/sectionSize);	
}

function setMessagingOverview(displayType, condition, number, information){
	type = displayType;
	setSelectByValue("type", displayType);
	$("condition").value = condition;
	$("itemNumber").value = number;
	info = information;
	if(type == "shortcode"){
		type = 'shortcode';
	}else if(type == "messaging"){
		type = 'messaging';
	}
}

function setSelectByValue(id, value){
	var options = $(id).options;
	for(var i = 0; i < options.length; i++){
		if(options[i].value == value){
			options[i].selected = true;
			break;
		}
	}
}

function composeOverviewUrl(type, isAll, condition){
	var url = "";
	url = url + "type=" + type;
	if(isAll){
		url = url + "&all=y";
	}else{
		url = url + "&all=n";
	}
	url = url + "&condition=" + condition;
	return url;
}