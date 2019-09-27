package ru.otus.springhomework04.model;

import lombok.*;
import org.springframework.stereotype.Repository;
import ru.otus.springhomework04.dao.PollingResult;

@Setter
@Getter
@Repository("person")
public class PollingPerson {
    private String firstName;
    private String surName;
    private PollingResult pollingResult;

    public PollingPerson(PollingResult pollingResult) {
        this.pollingResult = pollingResult;
    }
}
