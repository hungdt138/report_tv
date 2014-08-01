<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@include file="inc/new_header.jsp" %>

<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">

<script src="js/morris/raphael-min.js"></script>
<script src="js/morris/morris-0.4.3.min.js"></script>

<script>
    $(document).ready(function () {
        $('select[name="info"]').val('${param.info}');
        $('select[name="moneyType"]').val('${param.moneyType}');
    })
</script>

<style>
    .form-control {
        display: inline-block;
        width: 120px;
    }

    input[type=submit] {
        position: relative;
        top: -1px;
    }
</style>

<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>
        Nhật ký thay đổi tiền bằng Tool
    </h1>
</section>

<!-- Main content -->
<section class="content">
    <div class="filter-container">
        <form>
            <div class="form-group">
                <span>Tên đăng nhập</span>
                <input type="text" name="loginName" class="form-control" value="${param.loginName}"/>
                <input type="submit" class="btn btn-primary" value="Lọc"/>
            </div>
            <%--<div class="form-group float-left">--%>
            <%--<label>Lý do</label>--%>
            <%--<select name="reason">--%>
            <%--<option selected="selected" value="">Tất cả</option>--%>
            <%--<option value="0">ReportTool</option>--%>
            <%--<option value="1">Exchange</option>--%>
            <%--<option value="2">SMS</option>--%>
            <%--<option value="3">Card</option>--%>
            <%--</select>--%>
            <%--&lt;%&ndash;<input type="text" name="info" value="${param.info}" />&ndash;%&gt;--%>
            <%--</div>--%>
            <%--<div class="form-group float-left">--%>
            <%--<label>Loại tiền</label>--%>
            <%--<select name="moneyType">--%>
            <%--<option selected="selected" value="">Tất cả</option>--%>
            <%--<option value="0">Gold</option>--%>
            <%--<option value="1">Xeeng</option>--%>
            <%--</select>--%>
            <%--&lt;%&ndash;<input type="text" name="moneyType" value="${param.moneyType}" />&ndash;%&gt;--%>
            <%--</div>--%>
        </form>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>UserID</th>
                        <th>LoginName</th>
                        <th>Thay đổi</th>
                        <th>Số dư cuối</th>
                        <th>Loại tiền</th>
                        <th>Lý do</th>
                        <th>Ngày thực hiện</th>
                        <th>Người thực hiện</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${logs}" var="log">
                        <tr style="text-align: center;">
                            <td>${log.userId}</td>
                            <td>${log.username}</td>
                            <td class="align-right"><fmt:formatNumber type="number"
                                                                      value="${log.moneyDiff}"/></td>
                            <td class="align-right"><fmt:formatNumber type="number" value="${log.moneyAfter}"/></td>
                            <td><img
                                    src="${pageContext.request.contextPath}/res/img/${log.moneyType == 0 ? 'gold' : 'xeeng'}.png"/>
                            </td>
                            <td>${log.message}</td>
                            <td><fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${log.modifyDate}"/></td>
                            <td>${log.refName}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="text-right">
                    <ul class="pagination">
                        <c:if test="${page > 1}">
                            <li><a href="?page=1">Trang đầu</a></li>
                            <li><a href="?page=${page - 1}">&laquo;</a></li>
                        </c:if>

                        <c:if test="${page <= 1}">
                            <li class="disabled"><span>Trang đầu</span></li>
                            <li class="disabled"><span>&laquo;</span></li>
                        </c:if>

                        <li class="disabled"><span>${page} / ${totalPage}</span></li>

                        <c:if test="${page < totalPage}">
                            <li><a href="?page=${page + 1}">&raquo;</a></li>
                            <li><a href="?page=${totalPage}">Trang cuối</a></li>
                        </c:if>
                        <c:if test="${page >= totalPage}">
                            <li class="disabled"><span>&raquo;</span></li>
                            <li class="disabled"><span>Trang cuối</span></li>
                        </c:if>
                    </ul>
                </div>
            </div>

        </div>

        <%--<br/>--%>
        <%--<table style="width: 100% ; border: 1px; border-color: green; float: left;">--%>
        <%--<%--%>
        <%--int pageC = Integer.parseInt(request.getAttribute("page").toString());--%>
        <%--%>--%>
        <%--<tr>--%>
        <%--<td width="100%">--%>
        <%--<div class="paging" style="padding: 4px 4px 4px 0px">--%>
        <%--<div style="float: left">--%>
        <%--<a href="#" style="text-decoration: none;" onclick="getDataFirstPage();"> Trang--%>
        <%--đầu </a>--%>
        <%--<a href="#" style="text-decoration: none;  margin-left: 6px;"--%>
        <%--onclick="backPageData();"> « </a>--%>
        <%--</div>--%>
        <%--<div style="float: left; margin-left: 6px;">--%>
        <%--<div style="float: left; width: 50px;">--%>
        <%--<input id="pageIndex" size="2" type="text" style="text-align: center;"--%>
        <%--value="<% out.print(pageC);%>"/>--%>
        <%--</div>--%>
        <%--<div style="float: left; ">--%>
        <%--<img src="${pageContext.request.contextPath}/res/img/search.gif"--%>
        <%--style="cursor:hand;padding-top:2px ; cursor:pointer; color:#0000FF;"--%>
        <%--onclick="getDataPageIndex();"/>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div style="float: left;">--%>
        <%--<a href="#" style="text-decoration: none; margin-left: 6px"--%>
        <%--onclick="nextPageData();"> » </a>--%>
        <%--<a href="#" style="text-decoration: none; margin-left: 6px"--%>
        <%--onclick="getDataLastPage();" id="lastPage"> Trang cuối </a>--%>
        <%--<span style="text-decoration: none; margin-left: 6px">${totalRecord} bản ghi / ${totalPage} trang</span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--</table>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</section>

<%@include file="inc/new_footer.jsp" %>