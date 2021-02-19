package br.com.cgms.grandpabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class GrandpabotApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(GrandpabotApplication.class, args);
    }
}
