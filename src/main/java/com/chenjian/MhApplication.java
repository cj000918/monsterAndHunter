package com.chenjian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan(basePackages = {"com.chenjian.mapper"})
@SpringBootApplication
public class MhApplication {


    public static void main(String[] args) {
        SpringApplication.run(MhApplication.class, args);
    }
}