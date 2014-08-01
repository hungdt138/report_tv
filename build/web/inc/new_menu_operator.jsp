<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- menu start -->
<ul class="sidebar-menu">
    <li class="active">
        <a href="index.jsp">
            <i class="fa fa-dashboard"></i> <span>Trang chủ</span>
        </a>
    </li>
    <li class="treeview">
        <a href="#">
            <i class="fa fa-gamepad"></i>
            <span>Quản lý người chơi</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="workingUserList.html?page=1&orderType=1&menuItem=wuser1"><i
                    class="fa fa-angle-double-right"></i> Theo thông tin cơ bản</a></li>
            <li><a href="workingUserList.html?page=1&orderType=2&menuItem=wuser2"><i
                    class="fa fa-angle-double-right"></i> Theo ngày đăng ký</a></li>
            <li><a href="workingUserList.html?page=1&orderType=3&menuItem=wuser3"><i
                    class="fa fa-angle-double-right"></i> Theo lần đăng nhập cuối</a></li>
            <li><a href="workingUserList.html?page=1&orderType=4&menuItem=wuser4"><i
                    class="fa fa-angle-double-right"></i> Theo lần đăng nhập gần cuối</a></li>
            <li><a href="view_user_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê đăng ký</a></li>
            <li><a href="view_device_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê thiết bị</a></li>
            <li><a href="view_user_online_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê online</a></li>
        </ul>
    </li>

    <li class="treeview">
        <a href="#">
            <i class="fa fa-dollar"></i>
            <span>Quản lý doanh thu</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="view_shop_statistics.html"><i class="fa fa-angle-double-right"></i> Cửa hàng</a></li>
            <li><a href="view_exchange_log.html"><i class="fa fa-angle-double-right"></i> Nhật ký mua Gold</a></li>
            <li><a href="view_charging_monthly_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê theo tháng</a></li>
            <li><a href="reportbyday.html?action=prepareReport&menuItem=reportbyday1"><i
                    class="fa fa-angle-double-right"></i> Theo thời gian</a></li>
            <li><a href="reportbypartner.html?action=prepareReport&menuItem=reportbypartner"><i
                    class="fa fa-angle-double-right"></i> Thống kê theo partner</a></li>
        </ul>
    </li>

    <li class="treeview">
        <a href="#">
            <i class="fa fa-edit"></i> <span>Quản lí nội dung</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="goldenhourList.html?page=1&menuItem=goldH"><i class="fa fa-angle-double-right"></i> Khuyến mãi</a>
            </li>
            <li><a href="advertisingList.html?page=1&menuItem=adv"><i class="fa fa-angle-double-right"></i> Thông báo/Sự
                kiện</a></li>
            <li><a href="questionList.html?page=1&menuItem=altp"><i class="fa fa-angle-double-right"></i> Câu hỏi
                ALTP</a></li>
        </ul>
    </li>


    <%--<li class="treeview">--%>
        <%--<a href="#">--%>
            <%--<i class="fa  fa-user"></i> <span>Quản lý tài khoản</span>--%>
            <%--<i class="fa fa-angle-left pull-right"></i>--%>
        <%--</a>--%>
        <%--<ul class="treeview-menu">--%>
            <%--<li><a href="listReportUser.html?page=1&menuItem=listac"><i class="fa fa-angle-double-right"></i> Danh sách</a>--%>
            <%--</li>--%>
            <%--<li><a href="add_report_user.jsp?menuItem=addacc"><i class="fa fa-angle-double-right"></i> Tạo mới</a></li>--%>
        <%--</ul>--%>
    <%--</li>--%>


    <li class="treeview">
        <a href="#">
            <i class="fa  fa-money"></i> <span>Quản lý tiền</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="view_money_statistics.html"><i class="fa fa-angle-double-right"></i> Luồng tiền</a></li>
            <li><a href="view_modify_money_log.html"><i class="fa fa-angle-double-right"></i> Thay đổi bằng Tool</a>
            </li>
            <li><a href="view_money_charging_log.html"><i class="fa fa-angle-double-right"></i> Nạp tiền</a></li>
        </ul>
    </li>

    <li class="treeview">
        <a href="#">
            <i class="fa fa-th"></i> <span>Khác</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="reportUserRegister.html?action=prepareReport&statusTag=register1"><i
                    class="fa fa-angle-double-right"></i> Danh sách đăng ký mới theo ngày</a></li>
            <li><a href="reportChargingDetail.html?action=prepareReport&statusTag=reportdetailbyday1"><i
                    class="fa fa-angle-double-right"></i> Thống kê theo ngày</a></li>
            <li><a href="reportDailyStat.html?action=prepareReport&statusTag=statbyday1"><i
                    class="fa fa-angle-double-right"></i> Thống kê số liệu chung</a></li>
            <%--<li><a href="giftcode_use.jsp?menuItem=addgift"><i class="fa fa-angle-double-right"></i> Nạp Gift Code</a></li>--%>
            <li><a href="listGiftCode.html?page=1&menuItem=giftL"><i class="fa fa-angle-double-right"></i> Danh sách
                Gift Code</a></li>
            <li><a href="view_match_log.html"><i class="fa fa-angle-double-right"></i> Nhật ký bàn chơi</a></li>
            <li><a href="view_campaign_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê quảng cáo</a></li>
        </ul>
    </li>
</ul>
<!-- menu end -->
