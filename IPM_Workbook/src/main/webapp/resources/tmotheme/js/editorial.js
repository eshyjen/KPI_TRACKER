function addScreens() {
  addSelectedScreenItems('availableScreensSelect', 'selectedScreensSelect');
  return false;
}

function removeScreens() {
  removeSelectedScreenItems('availableScreensSelect', 'selectedScreensSelect');
  return false;
}
 
function addSelectedScreenItems(availableID, selectedID) {
  var availableField = document.getElementById(availableID);
  var selectedField = document.getElementById(selectedID);
  for (var i = availableField.options.length-1; i >= 0; i--) {
    if (availableField.options[i].selected) {
      var option=new Option(availableField.options[i].text, availableField.options[i].value);
      option.title=availableField.options[i].title;
      selectedField.options.add(option);
      availableField.remove(availableField.options[i].index);
    }
  }
  sortScreenOptions(selectedID);
  checkFormElement(selectedField);
}
 
function removeSelectedScreenItems(availableID, selectedID) {
  var availableField = document.getElementById(availableID);
  var selectedField = document.getElementById(selectedID);
  for (var i = selectedField.options.length-1; i >= 0; i--) {
    if (selectedField.options[i].selected) {
      var option=new Option(selectedField.options[i].text, selectedField.options[i].value);
      option.title=selectedField.options[i].title;
      availableField.options.add(option);
      selectedField.remove(selectedField.options[i].index);
    }
  }
  sortScreenOptions(availableID);
  checkFormElement(selectedField);
}
 
function sortScreenOptions(id) {
  var field = document.getElementById(id);
  var list = new Array(field.options.length);
  for (var i = field.options.length - 1; i >= 0; i--) {
    list[i] = {text: field.options[i].text, value: field.options[i].value,title:field.options[i].title};
    field.remove(field.options[i].index);
  }
  list.sort(comparator);
  for (var i = 0; i < list.length; i++) {
    var option=new Option(list[i].text, list[i].value);
	option.title=list[i].title;
    field.options.add(option);
  }
}
 
function comparator(a, b) {
  return a.value > b.value ? 1 : (a.value < b.value ? -1 : 0);
}
 
function screensSubmit() {
  buildString('selectedScreensSelect', 'selectedScreens');
}

function screensReset() {
  restoreScreenOptions(selectedScreensOrgOptions, 'selectedScreensSelect');
  restoreScreenOptions(availableScreensOrgOptions, 'availableScreensSelect');

  setTimeout('resetScreensEditButtons();', 1);
}

function resetScreensEditButtons() {
  button = document.getElementById('screensAddButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('screensRemoveButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }
}
 
function buildString(selectID, hiddenID) {
  var field = document.getElementById(selectID);
  if (field == null) {
    return;
  }
  var str = '';
  for (var i = 0; i < field.options.length; i++) {
    str += escape(field.options[i].value) + ';';
  }
  document.getElementById(hiddenID).value = str;
}
 
function initScreens() {
  resetScreensEditButtons();
  saveOrgScreenOptions(selectedScreensOrgOptions, 'selectedScreensSelect');
  saveOrgScreenOptions(availableScreensOrgOptions, 'availableScreensSelect');
  form=document.getElementById('editorialScreensForm');
  if (form != null) {
    form.onreset = screensFormOnReset;
  }
}

registerOnload(initScreens);

var selectedScreensOrgOptions = new Array();
var availableScreensOrgOptions = new Array();

function saveOrgScreenOptions(orgOptions, id) {
  var field = document.getElementById(id);
  if (field == null) {
    return;
  }
  for (var i = 0; i < field.options.length; i++) {
    orgOptions[i] = {text: field.options[i].text, value: field.options[i].value,title: field.options[i].title };
  }
}
 
function restoreScreenOptions(orgOptions, id) {
  var field = document.getElementById(id);
  if (field == null) {
    return;
  }
  for (var i = field.options.length-1; i >= 0; i--) {
    field.remove(i);
  }
  for (var i = 0; i < orgOptions.length; i++) {
	var option=new Option(orgOptions[i].text, orgOptions[i].value);
	option.title=orgOptions[i].title;
    field.options.add(option);
  }
}
 
function editScreens() {
  document.getElementById('availableScreensSelect').style.visibility = 'visible';
  document.getElementById('availableScreensLabel').style.visibility = 'visible';
  document.getElementById('screensRemoveButton').style.visibility = 'visible';
  document.getElementById('screensAddButton').style.visibility = 'visible';
  return false;
}

function screensFormOnReset() {
  clearModified();
  screensReset();
}
