<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê doanh thu cửa hàng
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
                                <th class="text-left">Ngày</th>
                                <c:forEach items="${shopItems}" var="item">
                                    <th class="text-right">Item #${item.id}</th>
                                </c:forEach>
                                <th class="text-right">Doanh thu</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${stat}" var="item">
                                <tr>
                                    <td>${item.reportDate}</td>
                                    <c:forEach begin="0" end="${fn:length(item.itemCounts) - 1}" var="i">
                                        <td class="text-right">${blah:getHumanNumberString(item.itemCounts[i])}</td>
                                    </c:forEach>
                                    <td class="text-right">${blah:getHumanNumberString(item.totalXeeng)}</td>
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
                    <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng Item bán được</h3>
                </div>
                <div class="panel-body">
                    <div id="chart_item_count" class="text-center" style="min-width: 128px;">
                        <img src="img/loading.gif" />
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
                                ['Month',
                                    <c:forEach items="${shopItems}" var="item">
                                    'Item #${item.id}',
                                    </c:forEach>
                                ],
                                <c:forEach items="${stat}" var="item">
                                ['${fn:substring(item.reportDate, 0, 5)}',
                                    <c:forEach begin="0" end="${fn:length(item.itemCounts) - 1}" var="i">
                                    ${item.itemCounts[i]},
                                    </c:forEach>
                                ],
                                </c:forEach>
                            ]);

                            // Create and draw the visualization.
                            var ac = new google.visualization.LineChart(document.getElementById('chart_item_count'));
                            ac.draw(data, {
//                                title: 'Monthly Coffee Production by Country',
                                isStacked: false,
//                                vAxis: {title: "Số lượng"},
                                hAxis: {
//                                    title: "Ngày",
//                                    textPosition: 'out',
//                                    allowContainerBoundaryTextCufoff: true,
                                    showTextEvery: <fmt:formatNumber value="${fn:length(stat) / 6 + 0.5}" maxFractionDigits="0"/>
                                },
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
            <%--<div class="col-lg-12">--%>
            <%--<div class="panel panel-primary">--%>
            <%--<div class="panel-heading">--%>
            <%--<h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ số lượng Item bán được</h3>--%>
            <%--</div>--%>
            <%--<div class="panel-body">--%>
            <%--<div id="chart_item_count"></div>--%>
            <%--<script>--%>
            <%--Morris.Area({--%>
            <%--element: 'chart_item_count',--%>
            <%--data: [--%>
            <%--<c:forEach items="${stat}" var="item">--%>
            <%--{ y: '<fmt:formatDate pattern="dd/MM/yyyy" value="${item.reportDate}" />', b: ${item.item2Count}, c: ${item.item3Count}, d: ${item.item4Count}, e: ${item.item5Count}, f: ${item.item6Count}, g: ${item.item7Count} },--%>
            <%--</c:forEach>--%>
            <%--],--%>
            <%--xkey: 'y',--%>
            <%--parseTime: false,--%>
            <%--ykeys: ['b', 'c', 'd', 'e', 'f', 'g'],--%>
            <%--labels: ['Gói 1M Gold', 'Gói 2M Gold', 'Gói 5M Gold', 'Gói 10M Gold', 'Gói 25M Gold', 'Gói 75M Gold']--%>
            <%--});--%>
            <%--</script>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

        <div class="col-lg-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Biểu đồ tổng doanh thu</h3>
                </div>
                <div class="panel-body">
                    <div id="chart_total_xeeng"></div>
                    <script>
                        Morris.Area({
                            // ID of the element in which to draw the chart.
                            element: 'chart_total_xeeng',
                            // Chart data records -- each entry in this array corresponds to a point on
                            // the chart.
                            data: [
                                <c:forEach items="${stat}" var="item">
                                { y: '${item.reportDate}', a: ${item.totalXeeng} },
                                </c:forEach>
                            ],
                            xkey: 'y',
                            parseTime: false,
                            ykeys: ['a'],
                            labels: ['Doanh thu']
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
    </c:if>
<%@include file="inc/new_footer.jsp" %>