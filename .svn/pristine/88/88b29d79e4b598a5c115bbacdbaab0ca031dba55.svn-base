<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê SMS, thẻ nạp theo tháng
    </h1>
</section>

<!-- Main content -->
<section class="content">

<c:if test="${empty smsCounts && empty cardCounts}">
Chưa có dữ liệu để thống kê, vui lòng quay lại vào ngày hôm sau.
</c:if>

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

<c:if test="${not empty smsCounts && not empty cardCounts}">
<div class="row">
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-aqua">
            <div class="inner">
                <h3>
                        ${numOfPayer}
                </h3>

                <p>
                    Người chơi nạp tiền
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-person-stalker"></i>
            </div>
                <%--<a href="#" class="small-box-footer">--%>
                <%--More info <i class="fa fa-arrow-circle-right"></i>--%>
                <%--</a>--%>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-green">
            <div class="inner">
                <h3>
                        ${numOfSMS}
                </h3>

                <p>
                    SMS
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-iphone"></i>
            </div>
                <%--<a href="#" class="small-box-footer">--%>
                <%--More info <i class="fa fa-arrow-circle-right"></i>--%>
                <%--</a>--%>
        </div>
    </div>
    <!-- ./col -->
    <div class="col-lg-4">
        <!-- small box -->
        <div class="small-box bg-yellow">
            <div class="inner">
                <h3>
                        ${numOfCard}
                </h3>

                <p>
                    Thẻ nạp
                </p>
            </div>
            <div class="icon">
                <i class="ion ion-card"></i>
            </div>
                <%--<a href="#" class="small-box-footer">--%>
                <%--More info <i class="fa fa-arrow-circle-right"></i>--%>
                <%--</a>--%>
        </div>
    </div>
    <!-- ./col -->
</div>
</c:if>

<c:if test="${not empty numOfPayerByDays}">
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng người chơi trả tiền theo ngày
                </h3>
            </div>
            <div class="panel-body">
                <div id="chart_payer_count" class="text-center" style="min-width: 128px;">
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
                            ['Ngày', 'Người chơi trả tiền', 'Người chơi trả tiền lần đầu'],
                            <c:forEach items="${numOfPayerByDays}" var="item">
                            ['<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/>', ${item.numOfPayer}, ${item.numOfNewPayer}],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.AreaChart(document.getElementById('chart_payer_count'));
                        chart.draw(data, {
                            height: 300,
                            'chartArea': {'width': '90%', 'height': '80%'},
                            hAxis: { textPosition: 'none' },
                            legend: {position: 'bottom'},
                            curveType: 'function',
//                            isStacked: true
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
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ doanh thu từ những người chơi trả tiền
                    lần đầu theo ngày</h3>
            </div>
            <div class="panel-body">
                <div id="chart_revenue_of_new_payer" class="text-center" style="min-width: 128px;">
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
                            ['Ngày', 'SMS', 'Card'],
                            <c:forEach items="${numOfPayerByDays}" var="item">
                            ['<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}"/>', ${item.revenueOfNewPayerBySMSDaily}, ${item.revenueOfNewPayerByCardDaily}],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.LineChart(document.getElementById('chart_revenue_of_new_payer'));
                        chart.draw(data, {
                            height: 300,
                            'chartArea': {'width': '80%', 'height': '80%'},
                            hAxis: { textPosition: 'none' },
                            legend: {position: 'bottom'},
                            smoothLine: true
//                            isStacked: true
                        });
                    }
                </script>
            </div>
        </div>
    </div>

</div>
</c:if>

<c:if test="${not empty smsCounts}">

<div class="row">
    <div class="col-lg-12">
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title">SMS</h3>
            </div>
            <div class="box-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <c:forEach items="${smsValues}" var="item">
                                <th class="text-right">
                                    <fmt:formatNumber value="${item}"/></th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <c:forEach items="${smsCounts}" var="item">
                                <td class="text-right"><fmt:formatNumber value="${item}"/></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

<c:if test="${not empty hasSMS}">
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng SMS theo mệnh giá</h3>
            </div>
            <div class="panel-body">
                <div id="chart_sms_count" class="text-center" style="min-width: 128px;">
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
                            ['Mệnh giá', 'Số lượng'],
                            <c:forEach var="i" begin="0" end="${fn:length(smsValues) - 1}">
                            ['<fmt:formatNumber value="${smsValues[i]}" />', ${smsCounts[i]}],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.PieChart(document.getElementById('chart_sms_count'));
                        chart.draw(data, {
//                                title: 'Biểu đồ số lượng SMS theo mệnh giá',
                            height: 400,
                            'chartArea': {'width': '90%', 'height': '80%'},
                        });
                    }
                </script>
            </div>
        </div>
    </div>

</div>
</c:if>
</c:if>

<c:if test="${not empty cardCounts}">
    <%-- Card { --%>
<div class="row">
    <div class="col-lg-12">
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title">Thẻ nạp</h3>
            </div>
            <div class="box-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <c:forEach items="${cardValues}" var="item">
                                <th class="text-right">
                                <fmt:formatNumber value="${item}"/></td></th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <c:forEach items="${cardCounts}" var="item">
                                <td class="text-right"><fmt:formatNumber value="${item}"/></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>

<c:if test="${not empty hasCard}">
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng thẻ nạp theo mệnh giá
                </h3>
            </div>
            <div class="panel-body">
                <div id="chart_card_count" class="text-center" style="min-width: 128px;">
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
                            ['Mệnh giá', 'Số lượng'],
                            <c:forEach var="i" begin="0" end="${fn:length(cardValues) - 1}">
                            ['<fmt:formatNumber value="${cardValues[i]}" />', ${cardCounts[i]}],
                            </c:forEach>
                        ]);

                        // Create and draw the visualization.
                        var chart = new google.visualization.PieChart(document.getElementById('chart_card_count'));
                        chart.draw(data, {
//                                title: 'Biểu đồ số lượng Thẻ nạp theo mệnh giá',
                            height: 400,
                            'chartArea': {'width': '90%', 'height': '80%'},
                        });
                    }
                </script>
            </div>
        </div>
    </div>

</div>
</c:if>
</c:if>
<%-- } Card --%>
<%@include file="inc/new_footer.jsp" %>