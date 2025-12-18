package com.example.trendyolgobaseapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrendyolgobaseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendyolgobaseapiApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return  new ModelMapper();
	}


}
