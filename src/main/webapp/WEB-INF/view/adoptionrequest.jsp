<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Adoption Request</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<form class="pure-form" method="post">
				<fieldset>
					<legend>領養申請者 資訊</legend>
					名稱：<input type="text" name="" value="" placeholder="請輸入 名稱" required /><p />
					帳號：<input type="text" name="" value="" readonly /><p />
					年齡：<input type="text" name="" value="" placeholder="請輸入 年齡" required /><p />
					電話：<input type="text" name="" value="" placeholder="請輸入 電話" required /><p />
					電郵：<input type="email" name="" value="" placeholder="請輸入 email" required /><p />
					<input type="checkbox" name="" /> 同意貓咪植入晶片、施打疫苗。<p />
					<input type="checkbox" name="" /> 同意貓咪進行結紮手術。<p />
					<button type="submit" class="button-secondary pure-button">申請</button>
					<a href="/adoption" class="button-error pure-button">取消</a>
				</fieldset>
			</form>
		</div>
	</body>
</html>