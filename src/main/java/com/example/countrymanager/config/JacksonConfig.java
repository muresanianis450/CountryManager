package com.example.countrymanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean // The object returned by this method should be managed by the Spring container
    //Spring creates it, stores it and injects it wherever needed
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
