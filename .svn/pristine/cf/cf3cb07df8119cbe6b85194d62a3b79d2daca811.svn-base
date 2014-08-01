<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../inc/prelude.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>
<c:if test="${requestScope.message != null}" >
    <tr>
        <td align="center">        
            <br/>
            <font color="red" >
            <c:out value="${requestScope.message}" />
            </font>        
        </td>
    </tr>
</c:if>
<tr>
	
    <td align="center">
    	<div style="float: left;"><jsp:include page="../../inc/menu.jsp"  /></div>
    	<div style="float: left; padding-left: 34px; margin-left: 16px">
    		<div style="clear: both; float: left; width:100% ; font-size: large; font-style: italic; background-color: #E5E9F0;">
				<form action="addSuperUser.html" method="get" style="float: left">
					<table>
					<tr>
						<td>Thêm thành viên Super User:</td>
						<td><input type="text" name="name" /></td>
						<td><input type="submit" value="Thêm" /></td>
					</tr>
				</table>
				</form>
			</div>
    		<h:form action="superuserslist.html" method="post" styleId="form">
            <table style="width: 1024px" align="center">      
            <c:if test="${requestScope.userslist != null}" > 
                <tr bgcolor="#E5E9F0">
                    <td width="6%" align="center"><b:message key="admin.superaccount.id"/></td>
                    <td width="10%" align="center"><b:message key="admin.superaccount.name"/></td>
                    <td width="15%" align="center"><b:message key="admin.superaccount.money"/></td>
                    <td width="15%" align="center"><b:message key="admin.superaccount.playing"/></td>
                    <td width="15%" align="center"><b:message key="admin.superaccount.lastLogin"/></td>
                    <td width="15%" align="center"><b:message key="admin.superaccount.active"/></td>
                    <td width="5%" align="center"></td>
                </tr>              
                <c:forEach items="${requestScope.userslist}"  var="record">                     
                    <tr>     
                        <td align="center">
                            <c:out value="${record.id}" />
                        </td>                                                   
                        <td align="center">
                            <c:out value="${record.username}" />
                        </td >   
                        <td align="center">
                            <c:out value="${record.money}" />
                        </td>  
                        <td align="center">                            
                            <c:out value="${record.online}" />                            
                        </td>
                        <td align="center">                            
                            <c:out value="${record.lastLogin}" />                            
                        </td>
                        <td align="center" style="text-align: center;">                            
                            <c:out value="${record.active}" />                            
                        </td>
                        <td align="center" style="text-align: center;"><a href="#" onclick="warningDelete(${record.id});">Xóa</a></td>
                    </tr>                                  
                </c:forEach>
                </c:if> 
            </table>                    
        </h:form>
        <br/>
						<table style="width: 1024px ; border: 1px; border-color: green; float: left;">
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
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số trang ${requestScope.totalPage}</a>&nbsp&nbsp
										<a style="text-decoration: none; float: left; margin-left: 20px">Tổng số bản ghi ${requestScope.totalRecord}</a>&nbsp&nbsp
										</div>
									</div>
								</div>
								</td>
							</tr>
						</table>
    	</div>
    </td>
</tr>
<%@include file="../../inc/coda.jsp" %>
<script type="text/javascript" >
	function warningDelete(questionId) { 
		var v = confirm("Bạn có chắc chắn muốn xóa tài khoản này ?");
		if( v == true) { 
			window.location.href="deleteSuperUser.html?userId=" + questionId;
		} else {
			
		}
	}
	function getDataPageIndex() {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${requestScope.totalPage};
		if( pageIndex >= 1 && pageIndex <= ${requestScope.totalPage}) {
			window.location.href="superuserslist.html?action=prepareReport&page=" + pageIndex;
		} else {
			alert("Bạn phải nhập trong khoảng từ: 1 =>" + ttPage);
		}
	}
	function getDataFirstPage() {
		window.location.href="superuserslist.html?action=prepareReport&page=1";
	}
	function getDataLastPage() {
		window.location.href="superuserslist.html?action=prepareReport&page=" + ${requestScope.totalPage};
	}
	function backPageData () {
		/* alert("pageIndex = " + pageIndex); */
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${requestScope.totalPage};
		pageIndex--;
		if( pageIndex >= 1) { 
			window.location.href="superuserslist.html?action=prepareReport&page=" + pageIndex;
		} else {
			alert("Bạn đang ở đầu trang");
		}
	}
	function nextPageData () {
		var pageIndex = document.getElementById('pageIndex').value;
		var ttPage = ${requestScope.totalPage};
		pageIndex++;
		if( pageIndex >= 1 && pageIndex <= ttPage) {
			window.location.href="superuserslist.html?action=prepareReport&page=" + pageIndex;
		} else {
			alert("Đã hết trang!");
		}
	}
	
</script>
