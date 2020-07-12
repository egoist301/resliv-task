package by.resliv.task.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = "by.resliv.task", exclude = DataSourceAutoConfiguration.class)
public class App {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);
    }
}
