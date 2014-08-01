<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp" %>

<tr style="width: 100%">
    <td align="center" style="border-color: green;">
        <div style="float: left; width: 200px; background-color: white;">
            <jsp:include page="inc/menu.jsp"/>
        </div>
        <div
                style="float: left; width: 600px; background-color: white; margin-left: 300px;">
            <%
                if (request.getAttribute("addMS") != null) {
                    String addMSG = request.getAttribute("addMS").toString();
                    if (!addMSG.equals("")) {
                        out.println(addMSG);
                    }
                }
            %>
            <form action="addReportUser.html" method="post">
                <table style="">

                    <tr>
                        <td>Tên đăng nhập</td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Mật khẩu</td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td>Xác nhận mật khẩu</td>
                        <td><input type="password" name="passwordRetype"/></td>
                    </tr>

                    <%--<tr>--%>
                    <%--<td>PartnerId:</td>--%>
                    <%--<td><input type="text" name="partnerId" /></td>--%>

                    <%--</tr>--%>
                    <tr>
                        <td>Nhóm quyền</td>
                        <td>
                            <select name="roleId">
                                <option value="1" ${rpuBean.roleId == 1 ? "selected='selected'" : ""}>Admin</option>
                                <option value="2" ${rpuBean.roleId == 2 ? "selected='selected'" : ""}>Operator</option>
                                <option value="3" ${rpuBean.roleId == 3 ? "selected='selected'" : ""}>GM</option>
                                <option value="4" ${rpuBean.roleId == 4 ? "selected='selected'" : ""}>IA</option>
                            </select>
                            <%--<input type="text" name="userTypeId" value="${rpuBean.userTypeId}"/>--%>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="text-align: left;"><input type="submit" value="Thêm mới"/> || <input
                                type="button" onclick="backList();" value="Thoát"/></td>
                    </tr>

                </table>
            </form>
        </div>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>
<script type="text/javascript">
    function backList() {
        window.location.href = "listReportUser.html?page=1&menuItem=listacc";
    }
</script>

