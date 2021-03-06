<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê số lượng thiết bị theo tháng
    </h1>
</section>

<!-- Main content -->
<section class="content">

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
                <span>Tháng</span>
                <input class="form-control date-picker" type="text" name="month" value="${monthStr}"/>
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </div>
        </form>
    </div>
</div>

<c:if test="${empty stat}">
Chưa có dữ liệu để thống kê, vui lòng quay lại vào ngày hôm sau.
</c:if>


<c:if test="${not empty stat}">


<c:if test="${not empty monthStat}">
<div class="row">
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-aqua">
            <div class="inner">
                <h3>
                        ${numOfAndroid}
                </h3>

                <p>
                    Android
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-social-android"></i>
            </div>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-teal">
            <div class="inner">
                <h3>
                        ${numOfIOS}
                </h3>

                <p>
                    iOS
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-social-apple"></i>
            </div>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-light-blue">
            <div class="inner">
                <h3>
                        ${numOfJava}
                </h3>

                <p>
                    Java
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-coffee"></i>
            </div>
        </div>
    </div>
    <!-- ./col -->
</div>
</c:if>

<div class="row">
    <div class="col-lg-12">
        <div class="box box-success">
            <div class="box-header">
                <h3 class="box-title">Số lượng thiết bị theo ngày</h3>
            </div>
            <div class="box-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover tablesorter">
                        <thead>
                        <tr>
                            <th style="width: 16%">Ngày <i class="fa fa-sort"></i></th>
                            <th style="width: 28%" class="text-right">Android <i class="fa fa-sort"></i></th>
                            <th style="width: 28%" class="text-right">iOS <i class="fa fa-sort"></i></th>
                            <th style="width: 28%" class="text-right">Java <i class="fa fa-sort"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${stat}" var="item">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.totalAndroid)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.totalIOS)}"/></td>
                                <td class="text-right"><fmt:formatNumber value="${(item.totalJava)}"/></td>
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
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng thiết bị theo ngày
                </h3>
            </div>
            <div class="panel-body">
                <div id="chart_device_count" class="text-center" style="min-width: 128px;">
                    <img src="img/loading.gif"/>
                </div>

                <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                <script type="text/javascript">

                    // Load the Visualization API and the piechart package.
                    google.load('visualization', '1.0', {'packages': ['corechart']});

                    // Set a callback to run when the Google Visualization API is loaded.
                    google.setOnLoadCallback(drawChart);

                    function drawChart() {

                        // Create the data table.
//                            var data = new google.visualization.DataTable();
                        var data = google.visualization.arrayToDataTable([
                            ['Ngày', 'Android', 'iOS', 'Java'],
                            <c:forEach items="${stat}" var="item">
                            ['<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/>', ${item.totalAndroid}, ${item.totalIOS}, ${item.totalJava}],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.AreaChart(document.getElementById('chart_device_count'));
                        chart.draw(data, {
                            height: 300,
                            'chartArea': {'width': '90%', 'height': '80%'},
                            hAxis: { textPosition: 'none' },
                            legend: {position: 'bottom'},
                            curveType: 'function',
                            isStacked: true
                        });
                    }
                </script>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng HĐH theo ngày
                </h3>
            </div>
            <div class="panel-body">
                <div id="chart_os_count" class="text-center" style="min-width: 128px;">
                    <img src="img/loading.gif"/>
                </div>

                <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                <script type="text/javascript">

                    // Load the Visualization API and the piechart package.
                    google.load('visualization', '1.0', {'packages': ['corechart']});

                    // Set a callback to run when the Google Visualization API is loaded.
                    google.setOnLoadCallback(drawChart);

                    function drawChart() {

                        // Create the data table.
//                            var data = new google.visualization.DataTable();
                        var data = google.visualization.arrayToDataTable([
                            ['Ngày',
                                <c:forEach items="${stat[0].deviceCounts}" var="device">
                                '${device.deviceName}',
                                </c:forEach>
                            ],
                            <c:forEach items="${stat}" var="item">
                            ['<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/>',
                                <c:forEach items="${item.deviceCounts}" var="device">
                                ${device.deviceCount},
                                </c:forEach>
                            ],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.AreaChart(document.getElementById('chart_os_count'));
                        chart.draw(data, {
                            height: 300,
                            'chartArea': {'width': '90%', 'height': '80%'},
                            hAxis: { textPosition: 'none' },
                            legend: {position: 'bottom'},
                            curveType: 'function',
                            isStacked: true
                        });
                    }
                </script>
            </div>
        </div>
    </div>

</div>
</c:if>
<%-- } Card --%>
<%@include file="inc/new_footer.jsp" %>