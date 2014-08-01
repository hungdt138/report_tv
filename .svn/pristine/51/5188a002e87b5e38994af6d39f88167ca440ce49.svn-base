<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê quảng cáo
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
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th style="width: 20%" class="text-left">Chiến dịch</th>
                                <c:forEach items="${stat[0].osCounts}" var="osCount">
                                    <th style="width: 20%" class="text-right">${osCount.osName}</th>
                                </c:forEach>
                                <th style="width: 20%" class="text-right">Tổng số</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${stat}" var="campaign">
                                <tr>
                                    <td>${campaign.campaignName}</td>
                                    <c:forEach items="${campaign.osCounts}" var="osCount">
                                        <td class="text-right">${osCount.osCount}</td>
                                    </c:forEach>
                                    <td class="text-right">${campaign.sumOfCount}</td>
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
        <c:forEach items="${stat}" var="campaign">

            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> ${campaign.campaignName}</h3>
                    </div>
                    <div class="panel-body">
                        <div id="chart_${campaign.campaignName}" class="text-center" style="min-width: 128px;">
                            <img src="img/loading.gif"/>
                        </div>

                        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
                        <script type="text/javascript">

                            // Load the Visualization API and the piechart package.
                            google.load('visualization', '1.0', {'packages': ['corechart']});

                            // Set a callback to run when the Google Visualization API is loaded.
                            google.setOnLoadCallback(drawChart);

                            // Callback that creates and populates a data table,
                            // instantiates the pie chart, passes in the data and
                            // draws it.
                            function drawChart() {

                                // Create the data table.
                                var data = new google.visualization.DataTable();
                                var data = google.visualization.arrayToDataTable([
                                    ['OS', 'Số lượng'],
                                    <c:forEach items="${campaign.osCounts}" var="osCount">
                                    ['${osCount.osName}', ${osCount.osCount}],
                                    </c:forEach>
                                ]);

                                // Create and draw the visualization.
                                var ac = new google.visualization.PieChart(document.getElementById('chart_${campaign.campaignName}'));
                                ac.draw(data, {
                                    height: 300,
                                    is3D: true,
                                    'chartArea': {'width': '90%', 'height': '80%'},
                                'legend': {'position': 'bottom'}
                                });
                            }
                        </script>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    </c:if>
<%@include file="inc/new_footer.jsp" %>