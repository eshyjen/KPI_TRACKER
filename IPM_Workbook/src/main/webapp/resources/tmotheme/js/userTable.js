/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/userTable.js $ */
/* $Id: userTable.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* Copyright (c) 2005 Drutt Corporation, all rights reserved. */

/**
 * id             : of table
 * class          : of table
 * tabledata      : [0] = form field ids, [1] = HeaderInfo, [1-n] Rows
 *  HeaderInfo    : Array[names]
 *  Row           : Array[id,((TableImage|TableLink|TableText),*)]
 * url            : Where to go when submitting form
 */

UserTable.prototype.id = null;
UserTable.prototype.className = null;
UserTable.prototype.tabledata = null;
UserTable.prototype.url = null;
UserTable.prototype.selectedRow = null;
UserTable.prototype.yesText = null;
UserTable.prototype.isUpdateUserId = false;
UserTable.prototype.newSelectedRowId = null;

function UserTable(prefix, className, url, header, edit, create, del, saveOne, userIdFiledId, amEditSr, currentUserId) {
  this.prefix = prefix;
  this.className = className;
  this.url = url;
  //the id of the user id filed
  this.userIdFiledId = userIdFiledId;
  // true don't delete all in table
  this.saveOne = saveOne;
  var userTable = this;
  //Create the span that holds the user table and the create/edit/delete buttons
  this.stab = document.createElement('span');
  this.stab.id = prefix+'-stab';
  //Create the header that holds the title
  if (header != null) {
    var h2 = document.createElement('h2');
    this.stab.appendChild(h2);
    h2.innerHTML = header;
  }
  //Create the div that holds the user table
  var div = document.createElement('div');
  div.className = 'contentBox white';
  this.stab.appendChild(div);
  this.table = document.createElement('table');
  div.appendChild(this.table);
  //Create the div that holds the create/edit/delete buttons
  div = document.createElement('div');
  div.className = 'contentBox white';
  this.stab.appendChild(div);
  

  //Create the edit button
  var b = this.createButton(edit);
  div.appendChild(b);
  b.onclick = function() {
      userTable.showEditForm();
  }
  b.id = prefix+'-list-edit-button';
  //Default disabled
  b.disabled = true;

  //Create the create button
  b = this.createButton(create);
  div.appendChild(b);
  b.onclick = function() {
      userTable.showCreateForm();
  }
  b.id = prefix+'-list-create-button';

  //Create the delete button
  b = this.createButton(del);
  div.appendChild(b);
  b.onclick = function() {
      userTable.deleteSelected();
  }
  b.id = prefix+'-list-del-button';
  //Default disabled
  b.disabled = true;

  //Attach this object to the table
  this.table.users = this;
  this.table.id = prefix+'tab';
  this.table.className = className;
  // Get the form
  this.form = document.getElementById(prefix+'-form');
  if (!this.form || this.form == null) {
      alert('No '+prefix+'-form found');
  }
  this.form.parentNode.appendChild(this.stab);
  this.amEditSr = amEditSr;
  this.currentUserId = currentUserId;
}
UserTable.prototype.createButton = function(val) {
    var b = document.createElement('input');
    b.type = 'button';
    b.className = 'button';
    b.value = val;
    b.alt = val;
    return b;
}
UserTable.prototype.showEditForm = function() {
    this.populate(this.selected);
    registerForm(this.form);
    this.show($(this.prefix+'-form-save'));
    this.show($(this.prefix+'-header-edit-user'));
    this.hide($(this.prefix+'-header-create-user'));
    if ($(this.prefix+'-header-users') != null) {
    	this.hide($(this.prefix+'-header-users'));
    }
    this.hide($(this.prefix+'-form-create'));
    this.hide($(this.prefix+'-error'));
    this.show(this.form);   
	if ($(this.prefix + '-userid-div') != null) {
		if(this.prefix=="AM"){
			$(this.prefix + '-account-id-input-show').innerHTML=$(this.prefix + '-account-id-input').value;
		}else{
			//this.show($(this.prefix + '-userid-div'));
		}	
	}	
	this.show($(this.prefix + '-userStatus-div'));
	if (this.amEditSr) {
		this.hide($(this.prefix + '-account-div-oldpassword'));
		this.show($(this.prefix + '-account-div-password'));
		this.show($(this.prefix + '-account-div-validatePassword'));
		this.show($(this.prefix + '-account-div-passwordRules'));
		$(this.prefix + '-account-phone-input').style.cssText="";
		$(this.prefix + '-account-phone-input').readOnly = false;
		$(this.prefix + '-account-email-input').style.cssText="";
		$(this.prefix + '-account-email-input').readOnly = false;
	} else if (this.getSelectedObjectId() == this.currentUserId) {
		this.show($(this.prefix + '-account-div-oldpassword'));
		this.show($(this.prefix + '-account-div-password'));
		this.show($(this.prefix + '-account-div-validatePassword'));
		this.show($(this.prefix + '-account-div-passwordRules'));
		$(this.prefix + '-account-phone-input').style.cssText="";
		$(this.prefix + '-account-phone-input').readOnly = false;
		$(this.prefix + '-account-email-input').style.cssText="";
		$(this.prefix + '-account-email-input').readOnly = false;
	} else {
		this.hide($(this.prefix + '-account-div-oldpassword'));
		this.hide($(this.prefix + '-account-div-password'));
		this.hide($(this.prefix + '-account-div-validatePassword'));
		this.hide($(this.prefix + '-account-div-passwordRules'));
		$(this.prefix + '-account-phone-input').style.cssText="background-color:transparent;border-color:transparent;";
		$(this.prefix + '-account-phone-input').readOnly = true;
		$(this.prefix + '-account-email-input').style.cssText="background-color:transparent;border-color:transparent;";
		$(this.prefix + '-account-email-input').readOnly = true;
//		$(this.prefix + '-account-contactPerson-input').disabled = true;
//		this.hide($(this.prefix+'-form-save'));
	}
	$(this.prefix + '-account-loginName-input').style.cssText="background-color:transparent;border-color:transparent;";
	$(this.prefix + '-account-loginName-input').readOnly = true;
	
    this.hide(this.stab);      
    resizeFrame();
}
UserTable.prototype.showCreateForm = function() {
    this.populate(null);
    registerForm(this.form);
    this.hide($(this.prefix+'-form-save'));
    this.hide($(this.prefix+'-form-unlock'));
    this.hide($(this.prefix+'-header-edit-user'));
    this.show($(this.prefix+'-header-create-user'));
    if ($(this.prefix+'-header-users') != null) {
    	this.hide($(this.prefix+'-header-users'));
    }
    this.show($(this.prefix+'-form-create'));
    this.hide($(this.prefix+'-error'));
    this.show(this.form);    
	if ($(this.prefix + '-userid-div') != null) {
		this.hide($(this.prefix + '-userid-div'));
	}
	this.hide($(this.prefix + '-userStatus-div'));
	this.show($(this.prefix + '-account-div-password'));
	this.show($(this.prefix + '-account-div-validatePassword'));
	this.hide($(this.prefix + '-account-div-oldpassword'))
	this.show($(this.prefix + '-account-div-passwordRules'));
	$(this.prefix + '-account-loginName-input').style.cssText="";
	$(this.prefix + '-account-loginName-input').readOnly = false;
	$(this.prefix + '-account-phone-input').style.cssText="";
	$(this.prefix + '-account-phone-input').readOnly = false;
	$(this.prefix + '-account-email-input').style.cssText="";
	$(this.prefix + '-account-email-input').readOnly = false;
    this.hide(this.stab);  
    resizeFrame();
}
UserTable.prototype.deleteSelected = function() {
    if (confirm("Do you really want to delete this user?")) {
        var oid = this.getSelectedObjectId();
        var formData = 'cmd=delete&oid='+oid;
        this.req.open("POST", this.url, true);
        this.req.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=utf-8');
        var listener = this;
        this.req.onreadystatechange = function() {
            listener.doSelect(null);
            listener.process();
        };
        this.req.send(formData);
    }
}
UserTable.prototype.submit = function(callback) {
    var oid = this.getSelectedObjectId();
    this.checkRowIdChanged();
    var formData = this.buildFormData('cmd=edit&oid='+oid);
    this.req.open("POST", this.url, true);
    this.req.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=utf-8');
    var listener = this;
    this.req.onreadystatechange = function() {
      listener.process();
      if(callback!=null && typeof callback =='function'){
        callback();
      }
    };
    this.req.send(encodeURI(formData));
}
UserTable.prototype.create = function(callback) {
    var formData = this.buildFormData('cmd=create');
    this.req.open("POST", this.url, true);
    this.req.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=utf-8');
    var listener = this;
    this.req.onreadystatechange = function() {
        listener.doSelect(null);
        listener.process();
        if(callback!=null && typeof callback =='function'){
          callback();
        }

    };
    this.req.send(encodeURI(formData));
}

