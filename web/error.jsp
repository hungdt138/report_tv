<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<style>
    .table > tbody > tr:first-child > td {
        border-top: none;
    }
</style>

<legend>Có lỗi xảy ra</legend>
<p>${message}</p>
<%@include file="inc/new_footer.jsp" %>