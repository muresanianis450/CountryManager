package com.example.countrymanager.service;

import com.example.countrymanager.model.Country;
import com.example.countrymanager.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {


    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id){
        return countryRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Country not found!") );
    }

    public void deleteCountry(Long id){
        countryRepository.deleteById(id);
    }

    public Country updateCountry(Long id, Country updated){
        Country existing = getCountryById(id);

        existing.setCountry(updated.getCountry());
        existing.setCapital(updated.getCapital());
        existing.setPopulation(updated.getPopulation());

        return countryRepository.save(existing);
    }
}
