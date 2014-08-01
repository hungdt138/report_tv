<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../inc/prelude.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<script>
    function doPagging(index,totalPage){ 
        if(index <=0){
            alert('<b:message key="report.general.pagging.error.first"/>');
            return false;
        }
        if(index >totalPage){
            alert('<b:message key="report.general.pagging.error.last"/>');
            return false;
        }
        var frm = document.getElementById('form');
        document.getElementById('currentPageIndex').value=index;
        frm.submit();               
        return true;                        
    }
    function doModify(index) {
        var obj = document.getElementById('hdndetailform');
        obj.action = "advertisingdetail.html";
        document.getElementById('advertisingId').value=index;
        obj.submit();
        return true;
    }
     function dodelete(index) {
        var obj = document.getElementById('hdndetailform');
        obj.action = "advertisingdetail.html";
        document.getElementById('advertisingId').value=index;
        obj.submit();
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
        <h:form action="advertisinglist.html" method="post" styleId="form">
            <input type="hidden" id="currentPageIndex" name="currentPageIndex"/>
            <table width="1024" align="center">                
            <c:if test="${requestScope.report != null}" >    
                <tr bgcolor="#E5E9F0">
                    <td width="5%" align="center"><b:message key="admin.advertising.stt"/></td>
                    <td width="35%" align="center"><b:message key="admin.advertising.content"/></td>
                    <td width="10%" align="center"><b:message key="admin.advertising.partner"/></td>
                    <td width="15%" align="center"><b:message key="admin.advertising.fromdate"/></td>
                    <td width="15%" align="center"><b:message key="admin.advertising.todate"/></td>
                    <td width="10%" align="center"></td>
                    <td width="10%" align="center"></td>
                </tr>              
                <c:forEach items="${requestScope.report}"  var="record">                     
                    <tr>     
                         <td align="center">
                            <c:out value="${record.order}" />
                        </td>     
                        <td align="center">
                            <c:out value="${record.content}" />
                        </td>                                                   
                        <td align="center">
                            <c:out value="${record.partnerId}" />
                        </td >   
                        <td align="center">
                            <c:out value="${record.startDate}" />
                        </td>  
                        <td align="center">                            
                            <c:out value="${record.endDate}" />                            
                        </td>
                        <td align="center">  
                            <a  href='#' onclick='doDelete("${record.advertisingId}");'><b:message key="admin.advertising.delete" /></a>
                        </td>
                         <td align="center">  
                            <a  href='#' onclick='doModify("${record.advertisingId}");'><b:message key="admin.advertising.modify" /></a>
                        </td>
                    </tr>                                  
                </c:forEach>
             <tr bgcolor="#E5E9F0">
                        <td align="left" colspan="7" class="mfoot1">
                <c:if test="${requestScope.totalpage !=null}">
                            <span style="cursor:hand;cursor:pointer" onclick = "doPagging(${1},${requestScope.totalpage});" >|<< &nbsp;</span>
                            <span style="cursor:hand;cursor:pointer" onclick = "doPagging(${requestScope.currentPageIndex - 1},${requestScope.totalpage});">< &nbsp;</span>
                            <s:text name = "report.general.page"/>&nbsp;
                            <input id="pageInput"  name = "currentPageIndex" type="text" maxlength="4" size = "3" style="text-align:center;" value="${requestScope.currentPageIndex}" />                                                                                      
                            <img  src="${pageContext.request.contextPath}/res/img/search.gif" style="cursor:hand; cursor:pointer; color:#0000FF;vertical-align: middle" onclick = "doPaggingInput(${requestScope.totalpage});"/>                                                                                  
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
                 </c:if> 
                      </table>                    
        </h:form>
    </td>
</tr>
<h:form action="advertisingdetail.html" method="post" styleId="hdndetailform">    
    <input type="hidden" name="advertisingId" id="advertisingId"/>
</h:form>
<%@include file="../../inc/coda.jsp" %>