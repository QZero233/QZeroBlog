package com.qzero.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QZeroBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(QZeroBlogApplication.class, args);
    }

}
