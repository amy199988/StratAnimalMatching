<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!-- 核心庫 -->


		<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Login</title>
			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
			<link rel="stylesheet"
				href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
			<link rel="stylesheet" href="/css/buttons.css">
			
			</head>
			<body>
				<!-- menu bar include -->
				<%@ include file="/WEB-INF/view/menu.jspf"%>
				<div class="pure-form" style="padding: 15px;">
					<form class="pure-form" method="post"
					style="
					height: 100%;
					margin: 0;
					display: flex;
					justify-content: center;
					align-items: center;
					text-align: center
					">
						
			<fieldset>
				<legend>✨會員登入✨</legend>
				🐈🐈‍⬛：<input type="text" name="username" placeholder="請輸入帳號"
					required />
				<p />
				🔒🔑：<input type="password" name="password" placeholder="請輸入密碼"
					required />
				<p />
				<button type="submit" class="pure-button pure-button-primary">登入</button>
				<a href="/">忘記密碼</a>
			</fieldset>
			<%-- 置中--%>

		</form>
	</div>
</body>
</html>