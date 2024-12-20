package com.example.demo.aop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.dto.UserCert;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class CheckRoleAspect {

	@Autowired
	private HttpSession session;

	// 檢查角色
	@Pointcut("@annotation(com.example.demo.aop.CheckRole)")
	public void requireRolePointcut() {
	}

	@Before(value = "requireRolePointcut()")
	public void checkRole(JoinPoint joinPoint, HttpSession session, CheckRole checkRole) {

		UserCert userCert = (UserCert) session.getAttribute("userCert");
		if (userCert == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登入，請先登入");// 401
		}
		String[] requiredRoles = checkRole.value();
		// 取得角色資訊
		String userRole = userCert.getRole();
		// 檢查角色是否符合要求
		if (Arrays.stream(requiredRoles).anyMatch(role -> role.equals(userRole))) {
			return;
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "沒有訪問該資源的權限"); // 403
		}
	}

}
