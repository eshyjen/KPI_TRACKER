/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/tooltip.js $ */
/* $Id: tooltip.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* Copyright (c) 2005 Drutt Corporation, all rights reserved. */

var tooltipTxt = null;
var tooltipEnabled = true;

function loadTooltip() {
  if (document.all){
     document.onmousemove = mouseMoved;
  }else {
     document.addEventListener('mousemove',mouseMoved,false);
  }
}

function getTipDoc() {
  var doc = null;
  // Find the top frame
  if (window.top.frames && window.top.frames.length > 0) {
     var w = window;
     while (doc == null && w != window.top) {
       if (w && w.frameElement && w.frameElement.nodeName == 'FRAME') {
          doc = w.document;
       } else {
         w = w.parent;
       }
     }
  } else if (window.top.document) {
    doc = window.top.document;
  }
  if (doc == null) {
    doc = document;
  }
  var tipDiv = doc.getElementById('tipDiv');
  if (!tipDiv) {
    tipDiv = doc.createElement('DIV');
    tipDiv.id = 'tipDiv';
    tipDiv.style.position = 'absolute';
    tipDiv.style.visibility = 'hidden';
    tipDiv.style.zIndex = 100;
    //document.body.appendChild(tipDiv);
    var tipContent = doc.createElement('DIV');
    tipContent.id = 'tipContent';
    tipContent.className = 'TTip';
    tipDiv.appendChild(tipContent);
    doc.body.appendChild(tipDiv);
  }
  return doc;
}

function setClass(newClass){
	var tipDoc = getTipDoc();
	var tipContent = tipDoc.getElementById('tipContent');
	tipContent.className = newClass;
}

function setTooltip() {
  tooltipTxt = this.toolTip;
}
function showTooltip(txt) {
  tooltipTxt = formatTooltip(txt);
}
function formatTooltip(txt) {
  if (txt) {
    var r = new RegExp('<');
    while(txt.search(r) != -1) {
      txt = txt.replace(r, '&lt;');
    }
    r = new RegExp('\\r\\n');
    while(txt.search(r) != -1) {
      txt = txt.replace(r, '<br/>');
    }
    r = new RegExp('\\n');
    while(txt.search(r) != -1) {
      txt = txt.replace(r, '<br/>');
    }
  }
  return txt;
}

function disableTooltip() {
  hideTooltip();
  tooltipEnabled = false;
}
function enableTooltip() {
  tooltipEnabled = true;
}
function hideTooltip() {
  var tipDoc = getTipDoc();
  var tipDiv = tipDoc.getElementById('tipDiv');
  var tipContent = tipDoc.getElementById('tipContent');
  if (tipDiv) {
    tooltipTxt = null;
    tipContent.innerHTML = '';
    tipDiv.style.visibility = 'hidden';
  }
  setClass('TTip');
}
function calcOffsetY(w) {
  var ret = 0;
  if (w && w.frameElement && w.frameElement.nodeName != 'FRAME') {
    ret = w.frameElement.offsetTop + calcOffsetY(w.parent);
  }
  return ret;
}
function calcOffsetX(w) {
  var ret = 0;
  if (w && w.frameElement && w.frameElement.nodeName != 'FRAME') {
    ret = w.frameElement.offsetLeft + calcOffsetX(w.parent);
  }
  return ret;
}
function mouseMoved() {
  if (tooltipEnabled && tooltipTxt != null && tooltipTxt.length > 0) {
    var tipDoc = getTipDoc();
    var tipDiv = tipDoc.getElementById('tipDiv');
    var tipContent = tipDoc.getElementById('tipContent');
    var evt = (document.all ? window.event : arguments[0]);
    tipContent.innerHTML = tooltipTxt;
    var offsetY = calcOffsetY(window) + 5;
    var offsetX = calcOffsetX(window) + 20;
    if (document.all) {
      tipDiv.style.pixelLeft = evt.clientX+offsetX+document.documentElement.scrollLeft;
      tipDiv.style.pixelTop =  evt.clientY+offsetY+document.documentElement.scrollTop;
    } else {
      tipDiv.style.left = (evt.clientX+offsetX+window.pageXOffset) + 'px';
      tipDiv.style.top = (evt.clientY+offsetY+window.pageYOffset) + 'px';
    }
    tipDiv.style.visibility = "visible";
  }
}
registerOnload(loadTooltip);
