package com.example.countrymanager.controller;


import com.example.countrymanager.model.Country;
import com.example.countrymanager.repository.CountryRepository;
import com.example.countrymanager.service.CountryService;
import com.example.countrymanager.service.DataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepo;

    public CountryController(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    // GET /countries
    @GetMapping
    public List<Country> getAll() {
        return countryRepo.findAll();
    }

    // GET /countries/{id}
    @GetMapping("/{id}")
    public Country getById(@PathVariable Long id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));
    }

    // DELETE /countries/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!countryRepo.existsById(id)) {
            throw new RuntimeException("Country not found: " + id);
        }
        countryRepo.deleteById(id);
    }

    // PUT /countries/{id}
    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @RequestBody Country body) {
        Country existing = countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));

        existing.setCountry(body.getCountry());
        existing.setCapital(body.getCapital());
        existing.setPopulation(body.getPopulation());

        return countryRepo.save(existing);
    }

    // POST /countries
    @PostMapping
    public Country create(@RequestBody Country body) {
        body.setId(null); // ensure DB generates id
        return countryRepo.save(body);
    }
}