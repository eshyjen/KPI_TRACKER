var activeElement = "";

function activateCreate() {
	document.getElementById("whitelist-form-create").disabled = false;
}
function updateParameters() {
	var parameters = getParams();
	document.getElementById("service.shortcode").value = parameters[0];
	document.getElementById("keyword").value = parameters[1];
	document.getElementById("cscaid").value = parameters[2];
}
function activateEdit() {
	updateParameters();
}
function confirmDelete() {
	var parameters = getParams();
	if (confirm("Do you want to delete " + parameters[0] + "?")) {
		alert("Delete");
	}
}

function getParams() {
	return activeElement.split("_");
}
/**
 * when user click certain value, display the text into input field
 */
function addTabClass(value) {
	if (!value) {
		return;
	}
	if (document.getElementById(value).className != 'selectedRow') {
		if (activeElement != '') {
			document.getElementById(activeElement).className = '';
		}
		document.getElementById(value).className = 'selectedRow';
		activeElement = value;

		if (document.getElementById("SMSAccess-list-edit-button") != null) {
			document.getElementById("SMSAccess-list-edit-button").disabled = false;
		}

		document.getElementById("SMSAccess-list-del-button").disabled = false;
		// updateParameters();
	} else {
		document.getElementById(value).className = '';
		document.getElementById("SMSAccess-list-del-button").disabled = true;
	}

	if (document.getElementById("testlist-meta-delete") != null) {
		document.getElementById("testlist-meta-delete").value = value;
	}
}
function activateCreate() {
	document.getElementById("whitelist-form-create").disabled = false;
}

$ = jQuery.noConflict();

// toggle show/hide for native/rest panels
// TODO: Need to use jquery api
function toggleForm(cbox, panel) {
	var showPanel = cbox.checked || ($(cbox).attr("disabledval") == "true" || $(cbox).attr("disabledval") == "checked");
	document.getElementById(panel).style.display = (showPanel == true) ? 'block'
			: 'none';
	//disable editing, if user is not am
	if (userRole == "am") {
		$("form.saveForm input[type='submit'], form.saveForm input[type='reset']").removeAttr("disabled");
	}
}

function syncElements(fromId, toId) {
	$("#"+toId).val("#" + fromId);
}

function toggleAPILink(apiLinkId) {
	//document.getElementById("globalmsg").innerHTML = "";
	$("div.unselected").hide();
	document.getElementById(apiLinkId).style.display = 'block';
	selectTab2(document.getElementById("api"));
	expandCapPanels();
}

function activateRadiobuttons(cbox, value1, value2) {
	var val1 = $("#" + value1);
	var val2 = $("#" + value2);
	var disabledVal1 = val1.attr("disabledval");
	var disabledVal2 = val2.attr("disabledval");
	if (cbox.checked) {
		document.getElementById(value1).disabled = disabledVal1 && (disabledVal1 == "true" || disabledVal1 == "checked");
		document.getElementById(value2).disabled = disabledVal2 && (disabledVal2 == "true" || disabledVal2 == "checked");
	} else {
		document.getElementById(value1).disabled = true;
		document.getElementById(value2).disabled = true;
	}
}

function activateRadiobuttonsForAgreement(cbox, value1, value2) {
    var val1 = document.getElementById(value1);
    var val2 = document.getElementById(value2);
    var disabledVal1,disabledVal2;
    if(val1 != null) {
                    val1 = $("#" + value1);
                    disabledVal1 = val1.attr("disabledval");
    } else {
                    disabledVal1 = 'undefined';
    }      
    if(val2 != null) {
                    val2 = $("#" + value2);
                    disabledVal2 = val2.attr("disabledval");
    } else {
                    disabledVal2 = 'undefined';
    }
    if (cbox.checked) {
            if (disabledVal1 != 'undefined'){
                    document.getElementById(value1).disabled = disabledVal1 && (disabledVal1 == "true" || disabledVal1 == "checked");
            }
            if (disabledVal2 != 'undefined'){
                    document.getElementById(value2).disabled = disabledVal2 && (disabledVal2 == "true" || disabledVal2 == "checked");
            }
    } else {
                     if (val1 != null){
                             document.getElementById(value1).disabled = true;
                     }
                     if (val2 != null){
                             document.getElementById(value2).disabled = true;
                     }
    }
}

function activateRadbuttons(cbox, value1) {
	var val1 = $("#" + value1);
	var disabledVal1 = val1.attr("disabledval");
	if (cbox.checked) {
		document.getElementById(value1).disabled = disabledVal1 && (disabledVal1 == "true" || disabledVal1 == "checked");
	} else {
		document.getElementById(value1).disabled = true;
	}
}

function selectradio(cbox, value)
{
	if (cbox.checked){
	document.getElementById(value).checked = true ;
	}
}


$.fn.outerHTML = function() {
	return jQuery('<div />').append(this.eq(0).clone()).html();
};

