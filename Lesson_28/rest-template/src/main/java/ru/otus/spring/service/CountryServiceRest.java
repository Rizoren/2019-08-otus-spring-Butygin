package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import ru.otus.spring.dto.Country;

import java.net.URI;
import java.util.List;

@Service
public class CountryServiceRest implements CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceRest.class);

    private RestOperations rest = new RestTemplate();

    public Country getCountry(String id) {
        log.info("Request");
        return rest.getForObject("https://restcountries.eu/rest/v2/alpha/" + id, Country.class);
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000))
    @Override
    public Country getCountryRetry(String id) {
        log.info("Request ret");
        return rest.getForObject("https://restcountries.eu/rest/v7/alpha/" + id, Country.class);
    }

    @Cacheable("countries")
    @Override
    public List<Country> getAllCountries() {

        RequestEntity request = new RequestEntity(HttpMethod.GET, URI.create("https://restcountries.eu/rest/v2/"));
        ResponseEntity<List<Country>> response = rest.exchange(request, new ParameterizedTypeReference<List<Country>>() {});
        return response.getBody();

    }
}
