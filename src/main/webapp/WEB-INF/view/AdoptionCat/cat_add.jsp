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
			<form class="pure-form" method="post" action="/cat/add" enctype="multipart/form-data">
				<fieldset>
					<legend>領養貓咪 新增</legend>
					貓咪名稱：<input type="text" name="catName" required /><p />
					貓咪花紋：<input type="text" name="breed" required /><p />
					貓咪年齡：<input type="text" name="age" required /><p />
					特殊狀況：<input type="text" name="healthStatus" required /><p />
					詳細描述：<input type="text" name="description" required /><p />
					<label class="pure-radio">目前狀態：
					<input type="radio" name="isApply" value="true" /> 可申請領養
					<input type="radio" name="isApply" value="false" checked="" /> 不可申請領養
					</label><p />
					<input type="file" name="photo" accept="image/*" required /><p />
					<button type="submit" class="button-secondary pure-button">新增</button>
					<a href="/cat_list" class="button-error pure-button">取消</a>
				</fieldset>
		</div>
	</body>
</html>