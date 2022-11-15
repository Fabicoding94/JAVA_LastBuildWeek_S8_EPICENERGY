package com.example.lastbuildweek;

import com.example.lastbuildweek.repositories.UserRepository;
import com.example.lastbuildweek.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LastBuildWeekApplication implements CommandLineRunner {

    @Autowired
    UserRepository ur;
    public static void main( String[] args ) {
        SpringApplication.run( LastBuildWeekApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

    }
}
