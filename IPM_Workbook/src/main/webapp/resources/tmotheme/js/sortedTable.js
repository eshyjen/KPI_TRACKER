/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/sortedTable.js $ */
/* $Id: sortedTable.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* Copyright (c) 2005 Drutt Corporation, all rights reserved. */

/**
 * id             : of table
 * class          : of table
 * tabledata      : [0] = SelecteRowId, [1] = HeaderInfo, [2-n] Rows
 *  HeaderInfo    : Array[[Title,class],*]
 *  Row           : Array[rowId,((TableImage|TableLink|TableText),*)]
 * sortByColumn   : Default column to sort on
 * selectByColumn : Column that selects a row in the table, if -1 no selections possible
 *                  Note: only TableLink is selectable columns.
 * ctx            : Context path, used ctx/i/arrow[up|down].gif
 * skipSelectFirst: if 'true' skip select on load
 */

function TableListener(id, className, sortByColumn, selectByColumn, ctx, skipSelectFirst) {
  this.id = id;
  this.className = className;
  this.sortByColumn = sortByColumn;
  this.selectByColumn = selectByColumn;
  this.skipSelectFirst = skipSelectFirst;
  this.ctx = ctx;
}
TableListener.prototype.setUrl = function(url) {
  this.url = url;
}
TableListener.prototype.setParams = function(params) {
  this.params = params;
}

TableListener.prototype.refresh = function() {
  var listener = this;
  if (this.url) {
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

    this.req.open("GET", this.url, true);
    this.req.send(null);
  }
}
TableListener.prototype.sortedTable = null;
TableListener.prototype.url = null;
TableListener.prototype.req = null;
TableListener.prototype.id = null;
TableListener.prototype.className = null;
TableListener.prototype.sortByColumn = null;
TableListener.prototype.selectByColumn = null;
TableListener.prototype.ctx = null;
TableListener.prototype.skipSelectFirst = null;
TableListener.prototype.params = null;

function process(listener) {
  if (listener.req.readyState == 4) {
    if (listener.req.status == 200) {
      var tabledata = eval(listener.req.responseText);
      if (listener.sortedTable) {
        listener.sortedTable.setData(tabledata);
      } else {
        listener.sortedTable = new SortedTable(
          listener.id,
          listener.className,
          null,
          listener.sortByColumn,
          listener.selectByColumn,
          listener.ctx,
          listener.skipSelectFirst);
        listener.sortedTable.createTableHeader(tabledata[1], listener.ctx);
        var selectedRow = listener.sortedTable.setData(tabledata);
        if (selectedRow != null && !listener.skipSelectFirst) {
          var el = selectedRow.cells[listener.sortedTable.selectByColumn];
          var w = lookupFrame(window.top, el.target);
          if (w) {
            if (listener.params != null) {
                if (el.href.indexOf('?') != -1) {
                  w.location.href = el.href+'&'+listener.params;
                } else {
                  w.location.href = el.href+'?'+listener.params;
                }
            } else {
              w.location.href = el.href;
            }
            listener.params = null;
          }
        }
      }
      resizeFrame();
    } else {
      alert("Search failed: " + listener.req.statusText);
    }
  }
}


function SortedTable(id, className, tabledata, sortByColumn, selectByColumn, ctx, skipSelectFirst) {
  this.id = id;
  this.sortByColumn = sortByColumn;
  this.sortAscending = true;
  this.skipSelectFirst = skipSelectFirst;
  if (!top.sortedTables) {
    top.sortedTables = new Array();
  } else if (top.sortedTables[this.id]) {
    this.sortByColumn = top.sortedTables[this.id].sortByColumn;
    this.sortAscending = top.sortedTables[this.id].sortAscending;
  }
  top.sortedTables[this.id] = this;
  this.table = document.createElement('TABLE');
  this.table.select = function(el) {
    // el is the element that was clicked i.e <a ...>
    if (confirmExit()) {
      var instance = getSortedTableInstance(el);
      var oldRow = document.getElementById(instance.selectedRowId);
      var newRow = getParent(el, 'TR');
      if (oldRow && oldRow != newRow) {
        removeClass(oldRow, 'selectedRow');
        addClass(newRow, 'selectedRow');
      } else {
        addClass(newRow, 'selectedRow');
      }
      instance.selectedRowId = newRow.id;
      instance.doClick(el);
    }
  }
  this.table.clicked = function(el) {
    // el is the element that was clicked i.e <a ...>
    if (confirmExit()) {
      getSortedTableInstance(el).doClick(el);
    }
  }
  this.table.id = id;
  this.table.className = className;
  this.selectByColumn = selectByColumn;
  var script = document.getElementById('s'+id);
  script.parentNode.appendChild(this.table);
  if (tabledata != null) {
    this.createTableHeader(tabledata[1], ctx);
    var selectedRow = this.setData(tabledata);
    if (selectedRow != null && !skipSelectFirst) {
        this.doClick(selectedRow.cells[selectByColumn]);
    }
  }
}
SortedTable.prototype.setData = function(tabledata) {
  var selectedRow = null;
  while(this.table.rows.length > 1) {
    this.table.deleteRow(this.table.rows.length-1);
  }
  this.selectedRowId = tabledata[0];
  for(var i=2; i<tabledata.length; i++) {
    var row = this.table.insertRow(-1);
    this.fillTableRow(row, tabledata[i], this.selectByColumn);
  }
  if (this.table.rows.length > 1) {
    this.doSort(this.table.rows[0].cells[this.sortByColumn]);
    if (this.selectByColumn >= 0) {
      if (this.selectedRowId != 'none') {
        selectedRow = this.selectedRowId ? document.getElementById(this.selectedRowId) : null;
        if (selectedRow == null && !this.skipSelectFirst) {
          selectedRow = this.table.rows[1];
          this.selectedRowId = selectedRow.id;
        }
        if (selectedRow) {
          addClass(selectedRow, 'selectedRow');
        }
      }
    }
  }
  return selectedRow;
}
SortedTable.prototype.doClick = function(el) {
  var w = lookupFrame(window.top, el.target);
  if (w) {
    w.location.href = el.href;
  }
}

