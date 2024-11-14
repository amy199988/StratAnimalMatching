<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lovehome</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<fieldset>
				<legend>中途之家列表</legend>
				<c:forEach var="lovehomeDto" items="${lovehomeDtos}">
					<div style="float: left; margin-right: 40px; margin-bottom: 40px; width: 260px;">
					<table class="pure-table pure-table-bordered">
						<c:if test="${not empty lovehomeDtos}">
							<tr><td>
							<c:if test="${ not empty lovehomeDto.lovehome_Url }">
						       	<img src="${lovehomeDto.lovehome_Url}" alt="Lovehomephoto" width="200">
						    </c:if>
						    <c:if test="${empty lovehomeDto.lovehome_Url}">
						        <p>Failed to upload image. Please try again.</p>
						    </c:if>
							</td></tr>
							<tr><td>中途之家名稱：${ lovehomeDto.lovehomeName }</td></tr>
							<tr><td>中途之家地址：${ lovehomeDto.lovehomeCity }${ lovehomeDto.lovehomeDistrict }${ lovehomeDto.lovehomeAddress }</td></tr>
							<tr><td>聯絡方式：<p />
							${ lovehomeDto.contactInfo }
							</td></tr>
						</c:if>
						<c:if test="${empty lovehomeDtos}">
					        <p>No visible cat information available.</p>
					    </c:if>
					</table>
					</div>
				</c:forEach>
			</fieldset>
		</div>
	</body>
</html>