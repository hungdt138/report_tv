<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/prelude.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<script>
    function change(){
        var pass1 = document.getElementById("passNew");
        var pass2 = document.getElementById("passNewRetype");
        pass1.value = trim(pass1.value);
        pass2.value = trim(pass2.value);
        if(pass1.value != pass2.value){
            alert("<b:message key='account.pass.mismatch'/>");
            return false;
        }
        return true;
    }
    function trim(stringToTrim) {
        return stringToTrim.replace(/^\s+|\s+$/g,"");
    }
</script>
<tr>
    <td align="center" width="100%">
    	<div style="float: left; width: 200px">
   			<jsp:include page="inc/menu.jsp"/>
   		</div>
        <font color="red" >
            <c:if test='${requestScope.message !=null && requestScope.message == "success"}' >
                <b:message key="account.pass.change.success" />
                <br/>
                <br/>
            </c:if>
            <c:if test='${requestScope.message !=null && requestScope.message == "fail"}' >
                <b:message key="account.pass.change.fail" />
                <br/>
                <br/>
            </c:if>
            <c:if test='${requestScope.message !=null && requestScope.message == "incorrectpassword"}' >
                <b:message key="account.pass.change.incorrectpassword" />
                <br/>
                <br/>
            </c:if>
            <c:if test='${requestScope.message !=null && requestScope.message == "invalidpassword"}' >
                <b:message key="account.pass.change.invalidpassword" />
                <br/>
                <br/>
            </c:if>
        </font>
        <table width="55%" style="border-width: 1px 1px 1px 1px;">
            <h:form action="account.html?action=changePassword" method="post" >
                <tr>
                    <td align="right">
                        <b:message key="account.pass.old" /> &nbsp;
                    </td>
                    <td align="left">
                        <h:password property="passOld" styleId="passOld" />
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b:message key="account.pass.new" /> &nbsp;
                    </td>
                    <td  align="left">
                        <h:password property="passNew" styleId="passNew" />
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <b:message key="account.pass.new.retype" /> &nbsp;
                    </td>
                    <td  align="left">
                        <h:password property="passNewRetype" styleId="passNewRetype" />
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td  align="left">
                        <h:submit value='Chấp nhận' onclick="return change();"/>
                    </td>
                </tr>
            </h:form>
        </table>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>