SortedTable.prototype.createTableHeader = function( data, ctx) {
  var row = this.table.insertRow(0);
  for(var i=0; i<data.length; i++) {
    var th = document.createElement("TH");
    row.appendChild(th);
    th.onclick = resortTable;
    th.className = data[i][1];
    th.appendChild(document.createTextNode(data[i][0]));
    th.appendChild(this.createArrow('up', ctx));
    th.appendChild(this.createArrow('down', ctx));
  }
}
SortedTable.prototype.createArrow = function(arrow, ctx) {
    var s = document.createElement('SPAN');
    s.className = 'sorted_'+arrow;
    s.style.display = 'none';
    var i = document.createElement('IMG');
    s.appendChild(i);
    i.className='arrow';
    i.src = ctx + '/i/' + arrow + '.gif';
    i.alt = arrow;
    return s;
}

SortedTable.prototype.fillTableRow = function(row, data, selectByColumn) {
  var ctor = new String(data[0].constructor);
  if (ctor.indexOf('function Array') == 1) {
    row.id = data[0][0];
    if (data[0].length > 1) {
      row.className = data[0][1];
    }
  } else {
    row.id = data[0];
  }
  for (var i=1; i<data.length; i++) {
    var td = row.insertCell(i-1);/*row.cells.length);*/
    data[i].format(td, selectByColumn == (i-1), row.id);
  }
}

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
  newRows.sort(this.sort);
  // Since ie unchecks checkboxes when reappended, remember the selected checkboxes/radios
  var inputs = this.table.getElementsByTagName('INPUT');
  var cb = new Array();
  for(var i=0; i<inputs.length; i++) {
      if ((inputs[i].type.toLowerCase() == "checkbox" || inputs[i].type.toLowerCase() == "radio") && inputs[i].checked) {
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
}
SortedTable.prototype.showArrow = function(span, className) {
  var arrow = this.findElement(span, className);
  if (arrow != null) {
    arrow.style.display = 'inline';
  }
}
SortedTable.prototype.hideArrows = function(className) {
  var spans = GetElementsByClassName(className, this.table, 'SPAN');
  for(var i=0; i<spans.length; i++) {
    spans[i].style.display= "none";
  }
}
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

/* Cell types */
function TableImage(src, alt, text, sortBy) {
  this.src = src;
  this.alt = alt;
  this.text = text;
  this.sortBy = sortBy;
}
TableImage.prototype.format = function(parent, unused, rowId) {
  var i = document.createElement('IMG');
  i.className='icon';
  i.src = this.src;
  parent.id = makeId(rowId + "_image_" + this.src);
  parent.sortBy = this.sortBy;
  parent.appendChild(i);
  if (this.text && this.text.length > 0) {
    parent.appendChild(document.createTextNode(this.text));
  } else {
    parent.align='center';
  }
  parent.onmouseover = setTooltip;
  parent.onmouseout = hideTooltip;
  parent.toolTip=this.alt;
}

function TableLink(href, target, text, toolTip, className) {
  this.href = href;
  this.target = target;
  this.text = text;
  this.toolTip = formatTooltip(toolTip);
  if (className) {
	  this.className = className;
  } else {
      this.className = 'link';
  }

}
TableLink.prototype.format = function(parent, doSelect, rowId) {
  parent.className = this.className;
  parent.href = this.href;
  parent.target = this.target;
  parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
  parent.id = makeId(rowId + "_link_" + this.text);
  if (this.toolTip.length > 0) {
    parent.onmouseover = setTooltip;
    parent.onmouseout = hideTooltip;
    parent.toolTip=this.toolTip;
  }
  if (doSelect) {
      parent.onclick=function() {
          var tab = getParent(this, 'TABLE');
          tab.select(this);
      }
  } else {
      parent.onclick=function() {
          var tab = getParent(this, 'TABLE');
          tab.clicked(this);
      }
  }
  parent.appendChild(document.createTextNode(this.text));
}
function TableMailTo(address, hintText) {
    this.text = address;
    if (hintText) {
      this.toolTip = formatTooltip(hintText + ' ' + address);
    } else {
      this.toolTip = formatTooltip(address);
    }
}

TableMailTo.prototype.format = function(parent) {
  parent.className = 'link';
  parent.address = this.text;
  parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
  if (this.toolTip.length > 0) {
    parent.onmouseover = setTooltip;
    parent.onmouseout = hideTooltip;
    parent.toolTip=this.toolTip;
  }
  parent.onclick=function() {
      window.location = 'mailto:'+this.address;
  }
  parent.appendChild(document.createTextNode(this.text));
}

function TableText(text) {
  this.text = text;
}
TableText.prototype.format = function(parent, unused, rowId) {
  parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
  parent.appendChild(document.createTextNode(this.text));
  parent.id = makeId(rowId + "_text_" + this.text);
}
function TableDate(text, sortBy) {
  this.text = text;
  this.sortBy = -sortBy;
}
TableDate.prototype.format = function(parent, unused, rowId) {
  parent.appendChild(document.createTextNode(this.text));
  parent.sortBy = this.sortBy;
}

function TableCheckBox(name, value, onclickMethod, checked) {
  this.name = name;
  this.value = value;
  this.onclickMethod = onclickMethod;
}

TableCheckBox.prototype.format = function(parent, unused, rowId) {
  var i = document.createElement('INPUT');
  i.type = 'checkbox';
  i.className = 'checkbox';
  i.name = this.name;
  i.value = this.value;
  i.id = makeId(rowId + "_checkbox");
  if (this.checked) {
      i.checked = true;
      i.defaultChecked = true;
  }
  parent.sortBy = 0;
  parent.appendChild(i);
  parent.align='center';
  parent.ll = this;
  parent.cb = i;
  parent.sortValue = function(p) {
      if (p.cb.checked) {
          return 1;
      } else {
          return 0;
      }
  }
  parent.onclick = function() {
      if (this.ll.onclickMethod) {
          this.ll.onclickMethod();
      }
  }
}

function TableRadio(name, value, onclickMethod, enabled, checked) {
    this.name = name;
    this.value = value;
    this.onclickMethod = onclickMethod;
    this.enabled = enabled;
    this.checked = checked;
}

TableRadio.prototype.format = function(parent, unused, rowId) {
  var i = document.createElement('INPUT');
  i.type = 'radio';
  i.className = 'radio';
  i.name = this.name;
  i.value = this.value;
  if (this.checked) {
      i.checked = true;
      i.defaultChecked = true;
  }
  parent.sortBy = 0;
  parent.appendChild(i);
  parent.align='center';
  parent.ll = this;
  parent.cb = i;
  parent.sortValue = function(p) {
      if (p.cb.checked) {
          return 1;
      } else {
          return 0;
      }
  }
  if (this.enabled) {
      parent.onclick = function() {
          if (this.ll.onclickMethod) {
              this.ll.onclickMethod();
          }
      }
  } else {
      i.disabled = true;
  }
}

function TableJavascriptLink(href, text, onclickMethod, toolTip) {
  this.href = href;
  this.text = text;
  this.onclickMethod = onclickMethod;
  this.toolTip = formatTooltip(toolTip);
}

TableJavascriptLink.prototype.format = function(parent, doSelect, rowId) {
  parent.className = 'link';
  parent.href = this.href;
  parent.sortBy = this.text ? this.text.toLowerCase() : this.text;
  if (this.toolTip.length > 0) {
    parent.onmouseover = setTooltip;
    parent.onmouseout = hideTooltip;
    parent.toolTip=this.toolTip;
  }
  parent.ll = this;
  parent.id = makeId(rowId + "_link_" + this.text);
  parent.onclick = function() {
      this.ll.onclickMethod();
  }
  parent.appendChild(document.createTextNode(this.text));
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
function getSortedTableInstance(el) {
  var tab = getParent(el, 'TABLE');
  if (tab) {
     return top.sortedTables[tab.id];
  }
}
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

