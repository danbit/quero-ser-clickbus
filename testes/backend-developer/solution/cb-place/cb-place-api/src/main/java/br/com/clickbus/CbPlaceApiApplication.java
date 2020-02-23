package br.com.clickbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.clickbus")
public class CbPlaceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbPlaceApiApplication.class, args);
    }

}
