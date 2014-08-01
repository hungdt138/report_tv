<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../inc/prelude.jsp" %>
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
    
</script>

<tr>
    <td>&nbsp;</td>
</tr>

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
        <h:form action="partnermonthly.html?action=report" method="post" styleId="form">
            <input type="hidden" name="dt" id="dt" value="${requestScope.dt}"/>
            <table width="1024" align="center">
                <tr bgcolor="#E5E9F0">
                    <td align="center" colspan="6">
                    
                        <b:message key="report.gereral.fromdate"/>
                        <h:text styleId="fromDate" maxlength="16" property="fromDate" readonly="true" value='${requestScope.fromDate}' size="16"/>
                        <img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;" onclick="displayCalendar(document.getElementById('fromDate'), 'dd/mm/yyyy', this, 0, '')"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b:message key="report.gereral.todate"/>
                        <h:text  styleId="toDate" maxlength="16" property="toDate" readonly="true" value='${requestScope.toDate}' size="16"/>                        
                        <img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;vertical-align: middle" onclick="displayCalendar(document.getElementById('toDate'), 'dd/mm/yyyy', this, 0, '')"/>
                    
                        <span style="margin:0 3px 0 30px;">
                            <b:message key="report.gereral.sms"/>:
                        </span>
                        	<h:checkbox property="type" value="true"/>
                        <span style="margin:0 3px 0 30px;">
                            <b:message key="report.gereral.card"/>:
                        </span>
                        	<h:checkbox property="type" value="true"/>
                        <span style="margin:0 3px 0 30px;">
                             <input type="button" id = "btnGo" value="Tìm kiếm" onclick="return doReport();"></input>      
                        </span>
                                         
                    </td>
                </tr>
          
        <c:if test="${requestScope.report != null}" >     
                <tr bgcolor="#E5E9F0">
                    <td width="5%" align="center"><b:message key="report.chargingdetail.stt"/></td>
                    <td width="25%" align="center"><b:message key="report.chargingdetail.username"/></td>
                    <td width="20%" align="center"><b:message key="report.chargingdetail.description"/></td>
                    <td width="15%" align="center"><b:message key="report.chargingdetail.bi"/></td>
                    <td width="25%" align="center"><b:message key="report.chargingdetail.datetime"/></td>
                    <td width="10%" align="center"><b:message key="report.chargingdetail.type"/></td>
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
                            <c:out value="${record.description}" />
                        </td>  
                        <td align="center">                            
                            <c:out value="${record.money}" />                            
                        </td>
                        
                        <td align="center">
                            <c:out value="${record.datetime}" />
                        </td>  
                        <td align="center">
                            <c:choose>
                                <c:when test="${record.SMS==true}">
                                    <c:out value="SMS" />
                                </c:when>
                                <c:otherwise>
                                    <c:out value="Thẻ" />
                                </c:otherwise>
                            </c:choose>
                                    
                        </td>  
                    </tr>                                  
                </c:forEach>
                </table>
            </c:if> 
            </h:form>
    </td>
</tr>
<%@include file="../../inc/coda.jsp" %>
