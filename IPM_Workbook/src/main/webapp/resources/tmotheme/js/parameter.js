/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/parameter.js $ */
/* $Id: parameter.js 330795 2013-04-02 11:37:24Z emiazhu $ */

//Register "setup code"
document.onloads[document.onloads.length] = initParameter;
/*

'form.id'_param_attrib            : Array of attribute names, also used as table header and form control names.
'form.id'_param_tooltip           : Array of booleans where true indicates that this column use attribute value as tooltip
'form.id'Parameters               : 2 dimensional Array of object info
             one object           : Array[0]-['attribute names'.length-1] Attribute values. IN SAME ORDER AS 'attribute names'
                                    Array['attribute names'.length] Object id
                                    Optionals
                                    Array['attribute names'.length+1] boolean, false or absent - the object is not deletable
                                    Read/write flags absent => readonly
                                    Array['attribute names'.length+2] boolean attribute[0] is editable
                                    Array['attribute names'.length+3] boolean attribute[1] is editable
                                    ...
'form.id'_validate(form[, array]) : Validate the form's current data.
                                    param : form to validate
                                    param : array(otional), current value of one row, passed on edit
Optional
form.parameter_editable           : Absent or False - List is read only
                                    True - Possible to edit parameters according to each parameters attributes read/write flag
Optional utility methods
form.parameter_reset()            : if exist, called on form reset.
form.parameter_format(attr, value): if exist, called when to display an attribute value.
                                    param : attr name of attribute to format
                                    param : value to format
                                    return: formated value.

Hidden parameters added on form submit
deleted = object id - One/deleted parameter

added[x].attribute_name[0] = value
added[x].attribute_name[n] = value

modified[x].objectId = object id
modified[x].attribute_name[0] = value
modified[x].attribute_name[n] = value
*/

var deleted;
var modified;
var added;
var START_ROW = 2;

//Mark form as not modified
top.modified = null;

function doReset() {
  if (top.modified && top.modified.form) {
    var tables = GetElementsByClassName("parameterTable", top.modified.form, "table");
    for(var i=0; i<tables.length; i++) {
      var table = tables[i];
      var form = top.modified.form;
      form.reset();
      if (form.parameter_reset) {
         form.parameter_reset();
      }
      clearModified();
      deleted[form.id] = new Array();
      added[form.id] = new Array();
      modified[form.id] = new Array();
      var inParams = eval(form.id + "Parameters");
      while(table.rows.length > START_ROW) {
        table.deleteRow(table.rows.length-1);
      }
      if (inParams.length > 0) {
        for (var j = 0; j < inParams.length && inParams[j]!=null; j++) {
          addRow(inParams[j], table);
        }
        resizeFrame();
      } else {
        colorTableLines(table);
      }
    }
  } else if (top.modified) {
      top.modified.reset();
  }
}

function registerParameterTable(table) {
    var form = getParent(table, "FORM");
    var elist = form.elements;
    var add = GetElementsByClassName("createParameter", form, "input");
    if (add.length > 0) {
        add[0].onclick = function() {
            if (createParameter(this)) {
              checkParameterFormElement(table);
            }
        }
    }

  for (var eix=0; eix<elist.length; eix++) {
      if (isSubmitElement(elist[eix])) {
          disableElement(elist[eix]);
          elist[eix].onclick = populateForm;
      } else if (isResetElement(elist[eix])) {
          disableElement(elist[eix]);
      } else if (elist[eix].type &&
                 ((elist[eix].type == "radio") || (elist[eix].type == "checkbox"))) {
          if (!elist[eix].onclick) {
              elist[eix].onclick = updatedModifiedParams;
          }
      } else {
          if (!elist[eix].onkeyup) {
              elist[eix].onkeyup = updatedModifiedParams;
          }
          if (!elist[eix].onchange) {
              elist[eix].onchange = updatedModifiedParams;
          }
      }
  }
  form.onsubmit = function () { top.modified = null; return true; };
}

function updatedModifiedParams() {
  if (this.type) {
      if (isFormModified(this)) {
          var list = this.form.elements;
          for (var i=0; i < list.length; i++) {
              var element = list[i];
              if (isResetElement(element)) {
                  enableElement(element);
              }
          }
          top.modified = new ParameterForm(this.form);
      } else {
          checkParameterFormElement(getParent(this, "TABLE"));
      }
  }
  return true;
}

