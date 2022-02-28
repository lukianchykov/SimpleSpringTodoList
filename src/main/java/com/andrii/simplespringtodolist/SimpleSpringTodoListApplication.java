package com.andrii.simplespringtodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.andrii.simplespringtodolist")
public class SimpleSpringTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringTodoListApplication.class, args);
    }

}