UserTable.prototype.unlock = function(callback) {
	var oid = this.getSelectedObjectId();
	this.checkRowIdChanged();
	var formData = this.buildFormData('cmd=unlock&oid='+oid);
	this.req.open("POST", this.url, true);
    this.req.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=utf-8');
    var listener = this;
    this.req.onreadystatechange = function() {
        listener.doSelect(null);
        listener.process();
        if(callback!=null && typeof callback =='function'){
          callback();
        }

    };
    this.req.send(encodeURI(formData));
}

UserTable.prototype.buildFormData = function(data) {
    for(var i=0; i<this.fields.length; i++) {
        var el = this.form[this.fields[i]];
        // when roles are not used el is undefined
        if (el == undefined) continue;
        if (this.fields[i] == 'phone') {
			data += '&' + this.fields[i] + '=' + encodeURIComponent(el.value);
  	        continue;
	  	}
        if (el.length) {
            for(var ci=0; ci<el.length; ci++) {
                if (el[ci].checked) {
                    data += '&' + this.fields[i] + '=' + el[ci].value;
                }
            }
        } else if (el.type && el.type == 'checkbox') {
            if (el.checked) {
                data += '&' + this.fields[i] + '=' + el.value;
            }
        } else {
            data += '&' + this.fields[i] + '=' + el.value;
        }
    }
    return data;
}

