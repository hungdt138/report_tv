<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/prelude.jsp" %>
<script>
    function doReport() {
        var obj = document.getElementById('form');
        obj.submit();
        return true;
    }
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
             function doPaggingInput(total)
            {
                var obj = document.getElementById("pageInput");                
                var  strCode =/^[1-9]\d{0,5}$/;
                if(!strCode.test(obj.value))
                {  
                    alert('<b:message key="report.general.pagging.error.input"/>'); 
                    return false;
                }else if( obj.value > total){
                    alert('<b:message key="report.general.pagging.error.out"/>'); 
                    return false; 
                }             
                
                var pageInput = obj.value;
                var frm = document.getElementById('form');
                document.getElementById('currentPageIndex').value=pageInput;
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
        <h:form action="reportChargingDetail.html?action=report" method="post" styleId="form">
            <input type="hidden" name="currentPageIndex" id="currentPageIndex" value="1"/>
            <input type="hidden" name="dt" id="dt" value="${requestScope.dt}"/>
            <table width="1024" align="center">
            	<div style="float: left; width: 200px">
   					<jsp:include page="inc/menu.jsp"/>
   				</div>
                <tr bgcolor="#E5E9F0">
                    <td align="center" colspan="6">
                        <span style="margin:0 3px 0 30px;">
                            <b:message key="report.gereral.sms"/>:
                        </span>
                        <h:checkbox property="SMS" value="true"/>
                        <span style="margin:0 3px 0 30px;">
                            <b:message key="report.gereral.card"/>:
                        </span>
                        <h:checkbox property="card" value="true"/>
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
                </table>
            </c:if> 
                         </h:form>
    </td>
</tr>
</table>
<%@include file="inc/coda.jsp" %>
