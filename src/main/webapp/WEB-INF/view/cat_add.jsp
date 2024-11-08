<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Adoption Cat Add</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<form class="pure-form" method="post" action="/cat_add" enctype="multipart/form-data">
				<fieldset>
					<legend>領養貓咪 新增</legend>
					貓咪名稱：<input type="text" name="" value="" required /><p />
					貓咪花紋：<input type="text" name="" value="" required /><p />
					貓咪年齡：<input type="text" name="" value="" required /><p />
					特殊狀況：<input type="text" name="" value="" required /><p />
					詳細描述：<input type="text" name="" value="" required /><p />
					<label for="is_apply" class="pure-radio">目前狀態：</label>
					<input type="radio" name="apply" value="apply_yes" /> 可申請領養
					<input type="radio" name="apply" value="apply_no" checked="" /> 不可申請領養
					<p />
					<input type="file" name="file" accept="image/*" required>
					<button type="submit" class="button-secondary pure-button">新增</button>
					<a href="/cat_list" class="button-error pure-button">取消</a>
				</fieldset>
			</form>
		</div>
	</body>
</html>