package com.caterer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CatererApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatererApplication.class, args);
    }

}
