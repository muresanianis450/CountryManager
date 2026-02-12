package com.example.countrymanager.repository;

import com.example.countrymanager.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {}
