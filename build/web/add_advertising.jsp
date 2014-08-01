<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<tr style="width: 100%">
	<td align="center" style="border-color: green;">
		<div style="float: left; width: 200px; background-color: white;">
			<jsp:include page="inc/menu.jsp" />
		</div>
		<div
			style="float: left; width: 600px; background-color: white; margin-left: 300px;">	
			<html:form action="addAdvertising.html" method="post">
				<table >
					<tr>
						<td>
							<div style="float: left; width: auto;">Nội dung Quảng Cáo:</div>
						</td>
						<td><html:textarea style="width: 400px; height: 80px" property="content" value="" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr style="margin-top: 15px">
						<td>Trạng thái hiển thị</td>
						<td><html:text property="isDisplay" value=""/><br/>
							(Điền: 1: Hiển thị ||Điền: 0: Không hiển thị)
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr style="margin-top: 15px">
						<td>Mã đối tác</td>
						<td><html:text property="partnerId" value="" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr style="margin-top: 15px">
						<td>Ngày bắt đầu:</td>
						<td>
							<html:text styleId="fromDate" maxlength="16" property="startDate" readonly="true" value='${requestScope.fromDate}' size="16"/>
                        	<img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;" onclick="displayCalendar(document.getElementById('fromDate'), 'yyyy-mm-dd', this, 0, '')"/>
							<br/>
							<div style="width: 200px ; float: left;">
							<div style="width: 60px ; float: left;">Giờ:
							<select name="shour">
							<%
								for(int i = 0; i < 24 ; i++) {
									%>
										  <option value="<% out.print(i);%>"><% out.print(i);%></option>
									<%
								}
							%>
							</select>
							</div>
							<div style="width: 60px; float: left;margin-left: 5px">Phút:
							<select name="sminutes">
							<%
								for(int i = 0; i < 60 ; i++) {
									%>
										  <option value="<% out.print(i);%>" ><% out.print(i);%></option>
										
									<%
								}
							%>
							</select>
							</div>
							<div style="width: 60px;float: left;margin-left: 5px">Giây:
							<select name="ssecond">
							<%
								for(int i = 0; i < 60 ; i++) {
									%>
										  <option value="<% out.print(i);%>" ><% out.print(i);%></option>
									<%
								}
							%>
							</select>
							</div>
							</div>
							
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr style="margin-top: 15px">
						<td>Ngày kết thúc:</td>
						<td>
							<html:text styleId="endDate" maxlength="16" property="endDate" readonly="true" value='${requestScope.fromDate}' size="16"/>
                        	<img src="${pageContext.request.contextPath}/res/img/cal.gif" style="cursor:hand; cursor:pointer; color:#0000FF;" onclick="displayCalendar(document.getElementById('endDate'), 'yyyy-mm-dd', this, 0, '')"/>
							<br/>
							<div style="width: 200px ; float: left;">
							<div style="width: 60px;float: left;">Giờ:
							<select name="ehour">
							<%
								for(int i = 0; i < 24 ; i++) {
									%>
										  <option value="<% out.print(i);%>" ><% out.print(i);%></option>
									<%
								}
							%>
							</select>
							</div>
							<div style="width: 60px;float: left; margin-left: 5px">Phút:
							<select name="eminutes">
							<%
								for(int i = 0; i < 60 ; i++) {
									%>
									
										  <option value="<% out.print(i);%>" ><% out.print(i);%></option>
										
									<%
								}
							%>
							</select>
							</div>
							<div style="width: 60px;float: left;margin-left: 5px">Giây:
							<select name="esecond">
							<%
								for(int i = 0; i < 60 ; i++) {
									%>
										
										  <option value="<% out.print(i);%>" ><% out.print(i);%></option>
									<%
								}
							%>
							</select>
							</div>
							</div>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left;"><input type="submit" value="Thêm mới"/>&nbsp&nbsp<input type="button" onclick="backList();" value="Thoát"/></td>
						<td></td>
					</tr>
				</table>
			</html:form>
		</div>
	</td>
</tr>
<%@include file="inc/coda.jsp"%>
<script type="text/javascript">
	function backList() {
		window.location.href="advertisingList.html?page=1&m=adv";
	}
</script>
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
    function doDetail(datetime) {
        var obj = document.getElementById('hdnform');
        obj.action = "reportChargingDetail.html?action=report";
        document.getElementById('dt').value = datetime;
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
