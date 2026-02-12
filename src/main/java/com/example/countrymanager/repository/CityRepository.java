package com.example.countrymanager.repository;

import com.example.countrymanager.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {}
