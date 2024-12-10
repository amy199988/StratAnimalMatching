//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration // 這是一個配置類
//@EnableWebSecurity
//public class WebSecurityConfig{
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.authorizeHttpRequests((authz) -> authz
//				.requestMatchers("/common/**", "/auth/**").permitAll()
//				//.requestMatchers("/customers/**").hasRole("ADMIN")
//				.requestMatchers("/user/**").hasAnyRole("USER", "lovehome","manager")
//				.requestMatchers("/lovehome/**").hasAnyRole("lovehome","manager")
//				.requestMatchers("/manager/**").hasAnyRole("manager")
//				//.requestMatchers("/products/**").hasAnyRole("USER", "ADMIN")
//				.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults()) // 啟用 HTTP 基本驗證, 給 Rest 工具測試使用(例如: Insomnia, Postman) 
//				.formLogin(Customizer.withDefaults())  // 預設表單登入頁面, 給網頁用
//				.csrf(csrf -> csrf.disable()) // 關閉 CSRF(跨站請求偽造) 保護 
//				.cors(Customizer.withDefaults()); // 預設: 啟用 CORS (跨域資源共享)
//		// 登出
//		http.logout()
//			.deleteCookies("JSESSIONID")
//			.logoutSuccessUrl("/loginpage")
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // 可以使用任何的 HTTP 方法登出
//		
//		
//		// 勿忘我（remember-me）
//		http.rememberMe()
//			.userDetailsService(userDetailsService)
//			.tokenValiditySeconds(60); // 通常都會大於 session timeout 的時間
//		
//		http.requestCache()
//            .requestCache(new HttpSessionRequestCache()); // 啟用請求記錄功能
//		
//		
//		return http.build(); // 建立安全過濾器鏈
//	}
//	
//	
//	
//	
//	// 注意！規定！要建立密碼演算的實例
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//			// 表單提交
//		http
//			.formLogin()
//			// loginpage.html 表單 action 內容
//			.loginProcessingUrl("/login")
//			// 自定義登入頁面
//			.loginPage("/loginpage")
//			// 登入成功之後要造訪的頁面
//			.successForwardUrl("/")  // welcome 頁面
//			// 登入失敗後要造訪的頁面
//			.failureForwardUrl("/fail");
//		
//		// 授權認證
//		http.authorizeHttpRequests()
//			// 不需要被認證的頁面：/loginpage
//			.antMatchers("/loginpage").permitAll()
//			// 權限判斷
//			// 必須要有 admin 權限才可以訪問
//			.antMatchers("/adminpage").hasAuthority("admin")
//			// 必須要有 manager 角色才可以訪問
//			.antMatchers("/managerpage").hasRole("manager")
//			// 其他指定任意角色都可以訪問
//			.antMatchers("/employeepage").hasAnyRole("manager", "employee")
//			// 其他的都要被認證
//			.anyRequest().authenticated();
//		
//		// http.csrf().disable(); // 關閉 csrf 防護
//		
//}
	
