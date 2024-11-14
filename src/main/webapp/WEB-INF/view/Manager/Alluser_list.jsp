<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User list</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>使用者列表</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>ID</th><th>名稱</th><th>帳號</th><th>電話</th><th>生日</th>
							<th>電子郵件</th><th>帳號認證</th><th>帳號權限</th><th>修改</th><th>刪除</th>
						</tr>
					</thead>
					<c:if test="${not empty userDtos}">
						<c:forEach var="userDto" items="${userDtos}">
						<tr>
							<td>${ userDto.userId }</td>
							<td>${ userDto.userName }</td>
							<td>${ userDto.account }</td>
							<td>${ userDto.phone }</td>
							<td>${ userDto.birthdate }</td>
							<td>${ userDto.email }</td>
							<td>${ userDto.active }</td>
							<td>${ userDto.role }</td>
							<td><a href="/user/update?userId=${userDto.userId}" class="button-secondary pure-button">修改</a></td>
							<td><a href="/user/delete?userId=${userDto.userId}" class="button-error pure-button">刪除</a></td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
			</fieldset>
		</div>
	</body>
</html>