function expandCapPanels() {
	 $('input[checkedval*="checked"], input[checkedval*="true"], input[disabledval*="checked"], input[disabledval*="true"]').each(
				function() {
					var tname = $(this).attr("name");
					var disabledAttr = $(this).attr("disabledval");
					var checkedAttr = $(this).attr("checkedval");
	    			if (tname && (tname.indexOf("_native") != -1 || tname.indexOf("_rest") != -1)) {
		    			var panelId = $(this).attr("id");
		    			if (panelId && (panelId.indexOf("_native") != -1 || panelId.indexOf("_rest") != -1)) {
		    				toggleForm($("#" + panelId)[0], panelId + '-panel');
		    			}
		    		}
	    			//For enabling checkBoxes of CR
	    			if (checkedAttr && disabledAttr && (disabledAttr == "true" || disabledAttr == "checked")
	    					&& (checkedAttr.indexOf("true") != -1 || checkedAttr.indexOf("checked") != -1)) {
	    				$(this).attr("disabled", "disabled");
	    			}
				}
	);
}

$(function() {
		expandCapPanels();
		$('input[checkedval*="checked"], input[checkedval*="true"]').each(
			function() {
				var disabledAttr = $(this).attr("disabledval");
				var checkedAttr = $(this).attr("checkedval");
				$(this).attr("checked", "true");
				var children = $(this).parent().siblings();
				children.each(function(index) {
	    			if (checkedAttr && disabledAttr && (disabledAttr == "true" || disabledAttr == "checked")
	    					&& (checkedAttr.indexOf("true") != -1 || checkedAttr.indexOf("checked") != -1)) {
	    				$(this).find("input").attr("disabled", "disabled");
	    			} else {
	    				$(this).find("input").removeAttr("disabled");
	    			}
				});
		    });
		$('#agreementAPIForm input[disabledval*="checked"], #agreementAPIForm input[disabledval*="true"]').each(
				function() {
					var disabledAttr = $(this).attr("disabledval");
					var checkedAttr = $(this).attr("checkedval");
					var cbtype = $(this).prop("type");
	    			if (disabledAttr && (disabledAttr == "true" || disabledAttr == "checked")) {
	    				$(this).attr("disabled", "disabled");
	    			}
		});

	// workaround - because of a jquery bug in the select options looping with
	// attributes
	var selectHtml = $("#serviceType-meta").html();
	if (selectHtml && selectHtml.match(/checkedval="checked"/)) {
		$("#serviceType-meta option").each(function() {
			var outerHtml = $(this).outerHTML();
			// console.log(outerHtml);
			var checkedval = outerHtml.match(/checkedval/);
			var selected = outerHtml.match(/checkedval="checked"/);
			if (selected != 'undefined' && selected != null && selected != "") {
				$("#serviceType-meta").val($(this).val());
			} else if (checkedval == 'undefined' || checkedval == null) {
				$(this).remove();
			}
		});
	}

	if (typeof $("#startDateTime").datepicker == 'function') {
		$("#startDateTime").datepicker(
				{
					showOn : "button",
					buttonImage : "../../resources/tmotheme/i/calendar2.png",
					buttonImageOnly : true,
					dateFormat: 'yy-mm-dd',
					defaultDate : "+1w",
					changeMonth : true,
					numberOfMonths : 1,
					beforeShow : function(i) {
						if ($(i).attr('disabled')) {
							return false;
						}
					},
					onClose : function(selectedDate) {
						$("#endDateTime").datepicker("option", "minDate",
								selectedDate);
					}
				});
	}

	if (typeof $("#endDateTime").datepicker == 'function') {
		$("#endDateTime").datepicker(
				{
					showOn : "button",
					buttonImage : "../../resources/tmotheme/i/calendar2.png",
					buttonImageOnly : true,
					dateFormat: 'yy-mm-dd',
					defaultDate : "+1w",
					changeMonth : true,
					numberOfMonths : 1,
					beforeShow : function(i) {
						if ($(i).attr('disabled')) {
							return false;
						}
					},
					onClose : function(selectedDate) {
						$("#startDateTime").datepicker("option", "maxDate",
								selectedDate);
					}
				});
		var endDateVal = $("#endDateTime").val();
		//$("#startDateTime").attr('readonly', 'readonly');
		//$("#endDateTime").attr('readonly', 'readonly');
		if (endDateVal) {
			$("#expire").attr("checked", "checked");
		} else {
			$("#neverExpire").attr("checked", "checked");
			$("#startDateTime").datepicker().attr('readonly', 'readonly');
			$("#endDateTime").datepicker().attr('readonly', 'readonly');
			$("#startDateTime").attr('disabled', 'disabled');
			$("#endDateTime").attr('disabled', 'disabled');
		}
	}

	var tabs = $("table.tabs");
	if (tabs) {
		var cTab = tabs.attr("cTabId");
		if (cTab && cTab != "") {
			if (typeof selectTab2 == 'function') {
				selectTab2(document.getElementById(cTab));
			} else {
				$("#" + cTab).click();
			}
		}
	}
	$("#expire").click(function() {
		//$("#startDateTime").datepicker().removeAttr('readonly');
		//$("#endDateTime").datepicker().removeAttr('readonly');
		$("#startDateTime").removeAttr('disabled');
		$("#endDateTime").removeAttr('disabled');
	});

	$("#neverExpire").click(function() {
		$("#startDateTime").datepicker().attr('readonly', 'readonly');
		$("#endDateTime").datepicker().attr('readonly', 'readonly');
		$("#startDateTime").attr('disabled', 'disabled');
		$("#endDateTime").attr('disabled', 'disabled');
	});
});