UserTable.prototype.cancel = function() {
    clearModified();
    this.showTab();   
}
UserTable.prototype.showTab = function() {
    this.hide($(this.prefix+'-error'));
    this.hide(this.form);
    this.show(this.stab);
    this.show($(this.prefix+'-header-users'));
	this.hide($(this.prefix+'-header-create-user'));
	this.hide($(this.prefix+'-header-edit-user'));
    resizeFrame();
}
UserTable.prototype.show = function(el) {
    if (el != null) {
      el.style.display='';
    }
}
UserTable.prototype.hide = function(el) {
    el.style.display='none';
}

UserTable.prototype.setLoadStatus = function (status) {
  var loadStatus = document.getElementById("userTableLoadStatus");
  if (loadStatus != null) {
    loadStatus.value = status;
  }
}

UserTable.prototype.load = function() {
  if (this.url) {
  if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    this.req.open("GET", this.url, true);
    var listener = this;
    this.req.onreadystatechange = function() {
      listener.process();
    };
    this.setLoadStatus("processingBeforeLoad");
    this.req.send(null);
  }
}



UserTable.prototype.process = function() {
  this.setLoadStatus("processing");
    if (this.req.readyState == 4) {
        if (this.req.status == 200) {
            // Edit ok
            var res;
            eval('res='+this.req.responseText);
            //only update the selected row id when the user id is modified and commit successfully
            this.updateSelectedRowId();
            this.parse(res);
            this.setLoadStatus("loaded");
            clearModified();
        } else if (this.req.status == 201) {
            // Create ok
            var res;
            eval('res='+this.req.responseText);
            this.parse(res);
            this.setLoadStatus("loaded");
            clearModified();
        } else if (this.req.status == 409) {
            // Conflict, e.g. not unique user id
            this.handleError(eval(this.req.responseText));
            //clear the old data of last operation
            this.isUpdateUserId = false;
        this.newSelectedRowId = null;
        this.setLoadStatus("loaded");
        } else {
            alert("UserTable failed: " + this.req.statusText);
        }
    }
}

