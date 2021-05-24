package com.csf.whoami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// this is to disable default login page from spring security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WhoamiSecurityApplication {

    public static void main(String[] args) {

        // TODO: List arguments.
        System.out.print("Start print ARG value");
        for (String arg : args) {
            System.out.println("Arg value:" + arg);
        }

        SpringApplication.run(WhoamiSecurityApplication.class, args);
        // TODO: List bean instanced.
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(WhoamiSecurityApplication.class, args);
//        for (String name : applicationContext.getBeanDefinitionNames()) {
//            System.out.println("Bean name: " + name);
//        }
    }

    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    /**
     * @return
     * @description create bean for restTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder().additionalInterceptors((httpRequest, bytes, clientHttpRequestExecution) -> {
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }).build();
    }

}
