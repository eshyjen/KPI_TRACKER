/**
 * Transfer the values from the display drop down boxes to the actual hidden fields.
 * This is done to allow keeping the drop down values, even if they are disabled (due to various other options)
 * Method should be called from within onSubmit and hence it returns weather the form should be allowed to submit (always yes)
 */
function reportTransferValues() {
    if (!checkDateSelection()) {
        return false;
    }

    var displayEMonth = document.report.displayEMonth;
    var displaySMonth = document.report.displaySMonth;
    var displayEDay   = document.report.displayEDay;
    var displaySDay   = document.report.displaySDay;
    var eMonth        = document.report.eMonth;
    var sMonth        = document.report.sMonth;
    var eDay          = document.report.eDay;
    var sDay          = document.report.sDay;

    eMonth.value = displayEMonth.value;        
    sMonth.value = displaySMonth.value;
    eDay.value   = displayEDay.value;
    sDay.value   = displaySDay.value;
    return true;
}

function reportTypeSelection(reportType) {
	var eMonth = document.report.displayEMonth;
	var sMonth = document.report.displaySMonth;
    var eDay = document.report.displayEDay;
    var sDay = document.report.displaySDay;
    var group = document.report.interval;
    var portal = document.report.portal;
    var type = document.report.type;

    if (portal != null) { // If portal selection is not shown, this will return null
      if (reportType != 'service') { // If no Service Usage report type, disable portal selection and show N/A (added if not available as the last option)
        if (portal.options[portal.length - 1].value != '__na__') {
          portal.options[portal.length] = new Option('N/A', '__na__');
        }
        portal.options[portal.length - 1].selected = true;
        portal.disabled = true;
      } else {
        if (portal.options[portal.length - 1].value == '__na__') {
          portal.options[portal.length - 1] = null;
        }
        portal.disabled = false;
      }
    }

	group.disabled = false;
	if (reportType == 'notusedtickets') {
		group.disabled = true;
		enableTimeContext();
		group.selectedIndex = 0;
	}
	
	//for message
	type.disabled = false;
	if (reportType == 'ad' || reportType == 'notusedtickets') {
		type.disabled = true;
		type.selectedIndex = 0;
	}
}

function enableTimeContext() {
	var eMonth = document.report.displayEMonth;
	var sMonth = document.report.displaySMonth;
    var eDay = document.report.displayEDay;
    var sDay = document.report.displaySDay;
	sMonth.disabled = false;
    eMonth.disabled = false;
    sDay.disabled = false;
    eDay.disabled = false;
}
	
function dateContextSelection(groupBy) {
	var eMonth = document.report.displayEMonth;
	var sMonth = document.report.displaySMonth;
    var eDay = document.report.displayEDay;
    var sDay = document.report.displaySDay;
    enableTimeContext();
    if (groupBy == 'Y') {
		sMonth.disabled = true;
   		eMonth.disabled = true;
        sDay.disabled = true;
        eDay.disabled = true;
    } else if (groupBy == 'M') {
       	sDay.disabled = true;
       	eDay.disabled = true;
    }
}

function checkDateSelection() {
    var sYearVal = document.report.sYear.value;
    var sMonthVal = document.report.displaySMonth.value;
    var sDayVal = document.report.displaySDay.value;

    if (document.report.displaySMonth.disabled == true) {
        sMonthVal = 0;
        sDayVal = 0;
    } else if (document.report.displaySDay.disabled == true) {
        sDayVal = 0;
    }
    
    var eYearVal = document.report.eYear.value;
    var eMonthVal = document.report.displayEMonth.value;
    var eDayVal = document.report.displayEDay.value;
    
    if (document.report.displayEMonth.disabled == true) {
        eMonthVal = 0;
        eDayVal = 0;
    } else if (document.report.displayEDay.disabled == true) {
        eDayVal = 0;
    }
    
    var sDate = new Date(sYearVal,sMonthVal,sDayVal);
    var eDate = new Date(eYearVal,eMonthVal,eDayVal);

    if (sDate > eDate) {
        document.getElementById("dateWarning").style.display = "block";
        return false;
    } else {
        document.getElementById("dateWarning").style.display = "none";
        return true;
    }
}
