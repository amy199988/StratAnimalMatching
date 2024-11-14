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
		<form class="pure-form" method="post" action="/lovehome/update" enctype="multipart/form-data">
			<fieldset>
				<legend>中途之家 修改</legend>
				編號：<input type="text" name="lovehomeId" value="${lovehomeDto.lovehomeId}" readonly />
				<p />
				名稱：<input type="text" name="lovehomeName" value="${lovehomeDto.lovehomeName}"/>
				<p />
				城市：<input type="text" name="lovehomeCity" value="${lovehomeDto.lovehomeCity}" />
				<p />
				區域：<input type="text" name="lovehomeDistrict" value="${lovehomeDto.lovehomeDistrict}" />
				<p />
				詳細地址：<input type="text" name="lovehomeAddress" value="${lovehomeDto.lovehomeAddress}" />
				<p />
				聯絡方式：<input type="text" name="contactInfo" value="${lovehomeDto.contactInfo}" />
				<p />
				可收容量：<input type="text" name="capacity" value="${lovehomeDto.capacity}" />
				<p />
				<input type="file" name="photo" accept="image/*" /><p />
				<button type="submit" class="button-secondary pure-button">確認</button>
				<a href="/lovehome/setting?lovehomeId=${lovehomeDto.lovehomeId}" class="button-error pure-button">取消</a>
			</fieldset>
		</form>
	</body>
</html>