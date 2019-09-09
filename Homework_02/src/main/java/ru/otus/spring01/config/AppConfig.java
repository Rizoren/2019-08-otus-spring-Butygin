package ru.otus.spring01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.otus.spring01.dao.PollingAnswer;
import ru.otus.spring01.dao.PollingPerson;
import ru.otus.spring01.dao.PollingQuestion;
import ru.otus.spring01.dao.PollingResultImpl;
import ru.otus.spring01.services.CSVReader;
import ru.otus.spring01.services.ConsolePolling;
import ru.otus.spring01.services.IOService;

@PropertySource("classpath:config.properties")
@Configuration
public class AppConfig {
    @Bean @Scope("prototype")
    public PollingQuestion question() { return new PollingQuestion(); }
    @Bean @Scope("prototype")
    public PollingAnswer answer() { return new PollingAnswer(); }
    @Bean
    public PollingPerson person() { return new PollingPerson(); }
    @Bean
    public PollingResultImpl result() { return new PollingResultImpl(); }
    @Bean
    public ConsolePolling exam() { return new ConsolePolling(); }
    @Bean
    public IOService ioService(@Value("${res.defLang}") String lang, @Value("${res.bundleBasePQA}") String bundle) {
        return new IOService(lang, bundle);
    }
    @Bean
    public CSVReader reader(@Value("${res.resPath}") String resPath, IOService ioService, PollingResultImpl pollingResultImpl) {
        return new CSVReader(resPath + "_" + ioService.getLc().getLanguage() , pollingResultImpl);
    }
}
