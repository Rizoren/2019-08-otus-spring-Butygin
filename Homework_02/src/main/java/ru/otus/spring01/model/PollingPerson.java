package ru.otus.spring01.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.otus.spring01.dao.PollingResultImpl;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PollingPerson {
    private String firstName;
    private String surName;
    private PollingResultImpl pollingResultImpl;

    public PollingPerson(PollingResultImpl pollingResultImpl) {
        this.pollingResultImpl = pollingResultImpl;
    }
}
