<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="h" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="b" uri="/WEB-INF/struts-bean.tld"%>
<%@taglib prefix="l" uri="/WEB-INF/struts-logic.tld"%>
<%@taglib prefix="date" uri="/WEB-INF/DisplayDate.tld"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><b:message key="app.title" /></title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css"></link>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></link>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/new_style.css"></link>
        <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js" ></script> --%>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-min-js.js"></script>
        
    </head>
    <body>
        <table width="100%">
            <tr bgcolor="#E5E9F0">
                <td colspan="1" height="50px;">
                    <table width="100%">
                        <tr>
                            <td width=200px""><img alt="XEENG" src="${pageContext.request.contextPath}/res/img/logo.png"/></td>
                            <td align="center">
                                <strong><b:message key="app.heading" /></strong><br/>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="1" >
                    <table width="100%" border="0">
                        <c:if test="${sessionScope.loggedInUser != null}" >
                            <tr>                    
                                <td align="left">                        
                                    <b:message key="menu.wellcome" />
                                    <c:choose>
                                        <c:when test='${sessionScope.funcMenu == "account" }'>
                                            <a href="account.html?action=prepare"  class="selected">[<c:out value="${sessionScope.loggedInUser.username}" />]</a>|
                                        </c:when>
                                        <c:otherwise>
                                            <a href="account.html?action=prepare">[<c:out value="${sessionScope.loggedInUser.username}" />]</a>|
                                        </c:otherwise>
                                    </c:choose>

                                    <a href="logout.html">[<b:message key="menu.logout" />]</a>                        
                                </td>
                            </tr>
                        </c:if>                    
                    </table>        
                    <hr/>
                </td>
            </tr>        
