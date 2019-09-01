package ru.otus.spring01.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PollingResult implements IPollingResult
{
    private List<IPollingQuestion> questions = new LinkedList<>();

    private int activeQuestion = 0;

    private int score;

    public List<IPollingQuestion> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<IPollingQuestion> questions)
    {
        this.questions = questions;
    }

    public void addQuestion(IPollingQuestion question)
    {
        this.questions.add(question);
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public int size() {
        return questions.size();
    }

    public Iterator<IPollingQuestion> iterator()
    {
        return new IteratorQuestion(this);
    }

    private class IteratorQuestion implements Iterator<IPollingQuestion>
    {
        private PollingResult pollingResult;

        public IteratorQuestion(PollingResult pollingResult)
        {
            this.pollingResult = pollingResult;
        }

        public boolean hasNext()
        {
            return pollingResult.questions.size() > pollingResult.activeQuestion;
        }

        public IPollingQuestion next()
        {
            int returnIndex = pollingResult.activeQuestion++;

            return pollingResult.questions.get(returnIndex);
        }

        public void remove() {

        }
    }

    @Override
    public int hashCode()
    {
        return questions.hashCode() + 13 * activeQuestion + 101 * score;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if ( ! (obj instanceof PollingResult)) {
            return false;
        }
        PollingResult other = (PollingResult) obj;

        return questions.equals(other.questions) && activeQuestion == other.activeQuestion && score == other.score;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName() + "{" +
                " questions=\"" + questions +
                "\", activeQuestion=" + activeQuestion +
                ", score=" + score +
                '}';
    }
}