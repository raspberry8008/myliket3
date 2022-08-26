package com.myliket.myliket3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.myliket.myliket3")
@EnableJpaRepositories("com.myliket.myliket3")
public class Myliket3Application {

    public static void main(String[] args) {
        SpringApplication.run(Myliket3Application.class, args);
    }

}
