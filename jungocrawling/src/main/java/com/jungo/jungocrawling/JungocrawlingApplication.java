package com.jungo.jungocrawling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JungocrawlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JungocrawlingApplication.class, args);
    }

}
