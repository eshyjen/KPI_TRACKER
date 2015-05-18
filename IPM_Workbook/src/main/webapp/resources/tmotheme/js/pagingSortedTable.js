/**
 * This is an utility java script toolbox which provides dispalying table function.
 * @author Nevin
 * @version 0.1
 * @since 2008.12.20
 */

/**
 * NOTE:
 *   1. You need to import ajax.js, util.js and tooltip.js files.
 *	 2. You need to import default.css file.
 *   3. You need to invoke the loadTooltip() method to add the mouseevent listener first.
 *
 *
 * The public methods are as follow:
 *   1. SortedTableClass(id, className, sortByColumn, ctx)		Create a dynamic sorted table
 *   2. SortedTableClass.setUrl(url)							Set the request
 *   3. SortedTableClass.setPageSize(pageSize)					Set the size of the table
 *   4. SortedTableClass.setTipVisibleOrNot(isVisible)			Set whether the tip is visible
 *   5. SortedTableClass.setUpButtonVisibleOrNot(isVisible)		Set whether the top buttons are visible
 *   6. SortedTableClass.setDownButtonVisibleOrNot(isVisible)	Set whether the bottom buttons are visible
 *   7. SortedTableClass.setEditButtonVisibleOrNot(isVisible)   Set whether the edit buttons are visible
 *   8. SortedTableClass.setRemoveButtonVisibleOrNot(isVisible) Set whether the remove buttons are visible
 *   9. SortedTableClass.refresh()								Send an XMLHttpRequest to get the table data
 *   10.SortedTableClass.destoryTable()							Destory the table
 *   11.SortedTableClass.getCheckedItemsOfBox()					Get all of the checked check box items
 *   12.SortedTableClass.getCheckedItemsOfRadio()				Get the checked radio
 *   13.SortedTableClass.deleteRow(obj)							Delete the row where the obj is in.
 *   14.SortedTable(id, className, tabledata, sortByColumn, ctx)Create a static sorted table
 *   15.SortedTable.setData(tabledata)							Set the data of sorted table
 *   16.SortedTable.deleteRow(obj)								Delete the row where the obj is in.
 */

/**
 * Define a sorted table object which contains paging funcion and can refresh
 * according to the user reqeust dynamicly.
 *
 * @param id
 *						the id of the sorted table
 * @param className
 * 						the style of the table
 * @param sortByColumn
 *						the default column number to sort by
 * @param ctx
 *						the context path of the request
 *
 */
function SortedTableClass(id, className, sortByColumn, ctx) {
    this.id = id;
    this.className = className;
    this.sortByColumn = sortByColumn;
    this.ctx = ctx;
}

SortedTableClass.prototype.sortedTable = null;
SortedTableClass.prototype.url = null;
SortedTableClass.prototype.req = null;
SortedTableClass.prototype.next = "Next";
SortedTableClass.prototype.previous = "Previous";
SortedTableClass.prototype.first = "First";
SortedTableClass.prototype.last = "Last";
SortedTableClass.prototype.edit = "Edit";
SortedTableClass.prototype.remove = "Delete";
SortedTableClass.prototype.currentPage = 1;
SortedTableClass.prototype.totalPage = 0;
SortedTableClass.prototype.pageSize = 0;
SortedTableClass.prototype.tipInfo = null;
SortedTableClass.prototype.noRecordInfo = null;
SortedTableClass.prototype.tipVisible = true;
SortedTableClass.prototype.upButtonVisible = true;
SortedTableClass.prototype.downButtonVisible = true;
SortedTableClass.prototype.sortByColumn = 0;
SortedTableClass.prototype.isSrcoll = false;
SortedTableClass.prototype.editButtonVisible = false;
SortedTableClass.prototype.removeButtonVisible = false;

/**
 * Set the request url from which the table will get data.
 */
SortedTableClass.prototype.setUrl = function(url) {
    this.url = url;
}

/**
 * Set the number of items the table display. 
 */
SortedTableClass.prototype.setPageSize = function(pageSize) {
    this.pageSize = pageSize;
}

/**
 * Set the number of currentPage. 
 */
SortedTableClass.prototype.setCurrentPage = function(currentPage) {
    this.currentPage = currentPage;
}

/**
 * Set the tip information of the table. 
 */
SortedTableClass.prototype.setTipInfo = function(tipInfo) {
    this.tipInfo = tipInfo;
}

/**
 * Set the tip information of the table when the table data is null. 
 */
SortedTableClass.prototype.setNoRecordInfo = function(tipInfo) {
    this.noRecordInfo = tipInfo;
}

