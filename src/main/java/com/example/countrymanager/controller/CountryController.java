package com.example.countrymanager.controller;


import com.example.countrymanager.model.Country;
import com.example.countrymanager.service.DataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class CountryController {

    private final DataService dataService;

    public CountryController(DataService dataService){
        this.dataService = dataService;
    }

    @GetMapping("/ping") // localhost:8080/ping
    public String ping(){
        return "pong";
    }

    //GET localhost:8080/countries
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return dataService.getAllCountries();
    }
    //GET localhost:8080/countries/1
    @GetMapping("/countries/{id}")
    public Country getCountry(@PathVariable int id){
        return dataService.getAllCountries().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Country not found" + id));
    }

    @PutMapping("/countries/{id}")
    public Country updateCountry(
            @PathVariable int id,
            @RequestBody Country country
    ){
        return dataService.updateCountry(id, country);
    }

}

