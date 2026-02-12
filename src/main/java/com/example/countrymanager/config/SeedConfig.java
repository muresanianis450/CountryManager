package com.example.countrymanager.config;

import com.example.countrymanager.model.Country;
import com.example.countrymanager.repository.CountryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import java.nio.file.Paths;
import java.util.Arrays;

@Configuration
public class SeedConfig {

    @Bean
    CommandLineRunner seed(CountryRepository repo, ObjectMapper mapper,

                           @Value("${app.data.path:data/data.json}") String path){

        return args ->{


            if(repo.count() == 0){
                Country[] arr = mapper.readValue(Paths.get(path).toFile(),Country[].class);
                for (Country c : arr)
                    c.setId(null); //we ignore the ids so the DB will autogenerate them for us.
                repo.saveAll(Arrays.asList(arr));
            }
        };
    }
}
