package ru.otus.spring01.dao;

import java.util.LinkedList;
import java.util.List;

public class PollingQuestion implements IPollingQuestion
{
    private String question;

    private List<IPollingAnswer> answers = new LinkedList<>();

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public List<IPollingAnswer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<IPollingAnswer> answers)
    {
        this.answers = answers;
    }

    public void addAnswer(IPollingAnswer answer)
    {
        this.answers.add(answer);
    }

    @Override
    public int hashCode()
    {
        return answers.hashCode() + (null != question ? 13 * question.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if ( ! (obj instanceof PollingQuestion)) {
            return false;
        }
        PollingQuestion other = (PollingQuestion) obj;

        return answers.equals(other.answers) && (null != question ? question.equals(other.question) : null == other.question);
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "{ question=\"" + question + "\", answers=" + answers + " }";
    }
}
