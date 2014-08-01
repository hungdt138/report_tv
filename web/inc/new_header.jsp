<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="h" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="b" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="l" uri="/WEB-INF/struts-logic.tld" %>
<%@taglib prefix="date" uri="/WEB-INF/DisplayDate.tld" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="blah" uri="http://tinhvan.com/xeeng/report/blahblah" %>
<!DOCTYPE htmlfn
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>[Xeeng] Admin Tool</title>

    <!-- bootstrap 3.0.2 -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- font Awesome -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <!-- Ionicons -->
    <link href="css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <!-- Theme style -->
    <link href="css/AdminLTE.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="css/new_design.css">

    <!-- JavaScript -->
    <!-- jQuery 2.0.2 -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="js/plugins/bootstrap.min.js" type="text/javascript"></script>
    <!-- AdminLTE App -->
    <script src="js/plugins/AdminLTE/app.js" type="text/javascript"></script>

    <script src="js/tablesorter/jquery.tablesorter.js"></script>
    <script src="js/tablesorter/tables.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
</head>

<body class="skin-blue container">

<header class="header">
<a href="workingUserList.html?page=1&orderType=1&menuItem=wuser1" class="logo">
    <!-- Add the class icon to your logo image or logo icon to add the margining -->
    [Xeeng] Admin Tool
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
<!-- Sidebar toggle button-->
<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
</a>

    <div class="navbar-right">
        <ul class="nav navbar-nav">
            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i>
                    <span>${loggedInUser.username} <i class="caret"></i></span>
                </a>
                <ul class="dropdown-menu">
                    <!-- Menu Footer-->
                    <li class="user-footer">
                        <div class="pull-left">
                            <a href="account.html?action=prepare" class="btn btn-default btn-flat">Đổi mật khẩu</a>
                        </div>
                        <div class="pull-right">
                            <a href="logout.html" class="btn btn-default btn-flat">Đăng xuất</a>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</header>

<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <%@include file="new_menu.jsp" %>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Right side column. Contains the navbar and content of the page -->
    <aside class="right-side">