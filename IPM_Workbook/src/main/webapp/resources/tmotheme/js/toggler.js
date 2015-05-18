/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/toggler.js $ */
/* $Id: toggler.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* Toggles a toggable and changes the toggler's icon*/
function toggle() {
  if (this.togglee.style.display=="none") {
    this.togglee.style.display = "block";
    var expandedImg = getTogglerImageExpandedUrl(); //Get the right expandedImg
    this.togglee.im.src=expandedImg;
    toggleOn(this.togglee.id);
  } else {
    this.togglee.style.display = "none";
    var collapsedImg = getTogglerImageCollapsedUrl();//Get the right collapsedImg
    this.togglee.im.src=collapsedImg;
    //this.togglee.im.src="/tpim/i/collapsed.gif";
    toggleOff(this.togglee.id);
  }
}

/* Initialize togglers.
 * 1. Find all toggables.
 * 2. Find corresponding toggler.
 * 3. On toggler, insert icon with onclich that hanles toggling.
*/
function initTogglers() {
  var tog = GetElementsByClassName("toggle", document, "span");
  for (var i=0; i<tog.length; i++) {
      initToggler(tog[i]);
  }
}
function initToggler(togglee) {
    if (togglee) {
        var toggler = document.getElementById("toggle_"+togglee.id);
        if (toggler) {
            var e = document.createElement("img");
            if (isToggledVisible(togglee.id)) {
              var expandedImg = getTogglerImageExpandedUrl(); //Get the right expandedImg
              e.src=expandedImg;
              togglee.style.display = "block";
            } else {
              var collapsedImg = getTogglerImageCollapsedUrl(); //Get the right collapsedImg
              e.src=collapsedImg;
              togglee.style.display = "none";
            }
            e.alt = 'Expand / Coll';
            toggler.onclick=toggle;
            toggler.togglee = togglee;
            toggler.togglee.im = e;
            toggler.style.cursor="pointer";

            // Sometimes two expanded/collapsed images are displayed.
            // This prevents adding an image if it's already there.
            var imgAlreadyInserted = false;
            for (var i = 0; i < toggler.childNodes.length; i++) {
              if (toggler.childNodes[i].tagName == 'IMG' && toggler.childNodes[i].src == e.src) {
                imgAlreadyInserted = true;
                break;
              }
            }

            if (!imgAlreadyInserted)
              toggler.insertBefore(e, toggler.firstChild);
        }
    }
}

function getTogglerImageExpandedUrl() {
    // Check if you are on Overview-page. Then you should have an icon without whitespace around it.
    var bodyObj = document.getElementById("todoBody");
    var todoMediaObj = document.getElementById("toggle_cSRToDoMedia");
    var expandeImg = "/tpim/i/expanded.png";
    if (bodyObj != null || todoMediaObj != null) {
      expandeImg="/tpim/i/expanded_trim.png";
    }
    return expandeImg;
}

function getTogglerImageCollapsedUrl() {
    // Check if you are on Overview-page. Then you should have an icon without whitespace around it
    var bodyObj = document.getElementById("todoBody");
    var todoMediaObj = document.getElementById("toggle_cSRToDoMedia");
    var expandeImg = "/tpim/i/collapsed.png";
    if (bodyObj != null || todoMediaObj != null) {
      expandeImg="/tpim/i/collapsed_trim.png";
    }
    return expandeImg;
}


function toggleOn(id) {
  var cookie = getCookie("toggler");
  if (cookie==null) {
    return;
  }
  var result="";
  var toggled = cookie.split(":");
  for (var i=0; i< toggled.length; i++) {
    if (!(toggled[i]==id)) {
      result += ":" + toggled[i];
    }
  }
  setToggleCookie(result);
}
function toggleOff(id) {
  var cookie = getCookie("toggler");
  if (cookie==null) {
    setToggleCookie(id);
  } else {
    setToggleCookie(cookie + ":" + id);
  }
}

function setToggleCookie(value) {
  var expire = new Date((new Date()).getTime() + 365*24*60*60*1000);
  setCookie("toggler", value, expire, "/", null, false);
}

function isToggledVisible(id) {
  var cookie = getCookie("toggler");
  if (cookie==null) {
    cookie="";
  }
  var toggled = cookie.split(":");
  for (var i=0; i< toggled.length; i++) {
    if (toggled[i]==id) {
      return false;
    }
  }
  return true;
}

/**
 * Sets a Cookie with the given name and value.
 *
 * @param name       Name of the cookie
 * @param value      Value of the cookie
 * @param [expires]  Expiration date of the cookie (default: end of current session)
 * @param [path]     Path where the cookie is valid (default: path of calling document)
 * @param [domain]   Domain where the cookie is valid
 *                   (default: domain of calling document)
 * @param [secure]   Boolean value indicating if the cookie transmission requires a
 *                   secure transmission
 */
function setCookie(name, value, expires, path, domain, secure)
{
    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

/**
 * Gets the value of the specified cookie.
 *
 * @param name  Name of the desired cookie.
 *
 * @return a string containing value of specified cookie,
 *   or null if cookie does not exist.
 */
function getCookie(name)
{
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1)
    {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1)
    {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

// Register "setup code"
registerOnload(initTogglers);
