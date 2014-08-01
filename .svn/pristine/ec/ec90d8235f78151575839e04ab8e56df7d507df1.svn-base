<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
    	<div style="float: left; background-color:white;">
    		<jsp:include page="inc/menu.jsp"/>
    	</div>
		<div style="float: left; border: 1px; width: 80%; margin-left: 30px; text-align: center;">
					<div style="clear: both; float: left; margin-bottom: 5px; width: 100%">
							<form action="searchWorkingUser.html" method="post" style="float: left;"> 
								<table style="width: 610px">
									<tr>
										<td width="200px" style="background-color: #E5E9F0">Mã người dùng:</td>
										<td width="100px"><input type="text" name="userId" style="text-align: center;" value="${requestScope.WorkingUserForm.userId}" /></td>
										<td width="150px" style="background-color: #E5E9F0">Tên tài khoản</td>
										<td width="100px"><input name="name" type="text"/></td>
										<td width="10px"><input type="submit" value="Tìm kiếm"/></td>
									</tr>
									<tr>
										<td style="background-color: #E5E9F0">Tổng số bản ghi:</td>
										<td>1</td>
									</tr>
								</table>
							</form>
					</div>
					<div style="clear: both; float: left; margin-bottom: 60px;">
						<table style="width: 100% ; border: 1px; border-color: green; float: left;">
							<tr style="background-color: #E5E9F0">
								<th width="14%">Mã người dùng</th>
								<th width="18%">Tên tài khoản</th>
								<th width="7%">Trạng thái</th>
								<th width="17%">Ngày đăng kí</th>
								<th width="10%">Mã tham chiếu</th>
								<th width="6%">Đối tác</th>
								<th width="19%">Lần đăng nhập cuối</th>
								<th width="13%"></th>
							</tr>
							</tr>
							<tr style="text-align: center;">
								<td>${requestScope.WorkingUserForm.userId}</td>
								<td>${requestScope.WorkingUserForm.name}</td>
								<td>${requestScope.WorkingUserForm.stateActive}</td>
								<td>${requestScope.WorkingUserForm.registerDate}</td>
								<td>${requestScope.WorkingUserForm.refCode}</td>
								<td>${requestScope.WorkingUserForm.partnerId}</td>
								<td>${requestScope.WorkingUserForm.lastLongin}</td>
								<td><a href="getAWorkingUser.html?userId=${requestScope.WorkingUserForm.userId}">Đổi mật khẩu</a></td>
							</tr>
						</table>
						<br/>
					</div>
					</div>
			</div>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>

