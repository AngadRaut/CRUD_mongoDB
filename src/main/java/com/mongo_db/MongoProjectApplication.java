package com.mongo_db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MongoProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(MongoProjectApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MongoProjectApplication.class, args);
		log.info("\nMongo Project Application Service Started!\n");
	}
}