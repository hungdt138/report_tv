<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="l" uri="http://struts.apache.org/tags-logic" %>

	<table style="width: 770px ; border: 1px; border-color: green;">
							<tr style="background-color: #E5E9F0">
								<th>Mã câu hỏi</th>
								<th>Tên câu hỏi</th>
								<th>Đáp án A:</th>
								<th>Đáp án B:</th>
								<th>Đáp án C</th>
								<th>Đáp án D</th>
								<th>Đáp án đúng:</th>
								<th>Level ID</th>
								<th>Lĩnh vực</th>
							</tr>
							<logic:iterate id="q" name="QuestionListForm" property="qlForm" scope="request">
								<tr style="text-align: center;">
									<td>${q.questionId}</td>
									<td>${q.detail}</td>
									<td>${q.choix1}</td>
									<td>${q.choix2}</td>
									<td>${q.choix3}</td>
									<td>${q.choix4}</td>
									<td>${q.answer}</td>
									<td>${q.levelId}</td>
									<td>${q.domainId}</td>
									<td><a href="${q.questionId}">Sửa</a></td>
									<td><a href="#" onclick="warningDelete(${q.questionId});">Xóa</a></td>
								</tr>
							</logic:iterate>
						</table>