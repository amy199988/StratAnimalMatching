<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Adoption</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>頁面功能</legend>
				<a href="/cat_add" class="button-secondary pure-button">新增領養貓咪</a>
			</fieldset>
			<fieldset>
				<legend>貓咪列表</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>ID</th><th>名稱</th><th>花紋</th><th>年齡</th><th>健康狀況</th>
							<th>描述</th><th>狀態</th><th>修改</th>
						</tr>
					</thead>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><a href="/cat_update" class="button-secondary pure-button">修改</a></td>
						</tr>
				</table>
			</fieldset>
		</div>
	</body>
</html>