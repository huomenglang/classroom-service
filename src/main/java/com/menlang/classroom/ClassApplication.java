package com.menlang.classroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
		"com.menlang.classroom",              // your microservice code
		"com.menglang.common.library"        // your common-library code
})
@EnableFeignClients
public class ClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassApplication.class, args);
	}

}
