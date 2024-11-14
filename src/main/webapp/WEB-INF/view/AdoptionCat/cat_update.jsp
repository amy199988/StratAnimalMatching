<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cat Update</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/cat/update" enctype="multipart/form-data">
			<fieldset>
				<legend>領養貓咪 修改</legend>
				編號：<input type="text" name="catId" value="${catDto.catId}" readonly />
				<p />
				名稱：<input type="text" name="catname" value="${catDto.catName}"/>
				<p />
				花紋：<input type="text" name="breed" value="${catDto.breed}" />
				<p />
				年齡：<input type="text" name="age" value="${catDto.age}" />
				<p />
				健康狀況：<input type="text" name="healthStatus" value="${catDto.healthStatus}" />
				<p />
				描述：<input type="text" name="description" value="${catDto.description}" />
				<p />
				<label class="pure-radio">目前狀態</label>
				<input type="radio" name="isapply" value="true" ${catDto.getIsApply() ? "checked" : ""} /> 可申請領養
				<input type="radio" name="isapply" value="false" ${catDto.getIsApply() ? "" : "checked"} /> 不可申請領養
				<p />
				<input type="file" name="photo" accept="image/*" /><p />
				<button type="submit" class="button-secondary pure-button">確認</button>
				<a href="/cat_list" class="button-error pure-button">取消</a>
			</fieldset>
		</form>
	</body>
</html>