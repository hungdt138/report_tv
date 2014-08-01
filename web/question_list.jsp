<%@page contentType="text/html" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@include file="inc/prelude.jsp" %>
<tr style="width:100%">
    <td align="center" style="border-color: green;">
        <div style="float: left; background-color: white;">
            <jsp:include page="inc/menu.jsp"/>
        </div>
        <div style="float: left; border: 1px; width: 80%; margin-left: 28px">
            <div style="clear: both; float: left; width:100%; font-size: large; font-style: italic; background-color: #E5E9F0;">
                &nbsp&nbsp<a style="text-decoration: none; float: left; color: green;" href="add_question.jsp">Thêm mới
                một câu hỏi</a> &nbsp&nbsp
            </div>
            <div style="clear: both; float: left; margin-bottom: 60px;">
                <div class="filter-container" style="margin-top: 10px">
                    <form method="get">
                        <input type="hidden" name="page" value="1" />
                        <input type="hidden" name="menuItem" value="altp" />

                        <div class="form-group float-left">
                            <label>Nội dung câu hỏi</label>
                            <input type="text" name="keyword" value="${param.keyword}"/>
                        </div>

                        <div class="form-group">
                            <input type="submit" value="Lọc"/>
                            <p>Vui lòng nhập câu hỏi từ đầu câu(vd "Ai là người đầu tiên tìm ra châu Mỹ", nhập "Ai là người", không nhập "tìm ra châu Mỹ")</p>
                        </div>
                        <table style="width: 100% ; border: 1px; border-color: green;">
                            <tr style="background-color: #E5E9F0">
                                <th width="2%">Mã câu hỏi</th>
                                <th width="28%%">Nội dung câu hỏi</th>
                                <th width="10%">Đáp án A:</th>
                                <th width="10%">Đáp án B:</th>
                                <th width="10%">Đáp án C</th>
                                <th width="10%">Đáp án D</th>
                                <th width="10%">Đáp án đúng:</th>
                                <th width="5%">Level ID</th>
                                <th width="5%">Lĩnh vực</th>
                                <th width="5%"></th>
                                <th width="5%"></th>
                            </tr>
                            <logic:iterate id="q" name="QuestionListForm" property="qbList" scope="request">
                                <tr style="text-align: center;">
                                    <td>${q.questionId}</td>
                                    <td style="text-align: left;">${q.detail}</td>
                                    <td>${q.choix1}</td>
                                    <td>${q.choix2}</td>
                                    <td>${q.choix3}</td>
                                    <td>${q.choix4}</td>
                                    <td>${q.answer}</td>
                                    <td>${q.levelId}</td>
                                    <td>${q.domainId}</td>
                                    <td><a href="getAQuestion.html?questionId=${q.questionId}">Sửa</a></td>
                                    <td><a href="#" onclick="warningDelete(${q.questionId});">Xóa</a></td>
                                </tr>
                            </logic:iterate>
                        </table>
                        <br/>
                        <table style="width: 100% ; border: 1px; border-color: green; float: left;">
                            <%
                                int pageC = Integer.parseInt(request.getAttribute("page").toString());
                            %>
                            <tr style="background-color: #E5E9F0">
                                <td width="100%">
                                    <div style="width:600px;">
                                        <div style="width:40px; float: left; padding-top: 2px;">
                                            <a href="#" style="text-decoration: none;"
                                               onclick="getDataFirstPage();">|<< </a>
                                            <a href="#" style="text-decoration: none;  margin-left: 6px;"
                                               onclick="backPageData();"> < </a>
                                        </div>
                                        <div style="width: 90px;float: left; margin-left: 6px;">
                                            <div style="float: left; width: 50px;">
                                                <input id="pageIndex" size="2" type="text" style="text-align: center;"
                                                       value="<% out.print(pageC);%>"/>
                                            </div>
                                            <div style="float: left; width: 40px; ">
                                                <img src="${pageContext.request.contextPath}/res/img/search.gif"
                                                     style="cursor:hand;padding-top:2px ; cursor:pointer; color:#0000FF;"
                                                     onclick="getDataPageIndex();"/>
                                            </div>
                                        </div>
                                        <div style="width:450px;float: left;">
                                            <div style="width: 445px; margin-top: 2px;">
                                                <a href="#" style="text-decoration: none; float: left; margin-left: 6px"
                                                   onclick="nextPageData();"> > </a>
                                                <a href="#" style="text-decoration: none; float: left; margin-left: 6px"
                                                   onclick="getDataLastPage();" id="lastPage"> >>|</a>
                                                <a style="text-decoration: none; float: left; margin-left: 20px">Tổng số
                                                    trang ${q.totalPage}</a>&nbsp&nbsp
                                                <a style="text-decoration: none; float: left; margin-left: 20px">Tổng số
                                                    bản ghi ${q.totalRecord}</a>&nbsp&nbsp
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
<script type="text/javascript">
    function warningDelete(questionId) {
        var v = confirm("Bạn có chắc chắn muốn xóa ?");
        if (v == true) {
            window.location.href = "deleteQuestion.html?questionId=" + questionId + "&menuItem=altp";
        } else {

        }
    }
    function getDataPageIndex() {
        var pageIndex = document.getElementById('pageIndex').value;
        if (pageIndex >= 1 && pageIndex <= ${q.totalPage}) {
            window.location.href = "questionList.html?page=" + pageIndex + "&menuItem=altp&keyword=${param.keyword}";
        } else {
            alert("Bạn phải nhập trong khoảng từ: 1 =>" + ${q.totalPage});
        }
    }
    function getDataFirstPage() {
        window.location.href = "questionList.html?page=1&menuItem=altp&keyword=${param.keyword}";
    }
    function getDataLastPage() {
        window.location.href = "questionList.html?page=" + ${q.totalPage} +"&menuItem=altp&keyword=${param.keyword}";
    }
    function backPageData() {
        var pageIndex = document.getElementById('pageIndex').value;
        var ttPage = ${q.totalPage};
        pageIndex--;
        if (pageIndex >= 1) {
            window.location.href = "questionList.html?page=" + pageIndex + "&menuItem=altp&keyword=${param.keyword}";
        } else {
            alert("Bạn đang ở đầu trang");
        }
    }
    function nextPageData() {
        var pageIndex = document.getElementById('pageIndex').value;
        var ttPage = ${q.totalPage};
        pageIndex++;
        if (pageIndex >= 1 && pageIndex <= ttPage) {
            window.location.href = "questionList.html?page=" + pageIndex + "&menuItem=altp&keyword=${param.keyword}";
        } else {
            alert("Đã hết trang!");
        }
    }

</script>
