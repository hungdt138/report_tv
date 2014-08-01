<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<style>
    .form-control {
        display: inline-block;
        width: 120px;
    }

    input[type=submit] {
        position: relative;
        top: -2px;
    }
</style>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Nhật ký bàn chơi
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Id bàn chơi</span>
                <input type="text" name="matchId" class="form-control" value="${param.matchId}"/>

                <input type="submit" class="btn btn-primary" value="Xem"/>
            </div>
        </form>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <c:if test="${not empty log}">
                <textarea style="width: 100%; resize: none;" rows="15" readonly>${log}</textarea>
            </c:if>
            <c:if test="${empty log}">
                <c:if test="${not empty param.matchId}">
                    Không tìm thấy bàn chơi.
                </c:if>
            </c:if>
        </div>
    </div>
</section>

<%@include file="inc/new_footer.jsp" %>