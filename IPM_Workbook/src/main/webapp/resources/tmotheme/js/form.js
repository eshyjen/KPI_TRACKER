/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/form.js $ */
/* $Id: form.js 330795 2013-04-02 11:37:24Z emiazhu $ */

/**
 * This script script pop up a message if the user try to switch to another
 * tab before saving the result.
 * Instructions:
 * The form must have the class 'saveForm'
 * TODO Update this
 */

// Mark form as not modified
top.modified = null;

function updateModified() {
  if (this.type) {
    return checkFormElement(this);
  } else {
    return true;
  }
}

function checkFormElement(e) {
  var f = e.form;
  if (f.id === 'variant-form')
    return;
  top.modified = f;
  if (isFormModified(e)) {
      var list = f.elements;
      for (var i=0; i < list.length; i++) {
          var element = list[i];
          if (isSubmitElement(element) || isResetElement(element)) {
              enableElement(element);
          }
      }
  } else {
      clearModified();
  }
  return true;
}

function isFormModified(e) {
  var list = e.form.elements;
  for (var alli=0; alli < list.length; alli++) {
      var current = list[alli];
      if ((current.type.toLowerCase() == "radio" || current.type.toLowerCase() == "checkbox") &&
              current.checked != current.defaultChecked) {
          return true;
      } else if (current.options) {
          for (var oix = 0; oix < current.options.length; oix++) {
              if (current.options[oix].defaultSelected != current.options[oix].selected && 
                      current.options[oix].styleClass != "nochangeoption") {
                  return true;
              }
          }
      } else if (current.value != current.defaultValue) {
          //text, textarea and password
          return true;
      }
  }
  return false;
}

function clearModified() {
  if (top.modified) {
    var elist = top.modified.elements;
    top.modified = null;
    if (elist) {
      for (var eix=0; eix<elist.length; eix++) {
        if ((isSubmitElement(elist[eix]) || isResetElement(elist[eix])) 
              &&  elist[eix].className.match("alwaysEnabled") == null) {
            disableElement(elist[eix]);
        }
      }
    }
  }
  return true;
}

function registerForm(form) {
  var elist = form.elements;
  for (var eix=0; eix<elist.length; eix++) {
      if ((isSubmitElement(elist[eix]) || isResetElement(elist[eix]))
             &&  elist[eix].className.match("alwaysEnabled") == null) {
          disableElement(elist[eix]);
      } else if (elist[eix].type &&
                 ((elist[eix].type == "radio") || (elist[eix].type == "checkbox"))) {
          if (!elist[eix].onclick) {
              elist[eix].onclick = updateModified;
          }
      } else {
          if (!elist[eix].onkeyup && elist[eix].type != "select-multiple") {
              elist[eix].onkeyup = updateModified;
          }
          if (!elist[eix].onchange) {
              elist[eix].onchange = updateModified;
          }
      }
  }
  form.onreset = clearModified;
  form.onsubmit = function () { top.modified = null; return true; };
}

function registerWizardForm(form) {
  for (var i = 0; i < form.elements.length; i++) {
    if (form.elements[i].type && ((form.elements[i].type == "radio") || (form.elements[i].type == "checkbox"))) {
      if (!form.elements[i].onclick) {
        form.elements[i].onclick = updateModified;
      }
    } else {
      if (!form.elements[i].onkeyup) {
        form.elements[i].onkeyup = updateModified;
      }
      if (!form.elements[i].onchange) {
        form.elements[i].onchange = updateModified;
      }
    }
  }
}

function preventExit() {
  var siblings = new Array();
  getFrameSiblings(window.top.frames, siblings, document);
  for(var i=0; i<siblings.length; i++) {
    var anchors = siblings[i].document.getElementsByTagName('a');
    for (var aix=0; aix < anchors.length; aix++) {
      if (anchors[aix].target && anchors[aix].target != "_blank") {
        anchors[aix].onclick = confirmExit;
      }
    }
  }
}

function doButtonSubmit(button, extra) {
    var url = button.form.action + '?' + button.name + '=' + button.value;
    if (extra) {
        url+= '&' + extra;
    }
    window.location.href = url;
}

function getFrameSiblings(frames, siblings, doc) {
  for(var i=0; i<frames.length; i++) {
    var f = frames[i];
    try{
	    if (f.document != doc) {
	      siblings[siblings.length] = frames[i];
	    }
    }catch(err){
    }
    
    getFrameSiblings(f.frames, siblings, doc);
  }
}

function initForm() {
  var saveForms = GetElementsByClassName("saveform", document, "form");
  for (var i=0; i<saveForms.length; i++) {
    registerForm(saveForms[i]);
  }
  var wizardForms = GetElementsByClassName("wizardform", document, "form");
  for (var i = 0; i < wizardForms.length; i++) {
    registerWizardForm(wizardForms[i]);
  }
  preventExit();
}
// Register "setup code"
registerOnload(initForm);
