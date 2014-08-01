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
            <i class="fa fa-edit"></i> <span>Quản lí nội dung</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="advertisingList.html?page=1&menuItem=adv"><i class="fa fa-angle-double-right"></i> Thông báo/Sự
                kiện</a></li>
            <li><a href="questionList.html?page=1&menuItem=altp"><i class="fa fa-angle-double-right"></i> Câu hỏi
                ALTP</a></li>
        </ul>
    </li>

    <li class="treeview">
        <a href="#">
            <i class="fa fa-th"></i> <span>Khác</span>
            <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
            <li><a href="view_match_log.html"><i class="fa fa-angle-double-right"></i> Nhật ký bàn chơi</a></li>
            <li><a href="view_campaign_statistics.html"><i class="fa fa-angle-double-right"></i> Thống kê quảng cáo</a></li>
        </ul>
    </li>
</ul>
<!-- menu end -->
