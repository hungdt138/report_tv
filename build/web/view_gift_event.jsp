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
        Nhật ký quà tặng
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Tên người chơi</span>
                <input type="text" name="u" class="form-control" value="${username}"/>
                
                <span>Từ ngày</span>
                <input class="form-control date-picker" type="text" name="f" value="${fromDate}"/>
                <span class="">Đến ngày</span>
                <input class="form-control date-picker" type="text " name="t" value="${toDate}"/>
                 <span>Sự kiện</span>
                <select  name="ev" class="form-control">
                    <option value="0">-- Chọn sự kiện --</option>
                    <c:forEach items="${lstEvent}" var="e" step="1" begin="0">
                         
                        <c:if test="${eventId == e.id}">
                            <option value="${e.id}" selected="selected">${e.name}</option>
                        </c:if>
                        <c:if test="${eventId != e.id}">
                            <option value="${e.id}">${e.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
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
                            <th class="text-left">UserID</th>
                            <th class="text-left">Tên hiển thị</th>
                            <th class="text-right">Giá trị quà tặng</th>
                            <th class="text-left">Nội dung</th>
                            <th class="text-left">Thời gian nhận</th>
                            <th class="text-right">Số dư ban đầu</th>
                            <th class="text-right">Số dư cuối</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${lstSize != 0}">
                            <c:forEach items="${log}" var="l">
                                <tr style="text-align: center;">
                                    <td class="text-left">${l.userId}</td>
                                    <td class="text-left">${l.username}</td>
                                    <td class="text-right">${l.value}</td>
                                    <td class="text-left">${l.message}</td>
                                    <td class="text-left" title="<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${l.useDate}" />"><fmt:formatDate pattern="dd/MM/yyyy" value="${l.useDate}" /></td>
                                    <td class="text-right">${l.fromValue}</td>
                                    <td class="text-right">${l.toValue}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${lstSize == 0}">
                            <tr>
                                <td colspan="8" style="text-align: center;">Không có dữ liệu </td>
                            </tr>

                        </c:if>
                    </tbody>
                </table>

                <div class="text-right">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li><a href="?p=1&u=${username}&f=${fromDate}&t=${toDate}&ev=${eventId}">Trang đầu</a></li>
                            <li><a href="?p=${page - 1}&u=${username}&f=${fromDate}&t=${toDate}&ev=${eventId}">&laquo;</a></li>
                            </c:if>

                        <c:if test="${page <= 1}">
                            <li class="disabled"><span>Trang đầu</span></li>
                            <li class="disabled"><span>&laquo;</span></li>
                            </c:if>

                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                        <c:if test="${page < totalPage}">
                            <li><a href="?p=${page + 1}&u=${username}&f=${fromDate}&t=${toDate}&ev=${eventId}">&raquo;</a></li>
                            <li><a href="?p=${totalPage}&u=${username}&f=${fromDate}&t=${toDate}&ev=${eventId}">Trang cuối</a></li>
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