/**
 * Set whether the tip is visible.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setTipVisibleOrNot = function(isVisible) {
	this.tipVisible = isVisible;
}

/**
 * Set whether the top buttons are visible.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setUpButtonVisibleOrNot = function(isVisible) {
	this.upButtonVisible = isVisible;
}

/**
 * Set whether the bottom buttons are visible.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setDownButtonVisibleOrNot = function(isVisible) {
	this.downButtonVisible = isVisible;
}

/**
 * Set whether the table is srcolled to view.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setIsScroll = function(isSrcoll) {
	this.sortedTable.isSrcoll = isSrcoll;
}

/**
 * Set the sort column number.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setSortByColumn = function(sortByColumn, isAscending) {
	this.sortedTable.sortByColumn = sortByColumn;
	this.sortedTable.sortAscending = isAscending;
    this.sortedTable.hideArrows('sorted_up');
    this.sortedTable.hideArrows('sorted_down');
}

/**
 * Set whether the edit button is visible.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setEditButtonVisibleOrNot = function(isVisible) {
	this.editButtonVisible = isVisible;
}

/**
 * Set whether the remove button is visible.
 *
 * @param isVisible
 *					Ture of false
 */
SortedTableClass.prototype.setRemoveButtonVisibleOrNot = function(isVisible) {
	this.removeButtonVisible = isVisible;
}

/**
 * Send an XMLHttpRequest to get the table data.
 */
SortedTableClass.prototype.refresh = function() {
    var listener = this;
    var destination;
	if(this.pageSize != 0) {//need paging
		destination = this.composeUrl(this.url)
	} else {
		destination = encodeURI(this.url);
	}
	if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    this.req.onreadystatechange = function() {
        if (typeof( process) != 'undefined') {
            process(listener);
        }
    };
	openPopup("Loading...");
    this.req.open("GET", destination, true);
    this.req.send(null);
}

/**
 * Destory the table.
 */
SortedTableClass.prototype.destoryTable = function() {
	while(this.sortedTable.table.rows.length > 0) {
    	this.sortedTable.table.deleteRow(this.sortedTable.table.rows.length-1);
    }
	$(this.id + "_Table").removeChild(this.sortedTable.table);
    this.sortedTable = null;
    $(this.id + "_UpButton").innerHTML = "";
    $(this.id + "_DownButton").innerHTML = "";
}

/**
 * Delete the row where the obj is in.
 *
 * @param obj
 *				the html element, usually a button
 */
SortedTableClass.prototype.deleteRow = function(obj) {
	this.sortedTable.deleteRow(obj);
}

/**
 * Return an array which contains all of the check box elements that is checked.
 */
SortedTableClass.prototype.getCheckedItemsOfBox = function() {
	var list = new Array();
	var inputs = this.sortedTable.table.getElementsByTagName('INPUT');
	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type.toLowerCase() == "checkbox" && inputs[i].checked) {
			list[list.length] = inputs[i];
		}
	}
	return list;
}

/**
 * Return an array which contains all of the radio elements that is checked.
 */
SortedTableClass.prototype.getCheckedItemsOfRadio = function() {
	var list = new Array();
	var inputs = this.sortedTable.table.getElementsByTagName('INPUT');
	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type.toLowerCase() == "radio" && inputs[i].checked) {
			list[list.length] = inputs[i];
		}
	}
	return list;
}

/**
 * Define a sorted table object. This object only display the data and cannot
 * refresh dynamicly.
 */
function SortedTable(id, className, tabledata, sortByColumn, ctx) {
    this.id = id;
    this.sortByColumn = sortByColumn;
    this.sortAscending = true;
    if (!top.sortedTables) {
    	top.sortedTables = new Array();
    } else if (top.sortedTables[this.id]) {
    	this.sortByColumn = top.sortedTables[this.id].sortByColumn;
    	this.sortAscending = top.sortedTables[this.id].sortAscending;
    }
    top.sortedTables[this.id] = this;
    this.table = document.createElement('TABLE');
    //create a table and add it to table div
    this.table.id = id;
    this.table.className = className;
    var tableDiv = $(id + "_Table");
    tableDiv.appendChild(this.table);
    if (tabledata != null) {
        this.createTableHeader(tabledata[2], ctx);
    	var selectedRow = this.setData(tabledata);
    }
    this.isSrcoll = false;
}

/**
 * Set the data of sorted table. Only use it when you use SortedTable.
 *
 * @tabledata
 *				the data of the server return
 */
SortedTable.prototype.setData = function(tabledata) {
    var selectedRow = null;
    //delete old data
    while(this.table.rows.length > 1) {
    	this.table.deleteRow(this.table.rows.length-1);
    }
    //the first data of data set is the selected row id
    this.selectedRowId = (tabledata[0] == "")? tabledata[0] : tabledata[0] + "_" + this.id;
    //fill in new data
    for(var i=3; i<tabledata.length; i++) {
    	var row = this.table.insertRow(-1);
    	var father = this;
		row.onclick = function(){father.select(this);}
    	this.fillTableRow(row, tabledata[i]);
    }
    if (this.table.rows.length > 1) {
    	//sort the table
    	this.doSort(this.table.rows[0].cells[this.sortByColumn]);
    	//highlight the selected row if selectedRowId exists
       	if (this.selectedRowId != 'none') {
       		selectedRow = this.selectedRowId ? document.getElementById(this.selectedRowId) : null;
	        if (selectedRow) {
    		    addClass(selectedRow, 'selectedRow');
    		    if(this.isSrcoll){
    		    	window.top.frames[2].scrollTo(0,getPos(selectedRow));
    		    	this.isSrcoll = false;
    		    }
         	} else {
         		//selectedRow = this.table.rows[1];
         		//this.selectedRowId = this.table.rows[1].id;
         		//addClass(selectedRow, 'selectedRow');
         	}
       	}
    }
    return selectedRow;
}

