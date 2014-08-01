<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@include file="inc/prelude.jsp" %>

<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<script>
    var invalidFromDate = '<b:message key="report.invalid.fromdate" />';
    var invalidToDate = '<b:message key="report.invalid.fromdate" />';
    var invalidCompareDate = '<b:message key="report.invalid.comparedate" />';
    var invalidCompareDateTotal = '<b:message key="report.invalid.comparedatetotal" />';

    //remove white from left and right of string
    function trim(stringToTrim) {
        return stringToTrim.replace(/^\s+|\s+$/g, "");
    }
    function validateNumber(objID, intTotalFrom, intTotalTo) {
        var obj = document.getElementById(objID);
        obj.value = trim(obj.value);
        if (obj.value.length == 0) {
            return true;
        }
        if (obj.value.length < intTotalFrom || obj.value.length > intTotalTo) {
            return false;
        }
        return /^\d+$/.test(obj.value);
    }

    function compareDate(fromDate, toDate, totalAdd) {
        // fromDate = fromDate.trim();
        // Fomat: dd/MM/yyyy	
        var pos1 = fromDate.indexOf('/');
        if (pos1 < 0) {
            return false;
        }
        var pos2 = fromDate.indexOf('/', pos1 + 1);
        if (pos2 < 0) {
            return false;
        }
        var pos3 = fromDate.indexOf('/', pos2 + 1);
        if (pos3 > 0) {
            return false;
        }

        var dd = fromDate.substring(0, pos1);
        var mm = fromDate.substring(pos1 + 1, pos2);
        var yy = fromDate.substring(pos2 + 1);


        if (dd.length == 1)
            dd = '0' + dd;
        if (mm.length == 1)
            mm = '0' + mm;
        var date1 = mm + "/" + dd + "/" + yy;

        //toDate = toDate.trim();
        // Fomat: dd/MM/yyyy	
        pos1 = toDate.indexOf('/');
        if (pos1 < 0) {
            return false;
        }
        pos2 = toDate.indexOf('/', pos1 + 1);
        if (pos2 < 0) {
            return false;
        }
        pos3 = toDate.indexOf('/', pos2 + 1);
        if (pos3 > 0) {
            return false;
        }

        var dd2 = toDate.substring(0, pos1);
        var mm2 = toDate.substring(pos1 + 1, pos2);
        var yy2 = toDate.substring(pos2 + 1);


        if (dd2.length == 1)
            dd2 = '0' + dd2;
        if (mm2.length == 1)
            mm2 = '0' + mm2;
        var date2 = mm2 + "/" + dd2 + "/" + yy2;

        var from = new Date(date1);
        var to = new Date(date2);
        from.setDate(from.getDate() + totalAdd);
        if (from < to) {
            return false;
        }
        return true;
    }

    function doReport() {
        var fromDate = document.getElementById('fromDate').value;
        if (!validate_DateReport(fromDate)) {
            alert(invalidFromDate);
            return false;
        }
        var toDate = document.getElementById('toDate').value;
        if (!validate_DateReport(toDate)) {
            alert(invalidToDate);
            return false;
        }
        if (!validate_compareDate(fromDate, toDate)) {
            alert(invalidCompareDate);
            return false;
        }
        if (!compareDate(fromDate, toDate, 30)) {
            alert(invalidCompareDateTotal);
            return false;
        }
        var obj = document.getElementById('form');
        obj.submit();
        return true;
    }
</script>

<tr style="width:100%">
<td align="center" style="border-color: green;">
<div style="float: left; background-color:white;">
    <jsp:include page="inc/menu.jsp"/>
