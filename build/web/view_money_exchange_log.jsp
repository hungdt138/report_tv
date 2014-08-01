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
        Nhật ký mua Gold bằng Xeeng
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Tên đăng nhập</span>
                    <input type="text" name="loginName" class="form-control" value="${param.loginName}"/>
                    <span>Mệnh giá Xeeng</span>
                <input type="text" name="numOfXeeng" class="form-control" value="${param.numOfXeeng}"/>
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
                        <th class="text-right">Mệnh giá</th>
                        <th class="text-right">Số dư cuối</th>
                        <th class="text-right">Thời gian mua</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${logs}" var="log">
                        <tr style="text-align: center;">
                            <td>${log.userId}</td>
                            <td>${log.loginName}</td>
                            <td class="text-right">${blah:getHumanNumberString(-log.numOfXeeng)}</td>
                            <td class="text-right">${blah:getHumanNumberString(log.balance)}</td>
                            <td class="text-right" title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.exchangeDate}" />">
                                <fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.exchangeDate}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="text-right">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li><a href="?page=1">Trang đầu</a></li>
                            <li><a href="?page=${page - 1}">&laquo;</a></li>
                        </c:if>

                        <c:if test="${page <= 1}">
                            <li class="disabled"><span>Trang đầu</span></li>
                            <li class="disabled"><span>&laquo;</span></li>
                        </c:if>

                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                        <c:if test="${page < totalPage}">
                            <li><a href="?page=${page + 1}">&raquo;</a></li>
                            <li><a href="?page=${totalPage}">Trang cuối</a></li>
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