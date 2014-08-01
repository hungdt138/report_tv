<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thống kê số người chơi online
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-lg-12">
            <form class="" method="get">
                <div class="form-group">
                    <span>Ngày</span>
                    <input class="form-control date-picker" type="text" name="day" value="${day}"/>

                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                </div>
            </form>
        </div>
    </div>

    <c:if test="${empty numsBy5Minutes}">
    Chưa có dữ liệu để thống kê, vui lòng quay lại vào ngày hôm sau.
    </c:if>

    <style>
        .form-control {
            display: inline-block;
            width: 120px;
        }
    </style>

    <c:if test="${not empty numsBy5Minutes}">

    <div class="row">
        <div class="col-lg-12">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">Theo khoảng 5 phút (24h gần nhất)</h3>
                </div>
                <div class="box-body">
                    <div id="chart_by_5_minutes" class="text-center" style="min-width: 128px;">
                        <img src="img/loading.gif"/>
                    </div>

                    <%--<script>--%>
                        <%--Morris.Line({--%>
                            <%--element: 'chart_by_5_minutes',--%>
                            <%--data: [--%>
                                <%--<c:forEach items="${numsBy5Minutes}" var="item">--%>
                                <%--{time: '<fmt:formatDate pattern="HH:mm dd/MM" value="${item.reportDate}" />', total: ${item.num}, tienlen: ${item.numTienLen}, phom: ${item.numPhom}, bacay: ${item.numBaCay}, baucua: ${item.numBauCua}, altp: ${item.numALTP}, pikachu: ${item.numPikachu}},--%>
                                <%--</c:forEach>--%>
                            <%--],--%>
                            <%--xkey: 'time',--%>
                            <%--parseTime: false,--%>
                            <%--ykeys: ['total', 'tienlen', 'phom', 'bacay', 'baucua', 'altp', 'pikachu'],--%>
                            <%--labels: ['Tổng số người online', 'Tiến lên', 'Phỏm', 'Ba cây', 'Bầu cua', 'Ai là triệu phú', 'Pikachu'],--%>
                            <%--parseTime: false,--%>
                            <%--hideHover: 'auto',--%>
<%--//                            lineColors: ['#FF0066', '#3366FF']--%>
                        <%--});--%>
                    <%--</script>--%>

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
                                ['', 'Tổng số người online',
//                                    'Tiến lên', 'Phỏm', 'Ba cây', 'Bầu cua', 'Ai là triệu phú', 'Pikachu'
                                ],
                                <c:forEach items="${numsBy5Minutes}" var="item">
                                ['<fmt:formatDate pattern="HH:mm dd/MM" value="${item.reportDate}" />', ${item.num},
                                <%--${item.numTienLen}, ${item.numPhom}, ${item.numBaCay}, ${item.numBauCua}, ${item.numALTP}, ${item.numPikachu}--%>
                                ],
                                </c:forEach>
                            ]);

                            // Create and draw the visualization.
                            var ac = new google.visualization.LineChart(document.getElementById('chart_by_5_minutes'));
                            ac.draw(data, {
                                height: 300,
                                is3D: true,
                                'chartArea': {'width': '90%', 'height': '80%'},
                                hAxis: { textPosition: 'none' },
                                legend: {position: 'bottom'},
                                smoothLine: true,
                                vAxis: {
                                    viewWindowMode:'explicit',
                                    viewWindow: {
                                        min:0
                                    }
                                }
                            });
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
    </c:if>


    <c:if test="${not empty numsByHour}">

    <div class="row">
        <div class="col-lg-12">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">Theo giờ (7 ngày gần nhất)</h3>
                </div>
                <div class="box-body">
                    <div id="chart_by_hour" class="text-center" style="min-width: 128px;">
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
                                ['', 'Trung bình', 'Cao nhất',
//                                    'Tiến lên', 'Phỏm', 'Ba cây', 'Bầu cua', 'Ai là triệu phú', 'Pikachu'
                                ],
                                <c:forEach var="i" begin="0" end="${fn:length(numsByHour) - 1}">
                                <c:set value="${numsByHour[i]}" var="item" />

                                ['<fmt:formatDate pattern="HH" value="${numsByHour[i].reportDate}" />h <fmt:formatDate pattern="dd/MM" value="${numsByHour[i].reportDate}" />', ${numsByHour[i].num}, ${numsMaxByHour[i].num},
                                <%--${item.numTienLen}, ${item.numPhom}, ${item.numBaCay}, ${item.numBauCua}, ${item.numALTP}, ${item.numPikachu}--%>
                                ],
                                </c:forEach>
                            ]);

                            // Create and draw the visualization.
                            var ac = new google.visualization.LineChart(document.getElementById('chart_by_hour'));
                            ac.draw(data, {
                                height: 300,
                                is3D: true,
                                'chartArea': {'width': '90%', 'height': '80%'},
                                hAxis: { textPosition: 'none' },
                                legend: {position: 'bottom'},
                                smoothLine: true,
                                vAxis: {
                                    viewWindowMode:'explicit',
                                    viewWindow: {
                                        min:0
                                    }
                                }
                            });
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
    </c:if>

    <c:if test="${not empty numsByDay}">

    <div class="row">
        <div class="col-lg-12">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">Theo ngày (30 ngày gần nhất)</h3>
                </div>
                <div class="box-body">
                    <div id="chart_by_day" class="text-center" style="min-width: 128px;">
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
                                ['', 'Trung bình', 'Cao nhất',
//                                    'Tiến lên', 'Phỏm', 'Ba cây', 'Bầu cua', 'Ai là triệu phú', 'Pikachu'
                                ],
                                <c:forEach var="i" begin="0" end="${fn:length(numsByDay) - 1}">
                                    <c:set value="${numsByDay[i]}" var="item" />

                                ['<fmt:formatDate pattern="dd/MM" value="${numsByDay[i].reportDate}" />', ${numsByDay[i].num}, ${numsMaxByDay[i].num},
                                <%--${item.numTienLen}, ${item.numPhom}, ${item.numBaCay}, ${item.numBauCua}, ${item.numALTP}, ${item.numPikachu}--%>
                                ],
                                </c:forEach>
                            ]);

                            // Create and draw the visualization.
                            var ac = new google.visualization.LineChart(document.getElementById('chart_by_day'));
                            ac.draw(data, {
                                height: 300,
                                is3D: true,
                                'chartArea': {'width': '90%', 'height': '80%'},
                                hAxis: { textPosition: 'none' },
                                legend: {position: 'bottom'},
                                smoothLine: true,
                                vAxis: {
                                    viewWindowMode:'explicit',
                                    viewWindow: {
                                        min:0
                                    }
                                }
                            });
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
    </c:if>
<%@include file="inc/new_footer.jsp" %>