package com.todayfriend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodayfriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodayfriendApplication.class, args);
    }

}
