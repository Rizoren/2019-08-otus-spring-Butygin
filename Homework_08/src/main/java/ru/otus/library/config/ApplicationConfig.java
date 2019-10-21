package ru.otus.library.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private static final String CHANGELOGS_PACKAGE = "ru.otus.library.changelogs";

    @Bean
    public Mongock mongock(MongoProperties mongoProperties, MongoClient mongoClient) {
        Mongock m = new SpringMongockBuilder(mongoClient, mongoProperties.getDatabase(), CHANGELOGS_PACKAGE).build();
        return m;
    }
}
