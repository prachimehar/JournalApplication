package com.prachimehar.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JournalAppApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		String[] profiles = environment.getActiveProfiles();
		if (profiles.length > 0) {
			System.out.println("Active Profile: " + profiles[0]);
		} else {
			System.out.println("No active Spring profile.");
		}
	}

	@Bean
	public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory ){

		return new MongoTransactionManager(dbFactory);

	}

	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}

}
