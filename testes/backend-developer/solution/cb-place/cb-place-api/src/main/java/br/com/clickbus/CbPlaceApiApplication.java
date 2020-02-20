package br.com.clickbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "br.com.clickbus.repository")
public class CbPlaceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbPlaceApiApplication.class, args);
    }

}
