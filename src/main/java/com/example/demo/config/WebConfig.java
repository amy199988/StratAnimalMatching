package com.example.demo.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 開放所有端點，設置允許來自 ngrok 的請求
		registry.addMapping("/**") // 配置對所有路徑開放 CORS
				.allowedOrigins("https://db38-2402-7500-486-9058-bd8a-28ec-1628-e0ea.ngrok-free.app")  // 設定允許 ngrok 的隨機 URL
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 允許的 HTTP 方法
				.allowedHeaders("*") // 允許的請求頭
				.allowCredentials(true); // 允許傳送 cookie
	}
	
	@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
            connector.setSecure(true); // 確保 Secure 只在 HTTPS 請求時生效
        });
        return factory;
    }
	
}
