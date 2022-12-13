package com.example.databaseShared;

import com.example.databaseShared.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DatabaseSharedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseSharedApplication.class, args);
	}

}
