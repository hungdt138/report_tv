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

</c:if>
<div>
<tr>
    <td align="center">
    	<div style="width: 200px;float: left;">
	   			<jsp:include page="inc/menu.jsp"/>
	   	</div>
	   	<div style="float: left; width: 75%; margin-left: 20px; ">
	   			<h:form action="reportDailyStat.html?action=report" method="post" styleId="form">
		            <input type="hidden" name="currentPageIndex" id="currentPageIndex" value="1"/>
		            <table width="1024" align="center">
		                <tr bgcolor="#E5E9F0">
		                    <td align="center" colspan="32">
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
		           
		                <tr bgcolor="#E5E9F0">
		                    <td width="5%" align="center"><b:message key="report.register.stt"/></td>
		                    <td width="8%" align="center" nowrap ><b:message key="report.general.day"/></td>
		                    <td width="5%" align="center"><b:message key="report.general.hour"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.downloadcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.registcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.logincount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.ccu"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.smscount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.cardcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.samcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.sammoney"/></td>                    
		                    <td width="5%" align="center"><b:message key="report.dailystat.altpcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.altpmoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.phomcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.phommoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.tlmncount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.tlmnmoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.picachucount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.picachumoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.liengcount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.liengmoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.xitocount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.xitomoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.bacaycount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.bacaymoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.baucuacount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.baucuamoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.uniqueusergamecount"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.revenuemoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.vascmoney"/></td>
		                    <td width="5%" align="center"><b:message key="report.dailystat.feemoney"/></td>
		                    
		                </tr>
		                <c:if test="${requestScope.report != null && requestScope.size > 0}" >     
		                
		                <c:set var="sumDownload" value="0" />
		                <c:set var="sumRegist" value="0" />
		                <c:set var="sumLogin" value="0" />  
		                <c:set var="sumCCU" value="0" />
		                <c:set var="sumSMS" value="0" />
		                <c:set var="sumCard" value="0" />
		                <c:set var="sumSam" value="0" />
		                <c:set var="sumSamMoney" value="0" />
		                <c:set var="sumALTP" value="0" />
		                <c:set var="sumALTPMoney" value="0" />
		                <c:set var="sumPhom" value="0" />
		                <c:set var="sumPhomMoney" value="0" />
		                <c:set var="sumTLMN" value="0" />
		                <c:set var="sumTLMNMoney" value="0" />
		                <c:set var="sumPicachu" value="0" />
		                <c:set var="sumPicachuMoney" value="0" />
		                <c:set var="sumLieng" value="0" />
		                <c:set var="sumLiengMoney" value="0" />
		                <c:set var="sumXito" value="0" />
		                <c:set var="sumXitoMoney" value="0" />
		                <c:set var="sumBacay" value="0" />
		                <c:set var="sumBacayMoney" value="0" />
		                <c:set var="sumBaucua" value="0" />
		                <c:set var="sumBaucuaMoney" value="0" />
		                <c:set var="sumUniqueUser" value="0" />
		                <c:set var="sumRevenueMoney" value="0" />   
		                <c:set var="sumVascMoney" value="0" />   
		                <c:set var="sumFeeMoney" value="0" />        
		                
		                <c:forEach items="${requestScope.report}"  var="record">   
		                    <c:set var="sumDownload" >${sumDownload + record.downloadCount}</c:set>
			                <c:set var="sumRegist" >${sumRegist + record.registCount}</c:set>
			                <c:set var="sumLogin" >${sumLogin + record.loginCount}</c:set> 
			                <c:set var="sumCCU" >${sumCCU + record.ccu}</c:set>
			                <c:set var="sumSMS" >${sumSMS + record.smsCount}</c:set>
			                <c:set var="sumCard" >${sumCard + record.cardCount}</c:set>
			                <c:set var="sumSam" >${sumSam + record.samCount}</c:set>
			                <c:set var="sumSamMoney" >${sumSamMoney + record.samMoney}</c:set>
			                <c:set var="sumALTP" >${sumALTP + record.altpCount}</c:set>
			                <c:set var="sumALTPMoney" >${sumALTPMoney + record.altpMoney}</c:set>
			                <c:set var="sumPhom" >${sumPhom + record.phomCount}</c:set>
			                <c:set var="sumPhomMoney" >${sumPhomMoney + record.phomMoney}</c:set>
			                <c:set var="sumTLMN" >${sumTLMN + record.tlmnCount}</c:set>
			                <c:set var="sumTLMNMoney" >${sumTLMNMoney + record.tlmnMoney}</c:set>
			                <c:set var="sumPicachu" >${sumPicachu + record.picachuCount}</c:set>
			                <c:set var="sumPicachuMoney" >${sumPicachuMoney + record.picachuMoney}</c:set>
			                <c:set var="sumLieng" >${sumLieng + record.liengCount}</c:set>
			                <c:set var="sumLiengMoney" >${sumLiengMoney + record.liengMoney}</c:set>
			                <c:set var="sumXito" >${sumXito + record.xitoCount}</c:set>
			                <c:set var="sumXitoMoney" >${sumXitoMoney + record.xitoMoney}</c:set>
			                <c:set var="sumBacay" >${sumBacay + record.bacayCount}</c:set>
			                <c:set var="sumBacayMoney" >${sumBacayMoney + record.bacayMoney}</c:set>
			                <c:set var="sumBaucua" >${sumBaucua + record.baucuaCount}</c:set>
			                <c:set var="sumBaucuaMoney" >${sumBaucuaMoney + record.baucuaMoney}</c:set>
			                <c:set var="sumUniqueUser" >${sumUniqueUser + record.uniqueUserGameCount}</c:set>
			                <c:set var="sumRevenueMoney" >${sumRevenueMoney + record.revenueMoney}</c:set>  
			                <c:set var="sumVascMoney" >${sumVascMoney + record.vascMoney}</c:set>   
			                <c:set var="sumFeeMoney" >${sumFeeMoney + record.feeMoney}</c:set>  
		                                                     
		                    <tr>                        
		                        <td align="center">
		                            <c:out value="${record.order}" />
		                        </td>                                                   
		
		                        <td align="center" nowrap >
		                            <c:out value="${record.day}" />
		                        </td>                                                   
		                        <td align="center">
		                            <c:out value="${record.hour}" />
		                        </td>   
		                        <td align="center">                            
		                            <c:out value="${record.downloadCount}" />                            
		                        </td>
		
		                        <td align="center">
		                            <c:out value="${record.registCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.loginCount}" />
		                        </td>
		
		                        <td align="center">
		                            <c:out value="${record.ccu}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.smsCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.cardCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.samCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.samMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.altpCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.altpMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.phomCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.phomMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.tlmnCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.tlmnMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.picachuCount}" />
		                        </td>  
		                        
		                        <td align="center">
		                            <c:out value="${record.picachuMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.liengCount}" />
		                        </td>  
		                        <td align="center">
		                            <c:out value="${record.liengMoney}" />
		                        </td>  
		                        <td align="center">
		                            <c:out value="${record.xitoCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.xitoMoney}" />
		                        </td>  
		                        <td align="center">
		                            <c:out value="${record.bacayCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.bacayMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.baucuaCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.baucuaMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.uniqueUserGameCount}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.revenueMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.vascMoney}" />
		                        </td>  
		
		                        <td align="center">
		                            <c:out value="${record.feeMoney}" />
		                        </td>
		
		                    </tr>       
		                    
		                                               
		                </c:forEach>
		                
		                <!--  Total -->
		                <tr bgcolor="#E5E4E2" >                        
		                        <td colspan=3 align="center">
		                            Total                            
		                        </td>
		
		                        <td align="center">
		                            ${sumDownload}
		                        </td>  
		
		                        <td align="center">
		                            ${sumRegist}
		                        </td>
		
		                        <td align="center">
		                           ${sumLogin}
		                        </td>  
		
		                        <td align="center">
		                            ${sumCCU}
		                        </td>  
		
		                        <td align="center">
		                            ${sumSMS}
		                        </td>
		
		                        <td align="center">
		                           ${sumCard}
		                        </td> 
		
		                        <td align="center">
		                            ${sumSam}
		                        </td>  
		
		                        <td align="center">
		                            ${sumSamMoney}
		                        </td>
		
		                        <td align="center">
		                           ${sumALTP}
		                        </td> 
		
		                        <td align="center">
		                            ${sumALTPMoney}
		                        </td>  
		
		                        <td align="center">
		                            ${sumPhom}
		                        </td>
		
		                        <td align="center">
		                           ${sumPhomMoney}
		                        </td> 
		                                                
		                        <td align="center">
		                            ${sumTLMN}
		                        </td>  
		
		                        <td align="center">
		                            ${sumTLMNMoney}
		                        </td>
		
		                        <td align="center">
		                           ${sumPicachu}
		                        </td> 
		
		                        <td align="center">
		                            ${sumPicachuMoney}
		                        </td>  
		
		                        <td align="center">
		                            ${sumLieng}
		                        </td>
		
		                        <td align="center">
		                           ${sumLiengMoney}
		                        </td>                         
		
		                        <td align="center">
		                            ${sumXito}
		                        </td>
		
		                        <td align="center">
		                           ${sumXitoMoney}
		                        </td> 
		
		                        <td align="center">
		                            ${sumBacay}
		                        </td>
		
		                        <td align="center">
		                           ${sumBacayMoney}
		                        </td> 
		                        
		                        <td align="center">
		                            ${sumBaucua}
		                        </td>
		
		                        <td align="center">
		                           ${sumBaucuaMoney}
		                        </td> 
		                        
		                        <td align="center">
		                            ${sumUniqueUser}
		                        </td>
		
		                        <td align="center">
		                           ${sumRevenueMoney}
		                        </td>                                                 
		
		                        <td align="center">
		                            ${sumVascMoney}
		                        </td>
		
		                        <td align="center">
		                           ${sumFeeMoney}
		                        </td> 
		                        
		                    </tr>   
		                
		                    <tr bgcolor="#E5E9F0">
		                        <td align="left" colspan="32" class="mfoot1">
		                <c:if test="${requestScope.totalpage !=null}">
		                    <tr bgcolor="#E5E9F0">
		                        <td align="left" colspan="32" class="mfoot1">
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
	   			
	   		</div>
		        	       
        </div>
</td>
</tr>
</table>

<%@include file="inc/coda.jsp" %>