<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="inc/prelude.jsp"%>

<tr style="width: 100%">
	<td align="center" style="border-color: green;">
		<div style="float: left; width: 200px; background-color: white;">
			<jsp:include page="inc/menu.jsp" />
		</div>
		<div
			style="float: left; width: 600px; background-color: white; margin-left: 300px;">		
			<html:form action="updateQuestion.html" method="post">
				<table>
					<tr style="visibility: hidden;">
						<td><html:text property="questionId" value="${requestScope.QuestionForm.questionId}"/></td>
					</tr>
					<tr>
						<td>Mã câu hỏi:</td>
						<td><input disabled="disabled" type="text" value="${requestScope.QuestionForm.questionId}"/> </td>
					</tr>
					<tr>
						<td>Nội dung câu hỏi:
							<br/>
							<input  name="answerMain" type="text" value="${requestScope.QuestionForm.answer}" style="visibility: hidden;"/>
						</td>
						<td><html:textarea property="detail" style="width: 400px; height: 80px" value="${requestScope.QuestionForm.detail}" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Lựa chọn 1:</td>
						<td><html:text property="choix1"  value="${requestScope.QuestionForm.choix1}"/></td>
					</tr>
					<tr>
						<td>Lựa chọn 2:</td>
						<td><html:text property="choix2"  value="${requestScope.QuestionForm.choix2}"/></td>
					</tr>
					
					<tr>
						<td>Lựa chọn 3:</td>
						<td><html:text property="choix3"  value="${requestScope.QuestionForm.choix3}"/></td>
					</tr>
					<tr>
						<td>Lựa chọn 4:</td>
						<td><html:text property="choix4" value="${requestScope.QuestionForm.choix4}"/></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Đáp án đúng:</td>
						<td>
							<%-- Đáp án đúng đang là: ${requestScope.QuestionForm.answer} &nbsp;&nbsp; Chọn lại là:&nbsp;&nbsp;  --%>
							<select name="answer" >
								<%
									int aw = Integer.parseInt(request.getAttribute("aw").toString());
									for(int i = 1 ; i <= 4; i++) {
										if( aw == i){
										%>
											<option value="<%out.print(aw); %>" selected="selected" ><%out.print(aw);%></option>
										<%
										} else {
											%>
												<option value="<%out.print(i); %>"><%out.print(i); %></option>
											<%
										}
									}
								%>
							</select>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>Mã cấp độ:</td>
						<td><html:text property="levelId"  value="${requestScope.QuestionForm.levelId}"/></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Lĩnh vực văn hóa:</td>
						<td>
							<html:text property="domainId"  value="${requestScope.QuestionForm.domainId}"/>&nbsp;&nbsp;
							( Mặc định là 0 )
						</td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left;"><input type="submit" value="Cập nhật"/>&nbsp&nbsp<input type="button" onclick="backPage();" value="Thoát"/></td>
						<td></td>
					</tr>
				</table>
			</html:form>
		</div>
	</td>
</tr>
<%@include file="inc/coda.jsp"%>
<script type="text/javascript">
	function backPage() {
		window.location.href="questionList.html?page=1&m=altp";
	}
</script>
