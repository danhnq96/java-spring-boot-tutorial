package com.endgame.adminservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@EnableConfigurationProperties()
public class AdminServiceApplication {

    public static void main(String[] args) {
        String password = "admin";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.print(encodedPassword);
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
