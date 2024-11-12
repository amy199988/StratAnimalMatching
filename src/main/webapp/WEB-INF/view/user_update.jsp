<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Update</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/">
			<fieldset>
				<legend>會員資料 修改</legend>
				編號：<input type="text" name="userId" value="${UserDto.userId}" readonly />
				<p />
				名稱：<input type="text" name="username" value="${UserDto.username}"/>
				<p />
				帳號：<input type="text" name="account" value="${UserDto.account}" readonly/>
				<p />
				電話：<input type="text" name="phone" value="${UserDto.phone}" />
				<p />
				生日：<input type="date" name="birthdate" value="${UserDto.birthdate}" readonly/>
				<p />
				電子郵件：<input type="email" name="email" value="${UserDto.email}" />
				<p />
				地址：<input type="text" name="address" value="${UserDto.address}" />
				<p />
				帳號認證：<input type="text" name="active" value="${UserDto.active}" />
				<p />
				帳號權限：<input type="text" name="role" value="${UserDto.role}" readonly/>
				<p />
				<button type="submit" class="button-secondary pure-button">確認</button>
				<a href="/" class="button-error pure-button">取消</a>
			</fieldset>
		</form>
	</body>
</html>