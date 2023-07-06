package com.example.demo;

import com.example.demo.user.cvEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties({
        cvEntity.class
})
public class DemoApplication implements WebMvcConfigurer {



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
