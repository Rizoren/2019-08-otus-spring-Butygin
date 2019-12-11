package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.dto.Country;
import ru.otus.spring.service.CountryService;

import java.sql.Timestamp;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        CountryService service = ctx.getBean(CountryService.class);

        Country country = service.getCountry("col");

        log.info(country.getName());
/*
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        log.info(String.valueOf(timestamp));//2019-12-11 22:45:23.818
        List<Country> countries = service.getAllCountries();
        countries.forEach(c -> log.info(c.getName()));
        timestamp = new Timestamp(System.currentTimeMillis());
        log.info(String.valueOf(timestamp));//2019-12-11 22:45:24.296
        countries = service.getAllCountries();
        countries.forEach(c -> log.info(c.getName()));
        timestamp = new Timestamp(System.currentTimeMillis());
        log.info(String.valueOf(timestamp));//2019-12-11 22:45:24.304
        countries = service.getAllCountries();
        countries.forEach(c -> log.info(c.getName()));
        timestamp = new Timestamp(System.currentTimeMillis());
        log.info(String.valueOf(timestamp));//2019-12-11 22:45:24.304
*/
        service.getCountryRetry("col");
    }
}
