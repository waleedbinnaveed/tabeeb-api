package com.tabeeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TabeebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TabeebApplication.class, args);
    }

}
