<%@page import="org.apache.commons.codec.language.bm.Rule.RPattern"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/inc/prelude.jsp" %>
<tr style="background-color: white;">
	<td align="center" style="border-color: green;">
	    	<div style="float: left; width: 200px; background-color: white;">
	    		<jsp:include page="inc/menu.jsp"/>
	    	</div>
			<div style="float: left; border: 1px; width: 800px">
					<div style="clear: both; float: left; padding-left: 200px; margin-bottom: 60px;">
						<table style="width: 770px ; border: 1px; border-color: green;">
							<tr style="background-color: #E5E9F0">
								<th>Mã người dùng</th>
								<th>Tên tài khoản</th>
								<%--<th>Đối tác</th>--%>
								<th>Nhóm quyền</th>
								<th></th>
								<th></th>
							</tr>
							<logic:iterate id="rpu" name="ReportUserForm" property="rpUserList" scope="request">
								<tr style="text-align: center;">
									<td>${rpu.userId}</td>
									<td>${rpu.name}</td>
									<%--<td>${rpu.partnerId}</td>--%>
									<td>${rpu.roleName}</td>
									<td><a href="getReportUserById.html?userId=${rpu.userId}">Sửa</a></td>
									<td><a href="#" onclick="warningDelete(${rpu.userId});">Xóa</a></td>
								</tr>
							</logic:iterate>
						</table>
						<table style="width: 770px ; border: 1px; border-color: green;">
							<%
								/* int totalPage = Integer.parseInt(request.getAttribute("totalPage").toString()); */
								int pageC =  Integer.parseInt(request.getAttribute("page").toString());
							%>
							<tr style="background-color: #E5E9F0">
								<td>
								<div style="width:600px;">
									<div  style="width:40px; float: left; padding-top: 2px;">
										<a href="#" style="text-decoration: none;" onclick="getDataFirstPage();">|<< </a>
										<a href="#" style="text-decoration: none;" onclick="backPageData();"> < </a>
									</div>
									<div style="width: 100px;float: left; margin-left: 2px;">
										<div style="float: left; width: 50px">
											<input style="text-align: center;" id="pageIndex" size="2" type="text" value="<% out.print(pageC);%>"/>
										</div>
										<div style="float: left; width: 50px; ">
											<img  src="${pageContext.request.contextPath}/res/img/search.gif" style="cursor:hand;padding-top:2px ; cursor:pointer; color:#0000FF;" onclick = "getDataPageIndex();"/>
										</div>
									</div>
									<div style="width:450px;float: left;">
										<div style="width: 445px; margin-top: 2px;">
										<a href="#" style="text-decoration: none;" onclick="nextPageData();"> > </a>
										<a href="#" style="text-decoration: none;" onclick="getDataLastPage();" id="lastPage"> >>|</a>
										<a>Tổng số trang ${rpu.totalPage}</a>&nbsp&nbsp
										<a>Tổng số bản ghi ${rpu.totalRecord}</a>&nbsp&nbsp
										</div>
									</div>
								</div>
								</td>
							</tr>
						</table>
					</div>
					</div>
			</div>
	</td>
</tr>
<%@include file="/inc/coda.jsp" %>
<script type="text/javascript" >
	function warningDelete(userId) { 
		var v = confirm("Bạn có chắc chắn muốn xóa?");
		if( v == true) { 
			window.location.href="deleteReportUser.html?userId=" + userId;
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		if( pageIndex >= 1 && pageIndex <= ${rpu.totalPage}) { 
			window.location.href="listReportUser.html?page=" + pageIndex + "&menuItem=listacc";
		} else {
			alert("Bạn phải nhập số trang trong khoảng từ 1 đến " + ${rpu.totalPage});
		}
	}
	function getDataFirstPage() {
		window.location.href="listReportUser.html?page=1&menuItem=listacc";
	}
	function getDataLastPage() {
		window.location.href="listReportUser.html?page="+${rpu.totalPage}+"&menuItem=listacc";
	}
	function backPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${rpu.totalPage};
		pageIndex--;
		if( pageIndex >= 1) { 
			window.location.href="listReportUser.html?page=" + pageIndex + "&menuItem=listacc";
		} else {
			alert("Bạn đang ở đầu trang.");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${rpu.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			window.location.href="listReportUser.html?page=" + pageIndex + "&menuItem=listacc";
		} else {
			alert("Bạn đang ở cuối trang");
		}
	}
	
</script>