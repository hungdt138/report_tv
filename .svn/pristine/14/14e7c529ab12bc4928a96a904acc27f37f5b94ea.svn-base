<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp"%>

<tr style="width: 100%">
	<td align="center" style="border-color: green;">
		<div style="float: left; width: 200px; background-color: white;">
			<jsp:include page="inc/menu.jsp" />
		</div>
		<div
			style="float: left; width: 600px; background-color: white; margin-left: 300px;">
			<%-- <% out.print(" Userid : "+request.getParameter("userId").toString()); %> --%>
			<form action="updateReportUser.html" method="post">
				<table style="">
					<tr style="visibility: hidden;">
						<td>UserId:</td>
						<td style="text-align: left;"><input  type="text" name="userid" value=" <% out.print(request.getParameter("userId").toString()); %>"/></td>
					</tr>
					<tr>
						<td>Tên tài khoản:</td>
						<td><input type="text" name="name" value="${rpuBean.name}" disabled="disabled"/></td>
					</tr>
					<%--<tr>--%>
						<%--<td>PartnerId:</td>--%>
						<%--<td><input type="text" name="partnerId" value="${rpuBean.partnerId}"  /></td>--%>

					<%--</tr>--%>
					<tr>
						<td>Nhóm quyền</td>
						<td>
                            <select name="roleId">
                                <option value="1" ${rpuBean.roleId == 1 ? "selected='selected'" : ""}>Admin</option>
                                <option value="2" ${rpuBean.roleId == 2 ? "selected='selected'" : ""}>Operator</option>
                                <option value="3" ${rpuBean.roleId == 3 ? "selected='selected'" : ""}>GM</option>
                                <option value="4" ${rpuBean.roleId == 4 ? "selected='selected'" : ""}>IA</option>
                            </select>
                            <%--<input type="text" name="userTypeId" value="${rpuBean.userTypeId}"/>--%>
                        </td>
						<%--<td>{1: Admin; 2: Operator; 3: GameMaster}</td>--%>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left;"><input type="submit" value="Cập nhật" /> || <input id="back" type="button" value="Thoát" onclick="backPage();" /></td>
					</tr>

				</table>
			</form>
		</div>
	</td>
</tr>
<%@include file="inc/coda.jsp"%>
<script type="text/javascript">
	function backPage() {
		window.location.href="listReportUser.html?page=1&menuItem=listacc";
	}
</script>
