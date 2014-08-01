<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@include file="inc/prelude.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/rrdUI.css"></link>
<script src='${pageContext.request.contextPath}/js/rrdUI.js'></script>

<c:if test="${!sessionScope.loggedInUser.isAdmin && !sessionScope.loggedInUser.isOperator}">
    <% response.sendRedirect("404.html"); %>
</c:if>

<c:if test="${sessionScope.loggedInUser.isAdmin || sessionScope.loggedInUser.isOperator}">
    <tr style="width:100%">
        <td align="center" style="border-color: green;">
            <div style="float: left; background-color:white;">
                <jsp:include page="inc/menu.jsp"/>
            </div>
            <div style="float: left; border: 1px; width: 80%;">
                <button id='genGraphsBtn' onclick='loadGraphs();'>GENERATE DEFAULTS GRAPHS</button>
                <button onclick='toggle("additions");'>Show/Hide</button>
                <div id='additions' style='display:none;'>
                    <label>End date</label><input type="date" name="end_date" id='end_date' value=''/>
                    <label>End time</label><input type="time" name="end_time" id='end_time' value='0'/>
                    <label>period</label><input type="number" name='period' id='period' value=8/>
                    <label>unit</label><select id='unit' onchange='unitChange();'>
                    <option value='h'>hour</option>
                    <option value='d'>day</option>
                    <option value='w'>week</option>
                    <option value='mon'>month</option>
                </select>
                    <button id='genBtn' onclick='genGraph();'>GENERATE</button>
                </div>
                <div id=root>
                </div>
            </div>
        </td>
    </tr>
</c:if>


<%@include file="inc/coda.jsp" %>

