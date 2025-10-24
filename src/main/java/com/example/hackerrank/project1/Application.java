package com.example.hackerrank.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.hackerrank.project1", "com.hackerrank.api"})
@EnableJpaRepositories(basePackages = {"com.hackerrank.api.repository"})
@EntityScan(basePackages = {"com.hackerrank.api.model"})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
