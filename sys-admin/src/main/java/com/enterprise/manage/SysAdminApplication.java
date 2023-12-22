package com.enterprise.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.enterprise")
@MapperScan(basePackages = "com.enterprise.biz.*.repository")
public class SysAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysAdminApplication.class, args);
    }

}
