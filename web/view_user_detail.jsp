<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>
<script src="js/jquery.battatech.excelexport.min.js"></script>


<style>
    .table > tbody > tr:first-child > td {
        /*border-top: none;*/
    }

    .form-control {
        /*width: auto;*/
        display: inline-block;
    }
</style>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Thông tin chi tiết người chơi
        <small><a style="position: relative; top: -2px" class="label label-primary"
                  href="edit_user_detail.html?userId=${user.userId}">Chỉnh sửa</a></small>
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <c:if test="${empty user}">
        <h4>Không tìm thấy người chơi!</h4>
    </c:if>

    <c:if test="${not empty user}">
        <div class="row">
            <div class="col-lg-6">
                <div class="box box-primary">
                    <div class="box-header">
                        <h3 class="box-title">Thông tin cá nhân</h3>
                    </div>
                    <div class="box-body">
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">
                                ${message}
                            </div>
                        </c:if>

                        <div class="row">
                            <div class="col-md-12 text-center" style="margin-bottom: 10px;">
                                <img onerror="this.src='${pageContext.request.contextPath}/img/user_avatar_default.jpg'" width="120px"
                                     class="img img-thumbnail" src="http://avatar.xeengvn.com/avatar/${user.userId}/avatar.jpg"/>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td>Tên đăng nhập</td>
                                        <td>${user.loginName}</td>
                                    </tr>
                                    <tr>
                                        <td>Tên hiển thị</td>
                                        <td>${user.name}</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>${user.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Gold</td>
                                        <td><fmt:formatNumber type="number" value="${user.cash}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Xeeng</td>
                                        <td><fmt:formatNumber type="number" value="${user.xeeng}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Giới tính</td>
                                        <td>${user.sex == 1 ? 'Nam' : 'Nữ'}</td>
                                    </tr>
                                    <tr>
                                        <td>Điện thoại</td>
                                        <td>${user.phonenumber}</td>
                                    </tr>
                                    <tr>
                                        <td>CMND</td>
                                        <td>${user.cmnd}</td>
                                    </tr>
                                    <tr>
                                        <td>Ngày đăng ký</td>
                                        <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${user.registerDate}"/>">
                                            <fmt:formatDate
                                                pattern="HH:mm:ss dd/MM/yyyy" value="${user.registerDate}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Lần đăng nhập cuối</td>
                                        <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${user.lastLongin}"/>">
                                            <fmt:formatDate
                                                pattern="HH:mm:ss dd/MM/yyyy" value="${user.lastLongin}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Lần đăng nhập gần cuối</td>
                                        <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${user.lastSecondLogin}"/>">
                                            <fmt:formatDate
                                                pattern="HH:mm:ss dd/MM/yyyy" value="${user.lastSecondLogin}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Thiết bị</td>
                                        <td>${user.mobileVersion}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="box box-danger">
                            <div class="box-header">
                                <h3 class="box-title">Khóa tài khoản</h3>
                            </div>
                            <div class="box-body">
                                <c:if test="${not user.locked}">
                                    <div>
                                        <form action="lock_user.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <div class="row">
                                                <form>
                                                    <div class="col-lg-8">
                                                        <select class="form-control" name="numOfDay">
                                                            <option value="1">1 ngày</option>
                                                            <option value="3">3 ngày</option>
                                                            <option value="7" selected="selected">7 ngày</option>
                                                            <option value="15">15 ngày</option>
                                                            <option value="30">30 ngày</option>
                                                            <option value="31337">Vĩnh viễn</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-lg-4">
                                                        <button type="submit" class="btn btn-danger form-control">Khóa
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>

                                <c:if test="${user.locked}">
                                    <div>
                                        <form action="unlock_user.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <p>
                                                Tài khoản này đang bị khóa đến <strong><fmt:formatDate
                                                        pattern="HH:mm dd/MM/yyyy"
                                                        value="${user.lockExpired}"/></strong>.
                                            </p>

                                            <div>
                                                <button type="submit" class="btn btn-danger form-control">Mở khóa
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="box box-warning">
                            <div class="box-header">
                                <h3 class="box-title">Khóa chat</h3>
                            </div>
                            <div class="box-body">
                                <c:if test="${not user.chatLocked}">
                                    <div>
                                        <form action="lock_chat_user.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <div class="row">

                                                <form>
                                                    <div class="col-lg-8">
                                                        <select class="form-control" name="numOfDay">
                                                            <option value="1">1 ngày</option>
                                                            <option value="3">3 ngày</option>
                                                            <option value="7" selected="selected">7 ngày</option>
                                                            <option value="15">15 ngày</option>
                                                            <option value="30">30 ngày</option>
                                                            <option value="31337">Vĩnh viễn</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-lg-4">
                                                        <button type="submit" class="btn btn-warning form-control">
                                                            Khóa
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>

                                <c:if test="${user.chatLocked}">
                                    <div>
                                        <form action="unlock_chat_user.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <p>
                                                Tài khoản này đang bị khóa chat đến <strong><fmt:formatDate
                                                        pattern="HH:mm dd/MM/yyyy"
                                                        value="${user.chatLockExpired}"/></strong>.
                                            </p>

                                            <div>
                                                <button type="submit" class="btn btn-warning form-control">Mở khóa
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="box box-warning">
                            <div class="box-header">
                                <h3 class="box-title">Khóa avatar</h3>
                            </div>
                            <div class="box-body">
                                <c:if test="${not user.avatarLocked}">
                                    <div>
                                        <form action="lock_user_avatar.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <div class="row">
                                                <form>
                                                    <div class="col-lg-12">
                                                        <button type="submit" class="btn btn-warning form-control">
                                                            Khóa
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>

                                <c:if test="${user.avatarLocked}">
                                    <div>
                                        <form action="unlock_user_avatar.html" method="POST">
                                            <input type="hidden" name="userId" value="${user.userId}"/>

                                            <p>
                                                Tài khoản này đang bị khóa avatar.
                                            </p>

                                            <div>
                                                <button type="submit" class="btn btn-warning form-control">Mở khóa
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="box box-success">
                            <div class="box-header">
                                <h3 class="box-title">Nhật ký nạp tiền</h3>
                            </div>
                            <div class="box-body">
                                <c:if test="${not empty logs}">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>Thay đổi</th>
                                                    <th>Số dư cuối</th>
                                                    <th>Ngày thực hiện</th>
                                                    <th>Số điện thoại</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${logs}" var="log">
                                                    <tr style="text-align: center;">
                                                        <td class="align-right"><fmt:formatNumber type="number"
                                                                          value="${log.moneyDiff}"/> <img
                                                                src="${pageContext.request.contextPath}/res/img/xeeng.png"/></td>
                                                        <td class="align-right"><fmt:formatNumber type="number"
                                                                          value="${log.moneyAfter}"/></td>
                                                        <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.modifyDate}" />">
                                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${log.modifyDate}"/></td>
                                                        <td>${log.refId}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                        <div class="text-right">
                                            <ul class="pagination">
                                                <c:if test="${page > 1}">
                                                    <li><a href="?userId=${param.userId}&page=1&pageGold=${param.pageGold}">Trang
                                                            đầu</a></li>
                                                    <li>
                                                        <a href="?userId=${param.userId}&page=${page - 1}&pageGold=${param.pageGold}">&laquo;</a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${page <= 1}">
                                                    <li class="disabled"><span>Trang đầu</span></li>
                                                    <li class="disabled"><span>&laquo;</span></li>
                                                    </c:if>

                                                <li class="disabled"><span>${page} / ${totalPage}</span></li>

                                                <c:if test="${page < totalPage}">
                                                    <li>
                                                        <a href="?userId=${param.userId}&page=${page + 1}&pageGold=${param.pageGold}">&raquo;</a>
                                                    </li>
                                                    <li>
                                                        <a href="?userId=${param.userId}&page=${totalPage}&pageGold=${param.pageGold}">Trang
                                                            cuối</a></li>
                                                        </c:if>
                                                        <c:if test="${page >= totalPage}">
                                                    <li class="disabled"><span>&raquo;</span></li>
                                                    <li class="disabled"><span>Trang cuối</span></li>
                                                    </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${empty logs}">
                                    <div>Người chơi này chưa từng nạp tiền vào hệ thống.</div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="box box-success">
                    <div class="box-header">
                        <h3 class="box-title">Nhật ký thay đổi Gold</h3>
                    </div>
                    <div class="box-body">
                        <c:if test="${not empty logsGold}">
                            <div>
                                <%--<button id="btnExportToExcel" class="btn btn-primary">Xuất ra file Excel</button>--%>

                                <script>
                                    $(document).ready(function () {
                                        $("#btnExportToExcel").click(function () {
                                            $("#tblGoldLog").btechco_excelexport({
                                                containerid: "tblGoldLog", datatype: $datatype.Table
                                            });
                                        });
                                    });
                                </script>
                            </div>
                            <div class="table-responsive">
                                <table id="tblGoldLog" class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Thời gian</th>
                                            <th>Thay đổi</th>
                                            <th>Số dư cuối</th>
                                            <th>Lý do</th>
                                            <th>Thông tin</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${logsGold}" var="log">
                                            <tr style="text-align: center;">
                                                <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.modifyDate}" />">
                                                    <fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.modifyDate}"/></td>
                                                <td class="align-right"><fmt:formatNumber type="number"
                                                                  value="${log.moneyDiff}"/></td>
                                                <td class="align-right"><fmt:formatNumber type="number"
                                                                  value="${log.moneyAfter}"/></td>
                                                <td>${log.reason}</td>
                                                <td>${log.message}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <div id="money_changes" class="text-center" style="min-width: 128px; margin-top: 20px">
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
                                        var dataTable = new google.visualization.DataTable();
                                        dataTable.addColumn('string', 'Thời gian');
                                        dataTable.addColumn('number', 'Số dư trong tài khoản');
                                        // A column for custom tooltip content
                                        dataTable.addColumn({type: 'string', role: 'tooltip'});
                                        dataTable.addRows([
                                    <c:forEach begin="0" end="${fn:length(logsGold) - 1}" var="i">
                                        <c:set var="log" value="${logsGold[fn:length(logsGold) - i - 1]}" />
                                            ['<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.modifyDate}" />', ${log.moneyAfter}, '<fmt:formatNumber type="number"
                                                                                                                                                           value="${log.moneyAfter}" /> (${log.moneyDiff >= 0 ? '+' : ''}<fmt:formatNumber type="number"
                                                                                                                                                           value="${log.moneyDiff}" /> - ${log.reason})'],
                                    </c:forEach>
                                        ]);

                                        // Create and draw the visualization.
                                        var ac = new google.visualization.LineChart(document.getElementById('money_changes'));
                                        ac.draw(dataTable, {
                                            height: 300,
                                            is3D: true,
                                            'chartArea': {'width': '80%', 'height': '80%'},
                                            hAxis: { textPosition: 'none' },
                                            legend: {position: 'none'}
                                        });
                                    }
                                </script>

                                <div class="text-right">
                                    <ul class="pagination">
                                        <c:if test="${pageGold > 1}">
                                            <li><a href="?userId=${param.userId}&page=${param.page}&pageGold=1">Trang đầu</a>
                                            </li>
                                            <li>
                                                <a href="?userId=${param.userId}&page=${param.page}&pageGold=${pageGold - 1}">&laquo;</a>
                                            </li>
                                        </c:if>

                                        <c:if test="${pageGold <= 1}">
                                            <li class="disabled"><span>Trang đầu</span></li>
                                            <li class="disabled"><span>&laquo;</span></li>
                                            </c:if>

                                        <li class="disabled"><span>${pageGold} / ${totalPageGold}</span></li>

                                        <c:if test="${pageGold < totalPageGold}">
                                            <li>
                                                <a href="?userId=${param.userId}&page=${param.page}&pageGold=${pageGold + 1}">&raquo;</a>
                                            </li>
                                            <li><a href="?userId=${param.userId}&page=${param.page}&pageGold=${totalPageGold}">Trang
                                                    cuối</a></li>
                                                </c:if>
                                                <c:if test="${pageGold >= totalPageGold}">
                                            <li class="disabled"><span>&raquo;</span></li>
                                            <li class="disabled"><span>Trang cuối</span></li>
                                            </c:if>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${empty logsGold}">
                            <div>Người chơi này chưa có biến động Gold, điều này là không bình thường.</div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</section>
<%@include file="inc/new_footer.jsp" %>