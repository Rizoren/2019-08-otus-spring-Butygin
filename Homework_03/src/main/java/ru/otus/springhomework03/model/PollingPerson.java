package ru.otus.springhomework03.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.otus.springhomework03.dao.PollingResult;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PollingPerson {
    private String firstName;
    private String surName;
    private PollingResult pollingResult;

    public PollingPerson(PollingResult pollingResult) {
        this.pollingResult = pollingResult;
    }
}
