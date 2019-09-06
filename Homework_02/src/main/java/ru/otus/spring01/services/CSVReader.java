package ru.otus.spring01.services;

import ru.otus.spring01.dao.IPollingAnswer;
import ru.otus.spring01.dao.IPollingQuestion;
import ru.otus.spring01.dao.IPollingResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CSVReader implements IFileReader
{
    private String filename = "";
    private IPollingResult pollingResult;

    public CSVReader() {}

    public CSVReader(String filename, IPollingResult pollingResult)
    {
        this.filename = filename;
        this.pollingResult = pollingResult;
    }

    public static String unQuote(String in)
    {
        if ('"' == in.charAt(0) && '"' == in.charAt(in.length() - 1)) {
            return in.substring(1, in.length() - 1);
        }

        return in;
    }

    public static String[] split(String s)
    {
        ArrayList<String> words = new ArrayList<>();

        boolean notInsideComma = true;
        int start = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ';' && notInsideComma) {
                words.add(unQuote(s.substring(start, i)));
                start = i + 1;
            }
            else if (s.charAt(i) == '"')
                notInsideComma = ! notInsideComma;
        }

        words.add(unQuote(s.substring(start)));

        return words.toArray(new String[0]);
    }

    public static void readFile(InputStream fin, Consumer<String> workWithLine)
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            String line = null;
            while ((line = br.readLine()) != null) {
                workWithLine.accept(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(Supplier<IPollingQuestion> getQuestionBean, Supplier<IPollingAnswer> getAnswerBean)
    {
        try {
            InputStream file = this.getClass().getResourceAsStream(filename);
            readFile(file, line -> {
                String[] fields = split(line);

                IPollingQuestion question = getQuestionBean.get();
                question.setQuestion(fields[0]);

                for (int i = 1; i < fields.length; i += 2) {
                    IPollingAnswer answer = getAnswerBean.get();
                    answer.setAnswer(fields[i]);

                    if (i < fields.length) {
                        answer.setScore(Integer.parseInt(fields[i + 1]));
                    }
                    question.addAnswer(answer);
                }

                pollingResult.addQuestion(question);
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
