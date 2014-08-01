<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<c:if test="${not sessionScope.loggedInUser.isIA}">
    <% response.sendRedirect("workingUserList.html?page=1&orderType=1&menuItem=wuser1"); %>
</c:if>

<c:if test="${sessionScope.loggedInUser.isIA}">
    <% response.sendRedirect("view_shop_statistics.html"); %>
</c:if>