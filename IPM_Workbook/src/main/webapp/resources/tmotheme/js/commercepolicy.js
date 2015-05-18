/*
 * $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/commercepolicy.js $
 * $Id: commercepolicy.js 330795 2013-04-02 11:37:24Z emiazhu $
 *
 * Copyright (c) 2007 by Drutt Corporation, all rights reserved.
 *
 */
function addPremiumResource() {
  addSelectedItems('availablePremiumResourcesSelect', 'selectedPremiumResourcesSelect');
  return false;
}

function removePremiumResource() {
  removeSelectedItems('availablePremiumResourcesSelect', 'selectedPremiumResourcesSelect');
  return false;
}

function addPriceCode() {
  addSelectedItems('availablePriceCodesSelect', 'selectedPriceCodesSelect');
  return false;
}

function removePriceCode() {
  removeSelectedItems('availablePriceCodesSelect', 'selectedPriceCodesSelect');
  return false;
}

function addSelectedItems(availableID, selectedID) {
  var availableField = document.getElementById(availableID);
  var selectedField = document.getElementById(selectedID);
  for (var i = availableField.options.length-1; i >= 0; i--) {
    if (availableField.options[i].selected) {
      selectedField.options.add(new Option(availableField.options[i].text, availableField.options[i].value));
      availableField.remove(availableField.options[i].index);
    }
  }
  sortOptions(selectedID);
  checkFormElement(selectedField);
}

function removeSelectedItems(availableID, selectedID) {
  var availableField = document.getElementById(availableID);
  var selectedField = document.getElementById(selectedID);
  for (var i = selectedField.options.length-1; i >= 0; i--) {
    if (selectedField.options[i].selected) {
      availableField.options.add(new Option(selectedField.options[i].text, selectedField.options[i].value));
      selectedField.remove(selectedField.options[i].index);
    }
  }
  sortOptions(availableID);
  checkFormElement(selectedField);
}

function sortOptions(id) {
  var field = document.getElementById(id);
  var list = new Array(field.options.length);
  for (var i = field.options.length - 1; i >= 0; i--) {
    list[i] = {text: field.options[i].text, value: field.options[i].value};
    field.remove(field.options[i].index);
  }
  list.sort(comparator);
  for (var i = 0; i < list.length; i++) {
    field.options.add(new Option(list[i].text, list[i].value));
  }
}

function comparator(a, b) {
  return a.value > b.value ? 1 : (a.value < b.value ? -1 : 0);
}

function commercePolicySubmit() {
  buildString('selectedPremiumResourcesSelect', 'selectedPremiumResources');
  buildString('selectedPriceCodesSelect', 'selectedPriceCodes');
}

function commercePolicyReset() {
  restoreOptions(selectedPremiumResourcesOrgOptions, 'selectedPremiumResourcesSelect');
  restoreOptions(availablePremiumResourcesOrgOptions, 'availablePremiumResourcesSelect');
  restoreOptions(selectedPriceCodesOrgOptions, 'selectedPriceCodesSelect');
  restoreOptions(availablePriceCodesOrgOptions, 'availablePriceCodesSelect');
  setTimeout('resetEditButtons();', 1);
}

function resetEditButtons() {
  var button = document.getElementById('editPremiumResourcesButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('editPriceCodesButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('premiumResourcesAddButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('premiumResourcesRemoveButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('priceCodesAddButton');
  if (button != null) {
    button.disabled = false;
    removeClass(button, 'disabled');
  }

  button = document.getElementById('priceCodesRemoveButton');
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

function initCommercePolicy() {
  resetEditButtons();
  saveOrgOptions(selectedPremiumResourcesOrgOptions, 'selectedPremiumResourcesSelect');
  saveOrgOptions(availablePremiumResourcesOrgOptions, 'availablePremiumResourcesSelect');
  saveOrgOptions(selectedPriceCodesOrgOptions, 'selectedPriceCodesSelect');
  saveOrgOptions(availablePriceCodesOrgOptions, 'availablePriceCodesSelect');
  var form = document.getElementById('commercePolicyForm');
  if (form != null) {
    form.onreset = commercePolicyFormOnReset;
  }
}

registerOnload(initCommercePolicy);

var selectedPremiumResourcesOrgOptions = new Array();
var availablePremiumResourcesOrgOptions = new Array();
var selectedPriceCodesOrgOptions = new Array();
var availablePriceCodesOrgOptions = new Array();

function saveOrgOptions(orgOptions, id) {
  var field = document.getElementById(id);
  if (field == null) {
    return;
  }
  for (var i = 0; i < field.options.length; i++) {
    orgOptions[i] = {text: field.options[i].text, value: field.options[i].value};
  }
}

function restoreOptions(orgOptions, id) {
  var field = document.getElementById(id);
  if (field == null) {
    return;
  }
  for (var i = field.options.length-1; i >= 0; i--) {
    field.remove(i);
  }
  for (var i = 0; i < orgOptions.length; i++) {
    field.options.add(new Option(orgOptions[i].text, orgOptions[i].value));
  }
}

function editPremiumResources() {
  document.getElementById('availablePremiumResourcesSelect').style.visibility = 'visible';
  document.getElementById('availablePremiumResourcesLabel').style.visibility = 'visible';
  document.getElementById('premiumResourcesRemoveButton').style.visibility = 'visible';
  document.getElementById('premiumResourcesAddButton').style.visibility = 'visible';
  return false;
}

function editPriceCodes() {
  document.getElementById('availablePriceCodesSelect').style.visibility = 'visible';
  document.getElementById('availablePriceCodesLabel').style.visibility = 'visible';
  document.getElementById('priceCodesRemoveButton').style.visibility = 'visible';
  document.getElementById('priceCodesAddButton').style.visibility = 'visible';
  return false;
}

function commercePolicyFormOnReset() {
  clearModified();
  commercePolicyReset();
}

function onOfferSelectChange() {
  checkFormElement(document.getElementById('offerSelect'));
}