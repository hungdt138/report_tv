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
			<html:form action="addQuestion.html" method="post">
				<table>
					<tr>
						<td>Nội dung câu hỏi:</td>
						<td><html:textarea style="width: 400px; height: 80px" property="detail"/> </td>
					</tr>
					<tr>
						<td>Lựa chọn 1:</td>
						<td><html:text property="choix1" value="" /></td>
					</tr>
					<tr>
						<td>Lựa chọn 2:</td>
						<td><html:text property="choix2" value="" /></td>
					</tr>
					
					<tr>
						<td>Lựa chọn 3:</td>
						<td><html:text property="choix3" value="" /></td>
					</tr>
					<tr>
						<td>Lựa chọn 4:</td>
						<td><html:text property="choix4" value="" /></td>
					</tr>
					<tr>
						<td>Đáp án đúng:</td>
						<td>
							<select name="answer">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>Mã cấp độ:</td>
						<td><html:text property="levelId" value=""/></td>
					</tr>
					<tr>
						<td>Lĩnh vực văn hóa:</td>
						<td><html:text property="domainId" value=""/><br/>
							(Mặc định là 0)
						</td>
						<td></td>
					</tr>
					<tr style="visibility: hidden;">
						<td><input type="text" /></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td style="text-align: left;"><input type="submit" value="Thêm mới"/>&nbsp;&nbsp;<input type="button" onclick="backList();" value="Thoát"/></td>
						<td></td>
					</tr>
				</table>
			</html:form>
		</div>
	</td>
</tr>
<%@include file="inc/coda.jsp"%>
<<script type="text/javascript">
 function backList() {
	 window.location.href="questionList.html?page=1&menuItem=altp";
 }
</script>