UserTable.prototype.parse = function(tabledata) {
    this.fields = tabledata.fields;
    this.headers = tabledata.headers;
    this.isrm = tabledata.isrm;
    this.nrOfRoleManagers = tabledata.nrOfRoleManagers;
    this.poid = tabledata.poid;
    this.defaultRole = tabledata.defaultRole;
    this.users = tabledata.users;
    if (this.users.length < 2 && this.saveOne) {
      $(this.prefix + '-list-del-button').style.display = 'none';
    } else {
      $(this.prefix + '-list-del-button').style.display = '';
    }

    this.removeChilds(this.table);
      this.createTableHeader();

    this.setData();
    markHover(this.table);
    if (this.selected) {
        addClass($(this.selected), 'selectedRow');
    }
    this.showTab();
}

UserTable.prototype.handleError = function(data) {
    if (data && data.length > 0) {
        var el = $(this.prefix+'-error');
        this.removeChilds(el);
        for(var i=0; i<data.length; i++) {
            var d = el.ownerDocument.createElement('div');
            el.appendChild(d);
            d.appendChild(el.ownerDocument.createTextNode(data[i]));
        }
        this.show(el);
    }
}

UserTable.prototype.removeChilds = function(el) {
    if (el && el != null) {
        while (el.childNodes && el.childNodes.length > 0) {
            el.removeChild(el.lastChild);
        }
    }
}
UserTable.prototype.createTableHeader = function() {
    var doc = this.table.ownerDocument;
    var thead = doc.createElement('thead');
    this.table.appendChild(thead);
    var row = doc.createElement('tr');
    thead.appendChild(row);
    for(var i=0; i<this.headers.length; i++) {
      var th = doc.createElement('th');
      row.appendChild(th);
      th.className=this.fields[i];
      th.appendChild(document.createTextNode(this.headers[i]));
    }
}
/* if id is null => set default values */
UserTable.prototype.populate = function(id) {
	this.hide($(this.prefix+'-form-unlock'));
    var doc = this.table.ownerDocument;
    var row=null;
    if (id) {
        row = $(id);
    }
    for(var i=0; i<this.fields.length; i++) {
        var name = this.fields[i];
        var el = this.form[name];
        // when roles are not used el is undefined
        if (el == undefined) continue;
        var val = '';
        if (row != null) {
            if (row.user[name] instanceof TableMailTo) {
                val=row.user[name].text;
            } else if (row.user[name] instanceof TableRadio) {
                val=row.user[name].checked;
            } else if ('role' == name) {
                // row.user[name] is an Array
                // el is one checkbox or a collection of checkboxes
                if (el.type && el.type == 'checkbox') {
                    for(var ui=0; ui < row.user[name].length; ui++) {
                        if (el.value == row.user[name][ui]) {
                            el.checked = true;
                            el.defaultChecked = true;
                            break;
                        }
                    }
                } else {
                    for(var ei=0; ei<el.length; ei++) {
                        el[ei].checked = false;
                        el[ei].defaultChecked = false;
                        el[ei].disabled = !this.isrm;
                        for(var ui=0; ui < row.user[name].length; ui++) {
                            if (el[ei].value == row.user[name][ui]) {
                                el[ei].checked = true;
                                el[ei].defaultChecked = true;
                                break;
                            }
                        }
                        if (this.isrm && el[ei].value == 'tpim.rm' && this.nrOfRoleManagers == 1 && el[ei].checked) {
                            el[ei].disabled = true;
                        } else if (el[ei].value == 'tpim.sr') {
                            el[ei].disabled = true;
                        }
                        
                    }
                }
                continue;
            } else {
                val=row.user[name];
            }
        } else if ('role' == name) {
            if (el.type && el.type == 'checkbox') {
                el.checked = (el.value == this.defaultRole);
                el.defaultChecked = el.checked;
            } else {
                for(var ei=0; ei<el.length; ei++) {
                    el[ei].checked = false;
                    el[ei].defaultChecked = false;
                    el[ei].disabled = !this.isrm;
                    if (el[ei].value == this.defaultRole) {
                        el[ei].checked = true;
                        el[ei].defaultChecked = true;
                    }
                    if (this.isrm && el[ei].value == 'tpim.rm' && this.nrOfRoleManagers == 1 && el[ei].checked) {
                        el[ei].disabled = true;
                    } else if (el[ei].value == 'tpim.sr') {
                        el[ei].disabled = true;
                    }
                }
            }
            continue;
        }
        if (el.type == 'radio' || el.type == 'checkbox') {
            if (typeof val == 'string') {
                el.checked = (val == this.yesText);
                var tooltip = document.getElementById('contactPersonTooltip');
                if (tooltip != null) {
                    el.disabled = el.checked;
                    tooltip.style.visibility = el.checked ? 'visible' : 'hidden';
                }
            } else {
                el.checked = val;
            }
            el.defaultChecked = el.checked;
        } else {
            if (this[name]) {
                el.value = this[name];
            } else {
                el.value = val;
            }
            el.defaultValue = el.value;
        }
        if('status' == name && 'Locked' == val && this.amEditSr) {
        	this.show($(this.prefix+'-form-unlock'));
        	$(this.prefix+'-form-unlock').disabled = false;
        } 
        if ('status' == name) {
        	if ('Locked' == val) {
        		$(this.prefix+'-account-status-img').src='/tpim/i/locked.gif';
        	}else{
        		$(this.prefix+'-account-status-img').src='/tpim/i/unlocked.png';
        	}
        }
    }

}

