<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> <!-- 核心庫 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User sign_up</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/base-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		<div class="pure-form" style="padding: 15px;">
			<form class="pure-form" method="post" action="/user/add" enctype="multipart/form-data">
				<fieldset>
					<legend>註冊會員</legend>
					使用者姓名: <input type="text" name="username" placeholder="使用者姓名"  title="全名" required/><p />
					使用者帳號: <input type="text" name="account" placeholder="會員帳號"  title="只能使用字母、數字或底線" required/><p />
					密碼: <input type="text" name="password" placeholder="密碼"  title="需大於八個字" required/><p />
					身分證: <input type="text" name="idCard" placeholder="身分證字號"  required/><p />
					電話: <input type="text" name="phone" placeholder="電話"  required/><p />
					生日: <input type="date" name="birthdate" placeholder="生日"  title="出生年月日" required /><p />
					E-mail:<input type="text" name="email" placeholder="E-mail" required/><p />
					申請會員:
					<select name="role" id="roleSelect" onchange="handleRoleChange()">
						<option value="ROLE_USER">普通帳號申請</option>
						<option value="ROLE_LOVEMOM">愛媽帳號申請</option>
					</select>
					<p />
					<div class="pure-form" id="lovemomFields" style="display:none;">
						<legend>中途之家 新增</legend><p />
						中途之家名稱：<input type="text" name="lovehomeName" required /><p />
						中途之家城市：<input type="text" name="lovehomeCity" required /><p />
						中途之家區域：<input type="text" name="lovehomeDistrict" required /><p />
						詳細地址：<input type="text" name="lovehomeAddress" required /><p />
						聯絡方式：<input type="text" name="contactInfo" required /><p />
						可收容量：<input type="text" name="capacity" required /><p />
						<input type="file" name="photo" accept="image/*" required /><p />
					</div>
					<button type="submit" class="button-secondary pure-button">註冊</button>
				</fieldset>
			</form>
		</div>
		<script>
	        // 當選擇的角色改變時觸發的事件
	        function handleRoleChange() {
	            // 獲取選中的角色
	            var role = document.getElementById("roleSelect").value;
	            console.log("選中的角色:", role);
	
	            // 在這裡根據選擇的角色做相應的處理
	            // 例如：顯示不同的表單或更新某些UI元素
	            if (role === "ROLE_LOVEMOM") {
	                document.getElementById("lovemomFields").style.display = "block";  // 顯示額外的表單欄位
	            } else {
	                document.getElementById("lovemomFields").style.display = "none";   // 隱藏額外的表單欄位
	            }
	        }
	    </script>
	</body>
</html>