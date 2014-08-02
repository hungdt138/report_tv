<%-- 
    Document   : view_item_event
    Created on : Aug 2, 2014, 9:20:24 AM
    Author     : hungdt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<style>
    .form-control {
        display: inline-block;
        width: 120px;
    }

    input[type=submit] {
        position: relative;
        top: -1px;
    }
</style>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Log item
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Tên đăng nhập</span>
                <input type="text" name="u" class="form-control" value="${username}"/>
                <span>Mã item</span>
                <input type="text" name="i" class="form-control" value="${itemCode}"/>
                <span>Từ ngày</span>
                <input class="form-control date-picker" type="text" name="f" value="${fromDate}"/>
                <span class="">Đến ngày</span>
                <input class="form-control date-picker" type="text " name="t" value="${toDate}"/>

                <input type="submit" class="btn btn-primary" value="Lọc"/>
            </div>
        </form>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>UserID</th>
                            <th>Tên đăng nhập</th>
                            <th class="text-right">Mã item</th>
                            <th class="text-right">Nội dung</th>
                            <th class="text-right">Thời gian nhận</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:if test="${lstSize != 0}">
                        <c:forEach items="${log}" var="l">
                            <tr style="text-align: center;">
                                <td>${l.userid}</td>
                                <td>${l.username}</td>
                                <td class="text-right">${l.itemCode}</td>
                                <td class="text-right">${l.message}</td>
                                <td class="text-right" title="<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${l.datetime}" />"><fmt:formatDate pattern="dd/MM/yyyy" value="${l.datetime}" /></td>
                            </tr>
                        </c:forEach>
                    </c:if>
<c:if test="${lstSize == 0}">
    <tr>
        <td colspan="5" style="text-align: center;">Không có dữ liệu </td>
    </tr>
                        
                    </c:if>
                    </tbody>
                </table>

                <div class="text-right">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li><a href="?p=1&u=${username}&i=${itemCode}&f=${fromDate}&t=${toDate}">Trang đầu</a></li>
                            <li><a href="?p=${page - 1}&u=${username}&i=${itemCode}&f=${fromDate}&t=${toDate}">&laquo;</a></li>
                            </c:if>

                        <c:if test="${page <= 1}">
                            <li class="disabled"><span>Trang đầu</span></li>
                            <li class="disabled"><span>&laquo;</span></li>
                            </c:if>

                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                        <c:if test="${page < totalPage}">
                            <li><a href="?p=${page + 1}&u=${username}&i=${itemCode}&f=${fromDate}&t=${toDate}">&raquo;</a></li>
                            <li><a href="?p=${totalPage}&u=${username}&i=${itemCode}&f=${fromDate}&t=${toDate}">Trang cuối</a></li>
                            </c:if>
                            <c:if test="${page >= totalPage}">
                            <li class="disabled"><span>&raquo;</span></li>
                            <li class="disabled"><span>Trang cuối</span></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="inc/new_footer.jsp" %>
