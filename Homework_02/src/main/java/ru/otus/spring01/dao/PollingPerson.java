package ru.otus.spring01.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PollingPerson {
    private String firstName;
    private String surName;
    @Autowired
    private PollingResultImpl pollingResultImpl;

}
