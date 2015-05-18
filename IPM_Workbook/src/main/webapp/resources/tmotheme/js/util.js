/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/util.js $ */
/* $Id: util.js 330795 2013-04-02 11:37:24Z emiazhu $ */

//Initialize when loading script

/* Short command of document.getElementById(id) */

function $(id) {
  return document.getElementById(id);
}

function GetElementsByClassName(className, root, tagName)
{
  var matched = new Array();
  var re = new RegExp('\\b'+className+'\\b', 'i');
  var list = root.getElementsByTagName(tagName);
  for (var i = 0; i < list.length; ++i) {
    if (list[i].className.search(re) != -1) {
      matched[matched.length] = list[i];
    }
  }
  return matched;
}

/**
 * Add an event listener to a node.
 * @param target The element to attach the event (DOM element)
 * @param eventType. type of event to attach. (String)
 * @param listener The finction to execute for the event. (EventListener or JavaScript function)
 * @param useCapture. 'false' == bubble model.
 */
function addEvent(target, eventType, func, useCapture) {
  // event handling for IE and Firefox
    if (target.addEventListener) {
      // Firefox
        target.addEventListener(eventType, func, useCapture);
        return true;
    } else if (target.attachEvent) {
        // IE
        var r = target.attachEvent('on' + eventType, func);
        return r;
    } else {
        // The ugly one. Overwrite if it already exist one.
        target['on' + eventType] = func;
    }
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

function addClass(element, newClass) {
    if (element) {
        if (element.className.length == 0) {
            element.className = newClass;
        } else {
            var reg = new RegExp('\\b'+newClass+'\\b', 'i');
            if (!reg.test(element.className)) {
                element.className = element.className + " " + newClass;
            }
        }
    }
}

function removeClass(element, oldClass) {
    if (element) {
        var reg = new RegExp('\\b('+oldClass+'\\s?|\\s?'+oldClass+')\\b', 'i');
        var res = element.className.replace(reg, '');
        element.className = res;
    }
}

function removeArrayIndex(arr, index) {
  for (var i = index; i < arr.length -1; i++) {
    arr[i] = arr[i+1];
  }
  arr.length = arr.length -1;
}

function removeArrayElement(arr, obj) {
  if (arr) {
    for (var i = 0; i < arr.length; i++) {
      if (arr[i]==obj) {
        removeArrayIndex(arr, i);
      }
    }
  }
}

function colorTableLines(table) {
  if (table.rows.length > 0) {
    addClass(table.rows[0], "striped_header");
    for (var i = 1; i < table.rows.length; i++) {
      removeClass(table.rows[i], "odd");
      removeClass(table.rows[i], "even");
      addClass(table.rows[i], (i%2 == 0) ? "even" : "odd");
    }
  }
}

function markHover(table) {
  if (table.rows.length > 0) {
    for (var i = 1; i < table.rows.length; i++) {
        table.rows[i].onmouseout =  function() {return removeClass(this, 'hovered')};
        table.rows[i].onmouseover = function() {return addClass(this, 'hovered')};
    }
  }
}

/** Resize current frame */
function resizeFrame() {
  resizeFrames(window.top.frames, document);
}

/** Recursive, bottom up, resize all frames
 * Find target document, resize it's frame and all parants.
 * @param frames array of sibling frames
 * @param doc target document
 * @return height of all sibling frames if any has been resized
 */
function resizeFrames(frames, doc) {
  for(var i=0; i<frames.length; i++) {
    var f = frames[i];
    if (f.document == doc) {
       // Resize target frame.
       var bt = pxToInt(f.document.body.style.marginTop);
       var bc = f.document.body.offsetHeight;
       var bb = pxToInt(f.document.body.style.marginBottom);
       try {
           f.frameElement.height = Math.max(bt+bc+bb, 100);
       } catch (e) {
           return -1;
       }
       var fh = 0;
       for(var j=0; j<frames.length; j++) {
           var sib = frames[j];
           fh += Number(sib.frameElement.height).valueOf();
       }
       // new height of siblings
       return fh;
    } else {
      var ch = resizeFrames(f.frames, doc);
      if (ch > 0) { // the frames has been resized
        try { // Resize papa
          f.frameElement.height = Math.max(ch, 100);
        } catch (e) {
            return -1;
        }
        var fh = 0;
        for(var j=0; j<frames.length; j++) {
          var sib = frames[j];
          fh += Number(sib.frameElement.height).valueOf();
        }
        // new height of siblings
        return fh;
      } else if (ch < 0) {
          return ch;
      }
    }
  }
  return 0; // No changes
}
/** convert a pixel directive to int i.e. 10px => 10
 * @param str
 * @return int value of str
 */
function pxToInt(str) {
  var i = parseInt(str);
  if (isNaN(i)) {
    return 0;
  } else {
    return i;
  }
}

function isFrame(name) {
  var t = window.top.frames != null ? window.top.frames[0].frames[name] : null;
  return window == t;
}

function checkFrame() {
  var frameOk = true;
  var clazz = document.body.className;
  if (clazz.length > 0) {
    var reg = new RegExp('\\bleft\\b', 'i');
    if (reg.test(clazz)) {
      frameOk = isFrame('menuFrame');
    } else {
      reg = new RegExp('\\bcenter_main\\b', 'i');
      if (reg.test(clazz)) {
        frameOk = isFrame('mainFrame');
      }
    }
  }
  if (!frameOk) {
     window.top.location = '/tpim/';
     return false;
  }
  return true;
}

function empty(field) {
    return ( field.value == null || trim(field.value).length < 1);
}

function trim(sString) {
  while (sString.substring(0,1) == ' ') {
    sString = sString.substring(1, sString.length);
  }
  while (sString.substring(sString.length-1, sString.length) == ' ') {
    sString = sString.substring(0,sString.length-1);
  }
  return sString;
}

function loadUtil() {
  if (checkFrame()) {
    var tables = GetElementsByClassName("hstriped", document, "table");
    for (var i = 0; i<tables.length; i++) {
      colorTableLines(tables[i]);
    }
    tables = GetElementsByClassName("mark-hover", document, "table");
    for (var i = 0; i<tables.length; i++) {
      markHover(tables[i]);
    }
    //Parse query
    if (window.location.search) {
        var elems = window.location.search.substring(1).split('&');
        var params = new Array();
        var current;
        var name;
        var val;
        for(var i=0; i<elems.length; i++) {
            current = elems[i].split('=');
            name = unescape(current[0]).replace(/^\s*|\s*$/g,"");
            val = current[1];
            if (val) {
                val = unescape(val);
            } else {
                val = "";
            }
            params[name]=val;
        }
        var tpimupd = params['tpimupd'];
        if (tpimupd != null) {
            var action = params['action'];
            if (action != null && window.top.stateListeners ) {
                for(var i=0; i<window.top.stateListeners.length; i++) {
                    if (window.top.stateListeners[i] && window.top.stateListeners[i][1] == tpimupd) {
                        for(var ax=0; ax<window.top.stateListeners[i][2].length;ax++) {
                            if (window.top.stateListeners[i][2][ax]==action) {
                                window.top.stateListeners[i][0].reload();
                            }
                        }
                    }
                }
            }
            if (top.search_common) {
				top.search_common.refresh();
            }
            if (tpimupd=='partner') {
                if (!top.search_common && top.search_partners) {
                    top.search_partners.refresh();
                }
            } else if (tpimupd=='agreement') {
                if (!top.search_common && top.search_agreements) {
                    top.search_agreements.refresh();
                }
                if (top.list_agreements) {
                    top.list_agreements.refresh();
                }
            } else if (tpimupd=='service') {
                if (!top.search_common && top.search_services) {
                    top.search_services.refresh();
                }
                if (top.list_servies) {
                    top.list_servies.refresh();
                }
            }
        }
    }
    pl = GetElementsByClassName("resize", document, "body");
    if (pl.length > 0) {
      resizeFrame();
    }
  }
}

function unloadUtil() {
}



document.onloads = new Array();
document.onunloads = new Array();

function registerOnload(func) {
  document.onloads[document.onloads.length] = func;
}
function registerOnunload(func) {
  document.onunloads[document.onunloads.length] = func;
}

function callOnLoads() {
  if (document.onloads) {
    for(var i = 0; i<document.onloads.length; i++) {
       document.onloads[i]();
    }
  }
}

function callOnUnloads() {
  if (document.onunloads) {
    for(var i = 0; i<document.onunloads.length; i++) {
       document.onunloads[i]();
    }
  }
}

function confirmExit() {
  if (top && top.modified && top.modified != null) {
       doChange = confirm("Modifications not saved. Do you want to continue without saving?");
       if (doChange) {
         // Reset the form itself, incase of main service form
           top.modified = null;
       }
       return doChange;
  }
  return true;
}

function unescapeLF(txt) {
    if (txt != null) {
        r = new RegExp('\\\\n');
        while(txt.search(r) != -1) {
            txt = txt.replace(r, '\n');
        }
    }
    return txt;
}

function StateListener(parentId, type, action) {
    this.parentId = parentId;
    registerStateListener(this, type, action);
    var listener = this;
    this.unregister = function() {
        unregisterStateListener(listener);
    }
    registerOnunload(this.unregister);
}

StateListener.prototype.toString = function() {
    return 'StateListener{'+this.parentId+'}';
}


StateListener.prototype.setUrl = function(url) {
  this.url = url;
}

StateListener.prototype.reload = function() {
  var listener = this;
  if (this.url) {
    if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    this.req.onreadystatechange = function() {
      processStateListener(listener);
    };

    this.req.open("GET", this.url, true);
    this.req.send(null);
  }
}
function processStateListener(listener) {
  if (listener.req.readyState == 4) {
    if (listener.req.status == 200) {
      try {
          document.getElementById(listener.parentId).innerHTML=listener.req.responseText;
          if (listener.loaded) {
              listener.loaded();
          }
      } catch (ex) {
          alert("StateListener failed:"+ex);
      }
    } else {
      alert("StateListener failed: " + listener.req.statusText);
    }
  }
}

function registerStateListener(loc, type, action) {
    if (!window.top.stateListeners) {
        window.top.stateListeners = new Array();
    }
    window.top.stateListeners[window.top.stateListeners.length] = new Array();
    window.top.stateListeners[window.top.stateListeners.length-1][0] =loc;
    window.top.stateListeners[window.top.stateListeners.length-1][1] =type;
    window.top.stateListeners[window.top.stateListeners.length-1][2] =action;
}
function unregisterStateListener(loc) {
    var tmp = new Array();
    try {
        for(var i=0; window.top.stateListeners && i<window.top.stateListeners.length; i++) {
            if (window.top.stateListeners[i][0]==loc) {
                window.top.stateListeners[i] = null;
            }
            if (window.top.stateListeners[i] != null) {
                tmp[tmp.length] = window.top.stateListeners[i];
            }
        }
        window.top.stateListeners = tmp;
    } catch(e) {
        alert('Failed to unregister listener:'+loc);
    }
}

// Enable an element
function enableElement(element) {
    element.disabled = false;
    removeClass(element, "disabled");
}
// Disable an element
function disableElement(element) {
    element.disabled = true;
    addClass(element, "disabled");
}

// Checks if this element is a Reset button
function isResetElement(element) {
  if (element.type && (element.type == "reset")) {
    return true;
  }
  if (element.type && (element.type == "button")) {
      var reg = new RegExp('\\breset\\b', 'i');
      if (reg.test(element.className)) {
          return true;
      }
  }
  return false;
}
// Checks if this element is a Submit button
function isSubmitElement(element) {
  if (element.type && (element.type == "submit")) {
    return true;
  }

  if (element.type && (element.type == "button")) {
      var reg = new RegExp('\\bsubmit\\b', 'i');
      if (reg.test(element.className)) {
          return true;
      }
      // The following lines have been added to solve the case where a normal "button" does the
      // submitting of a form through javascript, after performing a check
      // If removed, make sure editService in the Alert Module still works!!
      if (element.value && (element.value == "Save")) {
          return true;
      }
  }

  return false;
}

function setWizardStep(value) {
  document.getElementById('wizardStep').value = value;
}

/**
 * Take a piece of text and make it into a string
 * suitable to be an id. Exchange whitespace for _
 * and make it lower case.
 */
function makeId(text) {
	if (text) {
	    text = text.toLowerCase();
	    text = text.replace(/\s+/g,'_');
	}
	return text;
}

var DateFormat=function(date){
  //implementation
  var format=function(str){
      str=str.replace(/yyyy/g,date.getFullYear());
      str=str.replace(/yy/g,date.getFullYear().toString().slice(2));
      str=str.replace(/mm/g,fixTwo(date.getMonth()+1));
      str=str.replace(/dd/g,fixTwo(date.getDate()));
      str=str.replace(/wk/g,date.getDay());
      str=str.replace(/hh/g,fixTwo(date.getHours()));
      str=str.replace(/mi/g,fixTwo(date.getMinutes()));
      str=str.replace(/ss/g,fixTwo(date.getSeconds()));
      str=str.replace(/ms/g,date.getMilliseconds());
      return str;
  }

  var valueOf=function(){}
  var toString=function(){
      return date.toLocaleString();
  }
  var fixTwo=function(str){
      if((""+str).length == 1){
          str = "0" + str;
      }
      return str;
  }

  //constructor
  if(date != undefined){
      date=new Date(date);
  }
  if(!date||date=="NaN"){
      date=new Date();
  }
  //inteface
  this.format=format;
  this.valueOf=valueOf;
  this.toString=toString;
  this.fixTwo=fixTwo;

}

window.onload = callOnLoads;
window.onunload = callOnUnloads;

registerOnload(loadUtil);
registerOnunload(unloadUtil);