/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/searchHandler.js $ */
/* $Id: searchHandler.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* Copyright (c) 2005 Drutt Corporation, all rights reserved. */

function SearchListener(name, uri, param, target, listMax, linkMax, idPrefix, prevStr, nextStr, emptyResult) {
  this.name = name;
  this.uri = uri;
  this.param = param;
  this.target = target;
  this.listMax = listMax;
  this.linkMax = linkMax;
  this.idPrefix = idPrefix;
  this.prevStr = prevStr;
  this.nextStr = nextStr;
  this.emptyResult = emptyResult;
}

SearchListener.prototype.setUrl = function(url) {
  this.url = url;
}

SearchListener.prototype.refresh = function(resetIx) {
  var listener = this;
  if (this.url) {
    if (window.XMLHttpRequest) {
        this.req = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        this.req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    this.req.onreadystatechange = function() {
      process(listener, resetIx);
    };

    this.req.open("GET", this.url, true);
    this.req.send(null);
  }
}

SearchListener.prototype.showIx = 0;
SearchListener.prototype.searchHandler = null;
SearchListener.prototype.url = null;
SearchListener.prototype.req = null;
SearchListener.prototype.uri = null;
SearchListener.prototype.name = null;
SearchListener.prototype.param = null;
SearchListener.prototype.target = null;
SearchListener.prototype.listMax = null;
SearchListener.prototype.linkMax = null;
SearchListener.prototype.idPrefix = null;
SearchListener.prototype.prevStr = null;
SearchListener.prototype.nextStr = null;

function process(listener, resetIx) {
  if (listener.req.readyState == 4) {
    if (listener.req.status == 200) {
      try {
        var showIx;
        if (resetIx == null || !resetIx) {
          var lastIx = -1;
          if (listener.searchHandler) {
            lastIx = listener.searchHandler.current;
          }
          showIx = lastIx;
        } else {
          showIx = -1;
        }
        listener.searchHandler = new SearchHandler(
          listener.name,
          listener.uri,
          listener.param,
          listener.target,
          eval(listener.req.responseText),
          listener.listMax,
          listener.linkMax,
          listener.idPrefix,
          listener.prevStr,
          listener.nextStr,
          showIx,
          listener.emptyResult);
      } catch (ex) {
          alert("Search failed");
      }
    } else {
      alert("Search failed: " + listener.req.statusText);
    }
  }
}

function SearchHandler(name, uri, param, target, arr, listMax, linkMax, idPrefix, prevStr, nextStr, lastIx, emptyResult) {
  this.id = name;
  var showIx = 0;
  if (lastIx && lastIx > 0) {
    showIx = lastIx;
  }
  this.current = -1;
  this.linkMax = linkMax;
  this.idPrefix = idPrefix;
  this.listPrefix  = idPrefix + '_list_';
  this.linkPrefix  = idPrefix + '_link_';
  this.linkSize  = 0;
  this.left = 0;
  this.right = linkMax-1;
  this.previousEnabled = true;
  this.nextEnabled = true;

  // locate the "holder" of the search result
  var resHolder = document.getElementById(name);
  // Clear previous
  while (resHolder.childNodes && resHolder.childNodes.length > 0) {
    resHolder.removeChild(resHolder.lastChild);
  }

  var span;
  if (arr.length > listMax) {
    var listIx=0;
    for(var ax=0; ax<arr.length && arr[ax] != null; ax+=listMax) {
      span = document.createElement("span");
      span.id=this.listPrefix+listIx;
      span.style.display="none";
      resHolder.appendChild(span);
      for(var lx=0; lx<listMax; lx++) {
        if (ax+lx<arr.length) {
          span.appendChild(this.listElement(target, uri, param, arr[ax+lx]));
        }
      }
      listIx++;
    }
    var prev_next = document.createElement("div");
    prev_next.className = "prev_next";
    span = document.createElement("span");
    span.onclick = this.prev;
    span.searchHandler = this;
    span.id = this.idPrefix + "prev";
    span.style.display = "inline";
    span.style.margin = "0px 3px 0px 0px";
    span.appendChild(document.createTextNode(prevStr));
    prev_next.appendChild(span);
    for(var ax=0; ax<arr.length && arr[ax] != null; ax += listMax) {
      span = document.createElement("span");
      span.onclick = this.show; // (this.linkSize)
      span.showIx = this.linkSize;
      span.searchHandler = this;
      span.id = this.linkPrefix + this.linkSize;
      span.style.display = "inline";
      span.style.margin = "3px 3px 0px 0px";
      span.appendChild(document.createTextNode(this.linkSize + 1));
      prev_next.appendChild(span);
      this.linkSize++;
    }
    span = document.createElement("span");
    span.onclick = this.next;
    span.searchHandler = this;
    span.id = this.idPrefix + "next";
    span.style.display = "inline";
    span.style.margin = "3px 0px 0px 0px";
    span.appendChild(document.createTextNode(nextStr));
    prev_next.appendChild(span);
    resHolder.appendChild(prev_next);

    ix = Math.min(showIx, this.linkSize-1);
    this.setupMargin(ix);
    this.showIx(ix);
  } else if (arr.length > 0){
    for(var ax=0; ax<arr.length; ax++) {
      resHolder.appendChild(this.listElement(target, uri, param, arr[ax]));
    }
  } else {
      var sp = document.createElement("span");
      sp.className = 'error';
      resHolder.appendChild(sp);
      sp.appendChild(document.createTextNode(emptyResult));
  }
}

SearchHandler.prototype.listElement = function(target, uri, param, info) {
  if (info != null) {
    var a = document.createElement("a");
    a.id = info[2];
    a.href = info[0] + uri + '?' + param + '=' + info[1];
    if (target != null && target.length > 0) {
      a.target = target;
    }
    for(var im=0; im<info[4].length; im++) {
      if (info[4][im] != null) {
        var img = document.createElement("img");
        a.appendChild(img);
        img.src = info[4][im][0];
        img.className = "icon";
        img.onmouseover = setTooltip;
        img.onmouseout = hideTooltip;
        img.toolTip = formatTooltip(info[4][im][1]);
      }
    }
    var span = document.createElement('SPAN');
    if (info[3] && info[3].length > 0) {
      span.onmouseover = setTooltip;
      span.onmouseout = hideTooltip;
      span.toolTip = formatTooltip(info[3]);
    }
    span.appendChild(document.createTextNode(info[2]));
    a.appendChild(span);
    return a;
  }
}
SearchHandler.prototype.enableScroll = function(el) {
  el.style.color = "#000000";
  el.style.cursor = "pointer";
  el.style.textDecoration = "underline";
}
SearchHandler.prototype.disableScroll = function(el) {
  el.style.color = "#bbbbbb";
  el.style.cursor = "default";
  el.style.textDecoration = "none";
}

SearchHandler.prototype.previousStyle = function() {
  var el = document.getElementById(this.idPrefix+'prev');
  if (this.current > 0) {
    this.previousEnabled = true;
    this.enableScroll(el);
  } else {
    this.previousEnabled = false;
    this.disableScroll(el);
  }
}
SearchHandler.prototype.nextStyle = function() {
  var el = document.getElementById(this.idPrefix+'next');
  if (this.current < this.linkSize-1) {
    this.nextEnabled = true;
    this.enableScroll(el);
  } else {
    this.nextEnabled = false;
    this.disableScroll(el);
  }
}
SearchHandler.prototype.linkStyle = function(ix) {
  var el = document.getElementById(this.linkPrefix+ix);
  if (this.left <= ix && ix <= this.right) {
    el.style.display = "inline";
    if (this.current == ix) {
      el.style.textDecoration = "none";
      el.style.fontWeight = "bold";
      el.style.cursor = "default";
    } else {
      el.style.textDecoration = "underline";
      el.style.fontWeight = "normal";
      el.style.cursor = "pointer";
    }
  } else {
    el.style.display = "none";
  }
}
SearchHandler.prototype.prev = function() {
  if (this.searchHandler.current > 0) {
    this.searchHandler.scroll(-1);
  }
}
SearchHandler.prototype.next = function() {
  if (this.searchHandler.current+1 < this.searchHandler.linkSize ) {
    this.searchHandler.scroll(1);
  }
}

SearchHandler.prototype.showIx = function(ix) {
  if (ix == this.current) {
     return false;
  }
  // Hide current group
  if (this.current >= 0) {
    document.getElementById(this.listPrefix+this.current).style.display = "none";
  }
  this.current = ix;
  // Show new group
  var el = document.getElementById(this.listPrefix+this.current);
  if (el) {
    el.style.display = "block";
  }

  for(var i=0; i < this.linkSize; i++) {
    this.linkStyle(i);
  }
  this.previousStyle();
  this.nextStyle();
  return true;
}

SearchHandler.prototype.show = function() {
  this.searchHandler.showIx(this.showIx);
}

SearchHandler.prototype.scroll = function(step) {

  var ix = this.current + step;
  this.setupMargin(ix);
  this.showIx(ix);
}
SearchHandler.prototype.setupMargin = function(ix) {
  if (ix < this.left) {
   this.left = ix;
   this.right = this.left + this.linkMax -1;
  } else if (ix > this.right) {
   this.right = ix;
   this.left = ix - this.linkMax + 1;
  }
}
