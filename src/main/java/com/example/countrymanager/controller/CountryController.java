package com.example.countrymanager.controller;


import com.example.countrymanager.model.Country;
import com.example.countrymanager.service.CountryService;
import com.example.countrymanager.service.DataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class CountryController {


    /*public CountryController(DataService dataService){
        this.service = dataService;
    }*/
   private final CountryService service;

   public CountryController(CountryService countryService) {
       this.service = countryService;
   }
    @GetMapping("/ping") // localhost:8080/ping
    public String ping(){
        return "pong";
    }

    //GET localhost:8080/countries
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return service.getAllCountries();
    }
    //GET localhost:8080/countries/1
    @GetMapping("/countries/{id}")
    public Country getCountry(@PathVariable int id){
        return service.getAllCountries().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow( () -> new RuntimeException("Country not found" + id));
    }

    @PutMapping("/countries/{id}")
    public Country updateCountry(
            @PathVariable Long id,
            @RequestBody Country country
    ){
        return service.updateCountry(id, country);
    }

}