function checkParameterFormElement(parameterTable) {
  var ismodified = false;
  var form = getParent(parameterTable, "FORM");
  var list = form.elements;
  var inParams = eval(form.id + "Parameters");
  var inSize= inParams.length == 0 || inParams[inParams.length-1] != null ? inParams.length : inParams.length-1;
  if (parameterTable.rows.length - START_ROW != inSize) {
      ismodified = true;
  } else {
      var param_attrib = eval(form.id + "_param_attrib");
      for (var inp = 0; inp < inParams.length && inParams[inp]!=null; inp++) {
          var foundData = false;
          for (var ptr = START_ROW; ptr < parameterTable.rows.length; ptr++) {
              for (var ptd = 0; ptd < param_attrib.length; ptd++) {
                  var inData = inParams[inp][ptd];
                  var cellData = parameterTable.rows[ptr].paramData[ptd];
                  if (inData != cellData) {
                      //Data not in current row
                      break;
                  }
                  if (ptd == param_attrib.length-1) {//last cell
                      foundData = true;
                  }
              }
              if (foundData) {
                  //Parameter found, look for next
                  break;
              }
          }
          if(!foundData) {
              //Looked in all rows, but parameter not found
              ismodified = true;
              break;
          }
      }
  }
  if (ismodified) {
    setModified(form);
    return true;
  } else {
    top.modified = new ParameterForm(form);
    clearModified();
    return false;
  }
}
/* same signature as in form.js */
function clearModified() {
  if (top.modified) {
    var elist = top.modified.form ? top.modified.form.elements : top.modified.elements;
    top.modified = null;
    if (elist) {
      for (var eix=0; eix<elist.length; eix++) {
          if ((isSubmitElement(elist[eix]) || isResetElement(elist[eix])) && elist[eix].className.match("alwaysEnabled") == null) {
              disableElement(elist[eix]);
          }
      }
    }
  }
  return true;
}

function setModified(form) {
  top.modified = new ParameterForm(form);
  var list = form.elements;
  if (list) {
    for (var i=0; i < list.length; i++) {
        var element = list[i];
        if (isSubmitElement(element) || isResetElement(element)) {
            enableElement(element);
        }
    }
  }
}

function initParameter() {
  deleted = new Array();
  added = new Array();
  modified = new Array();
  var tables = GetElementsByClassName("parameterTable", document, "table");
  for(var i=0; i<tables.length; i++) {
    var table = tables[i];
    var form = getParent(table, "FORM");
    deleted[form.id] = new Array();
    added[form.id] = new Array();
    modified[form.id] = new Array();
    var inParams = eval(form.id + "Parameters");
    registerParameterTable(table);
    if (inParams.length > 0) {
      for (var j = 0; j < inParams.length && inParams[j]!=null; j++) {
        addRow(inParams[j], table);
      }
      resizeFrame();
    } else {
      colorTableLines(table);
    }
  }
}
function addRow(data, table) {
    var i;
    for (i = START_ROW; i < table.rows.length; i++) {
      if (data[0] < table.rows[i].cells[0].firstChild.data) {
        break;
      }
    }
    var row = table.insertRow(i);
    row.id = nextId();

    var form = getParent(table, "FORM");
    // Create a copy of in data
    row.paramData = new Array();
    if (data && data.length > 0) {
        for(var i=0; i<data.length; i++) {
            row.paramData[i] = data[i];
        }
    }
    var param_attrib = eval(form.id + "_param_attrib");
    var param_tooltip = eval(form.id + "_param_tooltip");
    for (var j = 0; j < param_attrib.length; j++) {
      var td = row.insertCell(row.cells.length);
      var val = data[j];
      if (form.parameter_format) {
        val = form.parameter_format(param_attrib[j], val);
      }
      td.appendChild(document.createTextNode(val));
      if (param_tooltip[j]) {
        td.onmouseover = setTooltip;
        td.onmouseout = hideTooltip;
        td.toolTip = formatTooltip(data[j]);
      }
    }
    var td = row.insertCell(row.cells.length);
    td.className = "input";
    if (!form.parameter_editable) {
        td.colSpan=2;
    }
    var deleter = document.createElement("input");
    deleter.type = "button";
    deleter.value = "Delete";
    deleter.className = "button";
    td.appendChild(deleter);
    //parameter just created if data.length and param_attrib.length are equal
    if (data.length == param_attrib.length || eval(data[param_attrib.length + 1])) {
        td.onclick=function() {
            deleteRow(this);
            checkParameterFormElement(table);
            return false;
        }
    } else {
        deleter.disabled = "true";
        addClass(deleter, "disabled");
    }
    if (form.parameter_editable) {
      td = row.insertCell(row.cells.length);
      td.className = "input";
      var edit = document.createElement("input");
      edit.type = "button";
      edit.value = "Edit";
      edit.className = "button";
      td.appendChild(edit);
      td.onclick=function() {
        new ParameterEditor(this);
      }
    }
    row.objId = data[param_attrib.length];
    colorTableLines(table);
    return row.paramData;
}

