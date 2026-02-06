package com.example.countrymanager.service;

import com.example.countrymanager.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataService {

    private final ObjectMapper mapper;
    private final Path dataPath;

    public DataService(ObjectMapper mapper,
                       @Value("${app.data.path:data/data.json}") String path) {
        this.mapper = mapper;
        this.dataPath = Paths.get(path);
    }

    public List<Country> getAllCountries() {
        try {
            Country[] arr = mapper.readValue(dataPath.toFile(), Country[].class);
            return new ArrayList<>(Arrays.asList(arr));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + dataPath, e);
        }
    }



    public Country updateCountry(int id, Country updatedCountry) {
        List<Country> countries = getAllCountries();

        Country existing = countries.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Country not found: " + id));

        existing.setCountry(updatedCountry.getCountry());
        existing.setCapital(updatedCountry.getCapital());
        existing.setPopulation(updatedCountry.getPopulation());

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(dataPath.toFile(), countries);
            return existing;
        } catch (Exception e) {
            throw new RuntimeException("Failed to write file: " + dataPath, e);
        }
    }
}
