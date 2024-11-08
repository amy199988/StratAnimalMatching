<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Love MoM</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>愛媽頁面</legend>
				<a href="/" class="button-secondary pure-button">會員管理</a>
				<a href="/" class="button-secondary pure-button">修改會員資料</a>
				<a href="/" class="button-secondary pure-button">修改密碼</a>
				<a href="/catlist" class="button-secondary pure-button">貓咪清單管理</a>
				<a href="/" class="button-secondary pure-button">申請領養清單管理</a>
				<a href="/" class="button-secondary pure-button">通報清單管理</a>
				<a href="/" class="button-secondary pure-button">中途之家管理</a>
			</fieldset>
		</div>
	</body>
</html>