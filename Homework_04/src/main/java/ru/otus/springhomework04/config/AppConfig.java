package ru.otus.springhomework04.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.springhomework04.dao.PollingResult;
import ru.otus.springhomework04.dao.QuestionnaireReader;
import ru.otus.springhomework04.model.PollingPerson;
import ru.otus.springhomework04.dao.PollingResultImpl;
import ru.otus.springhomework04.dao.QuestionnaireReaderCSVImpl;
import ru.otus.springhomework04.services.ConsolePolling;
import ru.otus.springhomework04.services.ConsolePollingImpl;
import ru.otus.springhomework04.services.IOService;
import ru.otus.springhomework04.services.IOServiceImpl;

@SpringBootConfiguration
public class AppConfig {
    @Bean
    public PollingResult result() { return new PollingResultImpl(); }
    @Bean
    public PollingPerson person(PollingResult result) { return new PollingPerson(result); }
    @Bean
    public IOService ioService(@Value("${res.defLang}") String lang, @Value("${res.bundleBasePQA}") String bundle) {
        return new IOServiceImpl(lang, bundle);
    }
    @Bean
    public QuestionnaireReader reader(@Value("${res.resPath}") String resPath, IOService ioService, PollingResult pollingResult) {
        return new QuestionnaireReaderCSVImpl(resPath + "_" + ioService.getLang() , pollingResult);
    }
    @Bean
    public ConsolePolling exam(PollingPerson person, QuestionnaireReader reader, IOService ioService) {
        return new ConsolePollingImpl(person, reader, ioService);
    }
}