</div>
<div style="float: left; width: 80%; margin-left: 30px; padding-left:5px; text-align: center;">
<div style="clear: both; float: left; margin-bottom: 5px; width: 100%">
    <form action="workingUserList.html" method="post" style="float: left;" name="form" id="form">
        <input type="hidden" name="orderType" id="orderType" value="${requestScope.orderType}">
        <table>
            <c:if test="${requestScope.orderType == 1}">
                <tr>
                    <td nowrap width="150px" style="background-color: #E5E9F0">Mã người dùng:</td>
                    <td width="20px"><input type="text" name="userId" id="userId"
                                            value="${requestScope.userId}"/></td>
                    <td nowrap width="150px" style="background-color: #E5E9F0">LoginName:</td>
                    <td width="100px"><input type="text" name="name" id="name"
                                             value="${requestScope.name}"/></td>
                    <td nowrap width="150px" style="background-color: #E5E9F0">NickName:</td>
                    <td width="100px"><input type="text" name="nickName" id="nickName"
                                             value="${requestScope.nickName}"/></td>
                </tr>
                <tr>
                    <%--<c:if test="${sessionScope.loggedInUser.getPartnerId() <= 0}">--%>
                        <%--<td nowrap width="150px" style="background-color: #E5E9F0">PartnerID:</td>--%>
                        <%--<td width="20px"><input type="text" name="partnerId" id="partnerId"--%>
                                                <%--value="${requestScope.partnerId}"/></td>--%>
                    <%--</c:if>--%>
                    <td nowrap width="150px" style="background-color: #E5E9F0">Refcode:</td>
                    <td width="20px"><input type="text" name="refCode" id="refCode"
                                            value="${requestScope.refCode}"/></td>

                    <td nowrap width="150px" style="background-color: #E5E9F0">
                        Partner
                    </td>
                    <td width="20px">
                        <select id="partnerId" name="partnerId" style="width: 100%; padding: 3px">
                            <option value="0">Tất cả</option>
                            <c:forEach items="${partners}" var="partner">
                                <option value="${partner.id}">${partner.name}</option>
                            </c:forEach>
                        </select>

                        <script>
                            $(document).ready(function () {
                                $('select[name="partnerId"]').val(${partnerId});
                            })
                        </script>
                    </td>


                    <td width="10px"><input type="submit" value="Tìm kiếm"/></td>
                </tr>
            </c:if>

            <c:if test="${requestScope.orderType == 2}">
                <td align="center" colspan="4">
                    Registration Date :
                    <b:message key="report.gereral.fromdate"/>
                    <h:text styleId="fromDate" maxlength="16" property="fromDate" readonly="true"
                            value='${requestScope.fromDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('fromDate'), 'dd/mm/yyyy', this, 0, '')"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <b:message key="report.gereral.todate"/>
                    <h:text styleId="toDate" maxlength="16" property="toDate" readonly="true"
                            value='${requestScope.toDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('toDate'), 'dd/mm/yyyy', this, 0, '')"/>
                                <span style="margin:0 3px 0 30px;">
                                Partner
                                <select id="partnerId" name="partnerId">
                                    <option value="0">Tất cả</option>
                                    <c:forEach items="${partners}" var="partner">
                                        <option value="${partner.id}">${partner.name}</option>
                                    </c:forEach>
                                </select>

                                    <script>
                                        $(document).ready(function () {
                                            $('select[name="partnerId"]').val(${partnerId});
                                        })
                                    </script>
                                </span>

                                <span style="margin:0 3px 0 30px;">
                                    <input type="button" id="btnGo" value="Tìm kiếm"
                                           onclick="return doReport();"></input>
                                </span>
                </td>
            </c:if>

            <c:if test="${requestScope.orderType == 3}">
                <td align="center" colspan="4">
                    Last login :
                    <b:message key="report.gereral.fromdate"/>
                    <h:text styleId="fromDate" maxlength="16" property="fromDate" readonly="true"
                            value='${requestScope.fromDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('fromDate'), 'dd/mm/yyyy', this, 0, '')"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <b:message key="report.gereral.todate"/>
                    <h:text styleId="toDate" maxlength="16" property="toDate" readonly="true"
                            value='${requestScope.toDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('toDate'), 'dd/mm/yyyy', this, 0, '')"/>

                    <span style="margin:0 3px 0 30px;">
                                Partner
                                <select id="partnerId" name="partnerId">
                                    <option value="0">Tất cả</option>
                                    <c:forEach items="${partners}" var="partner">
                                        <option value="${partner.id}">${partner.name}</option>
                                    </c:forEach>
                                </select>

                                    <script>
                                        $(document).ready(function () {
                                            $('select[name="partnerId"]').val(${partnerId});
                                        })
                                    </script>
                                </span>

                                <span style="margin:0 3px 0 30px;">
                                    <input type="button" id="btnGo" value="Tìm kiếm"
                                           onclick="return doReport();"></input>
                                </span>
                </td>
            </c:if>

            <c:if test="${requestScope.orderType == 4}">
                <td align="center" colspan="4">
                    Second last login :
                    <b:message key="report.gereral.fromdate"/>
                    <h:text styleId="fromDate" maxlength="16" property="fromDate" readonly="true"
                            value='${requestScope.fromDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('fromDate'), 'dd/mm/yyyy', this, 0, '')"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <b:message key="report.gereral.todate"/>
                    <h:text styleId="toDate" maxlength="16" property="toDate" readonly="true"
                            value='${requestScope.toDate}' size="16"/>
                    <img src="${pageContext.request.contextPath}/res/img/cal.gif"
                         style="cursor:hand; cursor:pointer; color:#0000FF;"
                         onclick="displayCalendar(document.getElementById('toDate'), 'dd/mm/yyyy', this, 0, '')"/>

                    <span style="margin:0 3px 0 30px;">
                                Partner
                                <select id="partnerId" name="partnerId">
                                    <option value="0">Tất cả</option>
                                    <c:forEach items="${partners}" var="partner">
                                        <option value="${partner.id}">${partner.name}</option>
                                    </c:forEach>
                                </select>

                                    <script>
                                        $(document).ready(function () {
                                            $('select[name="partnerId"]').val(${partnerId});
                                        })
                                    </script>
                                </span>

                                <span style="margin:0 3px 0 30px;">
                                    <input type="button" id="btnGo" value="Tìm kiếm"
                                           onclick="return doReport();"></input>
                                </span>
                </td>
            </c:if>


        </table>
    </form>
