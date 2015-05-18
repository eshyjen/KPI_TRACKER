
function loginRequest() {
	var password = $("#password");	
	var separator = "\n";
	var session = null;
	$.ajax({
		url: "/tpim/ping.jsp", 
		async: false,
		success: function(data) {
			session = data;
		}
	});
	
	if (session != null && session.length > 0) {
		var op = "login";
		if (document.getElementById("confirm_password")) {
			op = "changePassword"
		}
		var obj = {
			"op":op,
			"password":password.val(),
			"sessionId":session
		};
		$("#jaas_password").val(encodeURIComponent(JSON.stringify(obj)));
		$("#jaas_username").val($("#username").val());
		$("#j_form").submit();
	}
}



var accountCheck = function() {
	var flag = true;
	var un = $("#username");
	var pwd = $("#password");
	

	if (un.val().length < 1) {
		flag = false;
		un.focus();
		return flag;
	}

	if (pwd.val().length < 1) {
		flag = false;
		pwd.focus();
	}
	
	if (document.getElementById("confirm_password")) {
		var pwd2 = $("#confirm_password");
		if (pwd2.val().length <  1 || pwd2.val() != pwd.val()) {
			$("#password_error").show();
			$("#password_reset_error").hide();
			flag = false;
			pwd2.focus();
		}
	}

	return flag;
}


var checkForm = function() {
	var flag = false;
	flag = accountCheck();
	if (flag) {
		loginRequest();
	}
	return false;
};
