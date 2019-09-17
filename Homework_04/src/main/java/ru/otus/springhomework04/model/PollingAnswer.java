package ru.otus.springhomework04.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PollingAnswer
{
    private String answer;
    private int score;
}
