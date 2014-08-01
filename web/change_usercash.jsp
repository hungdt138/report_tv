<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
        <div style="float: left; background-color:white;">
            <jsp:include page="inc/menu.jsp"/>
        </div>
        <div style="float: left; border: 1px; width: 80%;">
            <div style="clear: both; float: left; margin-bottom: 60px;">
                <form action="changeusercash.html" method="post">
                    <table style="width: 500px; border: 1px; border-color: green; margin-left: 400px">
                        <input type="hidden" name="userId" value="${requestScope.ChangeUserCashForm.userId}"/>
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="type" value="${param.type}" />
                        <tr>
                            <td>UserId</td>
                            <td>
                                ${requestScope.ChangeUserCashForm.userId}
                            </td>
                        </tr>								
                        <tr>
                            <td>Số lượng</td>
                            <td>
                                <input type="cash" name="cash"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Lý do</td>
                            <td>
                                <textarea name="message"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td style="text-align: left;">
                                <input type="submit" value="Thay đổi"/>  &nbsp&nbsp
                                <input type="button" value="Thoát" onclick="backList(${requestScope.ChangeUserCashForm.userId});"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <br/>
            </div>
        </div>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>

<script type="text/javascript">
    function backList(userId) {
        window.location.href = "workingUserList.html?page=1&orderType=1&userId=" + userId;
    }
</script>
