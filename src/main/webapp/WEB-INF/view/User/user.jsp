<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>使用者頁面</legend>
				<a href="/" class="button-secondary pure-button">修改會員資料</a>
				<a href="/" class="button-secondary pure-button">修改密碼</a>
				<a href="/" class="button-secondary pure-button">申請領養資料</a>
				<a href="/" class="button-secondary pure-button">通報救援資料</a>
				<a href="/" class="button-secondary pure-button">物資捐贈資料</a>
			</fieldset>
		</div>
	</body>
</html>