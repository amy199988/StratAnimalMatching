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
		<form class="pure-form" method="post" action="">
			<fieldset>
				<legend>領養貓咪 修改</legend>
				編號：<input type="text" name="" value="" readonly />
				<p />
				名稱：<input type="text" name="" value="" required />
				<p />
				花紋：<input type="text" name="" value="" required />
				<p />
				年齡：<input type="text" name="" value="" required />
				<p />
				健康狀況：<input type="text" name="" value="" required />
				<p />
				描述：<input type="text" name="" value="" required />
				<p />
				<label for="is_apply" class="pure-radio">目前狀態</label>
				<input type="radio" name="apply" value="apply_yes" /> 可申請領養
				<input type="radio" name="apply" value="apply_false" /> 不可申請領養
				<p />
				<button type="submit" class="button-secondary pure-button">確認</button>
				<a href="/cat_list" class="button-error pure-button">取消</a>
			</fieldset>
		</form>
	</body>
</html>