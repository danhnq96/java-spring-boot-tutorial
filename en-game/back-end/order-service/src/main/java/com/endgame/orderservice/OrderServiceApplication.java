package com.endgame.orderservice;

import com.endgame.orderservice.config.StripeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@EnableConfigurationProperties({StripeProperties.class})
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

}
