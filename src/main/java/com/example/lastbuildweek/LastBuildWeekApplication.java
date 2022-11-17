package com.example.lastbuildweek;

import com.example.lastbuildweek.repositories.UserRepository;
import com.example.lastbuildweek.services.UserService;
import com.example.lastbuildweek.utils.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LastBuildWeekApplication implements CommandLineRunner {
    
    public static void main( String[] args ) {
        SpringApplication.run( LastBuildWeekApplication.class, args );
    }

    @Override
    public void run( String... args ) throws Exception {

    }
}
