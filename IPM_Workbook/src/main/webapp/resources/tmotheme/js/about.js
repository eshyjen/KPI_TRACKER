// Originally in Header.jsp, but extracted to an external file to fool HttpUnit

$(document).ready(function() {
	$('#aboutbutton').click(function(e) { // Button which will activate our modal
	   	$('#modal').reveal({ // The item which will be opened with reveal
		  	animation: 'fade',                   // fade, fadeAndPop, none
			animationspeed: 50,                       // how fast animtions are
			closeonbackgroundclick: true,              // if you click background will modal close?
			dismissmodalclass: 'close'    // the class of a button or element that will close an open modal
		});
	return false;
	});
});