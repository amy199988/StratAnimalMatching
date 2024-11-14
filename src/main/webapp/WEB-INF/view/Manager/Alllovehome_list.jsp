<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lovehome list</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>中途之家列表</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>ID</th><th>名稱</th><th>城市</th><th>區域</th><th>詳細地址</th>
							<th>聯絡方式</th><th>可收容量</th><th>目前佔用率</th><th>照片</th><th>修改</th><th>刪除</th>
						</tr>
					</thead>
					<c:if test="${not empty lovehomeDtos}">
						<c:forEach var="lovehomeDto" items="${lovehomeDtos}">
						<tr>
							<td>${ lovehomeDto.lovehomeId }</td>
							<td>${ lovehomeDto.lovehomeName }</td>
							<td>${ lovehomeDto.lovehomeCity }</td>
							<td>${ lovehomeDto.lovehomeDistrict }</td>
							<td>${ lovehomeDto.lovehomeAddress }</td>
							<td>${ lovehomeDto.contactInfo }</td>
							<td>${ lovehomeDto.capacity }</td>
							<td>${ lovehomeDto.currentOccupancy }</td>
							<c:if test="${not empty lovehomeDto.lovehome_Url}">
							<td>有</td>
							</c:if>
							<c:if test="${empty lovehomeDto.lovehome_Url}">
							<td>照片Url沒有資料</td>
							</c:if>
							<td><a href="/lovehome/update?lovehomeId=${lovehomeDto.lovehomeId}" class="button-secondary pure-button">修改</a></td>
							<td><a href="/lovehome/delete?lovehomeId=${lovehomeDto.lovehomeId}" class="button-error pure-button">刪除</a></td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
			</fieldset>
		</div>
	</body>
</html>