package ru.otus.spring.service;

import ru.otus.spring.dto.Country;

import java.util.List;

public interface CountryService {

    Country getCountry(String id);
    Country getCountryRetry(String id);
    List<Country> getAllCountries();
}
