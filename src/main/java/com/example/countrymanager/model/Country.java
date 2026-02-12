package com.example.countrymanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // change from int - > LONG (better for DB ids)

    @Column(nullable = false)
    private String country;

    private String capital;

    private int population;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<City> cities = new ArrayList<>();

}
