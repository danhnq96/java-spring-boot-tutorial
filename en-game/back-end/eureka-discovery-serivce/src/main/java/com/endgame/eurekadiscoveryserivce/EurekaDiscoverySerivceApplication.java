package com.endgame.eurekadiscoveryserivce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoverySerivceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoverySerivceApplication.class, args);
	}

}
