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
										<td width="150px" style="background-color: #E5E9F0">Mã người dùng:</td>
										<td width="100px"><input type="text" name="userId"/></td>
										<td width="150px" style="background-color: #E5E9F0">Tên tài khoản</td>
										<td width="100px"><input name="name" type="text" value="<% out.print( request.getAttribute("qname").toString()); %>"/></td>
										<td width="10px"><input type="submit" value="Tìm kiếm"/></td>
									</tr>
									<tr>
										<td style="background-color: #E5E9F0">Tổng số bản ghi:</td>
										<td><% out.print(request.getAttribute("totalRc").toString()); %></td>
									</tr>
								</table>
							</form>
					</div>
					<div style="clear: both; float: left; margin-bottom: 60px;">
						<table style="width: 100% ; border: 1px; border-color: green; float: left;">
							<tr style="background-color: #E5E9F0">
								<th width="13%">Mã người dùng</th>
								<th width="18%">Tên tài khoản</th>
								<th width="7%">Trạng thái</th>
								<th width="17%">Ngày đăng kí</th>
								<th width="10%">Mã tham chiếu</th>
								<th width="6%">Đối tác</th>
								<th width="19%">Lần đăng nhập cuối</th>
								<th width="13%"></th>
							</tr>
							<logic:iterate id="wu" name="WorkingUserListByNameForm" property="wuList" scope="request">
								<tr style="text-align: center;">
									<td>${wu.userId}</td>
									<td>${wu.name}</td>
									<td>${wu.stateOnline}</td>
									<td>${wu.registerDate}</td>
									<td>${wu.refCode}</td>
									<td>${wu.partnerId}</td>
									<td>${wu.lastLongin}</td>
									<td><a href="getAWorkingUser.html?userId=${wu.userId}">Đổi mật khẩu</a></td>
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
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số trang ${wu.totalPage}</a>&nbsp&nbsp
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số bản ghi ${wu.totalRecord}</a>&nbsp&nbsp
										</div>
									</div>
								</div>
								</td>
							</tr>
						</table><br/>
						<input id="nameQ" type="text" style="visibility: hidden;" value="${wu.nameQ}"/>
					</div>
					</div>
			</div>
    </td>
    
</tr>
<%@include file="inc/coda.jsp" %>
<script type="text/javascript" >
	function warningDelete(questionId) { 
		var v = confirm("Are you sure!");
		if( v == true) { 
			window.location.href="deleteGiftCode.html?giftcodeId=" + questionId;
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		if( pageIndex >= 1 && pageIndex <= ${wu.totalPage}) {
			var name = document.getElementById('nameQ').value;
			window.location.href="workingUserListByName.html?page=" + pageIndex + "&menuItem=wuser&searchbyname="+name;
		} else {
			alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${wu.totalPage});
		}
	}
	function getDataFirstPage() {
		var name = document.getElementById('nameQ').value;
		window.location.href="workingUserListByName.html?page=1&menuItem=wuser&searchbyname=" +name;
	}
	function getDataLastPage() {
		var name = document.getElementById('nameQ').value;
		window.location.href="workingUserListByName.html?page="+${wu.totalPage} + "&menuItem=wuser&searchbyname="+name;
	}
	function backPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${wu.totalPage};
		pageIndex--;
		if( pageIndex >= 1) {
			var name = document.getElementById('nameQ').value;
			window.location.href="workingUserListByName.html?page=" + pageIndex + "&menuItem=wuser&searchbyname="+name;
		} else {
			alert("Bạn đang ở đầu trang");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${wu.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			var name = document.getElementById('nameQ').value;
			window.location.href="workingUserListByName.html?page=" + pageIndex + "&menuItem=wuser&searchbyname="+name;
		} else {
			alert("Đã hết trang!");
		}
	}
	
</script>
