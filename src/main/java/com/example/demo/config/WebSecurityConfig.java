//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

/*
@Configuration // 這是一個配置類
public class WebSecurityConfig extends WebSecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 表單提交
		http.formLogin()
			// loginpage.html 表單 action 內容
			.loginProcessingUrl("/login")
			// 自定義登入頁面
			.loginPage("/loginpage")
			// 登入成功之後要造訪的頁面
			.successForwardUrl("/")  // welcome 頁面
			// 登入失敗後要造訪的頁面
			.failureForwardUrl("/fail");
		
		// 授權認證
		http.authorizeHttpRequests()
			// 不需要被認證的頁面：/loginpage
			.antMatchers("/loginpage").permitAll()
			// 權限判斷
			// 必須要有 admin 權限才可以訪問
			.antMatchers("/adminpage").hasAuthority("admin")
			// 必須要有 manager 角色才可以訪問
			.antMatchers("/managerpage").hasRole("manager")
			// 其他指定任意角色都可以訪問
			.antMatchers("/employeepage").hasAnyRole("manager", "employee")
			// 其他的都要被認證
			.anyRequest().authenticated();
		
		// http.csrf().disable(); // 關閉 csrf 防護
		
		// 登出
		http.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/loginpage")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // 可以使用任何的 HTTP 方法登出
	
		// 異常處理
		http.exceptionHandling()
			//.accessDeniedPage("/異常處理頁面");  // 請自行撰寫
			.accessDeniedHandler(myAccessDeniedHandler);
		
		// 勿忘我（remember-me）
		http.rememberMe()
			.userDetailsService(userDetailsService)
			.tokenValiditySeconds(60); // 通常都會大於 session timeout 的時間
		
	}

	// 注意！規定！要建立密碼演算的實例
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/static/**").permitAll() // 允許未登入訪問
                .anyRequest().authenticated() // 其他請求需登入
                .and()
            .formLogin()
                .loginPage("/login") // 登入頁
                .defaultSuccessUrl("/home") // 預設登入成功後跳轉
                .permitAll()
                .and()
            .requestCache()
                .requestCache(new HttpSessionRequestCache()); // 啟用請求記錄功能
        return http.build();
    }
}
	
}
*/