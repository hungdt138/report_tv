<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
    	<div style="float: left; background-color: white;">
    		<jsp:include page="inc/menu.jsp"/>
    	</div>
		<div style="float: left; border: 1px; width: 80%; margin-left: 28px">
					<div style="clear: both; float: left; width:100%; font-size: large; font-style: italic; background-color: #E5E9F0;">
					   &nbsp&nbsp<a style="text-decoration: none; float: left; color: green;" href="add_goldenhour.jsp">Thêm mới khuyến mại</a> &nbsp&nbsp 
					</div>
					<div style="clear: both; float: left; margin-bottom: 60px;">
						<table style="width: 100% ; border: 1px; border-color: green;">
							<tr style="background-color: #E5E9F0">
								<th width="5%">Mã</th>
								<th width="15%">Thời gian bắt đầu</th>
								<th width="15%">Thời gian kêt thúc</th>
								<th width="5%">Trạng thái kích hoạt</th>
								<th width="5%">Kiểu</th>
								<th width="5%">Tiền thưởng</th>
								<th width="5%">Đối tác</th>
								<th width="5%">Tỉ lệ</th>
								<th width="35%">Mô tả</th>
								<th width="3%"></th>
								<th width="3%"></th>
							</tr>
							<logic:iterate id="gdh" name="GoldenHourListForm" property="gdhList" scope="request">
								<tr style="text-align: center;">
									<td>${gdh.id}</td>
									<td>${gdh.fromDate}</td>
									<td>${gdh.toDate}</td>
									<td>${gdh.isActive}</td>
									<td>${gdh.type}</td>
									<td>${gdh.bonusAmount}</td>
									<td>${gdh.partnerId}</td>
									<td>${gdh.externalParam}</td>
									<td style="text-align: left;">${gdh.description}</td>
									<td><a href="getGoldenHour.html?goldenhourId=${gdh.id}">Sửa</a></td>
									<td><a href="#" onclick="warningDelete(${gdh.id});">Xóa</a></td>
								</tr>
							</logic:iterate>
						</table>
						<br/>
						<table style="width: 100% ; border: 1px; border-color: green; float: left;">
							<%
								int pageC =  Integer.parseInt(request.getAttribute("page").toString());
							%>
							<tr style="background-color: #E5E9F0">
								<td width="100%">
								<div style="width:600px;">
									<div  style="width:40px; float: left; padding-top: 2px;">
										<a href="#" style="text-decoration: none;" onclick="getDataFirstPage();">|<< </a>
										<a href="#" style="text-decoration: none;  margin-left: 6px;" onclick="backPageData();"> < </a>
									</div>
									<div style="width: 90px;float: left; margin-left: 6px;">
										<div style="float: left; width: 50px;">
											<input id="pageIndex" size="2" type="text" style="text-align: center;" value="<% out.print(pageC);%>"/>
										</div>
										<div style="float: left; width: 40px; ">
											<img  src="${pageContext.request.contextPath}/res/img/search.gif" style="cursor:hand;padding-top:2px ; cursor:pointer; color:#0000FF;" onclick = "getDataPageIndex();"/>
										</div>
									</div>
									<div style="width:450px;float: left;">
										<div style="width: 445px; margin-top: 2px;">
										<a href="#" style="text-decoration: none; float: left; margin-left: 6px" onclick="nextPageData();"> > </a>
										<a href="#" style="text-decoration: none; float: left; margin-left: 6px" onclick="getDataLastPage();" id="lastPage"> >>|</a>
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số trang ${gdh.totalPage}</a>&nbsp&nbsp
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số bản ghi ${gdh.totalRecord}</a>&nbsp&nbsp
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
<%@include file="inc/coda.jsp" %>
<script type="text/javascript" >
	function warningDelete(questionId) { 
		var v = confirm("Bạn có chắc chắn muốn xóa ?");
		if( v == true) { 
			window.location.href="deleteGoldenhour.html?goldenhourId=" + questionId;
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		if( pageIndex >= 1 && pageIndex <= ${gdh.totalPage}) {
			window.location.href="goldenhourList.html?page=" + pageIndex + "&menuItem=goldH";
		} else {
			alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${gdh.totalPage});
		}
	}
	function getDataFirstPage() {
		window.location.href="goldenhourList.html?page=1&menuItem=goldH";
	}
	function getDataLastPage() {
		window.location.href="goldenhourList.html?page="+ ${gdh.totalPage} + "&menuItem=goldH";
	}
	function backPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage =  ${gdh.totalPage};
		pageIndex--;
		if( pageIndex >= 1) { 
			window.location.href="goldenhourList.html?page=" + pageIndex  + "&menuItem=goldH";
		} else {
			alert("Bạn đang ở đầu trang");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage =  ${gdh.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			window.location.href="goldenhourList.html?page=" + pageIndex  + "&menuItem=goldH";
		} else {
			alert("Đã hết trang!");
		}
	}
	
</script>
