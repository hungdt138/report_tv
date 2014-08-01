<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="h" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="b" uri="/WEB-INF/struts-bean.tld"%>
<%@taglib prefix="l" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib prefix="date" uri="/WEB-INF/DisplayDate.tld"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="css/menu.css"></link>

<ul id="nav" style="background-color: white;">
	<c:if test="${sessionScope.loggedInUser != null}">
		<c:if test="${sessionScope.loggedInUser.isAdmin}">
			<%@include file="menu_admin.jsp" %>
		</c:if>
		<c:if test="${sessionScope.loggedInUser.isOperator}">
			<%@include file="menu_operator.jsp" %>
		</c:if>
		<c:if test="${sessionScope.loggedInUser.isGameMaster}">
	        <%@include file="menu_gamemaster.jsp" %>
		</c:if>
        <c:if test="${sessionScope.loggedInUser.isIA}">
            <%@include file="menu_ia.jsp" %>
        </c:if>
    </c:if>
</ul>

<script type="text/javascript">
	var url = window.location.href;
	var actionName = url;
	var newString = new String();
	for (var i = 1; i <= actionName.length; i++) {
		if (actionName.charAt(actionName.length - i) !== "=") {
			newString += actionName.charAt(actionName.length - i);
		} else {
			break;
		}
	}
	var finalString = new String();
	for (var i = 1; i <= newString.length; i++) {
		finalString += newString.charAt(newString.length - i);
	}

	if (finalString === "statbyday1") {
		/* 	alert("statbyday is oke, prepare change user tag's status!!!!"); */
		document.getElementById("statbyday1").style.color = "green";
	} else if (finalString === "register1") {
		/* alert("register1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("register1").style.color = "green";
	} else if (finalString === "reportdetailbyday1") {
		/* alert("reportdetailbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("reportdetailbyday1").style.color = "green";
	} else if (finalString === "reportbyday1") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("reportbyday1").style.color = "green";
	} else if (finalString === "reportbypartner") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("reportbypartner").style.color = "green";		
	} else if (finalString === "listacc") {
		document.getElementById("listacc").style.color = "green";
	} else if (finalString === "addacc") {
		document.getElementById("addacc").style.color = "green";
	} else if (finalString === "addgift") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("addgift").style.color = "green";
	} else if (finalString === "adv") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("adv").style.color = "green";
	} else if (finalString === "goldH") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("goldH").style.color = "green";
	} else if (finalString === "giftL") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("giftL").style.color = "green";
	} else if (finalString === "altp") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("altp").style.color = "green";
	} else if (finalString === "superuser") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("superuser").style.color = "green";
	} else if (finalString === "wuser4") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("wuser4").style.color = "green";
	} else if (finalString === "wuser1") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("wuser1").style.color = "green";
	} else if (finalString === "wuser2") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("wuser2").style.color = "green";
	} else if (finalString === "wuser3") {
		/* alert("reportbyday1 is oke, prepare change user tag's status!!!!"); */
		document.getElementById("wuser3").style.color = "green";
	}
	
</script>