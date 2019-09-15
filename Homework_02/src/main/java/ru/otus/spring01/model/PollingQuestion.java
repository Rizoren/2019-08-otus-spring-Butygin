package ru.otus.spring01.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PollingQuestion
{
    private String question;

    private List<PollingAnswer> answers = new LinkedList<>();

    public void addAnswer(PollingAnswer answer)
    {
        this.answers.add(answer);
    }
}