function getPos(e){
    var t=e.offsetTop;
    //var l=e.offsetLeft;
    while(e=e.offsetParent){
        t+=e.offsetTop;
        //l+=e.offsetLeft;
    }
    return t;
}

/**
 * Delete the row where the obj is in.
 *
 * @param obj
 *				the html element, usually a button
 */
SortedTable.prototype.deleteRow = function(obj) {
	var row = getParent(obj, "TR");
	if(row) {
		this.table.deleteRow(row.rowIndex);
	}
}

/**
 * Get the inner text of an element.
 */
SortedTable.prototype.getInnerText = function(el) {
    if (typeof el == "string") {
  	    return el;
    } else if (typeof el == "undefined") {
    	return el;
    } else if (el.innerText) {
     //Not needed but it is faster
    	return el.innerText;
    } else {
    	var str = "";
    	var cs = el.childNodes;
    	var l = cs.length;
	    for (var i = 0; i < l; i++) {
    		switch (cs[i].nodeType) {
		        case 1: //ELEMENT_NODE
		          str += this.getInnerText(cs[i]);
		          break;
		        case 3:	//TEXT_NODE
		          str += cs[i].nodeValue;
		          break;
	      	}
    	}
    	return str;
    }
}

/******************************************************************************
 * The following functions are private.                                       *
 ******************************************************************************/

/**
 * Compose the url with the page number and page size.
 * Encode url to support the UTF-8 charset.
 *
 * @param originalUrl
 *						the url without page information
 * @Visible private
 */
SortedTableClass.prototype.composeUrl = function(originalUrl) {
	var startRow = (this.currentPage - 1) * this.pageSize + 1;
	if (originalUrl.indexOf("?") != -1) {
		return encodeURI(originalUrl + "&startRow=" + startRow + "&sectionSize=" + this.pageSize);
	} else {
		return encodeURI(originalUrl + "?startRow=" + startRow + "&sectionSize=" + this.pageSize);
	}
}

/**
 * @Visible private
 */
function process(listener) {
	if (listener.req.readyState == 4) {
	  	if (listener.req.status == 200) {
	    	var tabledata = eval(listener.req.responseText);
	    	if (listener.sortedTable) {//Table already exists
	        	listener.sortedTable.setData(tabledata);
	        	listener.updateTipAndButton(tabledata);
	    	} else {//Table does not exist
	    		listener.createDiv();
	    		listener.createUpButtons();
		        listener.sortedTable = new SortedTable(
			        listener.id,
			        listener.className,
			        tabledata,
			        listener.sortByColumn,
			        listener.ctx);
			    listener.createDownButtons();
		        listener.updateTipAndButton(tabledata);
	    	}
	    	closePopup();
	    	resizeFrame();
	    } else {
	    	alert("Search failed: " + listener.req.statusText);
	    }
	}
}

/**
 * Update the status of the tip and buttons.
 *
 * @param tabledata
 *					the data server return;
 * @Visible private
 */
SortedTableClass.prototype.updateTipAndButton = function(tabledata) {
	if (tabledata[1]) {
		if(this.pageSize == 0){
			if(tabledata[1] == 0){
				this.totalPage = 0;
			}else{
				this.totalPage = 1;
			}
		}
		else
			this.totalPage = Math.ceil(tabledata[1] / this.pageSize);
	}
	if (this.currentPage == 0) this.currentPage++;
	if (this.currentPage == 1) {
		disableElement($(this.id + "_First"));
		disableElement($(this.id + "_Previous"));
		disableElement($(this.id + "_First_Down"));
		disableElement($(this.id + "_Previous_Down"));
	} else {
		enableElement($(this.id + "_First"));
		enableElement($(this.id + "_Previous"));
		enableElement($(this.id + "_First_Down"));
		enableElement($(this.id + "_Previous_Down"));
	}
	if (this.currentPage == this.totalPage || this.totalPage == 0) {
		disableElement($(this.id + "_Last"));
		disableElement($(this.id + "_Next"));
		disableElement($(this.id + "_Last_Down"));
		disableElement($(this.id + "_Next_Down"));
	} else {
		enableElement($(this.id + "_Last"));
		enableElement($(this.id + "_Next"));
		enableElement($(this.id + "_Last_Down"));
		enableElement($(this.id + "_Next_Down"));
	}
	this.updateTip(tabledata[1]);
}

/**
 * Create four div and display according to the visible attribute
 * One div is tip div;the second is top button;the third is table div and the last is bottom div.
 *
 * @Visible private
 */
SortedTableClass.prototype.createDiv = function() {
	var script = $("s" + this.id);
	//tip div
    initDiv(this.id + "_Tip", this.tipVisible, script);
    //up button div
    initDiv(this.id + "_UpButton", this.upButtonVisible, script);
    //table div
    initDiv(this.id + "_Table", true, script);
    //down button div
    initDiv(this.id + "_DownButton", this.downButtonVisible, script);
}

