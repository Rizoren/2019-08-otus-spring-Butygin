package ru.otus.springhomework04;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("application")
public class AppProperties {

    private String resPath;
    private String defLang;
    private String bundleBasePQA;

}
