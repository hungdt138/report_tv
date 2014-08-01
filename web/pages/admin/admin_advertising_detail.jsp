<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../inc/prelude.jsp" %>
<script>
    function doupdate(index) {
        var obj = document.getElementById('hdnform');
        obj.action = "advertisingdetail.html?action=update";
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
     function doback() {
        window.location.href="advertisinglist.html";
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
        <c:if test="${sessionScope.loggedInUser != null}">
            
                <table width="500px" align="center">
                    <tr>
                        <td>
                            <b:message key="admin.advertising.partner"/>
                        </td>
                        <td>
                            <h:text property="partnerId" size="30"  value="${requestScope.data.partnerId}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b:message key="admin.advertising.fromdate"/>
                        </td>
                        <td>
                            <h:text property="startDate" size="30" value="${requestScope.data.startDate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b:message key="admin.advertising.todate"/>
                        </td>
                        <td>
                            <h:text property="endDate" size="30" value="${requestScope.data.endDate}"/>
                        </td>
                    </tr>

                     <tr>
                        <td>
                            <b:message key="admin.advertising.content"/>
                        </td>
                        <td>
                            <h:textarea property="content" rows="5" cols="50" value="${requestScope.data.content}"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" value="<b:message key="report.gereral.update" />" onclick='doupdate("${requestScope.advertisingId}");'/>
                            <input type="button" value="<b:message key="report.gereral.delete"/>" onclick='dodelete("${requestScope.advertisingId}");'/>
                            <input type="button" value="<b:message key="report.gereral.back"/>" onclick='doback();'/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="left">
                            <h:errors/>
                        </td>
                    </tr>
                </table>
            
        </c:if>
    </td>
</tr>
<h:form action="advertisingdetail.html" method="post" styleId="hdnform">    
    <input type="hidden" name="advertisingId" id="advertisingId"/>
</h:form>
<%@include file="../../inc/coda.jsp" %>
