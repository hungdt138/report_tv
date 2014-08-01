<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:if test="${loggedInUser != null}">
    <c:if test="${loggedInUser.isAdmin}">
        <%@include file="new_menu_admin.jsp" %>
    </c:if>
    <c:if test="${loggedInUser.isOperator}">
        <%@include file="new_menu_operator.jsp" %>
    </c:if>
    <c:if test="${loggedInUser.isGameMaster}">
        <%@include file="new_menu_gamemaster.jsp" %>
    </c:if>
    <c:if test="${loggedInUser.isIA}">
        <%@include file="new_menu_ia.jsp" %>
    </c:if>
</c:if>