UserTable.prototype.setData = function() {
    var doc = this.table.ownerDocument;
    var tbody = this.table.tBodies[0];
    if (tbody != null) {
        while(tbody.rows.length > 1) {
            this.table.deleteRow(tbody.rows.length-1);
        }
    } else {
        tbody = doc.createElement('tbody');
        this.table.appendChild(tbody);
    }
    for(var i=0; i<this.users.length; i++) {
        var row = doc.createElement('tr');
        tbody.appendChild(row);
        this.fillTableRow(row, this.users[i]);
    }
}

UserTable.prototype.getSelectedObjectId = function() {
    var row = $(this.selected);
    return row.user.oid;
}
UserTable.prototype.fillTableRow = function(row, user) {
    row.user = user;
    row.id = this.prefix+'-r-'+user.oid;
    row.onclick = function() {
        var tab = getParent(this, 'TABLE');
        tab.users.doSelect(this)
    }
    for (var i=0; i<this.headers.length; i++) {
        var name = this.fields[i];
        var td = row.insertCell(i);

        var attr = row.user[name];
        if (attr instanceof TableMailTo) {
            td.id = this.prefix+'-td-' + user.oid + '-' + name;
            attr.format(td);
        } else if (attr instanceof TableRadio) {
            attr.format(td);
        } else if (name == 'status'){
        	var img = '/tpim/i/unlocked.png';
        	if (attr == 'Locked') {
        		img = '/tpim/i/locked.gif';
        	}
        	var tooltip = "Active";
        	if (attr == 'Locked') {
        		tooltip = "Locked";
        	}
        	var t = new TableImage(img, tooltip, '', attr);
        	t.format(td);
            td.id = this.prefix + '-td-'+user.oid + '-' + name;
        } else {
            try {
	            var t = new TableText(attr);
	            t.format(td);
	            td.id = this.prefix + '-td-'+user.oid + '-' + name;
	            td.style.cursor = "pointer";
            } catch (e) {
                console.info(name + ' ' + attr + ' -> ' + e);
            }
        }
    }
}

UserTable.prototype.doSelect = function(tr) {
    if (this.selected != null) {
        removeClass($(this.selected), 'selectedRow');
    }
    if (tr && tr.id != this.selected) {
        addClass(tr, 'selectedRow');
        this.selected = tr.id;
        $(this.prefix+'-list-edit-button').disabled = false;
        $(this.prefix+'-list-del-button').disabled = !tr.user.write;
    } else {
        this.selected = null;
        $(this.prefix+'-list-edit-button').disabled = true;
        $(this.prefix+'-list-del-button').disabled = true;
    }
}

UserTable.prototype.updateSelectedRowId = function() {
  if (this.isUpdateUserId) {
    this.selected = this.newSelectedRowId;
    this.isUpdateUserId = false;
    this.newSelectedRowId = null;
  }
}

UserTable.prototype.checkRowIdChanged = function() {
  var oldUserId = this.getSelectedObjectId();
  var newUserId = trim($(this.userIdFiledId).value);
  //if the user id is modified, write down the changes
  if (oldUserId != newUserId) {
    this.isUpdateUserId = true;
    this.newSelectedRowId = this.prefix + '-r-' + newUserId;
  }
}
