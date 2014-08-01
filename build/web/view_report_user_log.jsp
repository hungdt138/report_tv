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
        Nhật ký hoạt động của người quản lý
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Người thực hiện</span>
                <input type="text" name="username" class="form-control" value="${param.username}"/>
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
                        <th>Thời gian</th>
                        <th>Người thực hiện</th>
                        <th>Hoạt động</th>
                        <th>Thông tin</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${logs}" var="log">
                        <tr style="text-align: center;">
                            <td><fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.logDate}"/></td>
                            <td>${log.username}</td>
                            <td>${log.action}</td>
                            <td>${log.refId}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="text-right">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li><a href="?page=1&username=${param.username}">Trang đầu</a></li>
                            <li><a href="?page=${page - 1}&username=${param.username}">&laquo;</a></li>
                        </c:if>

                        <c:if test="${page <= 1}">
                            <li class="disabled"><span>Trang đầu</span></li>
                            <li class="disabled"><span>&laquo;</span></li>
                        </c:if>

                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                        <c:if test="${page < totalPage}">
                            <li><a href="?page=${page + 1}&username=${param.username}">&raquo;</a></li>
                            <li><a href="?page=${totalPage}&username=${param.username}">Trang cuối</a></li>
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