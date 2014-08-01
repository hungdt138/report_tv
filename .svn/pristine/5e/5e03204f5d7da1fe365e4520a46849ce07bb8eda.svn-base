<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê nạp tiền
    </h1>
</section>

<!-- Main content -->
<section class="content">

    <c:if test="${empty stat}">
    Chưa có dữ liệu để thống kê, vui lòng quay lại vào ngày hôm sau.
    </c:if>

    <c:if test="${not empty stat}">
    <style>
        .form-control {
            display: inline-block;
            width: 120px;
        }
    </style>

    <div class="row">
        <div class="col-lg-12">
            <form class="">
                <div class="form-group">
                    <span>Từ ngày</span>
                    <input class="form-control date-picker" type="text" name="dayStart" value="${dayStart}"/>
                    <span class="">Đến ngày</span>
                    <input class="form-control date-picker" type="text " name="dayEnd" value="${dayEnd}"/>

                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="box box-primary">
                <div class="box-body">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover tablesorter">
                            <thead>
                            <tr>
                                <th>Ngày <i class="fa fa-sort"></i></th>
                                <th class="text-right">Người chơi mới <i class="fa fa-sort"></i></th>
                                <th class="text-right">Người chơi hoạt động <i class="fa fa-sort"></i></th>
                                <th class="text-right">Người chơi quay lại <i class="fa fa-sort"></i></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${stat}" var="item">
                                <tr>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                    <td class="text-right"><fmt:formatNumber value="${item.newUsers}"/></td>
                                    <td class="text-right"><fmt:formatNumber value="${item.activeUsers}"/></td>
                                    <td class="text-right"><fmt:formatNumber value="${item.returnUsers}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ người chơi hoạt động</h3>
                </div>
                <div class="panel-body">
                    <div id="chart_active_users"></div>
                    <script>
                        Morris.Area({
                            element: 'chart_active_users',
                            data: [
                                <c:forEach items="${stat}" var="item">
                                { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${item.activeUsers}, b: ${item.returnUsers} },
                                </c:forEach>
                            ],
                            xkey: 'y',
                            parseTime: false,
                            ykeys: ['a', 'b'],
                            labels: ['Người chơi hoạt động', 'Người chơi trở lại']
                        });
                    </script>
                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ người chơi đăng ký mới</h3>
                </div>
                <div class="panel-body">
                    <div id="chart_new_users"></div>
                    <script>
                        Morris.Area({
                            // ID of the element in which to draw the chart.
                            element: 'chart_new_users',
                            // Chart data records -- each entry in this array corresponds to a point on
                            // the chart.
                            data: [
                                <c:forEach items="${stat}" var="item">
                                { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${item.newUsers} },
                                </c:forEach>
                            ],
                            xkey: 'y',
                            parseTime: false,
                            ykeys: ['a'],
                            labels: ['Người chơi mới']
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
    </c:if>
<%@include file="inc/new_footer.jsp" %>