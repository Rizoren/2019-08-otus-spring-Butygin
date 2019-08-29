package ru.otus.spring01.dao;

import ru.otus.spring01.domain.Person;

public class PersonDaoSimple implements PersonDao {

    private int defaultAge;

    public void setdefaultAge(int age) {
        this.defaultAge = age;
    }

    public int getdefaultAge() {
        return this.defaultAge;
    }

    public Person findByName(String name) {
        return new Person(name, getdefaultAge());
    }
}
