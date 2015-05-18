function selectMenuItem(item) {

  var eles  = parent.frames['menuFrame'].document.getElementsByTagName("a");
  for ( var i = 0; i < eles.length; i++) {
	 if(eles[i].id != "" && (eles[i].className == 'menu-element' || (eles[i].className.indexOf('menu-element') != -1))){
		 removeClass(getMenuElement(eles[i].id), "menu-element-selected");
	 }
  }
  addClass(getMenuElement(item), "menu-element-selected");

}

function getMenuElement(id) {
  return parent.frames['menuFrame'].document.getElementById(id);
}