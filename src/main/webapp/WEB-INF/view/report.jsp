<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Report</title>
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
					<legend>浪浪通報</legend>
					通報地點：
					<input type="text" name="" placeholder="城市" required /><p />
					<input type="text" name="" placeholder="區域" required /><p />
					<input type="text" name="" placeholder="詳細地址" required /><p />
					狀況描述：<input type="text" name="" placeholder="請輸入 ..." required /><p />
					通報照片：<input type="file" name="photo" accept="image/*" required />
					<button type="submit" class="button-secondary pure-button">通報</button>
					<a href="/" class="button-error pure-button">取消</a>
				</fieldset>
			</form>
		</div>
	</body>
</html>