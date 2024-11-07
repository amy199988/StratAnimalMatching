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
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>領養表單</legend>
				<table class="pure-table pure-table-bordered">
					<tr><td>貓咪照片</td></tr>
					<tr><td>貓咪名稱：小八</td></tr>
					<tr><td>貓咪花紋：八字瀏海</td></tr>
					<tr><td>貓咪年齡：5歲</td></tr>
					<tr><td>特殊狀況：無</td></tr>
					<tr><td>詳細描述：性格開朗、樂觀，並且非常健談。</td></tr>
				</table>
			</fieldset>
		</div>
	</body>
</html>