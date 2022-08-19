package com.example.customerdemo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "customer-demo", version = "2.0", description = "customer-demo Information"))
public class CustomerdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerdemoApplication.class, args);
    }

}