</div>

<div style="float: left;">
    <input style="width:80" type="button"
           onClick="downloadCSV()" value="Dowload CSV">
</div>

<div style="clear: both; float: left; margin-bottom: 60px;">
    <table class="normal-table" style="width: 100% ; border: 1px; border-color: green; float: left;">
        <tr style="background-color: #E5E9F0">
            <th>ID</th>
            <th>Tên hiển thị</th>
            <th>Tên đăng nhập</th>
            <th>Gold</th>
            <th>Xeeng</th>
            <th>Trạng thái</th>
            <th>Đăng ký</th>
            <!--<th width="5%">Refcode</th>-->
            <!--<th width="5%">PartnerId</th>-->
            <th>Đăng nhập cuối</th>
            <th>Giới tính</th>
            <th>Partner</th>
            <th></th>
            <%--<th></th>--%>
        </tr>
        <logic:iterate id="wu" name="WorkingUserListForm" property="wuList" scope="request">
            <tr style="text-align: center;">
                <td><a href="view_user_detail.html?userId=${wu.userId}">${wu.userId}</a></td>
                <td nowrap align="left">${wu.name}</td>
                <td nowrap align="left">${wu.loginName}</td>
                <td class="align-right"><fmt:formatNumber type="number" value="${wu.cash}"/>
                    <c:if test="${sessionScope.loggedInUser.isAdmin}">
                        <a href="changeusercash.html?userId=${wu.userId}"><img
                                src="${pageContext.request.contextPath}/res/img/edit.png"/></a>
                    </c:if>
                </td>
                <td class="align-right"><fmt:formatNumber type="number" value="${wu.xeeng}"/>
                    <c:if test="${sessionScope.loggedInUser.isAdmin}">
                        <a href="changeusercash.html?userId=${wu.userId}&type=xeeng"><img
                                src="${pageContext.request.contextPath}/res/img/edit.png"/></a>
                    </c:if>
                </td>
                <td><img
                        src="${pageContext.request.contextPath}/res/img/${wu.stateOnline == 'Offline' ? 'offline' : 'online'}.png"/>
                </td>
                <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${wu.registerDate}" />">
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${wu.registerDate}"/></td>
                <!--<td>${wu.refCode}</td>-->
                <!--<td>${wu.partnerId}</td>-->
                <td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${wu.lastLongin}" />">
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${wu.lastLongin}"/></td>
                    <%--<td title="<fmt:formatDate pattern="HH:mm:ss dd/MM/yyyy" value="${wu.lastSecondLogin}" />"><fmt:formatDate pattern="dd/MM/yyyy" value="${wu.lastSecondLogin}" /></td>--%>
                    <%--<td>${wu.cmnd}</td>--%>
                    <%--<td nowrap>${wu.phonenumber}</td>--%>
                <td>${wu.sex == 1 ? 'Nam' : 'Nữ'}</td>
                <td>${wu.partner} (${wu.partnerId})</td>
                <td nowrap><a href="getAWorkingUser.html?userId=${wu.userId}">Đổi mật khẩu</a></td>
                    <%--<td nowrap><a href="#"--%>
                    <%--onclick="warningLock(${wu.userId}, '${wu.locked ? 'mở' : 'khóa'}');">${wu.locked ? 'Mở User' : 'Khóa User'}</a>--%>
                    <%--</td>--%>
            </tr>
        </logic:iterate>
    </table>
    <br/>
    <table style="width: 100% ; border: 1px; border-color: green; float: left;">
        <%
            int pageC = Integer.parseInt(request.getAttribute("page").toString());
        %>
        <tr>
            <td width="100%">
                <div class="paging" style="padding: 4px 4px 4px 0px">
                    <div style="float: left">
                        <a href="#" style="text-decoration: none;" onclick="getDataFirstPage();"> Trang
                            đầu </a>
                        <a href="#" style="text-decoration: none;  margin-left: 6px;"
                           onclick="backPageData();"> « </a>
                    </div>
                    <div style="float: left; margin-left: 6px;">
                        <div style="float: left; width: 50px;">
                            <input id="pageIndex" size="2" type="text" style="text-align: center;"
                                   value="<% out.print(pageC);%>"/>
                        </div>
                        <div style="float: left; ">
                            <img src="${pageContext.request.contextPath}/res/img/search.gif"
                                 style="cursor:hand;padding-top:2px ; cursor:pointer; color:#0000FF;"
                                 onclick="getDataPageIndex();"/>
                        </div>
                    </div>
                    <div style="float: left;">
                        <a href="#" style="text-decoration: none; margin-left: 6px"
                           onclick="nextPageData();"> » </a>
                        <a href="#" style="text-decoration: none; margin-left: 6px"
                           onclick="getDataLastPage();" id="lastPage"> Trang cuối </a>
                        <span style="text-decoration: none; margin-left: 6px">${wu.totalRecord} bản ghi / ${wu.totalPage} trang</span>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</div>

