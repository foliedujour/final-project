package gr.aueb.cf.springfinalproject;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@SpringBootApplication
public class SpringFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFinalProjectApplication.class, args);
    }


}