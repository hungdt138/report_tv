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
					   &nbsp&nbsp<a style="text-decoration: none; float: left; color: green;" href="add_advertising.jsp">Thêm mới một quảng cáo</a> &nbsp&nbsp 
					</div>
					<div style="clear: both; float: left; margin-bottom: 60px;">
						<table style="width: 100% ; border: 1px; border-color: green;">
							<tr style="background-color: #E5E9F0">
								<th width="2%">Mã quảng cáo</th>
								<th width="30%">Nội dung quảng cáo</th>
								<th width="12%">Ngày tạo</th>
								<th width="5%">Trạng thái</th>
								<th width="5%">Đối tác</th>
								<th width="12%">Ngày bắt đầu</th>
								<th width="12%">Ngày kết thúc</th>
								<th width="5%"></th>
								<th width="5%"></th>
							</tr>
							<logic:iterate id="adv" name="AdvertisingListForm" property="advList" scope="request">
								<tr style="text-align: center;">
									<td>${adv.advertisingId}</td>
									<td style="text-align: left;">${adv.content}</td>
									<td>${adv.createdDate}</td>
									<td>${adv.display}</td>
									<td>${adv.partnerId}</td>
									<td>${adv.startDate}</td>
									<td>${adv.endDate}</td>
									<td><a href="getAdvertising.html?advertisingId=${adv.advertisingId}">Sửa</a></td>
									<td><a href="#" onclick="warningDelete(${adv.advertisingId});">Xóa</a></td>
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
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số trang ${adv.totalPage}</a>&nbsp&nbsp
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số bản ghi ${adv.totalRecord}</a>&nbsp&nbsp
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
		var v = confirm("Bạn có chắc chắn muốn xóa ? ");
		if( v == true) { 
			window.location.href="deleteAdvertising.html?advertisingId=" + questionId;
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		if( pageIndex >= 1 && pageIndex <= ${adv.totalPage}) {
			window.location.href="advertisingList.html?page=" + pageIndex +"&menuItem=adv";
		} else {
			alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${adv.totalPage});
		}
	}
	function getDataFirstPage() {
		window.location.href="?page=1";
	}
	function getDataLastPage() {
		window.location.href="advertisingList.html?page="+${adv.totalPage} +"&menuItem=adv";
	}
	function backPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${adv.totalPage};
		pageIndex--;
		if( pageIndex >= 1) { 
			window.location.href="advertisingList.html?page=" + pageIndex  + "&menuItem=adv";
		} else {
			alert("Bạn đang ở đầu trang");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${adv.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			window.location.href="advertisingList.html?page=" + pageIndex + "&menuItem=adv";
		} else {
			alert("Đã hết trang!");
		}
	}
	
</script>
