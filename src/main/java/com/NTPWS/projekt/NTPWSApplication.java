package com.NTPWS.projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NTPWSApplication {

    public static void main(String[] args) {
        SpringApplication.run(NTPWSApplication.class, args);
    }
}
