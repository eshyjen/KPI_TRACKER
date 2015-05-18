/*$Id: frameset.js 330795 2013-04-02 11:37:24Z emiazhu $ */
/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/frameset.js $ */
var topLabel;
function setTopLabel(txt) {
  if (txt != topLabel) {
    topLabel = txt;
    refreshTopLabel();
  }
}
function refreshTopLabel() {
  var f = frames.topFrame;
  if (f) {
    var el = f.document.getElementById('topLabel');
    if (el) {
      el.innerHTML = topLabel ? topLabel : '';
    }
  }
}
