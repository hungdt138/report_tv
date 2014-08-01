$(document).ready(function() {
	
	var url = window.location.href;
	var actionName = url;
	var newString = new String();
	for (var i = 1; i <= actionName.length; i++) {
		if (actionName.charAt(actionName.length - i) !== "=") {
			newString += actionName.charAt(actionName.length - i);
		}
		else {
			break;
		}
	}
	var finalString = new String();
	for (var i = 1; i <= newString.length; i++) {
		finalString += newString.charAt(newString.length - i);
	}
	
	if (finalString === "statbyday1") {
	alert(finalString);
	$("#A1").css({"backgroundColor": "black", "color": "white"});
	$(document.getElementById("div2")).css({"backgroundColor": "black", "color": "white"});
	} else if (finalString === "register1") {
	alert(finalString);
	$(document.getElementById("A2")).css({"backgroundColor": "black", "color": "white"});
	} else if (finalString === "reportdetailbyday1") {
	alert(finalString);
	$(document.getElementById("A3")).css({"backgroundColor": "black", "color": "white"});
	} else if (finalString === "reportbyday1") {
	alert(finalString);
	$("#A4").css({"backgroundColor": "black", "color": "white"});
	} 
});