function createParameter(element) {
  var table = getParent(element, "TABLE");
  var form = getParent(element, "FORM");
  if (eval(form.id + "_validate(form)")) {
    var arr = new Array();
    var inputs = new Array();
    var param_attrib = eval(form.id + "_param_attrib");
    for (var j=0; j<param_attrib.length; j++) {
      inputs[j] = form.elements[param_attrib[j]];
      arr[j] = trim(inputs[j].value);
      if (inputs[j].type == "checkbox") {
        arr[j] = inputs[j].checked;
      }
    }
    var theAdded = added[form.id];
    added[form.id][theAdded.length] = addRow(arr, table);
    resizeFrame();
    // Clear parameter inputs.
    for (var alli=0; alli<inputs.length; alli++) {
      var current = inputs[alli];
      if (current.type.toLowerCase() == "radio" || current.type.toLowerCase() == "checkbox") {
        current.checked = current.defaultChecked;
      } else if (current.options) {
        current.options[0].selected = true;
        for (var oix = 0; oix < current.options.length; oix++) {
          if (current.options[oix].defaultSelected) {
            current.options[oix].selected = true;
          }
        }
      } else {
        //text, textarea and password
        current.value = current.defaultValue;
      }
    }
    return true;
  }
  return false;
}

function deleteRow(element) {
  var table = getParent(element, "TABLE");
  var form = getParent(element, "FORM");
  var tr = getParent(element, "TR");
  if (tr.objId) {
    deleted[form.id][tr.objId] = true;
    modified[form.id][tr.objId] = null;
  } else {
    deleteAdded(tr.paramData, form);
  }
  tr.paramData = null;
  var index = tr.rowIndex;
  table.deleteRow(index);
  colorTableLines(table);
  resizeFrame();
}

function deleteAdded(data, form) {
  var i;
  for(i=0; i < added[form.id].length; i++) {
    if (data == added[form.id][i]) break;
  }
  if (i < added[form.id].length) {
    added[form.id].splice(i,1);
  }
}

function populateForm() {
  var form = getParent(this, "FORM");
  var param_attrib = eval(form.id + "_param_attrib");
  for (var i in deleted[form.id]) {
    if (deleted[form.id][i]) {
      var element = document.createElement("input");
      element.type="hidden";
      element.name="deleted";
      element.value = i;
      form.appendChild(element);
    }
  }
  var ix;
  for (ix=0; ix<added[form.id].length; ix++) {
    for (var j=0; j<param_attrib.length; j++) {
      addHidden(form, "added", ix, param_attrib[j], added[form.id][ix][j]);
    }
  }
  ix = 0;
  for (var objId in modified[form.id]) {
    var values = modified[form.id][objId];
    if (values != null) {
      addHidden(form, "modified", ix, "objectId", objId);
      for (var j=0; j<param_attrib.length; j++) {
        addHidden(form, "modified", ix, param_attrib[j], values[j]);
      }
      ix++;
    }
  }
  var element = document.createElement("input");
  element.type="hidden";
  element.name="save";
  element.value = "true";
  form.appendChild(element);
}

