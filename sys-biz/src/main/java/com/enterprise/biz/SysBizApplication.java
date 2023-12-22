package com.enterprise.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enterprise")
@MapperScan(basePackages = "com.enterprise.biz.repository")
public class SysBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysBizApplication.class, args);
    }

}
