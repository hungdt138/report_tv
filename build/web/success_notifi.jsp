<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
    	<div style="float: left; width: 200px; background-color: white;">
    		<jsp:include page="inc/menu.jsp"/>
    	</div>
		<div style="text-align: center; float: left; width: 600px; margin-left: 300px">
		<%
			if(request.getAttribute("msg_notifi")!= null) {
				String containNotifi = request.getAttribute("msg_notifi").toString();
				out.print(containNotifi);
			} 
		%>
		
</div>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>
