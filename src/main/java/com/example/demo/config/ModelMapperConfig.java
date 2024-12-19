package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration // Springboot 在啟動完成前，會先行執行此配置
public class ModelMapperConfig {

	// 由 Springboot 來管理此物件(IOC)
	// 若有必要其他程式可以透過 @Autowired 取得該實體
	@Bean 
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