/**
 * Create the top buttons, including previous, first, next and last button.
 *
 * @Visible private
 */
SortedTableClass.prototype.createUpButtons = function() {
	var father = $(this.id + "_UpButton");
	father.appendChild(this.createFirstButton("First", this));
	father.appendChild(this.createPreviousButton("Previous", this));
	father.appendChild(this.createNextButton("Next", this));
	father.appendChild(this.createLastButton("Last", this));
	if (this.editButtonVisible) {
		father.appendChild(this.createEditButton("Edit", this));
	}
	if (this.removeButtonVisible) {
		father.appendChild(this.createRemoveButton("Remove", this));
	}
}

/**
 * Create the bottom buttons, including previous, first, next and last button.
 *
 * @Visible private
 */
SortedTableClass.prototype.createDownButtons = function() {
	var father = $(this.id + "_DownButton");
	father.appendChild(this.createFirstButton("First_Down", this));
	father.appendChild(this.createPreviousButton("Previous_Down", this));
	father.appendChild(this.createNextButton("Next_Down", this));
	father.appendChild(this.createLastButton("Last_Down", this));
	if (this.editButtonVisible) {
		father.appendChild(this.createEditButton("Edit_Down", this));
	}
	if (this.removeButtonVisible) {
		father.appendChild(this.createRemoveButton("Remove_Down", this));
	}
}

/**
 * Update the content of the tip.
 *
 * @Visible private
 */
SortedTableClass.prototype.updateTip = function(totalSize) {
	if (this.totalPage == 0) {
		$(this.id + "_Tip").innerHTML = this.noRecordInfo;
	} else {
		var start = (this.currentPage - 1) * this.pageSize + 1;
		var end = 0;
		if(this.pageSize == 0) {
			end = totalSize;
		} else {
			var temp = this.pageSize - 1 + start;
			end = (temp > totalSize) ? totalSize : (this.pageSize - 1 + start);
		}
		$(this.id + "_Tip").innerHTML = this.tipInfo + " " + start + " - " + end + " / " + totalSize;
	}
}

/**
 * @Visible private
 */
