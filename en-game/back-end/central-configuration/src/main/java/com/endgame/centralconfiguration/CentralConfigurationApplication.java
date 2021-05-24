package com.endgame.centralconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // ==> Important!!
public class CentralConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralConfigurationApplication.class, args);
	}

}
