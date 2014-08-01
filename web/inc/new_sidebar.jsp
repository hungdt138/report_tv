<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="menu-group">
    <legend>Quản lý người chơi</legend>
    <ul>
        <li><a href="workingUserList.html?page=1&orderType=1&menuItem=wuser1">Theo thông tin cơ bản</a></li>
        <li><a href="workingUserList.html?page=1&orderType=2&menuItem=wuser2">Theo ngày đăng ký</a></li>
        <li><a href="workingUserList.html?page=1&orderType=3&menuItem=wuser3">Theo lần đăng nhập cuối</a></li>
        <li><a href="workingUserList.html?page=1&orderType=4&menuItem=wuser4">Theo lần đăng nhập gần cuối</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Quản lý doanh thu</legend>
    <ul>
        <li><a href="reportbyday.html?action=prepareReport&menuItem=reportbyday1">Theo thời gian</a></li>
        <li><a href="reportbypartner.html?action=prepareReport&menuItem=reportbypartner">Theo partner</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Quản lý nội dung</legend>
    <ul>
        <li><a href="goldenhourList.html?page=1&menuItem=goldH">Khuyến mãi</a></li>
        <li><a href="advertisingList.html?page=1&menuItem=adv">Thông báo | Sự kiện</a></li>
        <li><a href="questionList.html?page=1&menuItem=altp">Câu hỏi Ai Là Triệu Phú</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Quản lý tài khoản</legend>
    <ul>
        <li><a href="listReportUser.html?page=1&menuItem=listacc">Danh sách</a></li>
        <li><a href="add_report_user.jsp?menuItem=addacc">Tạo mới</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Theo dõi</legend>
    <ul>
        <li><a href="monitor.jsp">Chỉ số hoạt động</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Quản lý tiền</legend>
    <ul>
        <li><a href="view_money_statistics.html">Luồng vào, ra</a></li>
    </ul>
</div>

<div class="menu-group">
    <legend>Quản lý nhật ký</legend>
    <ul>
        <li><a href="${pageContext.request.contextPath}/view_modify_money_log.html">Thay đổi bằng Tool</a></li>
        <li><a href="${pageContext.request.contextPath}/view_xeeng_charging_log.html">Nạp tiền</a></li>
    </ul>
</div>


<div class="menu-group">
    <legend>Khác</legend>
    <ul>
        <li><a id="register1" href="reportUserRegister.html?action=prepareReport&statusTag=register1">Danh sách người dùng theo ngày</a></li>
        <li><a id="superuser" href="superuserslist.html?action=prepareReport&page=1&mnItem=superuser">Danh sách siêu người dùng</a></li>
        <li><a id="reportdetailbyday1" href="reportChargingDetail.html?action=prepareReport&statusTag=reportdetailbyday1">Thống kê theo ngày</a></li>
        <li><a id="statbyday1" href="reportDailyStat.html?action=prepareReport&statusTag=statbyday1">Thống kê số liệu chung</a></li>
        <li><a id="giftL" href="listGiftCode.html?page=1&menuItem=giftL">Danh sách giftcode</a></li>
        <li><a id="addgift" href="giftcode_use.jsp?menuItem=addgift">Nạp giftcode</a></li>
    </ul>
</div>
<!-- menu end -->
