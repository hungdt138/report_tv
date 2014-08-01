<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp" %>

<c:if test="${!sessionScope.loggedInUser.isAdmin}">
    <% response.sendRedirect("404.html"); %>
</c:if>

<c:if test="${sessionScope.loggedInUser.isAdmin}">
    <tr style="width: 100%">
        <td align="center" style="border-color: green;">
            <div style="float: left; width: 200px; background-color: white;">
                <jsp:include page="inc/menu.jsp"/>
            </div>
            <div style="float: left; width: 600px; background-color: white; margin-left: 300px;">
                <html:form action="/addGiftCode.html" method="post">
                    <table>
                        <tr>
                            <td>Mệnh giá:</td>
                            <td><html:text property="value" value=""/></td>
                        </tr>
                        <tr>
                            <td>Số lượng:</td>
                            <td><html:text property="total" value=""/></td>
                        </tr>
                        <tr>
                            <td>Loại Giftcode (GC1, GC2...):</td>
                            <td><html:text property="type" value=""/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Thêm"/>&nbsp&nbsp&nbsp<input type="button"
                                                                                         onclick="backList();"
                                                                                         value="Thoát"/></td>
                        </tr>
                    </table>
                </html:form>
            </div>
        </td>
    </tr>
</c:if>
<%@include file="inc/coda.jsp" %>
<script type="text/javascript">
    function backList() {
        window.location.href = "listGiftCode.html?page=1&m=giftL";
    }
</script>

