package com.chenjian.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages =  "com.chenjian")
public class MhApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhApplication.class, args);
    }
}