function addHidden(form, list, ix, param, value) {
    var element = document.createElement("input");
    element.type="hidden";
    element.name=list + "[" + ix + "]." + param;
    element.value = value;
    form.appendChild(element);
}

function getParent(el, pTagName) {
  if (el == null) {
    return null;
  } else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase()) {
    return el;
  } else {
    return getParent(el.parentNode, pTagName);
  }
}

function ParameterForm(form) {
  this.form = form;
}
ParameterForm.prototype.reset = function() {
 doReset();
}
/* Parameter editor */
function ParameterEditor(editButton) {
  var tr = getParent(editButton, "TR");
  this.initEditor();
  this.oldModified = window.top.modified;

  this.win.popFormDiv.innerHTML = "";
  var form = getParent(tr, "FORM");
  var param_attrib = eval(form.id + "_param_attrib");
  this.win.popFormDiv.trId = tr.id;
  this.win.popForm = this.win.document.createElement("form");
  this.win.popForm.id = "popForm";
  this.win.popFormDiv.appendChild(this.win.popForm);
  var pTab = this.win.document.createElement("table");
  pTab.style.width = '0px';
  var tab = getParent(tr, "TABLE");
  if (tab.className) {
    pTab.className = tab.className;
  }
  this.win.popForm.appendChild(pTab);
  var pTr = pTab.insertRow(-1);
  for (var j=0; j<param_attrib.length; j++) {
    var inp = eval("form." + param_attrib[j]);
    var inpTd = getParent(inp, "TD");
    var current = tr.paramData[j];
    var pTd = pTr.insertCell(-1);
    pTd.style.width = inpTd.clientWidth + 'px';
    if (inpTd.className) {
      pTd.className = inpTd.className;
    }
    var pInp;
    if (document.defaultView)/*firefox*/ {
      pInp = inp.cloneNode(inp.options?true:false);
    } else {
      pInp = this.win.document.createElement(inp.outerHTML);
    }
    pInp.style.width = inp.clientWidth + 'px';
    pTd.appendChild(pInp);
    if (inp.type.toLowerCase() == "checkbox") {
      if (current) {
        pInp.checked = true;
      }
    } else if (inp.options) {
      for (var oix = 0; oix < inp.options.length; oix++) {
        if (!document.defaultView)/*ie*/ {
          var opt = this.win.document.createElement(inp.options[oix].outerHTML);
          pInp.appendChild(opt);
          opt.appendChild(this.win.document.createTextNode(inp.options[oix].innerHTML));
        }
        if (current == inp.options[oix].value) {
           pInp.options[oix].selected = true;
        }
      }
    } else {
      pInp.value = current;
    }
    //   isOriginal(have objectid)        && !editable
    if (tr.paramData[param_attrib.length] && !tr.paramData[param_attrib.length+2+j]) {
      pInp.disabled = true;
    }
  }
  pTr = pTab.insertRow(-1);
  var pTd = pTr.insertCell(-1);
  pTd.colSpan= param_attrib.length;
  var pInp = this.win.document.createElement("input");
  pInp.type = "button";
  pInp.value="Change";
  pInp.className="button edit";
  pInp.onclick= function() {
    this.editor.save();
  }
  pInp.editor = this;
  pTd.appendChild(pInp);
  pInp = this.win.document.createElement("input");
  pInp.type = "button";
  pInp.value="Cancel";
  pInp.className="button edit";
  pInp.onclick= function() {
    this.editor.cancel();
  }
  pInp.editor = this;
  pTd.appendChild(pInp);
  this.win.popDiv.style.width = this.win.document.body.clientWidth  + 'px';
  this.win.popDiv.style.height = this.win.document.body.clientHeight  + 'px';
  var left = tr.offsetLeft;
  var top  = tr.offsetTop;
  var off = tr.offsetParent;
  do {
    left += off.offsetLeft;
    top  += off.offsetTop;
    off = off.offsetParent;
  } while(off.offsetParent);
  left += calcOffsetX(window);
  top += calcOffsetY(window);
  this.win.popFormDiv.style.left = left + 'px';
  this.win.popFormDiv.style.top = top + 'px';
  this.showEditor(true);
  registerForm(this.win.popForm);
  disableTooltip();
}
ParameterEditor.prototype.initEditor = function() {
  this.win = this.getTopWin();
  if (!this.win.popFormDiv) {
    var popDiv = this.win.document.createElement('DIV');
    popDiv.id = 'popDiv';
    popDiv.className = 'PopDiv';
    popDiv.pixel = this.win.document.createElement('IMG');
    popDiv.pixel.src = "/tpim/i/pixel.gif";
    popDiv.appendChild(popDiv.pixel);
    var popFormDiv = this.win.document.createElement('DIV');
    popFormDiv.id = 'popFormDiv';
    popFormDiv.className = 'PopFormDiv';
    popDiv.appendChild(popFormDiv);
    this.win.document.body.appendChild(popDiv);
    this.win.popFormDiv = popFormDiv;
    this.win.popDiv = popDiv;
  }
}