</div>
</td>
</tr>
<%@include file="inc/coda.jsp" %>
<script type="text/javascript">
function getDataPageIndex() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    if (pageIndex >= 1 && pageIndex <= ${wu.totalPage}) {
        window.location.href = "workingUserList.html?page=" + pageIndex + "&orderType=" + orderType + "&menuItem=wuser"
                + "&userId=" + userId
                + "&name=" + name
                + "&nickName=" + nickName
                + "&partnerId=" + partnerId
                + "&fromDate=" + fromDate
                + "&toDate=" + toDate
                + "&refCode=" + refCode;
    } else {
        alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${wu.totalPage});
    }
}
function getDataFirstPage() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    window.location.href = "workingUserList.html?page=1&menuItem=wuser&orderType=" + orderType
            + "&userId=" + userId
            + "&name=" + name
            + "&nickName=" + nickName
            + "&partnerId=" + partnerId
            + "&fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&refCode=" + refCode;
}
function getDataLastPage() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    var ttPage = ${wu.totalPage};
    window.location.href = "workingUserList.html?page=" + ${wu.totalPage} +"&orderType=" + orderType + "&menuItem=wuser"
            + "&userId=" + userId
            + "&name=" + name
            + "&nickName=" + nickName
            + "&partnerId=" + partnerId
            + "&fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&refCode=" + refCode;
}
function backPageData() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    var pageIndex = document.getElementById('pageIndex').value;
    var ttPage = ${wu.totalPage};

    pageIndex--;
    if (pageIndex >= 1) {
        window.location.href = "workingUserList.html?page=" + pageIndex + "&orderType=" + orderType + "&menuItem=wuser"
                + "&userId=" + userId
                + "&name=" + name
                + "&nickName=" + nickName
                + "&partnerId=" + partnerId
                + "&fromDate=" + fromDate
                + "&toDate=" + toDate
                + "&refCode=" + refCode;
    } else {
        alert("Bạn đang ở đầu trang");
    }
}
function nextPageData() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    var ttPage = ${wu.totalPage};
    pageIndex++;
    if (pageIndex >= 1 && pageIndex <= ttPage) {
        window.location.href = "workingUserList.html?page=" + pageIndex + "&orderType=" + orderType + "&menuItem=wuser"
                + "&userId=" + userId
                + "&name=" + name
                + "&nickName=" + nickName
                + "&partnerId=" + partnerId
                + "&fromDate=" + fromDate
                + "&toDate=" + toDate
                + "&refCode=" + refCode;
    } else {
        alert("Đã hết trang!");
    }
}

function downloadCSV() {
    var orderType = document.getElementById('orderType').value;
    var userId = "";
    var name = "";
    var nickName = "";
    var partnerId = "";
    var refCode = "";
    var pageIndex = "";
    var fromDate = "";
    var toDate = "";

    try {
        pageIndex = document.getElementById('pageIndex').value;
    } catch (ex) {

    }

    if (orderType == 1) {
        userId = document.getElementById('userId').value;
        name = document.getElementById('name').value;
        nickName = document.getElementById('nickName').value;
        partnerId = document.getElementById('partnerId').value;
        refCode = document.getElementById('refCode').value;
    } else {
        fromDate = document.getElementById('fromDate').value;
        toDate = document.getElementById('toDate').value;
    }

    window.location.href = "exportUser.html?orderType=" + orderType + "&menuItem=wuser"
            + "&userId=" + userId
            + "&name=" + name
            + "&nickName=" + nickName
            + "&partnerId=" + partnerId
            + "&fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&refCode=" + refCode;
}

function warningLock(userId, text) {
    var v = confirm("Bạn có chắc chắn muốn " + text + " User này?");
    if (v == true) {
        window.location.href = "lockUser.html?userId=" + userId;
    }
}

</script>
