package dev.bcv.boothst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.bcv.boothst.hst.ServletContextInitializerImpl;

@SpringBootApplication
public class BootHstApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{BootHstApplication.class, ServletContextInitializerImpl.class}, args);
    }
}
