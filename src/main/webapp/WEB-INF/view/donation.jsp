<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Donation</title>
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
					<legend>捐贈表單</legend>
					<label for="radio_type" class="pure-radio">捐贈類型</label>
					<input type="radio" id="money" name="optionsradio" value="option_money" /> 錢
					<input type="radio" id="items" name="optionsradio" value="option_items" /> 物品
					<p />
					捐贈數量：<input type="text" name="" placeholder="請輸入 ..." required /><p />
					捐贈描述：<input type="text" name="" required /><p />
					<button type="submit" class="button-secondary pure-button">送出</button>
					<a href="/" class="button-error pure-button">取消</a>
				</fieldset>
			</form>
		</div>
	</body>
</html>