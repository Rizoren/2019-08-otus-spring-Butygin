package ru.otus.springhomework04.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springhomework04.AppProperties;
import ru.otus.springhomework04.model.PollingAnswer;
import ru.otus.springhomework04.model.PollingQuestion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Consumer;
@Service("reader")
public class QuestionnaireReaderCSVImpl implements QuestionnaireReader
{
    private String filename = "";
    private PollingResult pollingResult;

    public QuestionnaireReaderCSVImpl() {}

    @Autowired
    public QuestionnaireReaderCSVImpl(AppProperties appProperties, PollingResult pollingResult)
    {
        this.filename = appProperties.getResPath() + "_" + appProperties.getDefLang();
        this.pollingResult = pollingResult;
    }

    @Override
    public void setFileName(String fileName) {
        this.filename = fileName;
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
            if (s.charAt(i) == ',' && notInsideComma) {
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

    public void read()
    {
        try {
            InputStream file = this.getClass().getResourceAsStream(filename);

            if (pollingResult.size() > 0) { pollingResult.clear(); }

            readFile(file, line -> {
                String[] fields = split(line);

                PollingQuestion question = new PollingQuestion();
                question.setQuestion(fields[0]);

                for (int i = 1; i < fields.length; i += 2) {
                    PollingAnswer answer = new PollingAnswer();
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
