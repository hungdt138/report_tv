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
</ul>
<!-- menu end -->
