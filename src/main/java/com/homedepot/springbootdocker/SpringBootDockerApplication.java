package com.homedepot.springbootdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDockerApplication.class, args);
    }


}
