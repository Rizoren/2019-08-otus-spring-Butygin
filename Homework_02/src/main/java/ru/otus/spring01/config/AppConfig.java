package ru.otus.spring01.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring01.dao.QuestionnaireReader;
import ru.otus.spring01.model.PollingPerson;
import ru.otus.spring01.dao.PollingResultImpl;
import ru.otus.spring01.dao.QuestionnaireReaderCSVImpl;
import ru.otus.spring01.services.ConsolePollingImpl;
import ru.otus.spring01.services.IOServiceImpl;

@PropertySource("classpath:config.properties")
@Configuration
public class AppConfig {
    @Bean
    public PollingResultImpl result() { return new PollingResultImpl(); }
    @Bean
    public PollingPerson person(PollingResultImpl result) { return new PollingPerson(result); }
    @Bean
    public IOServiceImpl ioService(@Value("${res.defLang}") String lang, @Value("${res.bundleBasePQA}") String bundle) {
        return new IOServiceImpl(lang, bundle);
    }
    @Bean
    public QuestionnaireReader readerCSV(@Value("${res.resPath}") String resPath, IOServiceImpl ioService, PollingResultImpl pollingResultImpl) {
        return new QuestionnaireReaderCSVImpl(resPath + "_" + ioService.getLocale().getLanguage() , pollingResultImpl);
    }
    @Bean
    public ConsolePollingImpl exam(PollingPerson person, QuestionnaireReader readerCSV, IOServiceImpl ioService) { return new ConsolePollingImpl(person, readerCSV, ioService); }
}
