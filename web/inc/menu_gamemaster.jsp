<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!-- menu start -->
<li style="height: 235px">
    <a href="#" id="user1">Quản lý User</a>
    <a id="wuser1" href="workingUserList.html?page=1&orderType=1&menuItem=wuser1">Theo thông tin cơ bản</a>
    <a id="wuser2" href="workingUserList.html?page=1&orderType=2&menuItem=wuser2">Theo ngày đăng ký</a>
    <a id="wuser3" href="workingUserList.html?page=1&orderType=3&menuItem=wuser3">Theo lần đăng nhập cuối</a>
    <a id="wuser4" href="workingUserList.html?page=1&orderType=4&menuItem=wuser4">Theo lần đăng nhập gần cuối</a>
    <a href="view_user_statistics.html">Thống kê đăng ký</a>
    <a href="view_device_statistics.html">Thống kê thiết bị</a>
    <a href="view_user_online_statistics.html">Thống kê online</a>
</li>
<li style="height: 110px;">
    <a>Quản lí nội dung</a>
    <%--<a id="goldH" href="goldenhourList.html?page=1&menuItem=goldH">Nội dung khuyến mại</a>--%>
    <a id="adv" href="advertisingList.html?page=1&menuItem=adv">Thông báo/Sự kiện</a>
    <a id="altp" href="questionList.html?page=1&menuItem=altp">Câu hỏi ALTP</a>
</li>

<li style="height: 80px;">
    <a>Khác</a>
    <a href="view_match_log.html">Nhật ký bàn chơi</a>
    <a href="view_campaign_statistics.html">Thống kê quảng cáo</a>
</li>
<!-- menu end -->
