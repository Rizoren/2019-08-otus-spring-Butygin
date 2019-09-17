package ru.otus.springhomework03.dao;

import ru.otus.springhomework03.model.PollingQuestion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PollingResultImpl implements PollingResult
{
    private List<PollingQuestion> questions = new LinkedList<>();

    private int activeQuestion = 0;

    private int score;

    public List<PollingQuestion> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<PollingQuestion> questions)
    {
        this.questions = questions;
    }

    public void addQuestion(PollingQuestion question)
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

    public Iterator<PollingQuestion> iterator()
    {
        return new IteratorQuestion(this);
    }

    private class IteratorQuestion implements Iterator<PollingQuestion>
    {
        private PollingResultImpl pollingResultImpl;

        public IteratorQuestion(PollingResultImpl pollingResultImpl)
        {
            this.pollingResultImpl = pollingResultImpl;
        }

        public boolean hasNext()
        {
            return pollingResultImpl.questions.size() > pollingResultImpl.activeQuestion;
        }

        public PollingQuestion next()
        {
            int returnIndex = pollingResultImpl.activeQuestion++;

            return pollingResultImpl.questions.get(returnIndex);
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
        if ( ! (obj instanceof PollingResultImpl)) {
            return false;
        }
        PollingResultImpl other = (PollingResultImpl) obj;

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