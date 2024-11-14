<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Adoption</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>領養表單</legend>
				<c:forEach var="catDto" items="${catDtos}">
					<div style="float: left; margin-right: 40px; margin-bottom: 40px; width: 260px;">
					<table class="pure-table pure-table-bordered">
						<c:if test="${not empty catDtos}">
							<tr><td>
							<c:if test="${ not empty catDto.catphoto_Url }">
						       	<img src="${catDto.catphoto_Url}" alt="Catphoto" width="200">
						    </c:if>
						    <c:if test="${empty catDto.catphoto_url}">
						        <p>Failed to upload image. Please try again.</p>
						    </c:if>
							</td></tr>
							<tr><td>貓咪名稱：${ catDto.catName }</td></tr>
							<tr><td>貓咪花紋：${ catDto.breed }</td></tr>
							<tr><td>貓咪年齡：${ catDto.age }歲</td></tr>
							<tr><td>特殊狀況：${ catDto.healthStatus }</td></tr>
							<tr><td>詳細描述：${ catDto.description }</td></tr>
						</c:if>
						<c:if test="${empty catDtos}">
					        <p>No visible cat information available.</p>
					    </c:if>
						<tr><td>
							<a href="/adoptionrequest" class="button-secondary pure-button">領養</button>
						</td></tr>
					</table>
					</div>
				</c:forEach>
			</fieldset>
		</div>
	</body>
</html>