/* $HeadURL: http://svn.msdp.sh.cn.ao.ericsson.se/dev/msdp/branches/msdp-60/dpc/tpim/web/tabs.js $ */
/* $Id: tabs.js 330795 2013-04-02 11:37:24Z emiazhu $ */
function selectTab2(tab) {
		
		var root = document.getElementById("tabsRoot");
		var tabs = GetElementsByClassName("tab", root, "td");

		for ( var i = 0; i < tabs.length; ++i) {
			removeClass(tabs[i], "active");
			document.getElementById(tabs[i].id + "-details").style.display = 'none';
		}
		document.getElementById(tab.id + "-details").style.display = 'block';
		addClass(tab, "active");
		
		if($.trim($(tab).text()) == 'Migrate short codes'){
			$('#shortCodeHiddButton').click();
		}
		if($.trim($(tab).text()) == 'Change agreement'){
			$('#agreementHiddButton').click();
		}
		if($.trim($(tab).text()) == 'Change service state'){
			$('#serviceHiddButton').click();
		}
		

//		var menuHeader = document.getElementById("menuHeader");
//		if (menuHeader != null)
//			menuHeader.innerHTML = tab.firstChild.innerHTML;
//		try {
//			resizeFrame();
//		} catch (e) {
//			if (console) {
//	    		console.log(e);
//	    	}
//		}
//	}
}

function loadFirstTab(){
	$('#shortCodeHiddButton').click();
}


function selectTab3(tab) {
    var doChange = true;
    if (top && top.modified) {
       doChange = confirm("Modifications not saved. Do you want to continue without saving?");
       CONSTANT_CONFIRM_STATUS = doChange;
       if (doChange) {
         // Reset the form itself, incase of main service form
         top.modified = null;
       }
    }
    if (doChange) {
        //Find the "tabsRoot" element
        var root = tab.parentNode;
        while(root) {
          if (root.id == "tabsRoot") {
            break;
          } else {
            root = root.parentNode;
          }
        }
        var tabs = GetElementsByClassName("tab", root, "li");

        for (var i = 0; i < tabs.length; ++i) {
            removeClass(tabs[i], "active");
            document.getElementById(tabs[i].id + "-details").style.display = 'none';
        }
        document.getElementById(tab.id + "-details").style.display = 'block';
        addClass(tab, "active");
        
        var menuHeader = document.getElementById("menuHeader");
        if(menuHeader != null)
        	menuHeader.innerHTML = tab.firstChild.innerHTML;
        
        resizeFrame();
    }
}

function selectTabWithReload(tab, selectParameter) {
    var doChange = true;
    if (top && top.modified) {
        doChange = confirm("Modifications not saved. Do you want to continue without saving?");
        if (doChange) {
            // Reset the form itself, incase of main service form
            top.modified = null;
        }
    }
    if (doChange) {
        window.location = window.location.protocol + '//' + window.location.host + window.location.pathname + '?' + selectParameter + '=' + tab.id;
    }
}

function selectTab(tab) {
    var doChange = true;
    if (top && top.modified) {
       doChange = confirm("Modifications not saved. Do you want to continue without saving?");
       if (doChange) {
         // Reset the form itself, incase of main service form
         top.modified = null;
       }
    }
    if (doChange) {
      var wtab = tab.previousSibling;
      while(wtab) {
          removeClass(wtab, "active");
          document.getElementById(wtab.id + "-details").style.display = 'none';
          wtab = wtab.previousSibling;
      }
      wtab = tab.nextSibling;
      while(wtab) {
          removeClass(wtab, "active");
          document.getElementById(wtab.id + "-details").style.display = 'none';
          wtab = wtab.nextSibling;
      }
      document.getElementById(tab.id + "-details").style.display = 'block';
      addClass(tab, "active");
      resizeFrame();
   }
}
