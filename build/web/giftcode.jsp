<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="logic"  uri="http://struts.apache.org/tags-logic"%>
<%@include file="inc/prelude.jsp" %>
<%
    if (request.getAttribute("ttRc") == null) {
        request.setAttribute("msg_notifi", "Truy cập không hợp lệ! <br/> Bạn hãy kiểm tra lại");
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
                            <td style="background-color: #E5E9F0; font-weight: bold; width: 130px">Mã giftcode:</td>
                            <td style="width: 285px"><input type="text" size="35px" name="giftCodeId" value="${requestScope.GiftCodeForm.giftCodeId}"/></td>
                            <td style="background-color: #E5E9F0; font-weight: bold; width: 125px">Mệnh giá:</td>
                            <td style=" width: 235px"><input name="bonusMoney" size="35px" type="text"/></td>
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
                    <tr align="center">
                        <td width="10%">${requestScope.GiftCodeForm.giftCodeId}</td>
                        <td width="12%">${requestScope.GiftCodeForm.serial}</td>
                        <td width="20%">${requestScope.GiftCodeForm.createdDate}</td>
                        <td width="20%">${requestScope.GiftCodeForm.usedDate}</td>
                        <td width="10%">${requestScope.GiftCodeForm.bonusMoney}</td>
                        <td width="10%">${requestScope.GiftCodeForm.marketingChannelId}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</td>
</tr>
<%@include file="inc/coda.jsp" %>
