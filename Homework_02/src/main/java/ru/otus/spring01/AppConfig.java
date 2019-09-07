package ru.otus.spring01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.otus.spring01.dao.PollingAnswer;
import ru.otus.spring01.dao.PollingQuestion;
import ru.otus.spring01.dao.PollingResultImpl;
import ru.otus.spring01.services.CSVReader;
import ru.otus.spring01.services.ConsolePolling;

@PropertySource("classpath:config.properties")
@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    PollingQuestion question() { return new PollingQuestion(); }
    @Bean
    @Scope("prototype")
    PollingAnswer answer() { return new PollingAnswer(); }
    @Bean
    PollingResultImpl result() { return new PollingResultImpl(); }
    @Bean
    CSVReader reader(@Value("${res.resPath}") String resPath, PollingResultImpl pollingResultImpl) {
        return new CSVReader(resPath, pollingResultImpl);
    }
    @Bean
    ConsolePolling exam(PollingResultImpl result) {
        return new ConsolePolling(result);
    }
}
