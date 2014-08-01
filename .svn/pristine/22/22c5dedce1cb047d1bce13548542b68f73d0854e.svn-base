<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
    	<div style="float: left; background-color:white;">
    		<jsp:include page="inc/menu.jsp"/>
    	</div>
		<div style="float: left; border: 1px; width: 80%;">
					<!-- <div style="clear: both; float: left; width:100%; font-size: large; font-style: italic; background-color: #E5E9F0;">
					</div> -->
					<div style="clear: both; float: left; margin-bottom: 60px;">
						<form action="changePassWorkingUser.html" method="post">
							<table style="width: 500px; border: 1px; border-color: green; margin-left: 400px">
								<input type="hidden" name="userId" value="${requestScope.ChangePassWorkingUserForm.userId}"/>
								<input type="hidden" name="oldPass" value="${requestScope.ChangePassWorkingUserForm.oldPass}"/>
								
								<tr>
									<td>UserId</td>
									<td>
										${requestScope.ChangePassWorkingUserForm.userId}
									</td>
								</tr>								
<!--
								<tr>
									<td>Mật khẩu cũ</td>
									<td>
										<input type="password" name="reOldPass" />
									</td>
								</tr> -->
								<tr>
									<td>Mật khẩu mới</td>
									<td>
										<input type="password" name="newPass"/>
									</td>
								</tr>
								<tr>
									<td>Nhập lại-Mật khẩu mới</td>
									<td>
										<input type="password" name="reNewPass"/>
									</td>
								</tr>
								<tr style="visibility: hidden;">
									<td> </td>
									<td>
									</td>
								</tr>
								<tr>
									<td> </td>
									<td style="text-align: left;">
										<input type="submit" value="Thay đổi"/>  &nbsp&nbsp
										<input type="button" value="Thoát" onclick="backList(${requestScope.ChangePassWorkingUserForm.userId});"/>
										
									</td>
								</tr>
								
							</table>
						</form>
						
						<br/>
					
					</div>
					</div>
			</div>
    </td>
</tr>
<%@include file="inc/coda.jsp" %>
<script type="text/javascript">
	function backList(userId) {
		window.location.href="workingUserList.html?page=1&orderType=1&userId="+userId;
	}
</script>
