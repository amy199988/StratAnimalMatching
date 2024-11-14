<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lovehome Setting</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>中途之家資訊</legend>
				<div style="width: 500px; margin: 10px;">
					<table class="pure-table pure-table-bordered">
						<tr><td>ID：${ lovehomeDto.lovehomeId }</td></tr>
						<tr><td>名稱：${ lovehomeDto.lovehomeName }</td></tr>
						<tr><td>城市：${ lovehomeDto.lovehomeCity }</td></tr>
						<tr><td>區域：${ lovehomeDto.lovehomeDistrict }</td></tr>
						<tr><td>詳細地址：${ lovehomeDto.lovehomeAddress }</td></tr>
						<tr><td>聯絡方式：${ lovehomeDto.contactInfo }</td></tr>
						<tr><td>可收容量：${ lovehomeDto.capacity }</td></tr>
						<tr><td>目前佔用率：${ lovehomeDto.currentOccupancy }</td></tr>
						<c:if test="${not empty lovehomeDto.lovehome_Url}">
							<tr><td>有</td></tr>
						</c:if>
						<c:if test="${empty lovehomeDto.lovehome_Url}">
							<tr><td>照片Url沒有資料</td></tr>
						</c:if>
						<tr><td><a href="/lovehome/update" class="button-secondary pure-button">修改</a></td></tr>
					</table>
				</div>
			</fieldset>
		</div>
	</body>
</html>