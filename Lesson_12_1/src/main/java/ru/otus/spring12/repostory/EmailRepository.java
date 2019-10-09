package ru.otus.spring12.repostory;

import ru.otus.spring12.domain.Email;

public interface EmailRepository  {

    Iterable<Email> findAll();
    Email save(Email email);
}
