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
				<legend>合作中途之家列表</legend>
				<table class="pure-table pure-table-bordered">
					<tr><td>中途之家照片</td></tr>
					<tr><td>中途之家名稱</td></tr>
					<tr><td>中途之家地址</td></tr>
					<tr><td>聯絡方式</td></tr>
					<tr><td>中途之家描述</td></tr>
				</table>
			</fieldset>
		</div>
	</body>
</html>