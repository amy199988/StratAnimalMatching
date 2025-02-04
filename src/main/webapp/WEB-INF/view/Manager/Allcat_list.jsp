<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cat list</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>貓咪列表</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>ID</th><th>名稱</th><th>花紋</th><th>年齡</th><th>健康狀況</th>
							<th>描述</th><th>照片</th><th>狀態</th><th>屬於中途</th><th>修改</th><th>刪除</th>
						</tr>
					</thead>
					<c:if test="${not empty catDtos}">
						<c:forEach var="catDto" items="${catDtos}">
						<tr>
							<td>${ catDto.catId }</td>
							<td>${ catDto.catName }</td>
							<td>${ catDto.breed }</td>
							<td>${ catDto.age }</td>
							<td>${ catDto.healthStatus }</td>
							<td>${ catDto.description }</td>
							<c:if test="${not empty catDto.catphoto_Url}">
							<td>有</td>
							</c:if>
							<c:if test="${empty catDto.catphoto_Url}">
							<td>照片Url沒有資料</td>
							</c:if>
							<td>${ catDto.isApply }</td>
							<td>${ catDto.lovehome.lovehomeName }</td>
							<td><a href="/cat/update?catId=${catDto.catId}" class="button-secondary pure-button">修改</a></td>
							<td><a href="/cat/delete?catId=${catDto.catId}" class="button-error pure-button">刪除</a></td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
			</fieldset>
		</div>
	</body>
</html>