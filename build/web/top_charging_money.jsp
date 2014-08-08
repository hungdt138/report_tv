<%-- 
    Document   : top_charging_money
    Created on : Aug 7, 2014, 11:18:10 AM
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
        Top nạp tiền
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Top người chơi</span>
                <select  name="t" class="form-control">
                    <c:if test="${top == 10}">
                        <option value="10" selected="selected">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                    </c:if>
                    <c:if test="${top == 15}">
                        <option value="10" >10</option>
                        <option value="15" selected="selected">15</option>
                        <option value="20">20</option>
                    </c:if>
                    <c:if test="${top == 20}">
                        <option value="10" >10</option>
                        <option value="15" >15</option>
                        <option value="20" selected="selected">20</option>
                    </c:if>
                </select>

                <input type="submit" class="btn btn-primary" value="Lọc"/>
            </div>
        </form>
    </div>
    <div class="filter-container" style="margin-bottom: 10px;">
        <span style="font-size: 17px;">Top <b>${top}</b> người nạp tiền nhiều nhất.</span>

    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="text-left">STT</th>
                            <th class="text-left">UserID</th>
                            <th class="text-left">Tên hiển thị</th>
                            <th class="text-left">Tổng số tiền đã nạp</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${lstSize != 0}">
                            <c:forEach items="${lstTop}" var="l">
                                <tr style="text-align: center;">
                                    <td class="text-left">${l.id}</td>
                                    <td class="text-left">${l.userId}</td>
                                    <td class="text-left">${l.name}</td>
                                    <td class="text-left">${l.totalMoney} VND</td>
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
            </div>
        </div>
    </div>
</section>

<%@include file="inc/new_footer.jsp" %>
