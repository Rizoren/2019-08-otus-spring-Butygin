package ru.otus.spring01.dao;

public class PollingAnswer implements IPollingAnswer
{
    private String answer;
    private int score;

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public int hashCode()
    {
        return score + (null != answer ? 13 * answer.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if ( ! (obj instanceof PollingAnswer)) {
            return false;
        }
        PollingAnswer other = (PollingAnswer) obj;

        return (null != answer ? answer.equals(other.answer) : null == other.answer) && score == other.score;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "{ answer=\"" + answer + "\", score=" + score + " }";
    }
}