ParameterEditor.prototype.showEditor = function(enabled) {
  if (enabled) {
    this.win.popDiv.style.zIndex = 199;
    this.win.popDiv.pixel.style.width = this.win.popDiv.clientWidth + 'px';
    this.win.popDiv.pixel.style.height = this.win.popDiv.clientHeight + 'px';
    this.win.popFormDiv.style.zIndex = 200;
    this.win.popDiv.style.visibility = "visible";
    this.win.popFormDiv.style.visibility = this.win.popDiv.style.visibility;
  } else {
    this.win.popDiv.style.visibility = "hidden";
    this.win.popDiv.style.width='0px';
    this.win.popDiv.style.height='0px';
    this.win.popDiv.pixel.style.width = '0px';
    this.win.popDiv.pixel.style.height = '0px';
    this.win.popFormDiv.style.visibility = this.win.popDiv.style.visibility;
  }
  if (document.defaultView)/*firefox*/ {
    var siblings = this.win.popDiv.parentNode.childNodes;
    for(var i=0; i<siblings.length; i++) {
      if (siblings[i] != this.win.popDiv && siblings[i].nodeType == document.ELEMENT_NODE) {
        if (enabled) {
          addClass(siblings[i], "PopSibling");
        } else {
          removeClass(siblings[i], "PopSibling");
        }
      }
    }
  } else {
    if (enabled) {
      addClass(this.win.document.body, "PopSibling");
    } else {
      removeClass(this.win.document.body, "PopSibling");
    }
  }
}
ParameterEditor.prototype.cancel = function() {
  window.top.modified = this.oldModified;
  this.showEditor(false);
  enableTooltip();
}
ParameterEditor.prototype.save = function() {
  var tr = document.getElementById(this.win.popFormDiv.trId);
  var form = getParent(tr, "FORM");
  if (eval(form.id + "_validate(this.win.popForm,tr.paramData, tr.objId)")) {
    window.top.modified = this.oldModified;
    var param_attrib = eval(form.id + "_param_attrib");
    var arr = new Array();
    for(var i=0; i< param_attrib.length; i++) {
      var inp = this.win.popForm.elements[i];
      arr[i] = trim(inp.value);
      if (inp.type == "checkbox") {
        arr[i] = inp.checked;
      }
    }
    for(var i=0; i< arr.length; i++) {
      var td = tr.cells[i];
      var val = arr[i];
      if (form.parameter_format) {
        val = form.parameter_format(param_attrib[i], val);
      }
      if (td.childNodes.length > 0) {
        td.removeChild(td.childNodes[0]);
      }
      td.appendChild(document.createTextNode(val));
    }
    for(var i=0; i<arr.length && i<tr.paramData.length; i++) {
        tr.paramData[i] = arr[i];
    }
    checkParameterFormElement(getParent(tr, "TABLE"));
    if (tr.objId) {
      modified[form.id][tr.objId] = tr.paramData;
    }
    this.cancel();
  }
}
ParameterEditor.prototype.getTopWin = function() {
  var eWin;
  if (document.defaultView)/*firefox*/ {
    eWin = document.defaultView.window;
  } else {
    eWin = document.parentWindow;
  }
  while( eWin.parent != eWin.top) {
    eWin = eWin.parent;
  }
  return eWin;
}

var idCounter = 0;
function nextId() {
  idCounter++;
  return 'uid_'+idCounter;
}