function createButton(buttonValue, className) {
	var tempButton = document.createElement("INPUT");
	tempButton.type = "button";
	tempButton.value = buttonValue;
	tempButton.className = className;
	return tempButton;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createNextButton = function(id, obj) {
	var buttonObj = createButton(obj.next, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.onclick = function() {
		if(document.getElementById("AMShortcodeAssignment_Edit") != null){
			document.getElementById("AMShortcodeAssignment_Edit").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
			document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove") != null){
			document.getElementById("AMShortcodeAssignment_Remove").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove_Down") != null){
			document.getElementById("AMShortcodeAssignment_Remove_Down").disabled = true
		}
		if(obj.currentPage == obj.totalPage)
			return;
		obj.currentPage++;
		obj.refresh();
	}
	return buttonObj;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createPreviousButton = function(id, obj) {
	var buttonObj = createButton(obj.previous, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.onclick = function() {
		if(document.getElementById("AMShortcodeAssignment_Edit") != null){
			document.getElementById("AMShortcodeAssignment_Edit").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
			document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove") != null){
			document.getElementById("AMShortcodeAssignment_Remove").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove_Down") != null){
			document.getElementById("AMShortcodeAssignment_Remove_Down").disabled = true
		}
		if(obj.currentPage == 1)
			return;
		obj.currentPage = obj.currentPage - 1;
		obj.refresh();
	}
	return buttonObj;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createFirstButton = function(id, obj) {
	var buttonObj = createButton(obj.first, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.onclick = function() {
		if(document.getElementById("AMShortcodeAssignment_Edit") != null){
			document.getElementById("AMShortcodeAssignment_Edit").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
			document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove") != null){
			document.getElementById("AMShortcodeAssignment_Remove").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove_Down") != null){
			document.getElementById("AMShortcodeAssignment_Remove_Down").disabled = true
		}
		obj.currentPage = 1;
		obj.refresh();
	}
	return buttonObj;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createLastButton = function(id, obj) {
	var buttonObj = createButton(obj.last, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.onclick = function() {
		if(document.getElementById("AMShortcodeAssignment_Edit") != null){
			document.getElementById("AMShortcodeAssignment_Edit").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
			document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove") != null){
			document.getElementById("AMShortcodeAssignment_Remove").disabled = true
		}
		if(document.getElementById("AMShortcodeAssignment_Remove_Down") != null){
			document.getElementById("AMShortcodeAssignment_Remove_Down").disabled = true
		}
		obj.currentPage = obj.totalPage;
		obj.refresh();
		obj.currentPage = obj.totalPage;
	}
	return buttonObj;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createEditButton = function(id, obj) {
	var buttonObj = createButton(obj.edit, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.disabled = true;
	buttonObj.onclick = function() {
		document.getElementById("assignmentTip_edit").innerHTML = "";
  	    document.getElementById("assignmentTip_edit").style.display = "none";
		var radio = document.getElementsByName('radio');
	    if (radio.length == 0) {
	    	return;
	    }
	    var checked = false;
			for(var i = 0; i < radio.length; i++) {
				if (radio[i].checked == true) {
					var v = radio[i].value;
					if (v == 'radio') {
					    showError('The short code is in use, rental fee can not be edited.', 'searchTip');					
						return;
					} else {
						var dateArray = v.split(":");
						document.getElementById("shortcode-edit-range").value = dateArray[0];
						document.getElementById("shortcode-edit-pid").value = dateArray[1];
						if(dateArray[2]=='true'){
							document.getElementById("shortcode-edit-rentalEnabled").checked = true;
						}else{
							document.getElementById("shortcode-edit-rentalEnabled").checked = false;
						}
						showEditRentalFeeText();
						document.getElementById("shortcode-edit-rentalFee").value = dateArray[3];
						var currencyCodes = document.getElementById("shortcode-edit-currencyCode").options
			            for(var j = 0; j < currencyCodes.length; j++){
			                if(currencyCodes[j].value == dateArray[4])
			                	currencyCodes[j].selected = true;
			            }
						if(dateArray[5] == 'true'){
							document.getElementById("shortcode-edit-VAT-Inclusive").click();
						}else{							
							document.getElementById("shortcode-edit-VAT-Exclusive").click();
						}
						var vatCodes = document.getElementById("shortcode-edit-vatCode").options
		                for(var j = 0; j < vatCodes.length; j++){
		                    if(vatCodes[j].value == dateArray[6])
		                        vatCodes[j].selected = true;
		                }
					}
			}
		}

		document.getElementById("AMShortcodeAssignment_UpButton").style.display = "none";
		document.getElementById("AMShortcodeAssignment_Table").style.display = "none";
		document.getElementById("AMShortcodeAssignment_DownButton").style.display = "none";
		document.getElementById("AMShortcodeAssignment_Tip").style.display = "none";
		document.getElementById("assignmentPart").style.display = "none";
		document.getElementById("searchPart").style.display = "none";
		document.getElementById("editPart").style.display = "block";
		
	}
	return buttonObj;
}

/**
 * @Visible private
 */
SortedTableClass.prototype.createRemoveButton = function(id, obj) {
	var buttonObj = createButton(obj.remove, "msgbutton");
	buttonObj.id = obj.id + "_" + id;
	buttonObj.disabled = true;
	buttonObj.onclick = function() {
		var radio = document.getElementsByName('radio');
	    if (radio.length == 0) {
	    	return;
	    }
	    
	    var checked = false;
	    for(var i = 0; i < radio.length; i++) {
			if (radio[i].checked == true) {
				var v = radio[i].value;
				if (v == 'radio') {
				    showError('The short code is in use, cannot be deleted.', 'searchTip');					
					return;
				} else {
					var dateArray = v.split(":");
					var range = dateArray[0].split("-");
					var args = new Array(dateArray[1], 'Do you really want to delete short code', range[0], range.length > 1 ? range[1] : range[0]);
					revokeShortcode(args);
					if(document.getElementById("AMShortcodeAssignment_Edit") != null){
						document.getElementById("AMShortcodeAssignment_Edit").disabled = true
					}
					if(document.getElementById("AMShortcodeAssignment_Edit_Down") != null){
						document.getElementById("AMShortcodeAssignment_Edit_Down").disabled = true
					}
					if(document.getElementById("AMShortcodeAssignment_Remove") != null){
						document.getElementById("AMShortcodeAssignment_Remove").disabled = true
					}
					if(document.getElementById("AMShortcodeAssignment_Remove_Down") != null){
						document.getElementById("AMShortcodeAssignment_Remove_Down").disabled = true
					}
				}
			}
	    }
	    //need to wait for a short while, then refresh the table
	    setTimeout(updateMessagingOverview,200);
	}
	return buttonObj;
}

/**
 * create a div and set its visibility
 *
 * @param id
 *					id of the div
 * @param isVisible
 *					the div is visible or not
 * @param father
 *					where to add the div
 * 
 * @Visible private
 */
function initDiv(id, isVisible, father) {
	if($(id) != null) return;
	var tempDiv = document.createElement("DIV");
	tempDiv.id = id;
	if (isVisible == true) {
		tempDiv.style.display = "";
	} else {
		tempDiv.style.display = "none";
	}
    father.parentNode.appendChild(tempDiv);
}

/**
 * @Visible private
 */
SortedTable.prototype.createTableHeader = function( data, ctx) {
    var row = this.table.insertRow(0);
    this.sortMethods = new Array();
    for(var i=0; i<data.length; i++) {
    	var th = document.createElement("TH");
    	row.appendChild(th);
    	th.onclick = resortTable;
    	th.className = data[i][1];
    	th.appendChild(document.createTextNode(data[i][0]));
	    th.appendChild(this.createArrow('up', ctx));
    	th.appendChild(this.createArrow('down', ctx));
    	this.sortMethods[i] = data[i][2];
    }
}

/**
 * @Visible private
 */
SortedTable.prototype.createArrow = function(arrow, ctx) {
    var s = document.createElement('SPAN');
    s.className = 'sorted_'+arrow;
    s.style.display = 'none';
    var i = document.createElement('IMG');
    s.appendChild(i);
    i.className='arrow';
    i.src = ctx + '/i/' + arrow + '.gif';
    i.alt = arrow;//display the text when the img is unavailable
    return s;
}

/**
 * @Visible private
 */
SortedTable.prototype.fillTableRow = function(row, data) {
    var ctor = new String(data[0].constructor);
    if (ctor.indexOf('function Array') == 1) {
    	row.id = data[0][0] + "_" + this.id;
    	if (data[0].length > 1) {
        	row.className = data[0][1];
    	}
    } else {
  	    row.id = data[0] + "_" + this.id;
    }
    for (var i=1; i<data.length; i++) {
    	var td = row.insertCell(i-1);/*row.cells.length);*/
	    td.className = data[i][1];
    	data[i][0].format(td, false, row.id);
    }
}

/**
 * When an element is clicked, highlight the row where the element is in.
 *
 * @param el
 *				the element that is clicked
 * @Visible private
 *
 */
SortedTable.prototype.select = function(el) {
   	var instance = getSortedTableInstance(el);
 	if(instance) {
 		var oldRow = document.getElementById(this.selectedRowId);
	   	var newRow = getParent(el, 'TR');
	    if (oldRow && oldRow != newRow) {
	        removeClass(oldRow, 'selectedRow');
	        addClass(newRow, 'selectedRow');
	    } else {
	    	addClass(newRow, 'selectedRow');
	    }
	    this.selectedRowId = newRow.id;
 	}
}
 
/**
 * sort the table
 *
 * @Visible private
 */
SortedTable.prototype.doSort = function(th) {

    if (this.sortAscending) {
    	this.showArrow(th, 'sorted_down');
    } else {
  	    this.showArrow(th, 'sorted_up');
    }
    if (this.table.rows.length <= 1) return;

    var newRows = new Array();
    for (var i=1;i<this.table.rows.length;i++) {
    	newRows[i-1] = this.table.rows[i];
   		newRows[i-1].currentIndex = i;
    }
    //sort the table with this.sort method
    if(this.sortMethods[this.sortByColumn]){
    	newRows.sort(this.sortMethods[this.sortByColumn]);
    } else {
    	newRows.sort(this.sort);
    }
    // Since ie unchecks checkboxes and radios when reappended, 
    // write down the selected checkboxes and radios
    var inputs = this.table.getElementsByTagName('INPUT');
    var cb = new Array();
    for(var i=0; i<inputs.length; i++) {
        if ((inputs[i].type.toLowerCase() == "checkbox" 
        	|| inputs[i].type.toLowerCase() == "radio") && inputs[i].checked) {
            cb[cb.length] = inputs[i];
        }
    }
    // When appending childs rows that already exists in the tbody, it's moved rather appended twice.
    for (var i=0;i<newRows.length;i++) {
        this.table.tBodies[0].appendChild(newRows[i]);
    }
    //When done reselect checkboxes/radios
    for(var i=0; i<cb.length; i++) {
        cb[i].checked = true;
    }
    this.changeColour(th);
}

/**
 * @Visible private
 */
SortedTable.prototype.changeColour = function(th) {
    var index = this.sortByColumn;
	if (this.table.rows.length <= 1) return;
	var newRows = new Array();
	for (var i=1;i<this.table.rows.length;i++) {
    	newRows[i-1] = this.table.rows[i];
    	newRows[i-1].currentIndex = i;
	}
	var j = 0;
	for(i = 0; i < newRows.length; i++){    
  		newRows[0].className = "even"; 
		if(i>0){
  			var newCols =	newRows[i].getElementsByTagName('td');
			var newTemName = newCols[index];
			var oldCols =	newRows[i-1].getElementsByTagName('td');
			var oldTemName = oldCols[index];
			if( newTemName.innerHTML != oldTemName.innerHTML){         	
     			j++;
     		}
		}
    
		//manipulate rows 
		if(j % 2 == 0 ){     
            newRows[i].className = "even"; 
		}else{      
			newRows[i].className = "odd"; 
		}       
   }
   var selectedRow = this.selectedRowId ? document.getElementById(this.selectedRowId) : null;
   if(selectedRow) {
       addClass(selectedRow, 'selectedRow');
   }
}

/**
 * make the arrow visible
 *
 * @param element
 *					element which you want to operate
 * @param className
 *					the name of the style
 * @Visible private
 */
SortedTable.prototype.showArrow = function(element, className) {
    var arrow = this.findElement(element, className);
    if (arrow != null) {
    	arrow.style.display = 'inline';
    }
}

/**
 * Hide all of the arrows in the table.
 *
 * @Visible private
 */
SortedTable.prototype.hideArrows = function(className) {
    var spans = GetElementsByClassName(className, this.table, 'SPAN');
    for(var i=0; i<spans.length; i++) {
    	spans[i].style.display= "none";
    }
}

/**
 * Find the element whose syle is className and the parent is el recursively.
 *
 * @param el
 *				the parent
 * @param className
 *					the name of the style
 * @Visible private
 *
 */
SortedTable.prototype.findElement = function(el, className) {
    if (el.className == className ) {
    	return el;
    }
    for (var i=0;i<el.childNodes.length;i++) {
    	var target = this.findElement(el.childNodes[i], className);
    	if (target != null) {
        	return target;
        }
    }
    return null;
}

/**
 * Define the comparison algorithm.
 *
 * @param a,b
 *				the parameters that want to compare to
 */
SortedTable.prototype.sort = function(a,b) {
    var instance = getSortedTableInstance(a);
    var ca = a.cells[instance.sortByColumn];
    var cb = b.cells[instance.sortByColumn];
    var aa = ca.sortValue ? ca.sortValue(ca) : ca.sortBy;
    var bb = cb.sortValue ? cb.sortValue(cb) : cb.sortBy;
    if (aa==bb) {
      return a.currentIndex - b.currentIndex;
    } else if (aa<bb) {
      return instance.sortAscending ? -1 : 1;
    }
    return instance.sortAscending ? 1 : -1;
}

function sortByShortcode(a,b) {
    var instance = getSortedTableInstance(a);
    var ca = a.cells[instance.sortByColumn];
    var cb = b.cells[instance.sortByColumn];
    var aa = ca.sortValue ? ca.sortValue(ca) : ca.sortBy;
    var bb = cb.sortValue ? cb.sortValue(cb) : cb.sortBy;
    var ia = aa.indexOf("-");
    var ib = bb.indexOf("-");
    var saa = (ia > 0) ? trim(aa.substring(0,ia)) : aa;
    var sbb = (ib > 0) ? trim(bb.substring(0,ib)) : bb;
    if (saa == sbb) {
      return a.currentIndex - b.currentIndex;
    } else if (saa.length == sbb.length) {
    	if(saa < sbb){
      	  return instance.sortAscending ? -1 : 1;
      	}else{
      	   return instance.sortAscending ? 1 : -1;	
      	}
    } else if(saa.length < sbb.length){
    	 return instance.sortAscending ? -1 : 1;
    }
    return instance.sortAscending ? 1 : -1;
}

/* Utility functions */
function lookupFrame(w, target) {
    if (w.frameElement && w.frameElement.name == target) {
  	  return w;
    }
    for(var i=0; i<w.frames.length; i++) {
    	var f = lookupFrame(w.frames[i], target);
    	if (f != null) {
        return f;
        }
    }
    return null;
}
/**
 * get the current table instance
 *
 * @Visible private
 */
function getSortedTableInstance(el) {
    var tab = getParent(el, 'TABLE');
    if (tab) {
    	return top.sortedTables[tab.id];
    }
}

/**
 * resort the table order
 *
 * @Visible private
 */
function resortTable () {
    var instance = getSortedTableInstance(this);
    if (instance.sortByColumn != this.cellIndex) {
    	instance.sortByColumn = this.cellIndex;
    } else {
  		instance.sortAscending = !instance.sortAscending;
    }
    instance.hideArrows('sorted_up');
    instance.hideArrows('sorted_down');
    instance.doSort(this);
    return false;
}

/******************************************************************************
 * The following are the cells definition.                                    *
 ******************************************************************************/
 
function TableImage(src, alt, toolTip, sortBy) {
    this.src = src;
    this.alt = alt;
    this.toolTip = toolTip;
    this.sortBy = sortBy;
}

//parent is td which fills in the element
TableImage.prototype.format = function(parent, unused, rowId) {
    var i = document.createElement('IMG');
    i.className='icon';
    i.src = this.src;
    parent.sortBy = this.sortBy;
    parent.appendChild(i);
   	parent.align='center';
   	if (this.toolTip.length > 0) {
    	var msg = this.toolTip;
    	i.onmouseover = function(){showTooltip(msg);}
	    i.onmouseout = hideTooltip;
    }
}

function TableLink(href, target, text, toolTip, needConfirm, confirmInfo) {
    this.href = href;
    this.target = target;
    this.text = text;
    this.toolTip = toolTip;
    this.confirmInfo = confirmInfo;
    this.needConfirm = needConfirm;
}

TableLink.prototype.format = function(parent, doSelect, rowId) {
    addClass(parent, "link");
    parent.href = this.href;
    parent.target = this.target;
    parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
    parent.confirmInfo = this.confirmInfo;
    parent.needConfirm = this.needConfirm;
    parent.id = makeId(rowId + "_link_" + this.text);
    if (this.toolTip.length > 0) {
    	var msg = this.toolTip;
    	parent.onmouseover = function(){showTooltip(msg);}
    	parent.onmouseout = hideTooltip;
    }
    parent.onclick = function() {
	  	if(parent.needConfirm){
  	  	    var continued = confirm(parent.confirmInfo);
  	  		if(!continued) return;
  	    }
 		if(parent.target) {
 			var w = lookupFrame(window.top, this.target);
		    if (w) {
		    	w.location.href = this.href;
		    }
 		} else {
 			window.location.href = this.href;
 		}
    }
    parent.appendChild(document.createTextNode(this.text));
}

function TableMailTo(address, hintText) {
    this.text = address;
    if (hintText) {
        this.toolTip = hintText + ' ' + address;
    } else {
        this.toolTip = address;
    }
}

TableMailTo.prototype.format = function(parent, unused, rowId) {
    parent.className = 'link';
    parent.address = this.text;
    parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
    if (this.toolTip.length > 0) {
    	var msg = this.toolTip;
    	parent.onmouseover = function(){showTooltip(msg);}
    	parent.onmouseout = hideTooltip;
    }
    parent.onclick = function() {
        window.location = 'mailto:'+this.address;
    }
    parent.appendChild(document.createTextNode(this.text));
}

function TableText(text) {
    this.text = text;
}

TableText.prototype.format = function(parent, unused, rowId) {
    parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
    parent.innerHTML = this.text;
}

function TableDate(text, sortBy) {
    this.text = text;
    this.sortBy = -sortBy;
}

TableDate.prototype.format = function(parent, unused, rowId) {
    parent.appendChild(document.createTextNode(this.text));
    parent.sortBy = this.sortBy;
}

/**
 * It is usually put in the first column of a table.
 * NOTE: "onclickMethod" is the name of the function object. Don't add the "".
 * e.g. when we define a function like function test(){}, then "onclckMethod"
 * is NOT "test" but test.
 */
function TableCheckBox(name, value, onclickMethod, checked) {
    this.name = name;
    this.value = value;
    this.onclickMethod = onclickMethod;
    this.checked = checked;
}

TableCheckBox.prototype.format = function(parent, unused, rowId) {
    var i = document.createElement('INPUT');
    i.type = 'checkbox';
    i.className = 'checkbox';
    i.name = this.name;
    i.value = this.value;
    if (this.checked) {
        i.checked = true;
        i.defaultChecked = true;
    }
    parent.sortBy = 0;
    parent.appendChild(i);
    parent.align = 'center';
    parent.checkBoxObj = this;
    parent.checkBox = i;
    parent.sortValue = function(p) {
        if (p.checkBox.checked) {
            return 1;
        } else {
            return 0;
        }
    }
    if(this.onclickMethod) {
    	parent.onclick = function() {
	        if (this.checkBoxObj.onclickMethod) {
	            this.checkBoxObj.onclickMethod();
	        }
	    }
    }
}

/**
 * It is usually put in the first column of a table.
 * NOTE: "onclickMethod" is the name of the function object. Don't add the "".
 * e.g. when we define a function like function test(){}, then "onclckMethod"
 * is NOT "test" but test.
 */
function TableRadio(name, value, onclickMethod, checked) {
    this.name = name;
    this.value = value;
    this.onclickMethod = onclickMethod;
    this.checked = checked;
}

TableRadio.prototype.format = function(parent, unused, rowId) {
	//Since radio created by createElement('INPUT') cannot be checked under ie,
	//so we have to create it in another way.
	if(document.uniqueID) {//ie
	    var i = document.createElement("<input type='radio' name=" + this.name + " value=" + this.value + " class=radio>");
	} else{//other browsers
	    var i = document.createElement('INPUT');
	    i.type = 'radio';
	    i.className = 'radio';
	    i.name = this.name;
	    i.value = this.value;
	    if (this.checked) {
	        i.checked = true;
	        i.defaultChecked = true;
	    }
	}
    parent.sortBy = 0;
    parent.appendChild(i);
    parent.align='center';
    parent.radioObj = this;
    parent.radio = i;
    parent.sortValue = function(p) {
        if (p.radio.checked) {
            return 1;
        } else {
            return 0;
        }
    }
    if(this.onclickMethod) {
    	parent.onclick = function() {
	        if (this.radioObj.onclickMethod) {
	            this.radioObj.onclickMethod();
	        }
	    }
    }
}
function TableButton(name, value, onclickMethod) {
    this.name = name;
    this.value = value;
    this.onclickMethod = onclickMethod;
}

TableButton.prototype.format = function(parent, unused, rowId) {
    var i = document.createElement('INPUT');
    i.type = 'button';
    i.className = 'button';
    i.name = this.name;
    i.value = this.value;
    i.onclick = this.onclickMethod;
    parent.sortBy = this.value ? this.value.toLowerCase() : this.value;
    parent.appendChild(i);
    parent.align = 'center';
}

function TableJavascriptLink(text, onclickMethod, toolTip, args) {
    this.text = text;
    this.onclickMethod = onclickMethod;
    this.toolTip = toolTip;
    this.args = args;
}

TableJavascriptLink.prototype.format = function(parent, doSelect, rowId) {
    addClass(parent, "link");
    parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
    if (this.toolTip) {
    	var msg = this.toolTip;
        parent.onmouseover = function(){showTooltip(msg);}
    	parent.onmouseout = hideTooltip;
    }
    parent.ll = this;
    parent.id = makeId(rowId + "_link_" + this.text);
    parent.onclick = function() {
        this.ll.onclickMethod(this.ll.args);
    }
    parent.appendChild(document.createTextNode(this.text));
}