package com.example.countrymanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Country {

    private int id;
    private String country;
    private String capital;
    private int population;

}
