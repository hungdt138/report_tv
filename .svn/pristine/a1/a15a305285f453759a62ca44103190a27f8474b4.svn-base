<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp"%>

<tr style="width: 100%">
	<td align="center" style="border-color: green;">
		<div style="float: left; width: 200px; background-color: white;">
			<jsp:include page="inc/menu.jsp" />
		</div>
		<div style="float: left; width: 600px; background-color: white; margin-left: 300px;">
			<html:form action="giftcodeUse.html" method="post">
				<table>
					<tr>
						<td>Tên tài khoản:</td>
						<td><html:text property="username" value=""/></td>
					</tr>
					<tr>
						<td>Mã thẻ:</td>
						<td><html:text property="serial" value=""/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Nạp"/>&nbsp&nbsp&nbsp<input type="button" onclick="backList();" value="Thoát"/></td>
					</tr>
				</table>
			</html:form>
		</div>
	</td>
</tr>
<%@include file="inc/coda.jsp"%>
<script type="text/javascript">
	function backList() {
		window.location.href="listGiftCode.html?page=1";
	}
</script>

