<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê luồng tiền
    </h1>
</section>

<!-- Main content -->
<section class="content">
<c:if test="${empty moneyStatistics}">
    Chưa có dữ liệu để thống kê, vui lòng quay lại vào ngày hôm sau.
</c:if>

<c:if test="${not empty moneyStatistics}">
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
        <div class="box box-success">
            <div class="box-header">
                <h3 class="box-title">Luồng Xeeng vào</h3>
            </div>
            <div class="box-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th>Ngày <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tool <i class="fa fa-sort"></i></th>
                            <th class="text-right">SMS <i class="fa fa-sort"></i></th>
                            <th class="text-right">Card <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tổng <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${moneyStatistics}" var="item">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.xeengInByTool)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.xeengInBySMS)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.xeengInByCard)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.xeengInTotal)}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="box box-success">
            <div class="box-header">
                <h3 class="box-title">Luồng Xeeng ra</h3>
            </div>
            <div class="box-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th>Ngày <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tool <i class="fa fa-sort"></i></th>
                            <th class="text-right">Mua Gold <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tổng <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${moneyStatistics}" var="item">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.xeengOutByTool)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.xeengOutByExchange)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.xeengOutTotal)}"/></td>
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
    <div class="col-lg-12">
        <div class="box box-warning">
            <div class="box-header">
                <h3 class="box-title">Luồng Gold vào</h3>
            </div>
            <div class="box-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th>Ngày <i class="fa fa-sort"></i></th>
                            <th class="text-right">Đăng ký <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tool <i class="fa fa-sort"></i></th>
                            <th class="text-right">Gift Code <i class="fa fa-sort"></i></th>
                            <th class="text-right">Mua <i class="fa fa-sort"></i></th>
                            <th class="text-right">Kết bạn <i class="fa fa-sort"></i></th>
                            <th class="text-right">Hỗ trợ <i class="fa fa-sort"></i></th>
                            <th class="text-right">Bù âm <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tổng <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${moneyStatistics}" var="item">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByRegister / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByTool / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByGiftCode / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByExchange / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByAddFriend / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByFreeGold / 1000)}"/>K
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInByWrongCalc)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.goldInTotal)}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="box box-warning">
            <div class="box-header">
                <h3 class="box-title">Luồng Gold ra</h3>
            </div>
            <div class="box-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th>Ngày <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tool <i class="fa fa-sort"></i></th>
                            <th class="text-right">Phế 5% <i class="fa fa-sort"></i></th>
                            <th class="text-right">Tổng <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${moneyStatistics}" var="item">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.goldOutByTool)}"/>
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.goldOutByGame)}"/>
                                </td>
                                <td class="text-right"><fmt:formatNumber value="${(-item.goldOutTotal)}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


    <c:if test="${fn:length(moneyStatistics) > 1}">
    <div class="col-lg-12">
        <div class="box box-warning">
            <div class="box-header">
                <h3 class="box-title">Sai lệch số Gold trong hệ thống</h3>
            </div>
            <div class="box-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th>Ngày <i class="fa fa-sort"></i></th>
                            <th class="text-right">Theo tính toán <i class="fa fa-sort"></i></th>
                            <th class="text-right">Thực tế <i class="fa fa-sort"></i></th>
                            <th class="text-right">Sai lệch <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="i" begin="1" end="${fn:length(moneyStatistics) - 1}">
                            <c:set var="itemPrev" value="${moneyStatistics[i - 1]}"/>
                            <c:set var="item" value="${moneyStatistics[i]}"/>
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${itemPrev.goldInWorkingUsers}" /> + <fmt:formatNumber value="${item.goldInTotal}" />
                                    - <fmt:formatNumber value="${-item.goldOutTotal}" />
                                    = <fmt:formatNumber value="${itemPrev.goldInWorkingUsers + item.goldInTotal + item.goldOutTotal}" /></td>
                                <td class="text-right"><fmt:formatNumber value="${item.goldInWorkingUsers}" /></td>
                                <td class="text-right"><fmt:formatNumber value="${item.goldInWorkingUsers - (itemPrev.goldInWorkingUsers + item.goldInTotal + item.goldOutTotal)}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</div>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ luồng Xeeng vào</h3>
            </div>
            <div class="panel-body">
                <div id="chart_xeeng_in"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_xeeng_in',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${item.xeengInByTool}, b: ${item.xeengInBySMS}, c: ${item.xeengInByCard} },
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['a', 'b', 'c'],
                        labels: ['Tool', 'SMS', 'Card'],
                        behaveLikeLine: false,
                    });
                </script>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ luồng Xeeng ra</h3>
            </div>
            <div class="panel-body">
                <div id="chart_xeeng_out"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_xeeng_out',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${-item.xeengOutByTool}, b: ${-item.xeengOutByExchange} },
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['a', 'b'],
                        labels: ['Tool', 'Mua Gold'],
                        behaveLikeLine: false,
                    });
                </script>
            </div>
        </div>
    </div>
</div>

<%--<div class="row">--%>
<%----%>
<%--</div>--%>


<%-- Gold --%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ luồng Gold vào</h3>
            </div>
            <div class="panel-body">
                <div id="chart_gold_in"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_gold_in',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', f: ${item.goldInByRegister}, a: ${item.goldInByTool}, b: ${item.goldInByGiftCode}, c: ${item.goldInByExchange}, d: ${item.goldInByAddFriend}, e: ${item.goldInByFreeGold} },
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['f', 'a', 'b', 'c', 'd', 'e'],
                        labels: ['Đăng ký', 'Tool', 'Gift code', 'Mua bằng Xeeng ', 'Kết bạn', 'Tặng miễn phí'],
                        behaveLikeLine: false,
                    });
                </script>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ luồng Gold ra</h3>
            </div>
            <div class="panel-body">
                <div id="chart_gold_out"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_gold_out',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${-item.goldOutByTool}, b: ${-item.goldOutByGame} },
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['a', 'b'],
                        labels: ['Tool', 'Phế 5%'],
                        behaveLikeLine: false,
                    });
                </script>
            </div>
        </div>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ lượng Xeeng chưa được sử dụng</h3>
            </div>
            <div class="panel-body">
                <div id="chart_xeeng_in_working_users"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_xeeng_in_working_users',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${item.xeengInWorkingUsers}},
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['a'],
                        labels: ['Xeeng']
                    });
                </script>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ lượng Gold chưa được sử dụng</h3>
            </div>
            <div class="panel-body">
                <div id="chart_gold_in_working_users"></div>
                <script>
                    Morris.Area({
                        // ID of the element in which to draw the chart.
                        element: 'chart_gold_in_working_users',
                        // Chart data records -- each entry in this array corresponds to a point on
                        // the chart.
                        data: [
                            <c:forEach items="${moneyStatistics}" var="item">
                            { y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', a: ${item.goldInWorkingUsers}},
                            </c:forEach>
                        ],
                        xkey: 'y',
                        parseTime: false,
                        ykeys: ['a'],
                        labels: ['Gold']
                    });
                </script>
            </div>
        </div>
    </div>
</div>
</c:if>
</section>
<%@include file="inc/new_footer.jsp" %>