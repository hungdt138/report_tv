<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<%
	if(request.getAttribute("moneyS") == null) { 
		request.setAttribute("msg_notifi", "Truy cập không hợp lệ " + "moneyS: " + request.getAttribute("moneyS"));
		request.getRequestDispatcher("/failure_notifi.jsp").forward(request, response);
	} 
%>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
    	<div style="float: left; background-color: white;">
    		<jsp:include page="inc/menu.jsp"/>
    	</div>
		<div style="float: left; border: 1px; width: 1000px; margin-left: 100px; padding-left: 11px">
					<div style="clear: both; float: left; width:877px; font-size: large; font-style: italic; background-color: #E5E9F0;">
					   &nbsp;&nbsp;<a style="text-decoration: none; float: left; color:green ;" href="add_giftcode.jsp">Thêm mới một số lượng thẻ cào</a> &nbsp;&nbsp; 
					</div>
					<div style="clear: both; float: left; margin-bottom: 5px; width: 877px">
							<form action="getGiftCode.html" method="post" style="float: left;">
								<table style="width: 877px">
									<tr>
										
										<!-- 
										<td style="background-color: #E5E9F0; font-weight: bold; width: 130px">Mã giftcode:</td>
										<td style="width: 285px"><input type="text" size="35px" name="giftCodeId"/></td>
									    -->
									    
										<input type="hidden" name="giftCodeId" value="" /> 
										<td style="background-color: #E5E9F0; font-weight: bold; width: 125px">Mệnh giá:</td>
										<td style=" width: 235px">
										<input name="bonusMoney" size="35px" type="text" value="${requestScope.moneyS}"/></td>
										<td><input type="submit" value="Tìm kiếm"/></td>
									</tr>
									<tr>
										<td style="background-color: #E5E9F0; font-weight: bold; width: 130px">Tổng bản ghi:</td>
										<td> <% out.print(request.getAttribute("ttRc"));%></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</form>
					</div>
					<div style="clear: both ; float: left; margin-bottom: 60px;">
						<table style="width: 877px ; border: 1px; border-color: green;">
							<tr style="background-color: #E5E9F0">
								<th width="10%">Mã GiftCode</th>
								<th width="12%">Serial</th>
								<th width="20%">Ngày tạo</th>
								<th width="20%">Ngày sử dụng</th>
								<th width="10%">Mệnh giá</th>
								<th width="10%">Mã giao dịch</th>
							</tr>
							<logic:iterate id="gc" name="GiftCodeListForm" property="gcList" scope="request">
								<tr style="text-align: center;">
									<td>${gc.giffCodeId}</td>
									<td>${gc.serial}</td>
									<td>${gc.createdDate}&nbsp;&nbsp;${gc.createdTime}</td>
									<td>
										<c:if test="${gc.usedDate != null}">
											${gc.usedDate}&nbsp;&nbsp;${gc.usedTime}
										</c:if>
										<c:if test="${gc.usedDate == null}">
											NULL
										</c:if>	
									</td>
									<td>${gc.bonusMoney}</td>
									<td>${gc.marketingChannelId}</td>
								</tr>
							</logic:iterate>
						</table>
						<br/>
						<table style="width: 877px ; border: 1px; border-color: green; float: left;">
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
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số trang ${gc.totalPage}</a>&nbsp;&nbsp;
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số bản ghi ${gc.totalRecord}</a>&nbsp;&nbsp;
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
			window.location.href="deleteGiftCode.html?giftcodeId=" + questionId + "&menuItem=giftL";
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		if( pageIndex >= 1 && pageIndex <= ${gc.totalPage}) {
			window.location.href="getGiftCodeByName.html?page=" + pageIndex +"&money="+ ${requestScope.moneyS}+ "&menuItem=giftL";
		} else {
			alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${gc.totalPage});
		}
	}
	function getDataFirstPage() {
		window.location.href="getGiftCodeByName.html?page=1&money="+ ${requestScope.moneyS}+ "&menuItem=giftL";
	}
	function getDataLastPage() {
		window.location.href="getGiftCodeByName.html?page="+${gc.totalPage} +"&money="+ ${requestScope.moneyS}+  "&menuItem=giftL";
	}
	function backPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${gc.totalPage};
		pageIndex--;
		if( pageIndex >= 1) { 
			window.location.href="getGiftCodeByName.html?page=" + pageIndex  +"&money="+ ${requestScope.moneyS}+"&menuItem=giftL";
		} else {
			alert("Bạn đang ở đầu trang");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${gc.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			window.location.href="getGiftCodeByName.html?page=" + pageIndex  +"&money="+ ${requestScope.moneyS}+  "&menuItem=giftL";
		} else {
			alert("Đã hết trang!");
		}
	}
	
</script>
