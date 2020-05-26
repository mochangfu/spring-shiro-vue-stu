package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: qiuxinfa
 * @Date: 2019/11/17
 * @Description: com.demo
 */
@SpringBootApplication
@MapperScan("com.demo.mapper")
public class BootShiroVueStu {
    public static void main(String[] args) {
        SpringApplication.run(BootShiroVueStu.class,args);
    }
}
