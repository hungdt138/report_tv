<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<style>
    .table > tbody > tr.no-top-border > td {
        border-top: none;
    }
</style>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Chỉnh sửa thông tin người chơi
        <small><a style="position: relative; top: -2px" class="label label-primary"
                  href="view_user_detail.html?userId=${user.userId}">Xem</a></small>
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <c:if test="${empty user}">
        <h4>Không tìm thấy người chơi!</h4>
    </c:if>

    <c:if test="${not empty user}">
        <div class="row">
            <div class="col-lg-6">
                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                            ${message}
                    </div>
                </c:if>

                <div class="table-responsive">
                    <form method="POST">
                        <table class="table">
                            <tbody>
                            <input type="hidden" name="userId" value="${user.userId}"/>
                            <tr class="no-top-border">
                                <td>Tên đăng nhập</td>
                                <td>${user.loginName}</td>
                            </tr>
                            <tr>
                                <td>Tên hiển thị</td>
                                <td><input type="text" name="name" class="form-control" value="${user.name}"/></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="email" class="form-control" value="${user.email}"/></td>
                            </tr>
                            <tr>
                                <td>Giới tính</td>
                                <td>
                                        <%--<div class="radio">--%>
                                    <label><input type="radio" name="sex"
                                                  value="1" ${user.sex == 1 ? 'checked="checked"' : ''} /> Nam</label>
                                    <label style="margin-left: 40px"><input type="radio" name="sex"
                                                                            value="0" ${user.sex == 0 ? 'checked="checked"' : ''} />
                                        Nữ</label>
                                        <%--</div>--%>
                                </td>
                            </tr>
                            <tr>
                                <td>Điện thoại</td>
                                <td><input type="text" name="phone" class="form-control" value="${user.phonenumber}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>CMND</td>
                                <td><input type="text" name="cmnd" class="form-control" value="${user.cmnd}"/></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td class="text-right"><input type="submit" class="btn btn-danger" value="Cập nhật"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
</section>
<%@include file="inc/new_footer.jsp" %>