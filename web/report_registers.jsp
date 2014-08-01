<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/prelude.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
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
    function doPagging(index, totalPage) {
        if (index <= 0) {
            alert('<b:message key="report.general.pagging.error.first"/>');
            return false;
        }
        if (index > totalPage) {
            alert('<b:message key="report.general.pagging.error.last"/>');
            return false;
        }
        var frm = document.getElementById('form');
        document.getElementById('currentPageIndex').value = index;
        frm.submit();
        return true;
    }
    function doPaggingInput(total)
    {
        var obj = document.getElementById("pageInput");
        var strCode = /^[1-9]\d{0,5}$/;
        if (!strCode.test(obj.value))
        {
            alert('<b:message key="report.general.pagging.error.input"/>');
            return false;
        } else if (obj.value > total) {
            alert('<b:message key="report.general.pagging.error.out"/>');
            return false;
        }

        var pageInput = obj.value;
        var frm = document.getElementById('form');
        document.getElementById('currentPageIndex').value = pageInput;
        frm.submit();
        return true;
    }
</script>

<!-- <tr>
    <td>&nbsp;</td>
</tr> -->
<c:if test="${requestScope.message != null}" >
    <tr>
        <td align="center">        
            <br/>
            <font color="red" >
            <c:out value="${requestScope.message}" />
            </font>        
        </td>
    </tr>
</c:if>
<tr>
    <td align="center">
    	<div style="float: left; width: 200px">
   			<jsp:include page="inc/menu.jsp"/>
   		</div>
        <h:form action="reportUserRegister.html?action=report" method="post" styleId="form">
            <input type="hidden" name="currentPageIndex" id="currentPageIndex" value="1"/>
            <table width="1024" align="center">
                <tr bgcolor="#E5E9F0">
                    <td align="center" colspan="5">
                        <b:message key="report.gereral.fromdate"/>
                        <h:text styleId="fromDate" maxlength="16" property="fromDate" readonly="true" value='${requestScope.fromDate}' size="16"/>
                        <img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;" onclick="displayCalendar(document.getElementById('fromDate'), 'dd/mm/yyyy', this, 0, '')"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b:message key="report.gereral.todate"/>
                        <h:text  styleId="toDate" maxlength="16" property="toDate" readonly="true" value='${requestScope.toDate}' size="16"/>                        
                        <img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;vertical-align: middle" onclick="displayCalendar(document.getElementById('toDate'), 'dd/mm/yyyy', this, 0, '')"/>
                        <span style="margin:0 3px 0 30px;">
                            <input type="button"  id = "btnGo" value="Tìm kiếm" onclick="return doReport();"></input>      
                        </span>

                    </td>
                </tr>
        <c:if test="${requestScope.report != null && requestScope.size > 0}" >            
           
                <tr bgcolor="#E5E9F0">
                    <td width="5%" align="center"><b:message key="report.register.stt"/></td>
                    <td width="15%" align="center"><b:message key="report.register.username"/></td>
                    <td width="15%" align="center"><b:message key="report.register.bi"/></td>
                    <td width="15%" align="center"><b:message key="report.register.datetime"/></td>
                </tr>
                <c:forEach items="${requestScope.report}"  var="record">                                        
                    <tr>                        
                        <td align="center">
                            <c:out value="${record.order}" />
                        </td>                                                   
                        <td align="center">
                            <c:out value="${record.userName}" />
                        </td>   
                        <td align="center">                            
                            <c:out value="${record.money}" />                            
                        </td>

                        <td align="center">
                            <c:out value="${record.registerDate}" />
                        </td>  
                    </tr>                                  
                </c:forEach>
                    <tr bgcolor="#E5E9F0">
                        <td align="left" colspan="7" class="mfoot1">
                <c:if test="${requestScope.totalpage !=null}">
                    <tr bgcolor="#E5E9F0">
                        <td align="left" colspan="7" class="mfoot1">
                            <span style="cursor:hand;cursor:pointer" onclick = "doPagging(1,${totalPage});" >|<< &nbsp;</span>
                            <span style="cursor:hand;cursor:pointer" onclick = "doPagging(${requestScope.currentPageIndex -1},${totalPage});">< &nbsp;</span>
                    <s:text name = "report.general.page"/>&nbsp;
                    <input id="pageInput"  name = "currentPageIndex" type="text" maxlength="4" size = "3" style="text-align:center;" value="${requestScope.currentPageIndex}" />                                                                                      
                    <img  src="${pageContext.request.contextPath}/res/img/search.gif" style="cursor:hand; cursor:pointer; color:#0000FF;" onclick = "doPaggingInput(${requestScope.totalpage});"/>                                                                                  
                    &nbsp;
                    <span style="cursor:hand;cursor:pointer" onclick = "doPagging(${requestScope.currentPageIndex + 1},${requestScope.totalpage});"> > &nbsp;</span>                                           
                    <span style="cursor:hand;cursor:pointer" onclick = "doPagging(${requestScope.totalpage},${requestScope.totalpage});"> >>|&nbsp;</span>                                                                                                                                 
                    &nbsp;&nbsp;&nbsp;
                    <b:message key=  "report.general.page.total" /> ${requestScope.totalpage}   
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <b:message key=  "report.general.page.totalrec" /> ${requestScope.totalrecord}  
                                            
    </c:if> 
                      </td >                               
        </tr>  
</table>
</c:if> 
        </h:form>
</td>
</tr>
</table>
<%@include file="inc/coda.jsp" %>
