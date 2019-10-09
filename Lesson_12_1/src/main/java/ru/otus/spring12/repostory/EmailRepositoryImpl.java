package ru.otus.spring12.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring12.domain.Email;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    final private KeyValueOperations keyValueOperations;

    public EmailRepositoryImpl(KeyValueOperations keyValueOperations) {
        this.keyValueOperations = keyValueOperations;
    }

    @Override
    public Iterable<Email> findAll() {
        return keyValueOperations.findAll(Email.class);
    }

    @Override
    public Email save(Email email) {
        return keyValueOperations.insert(email);
    }
}
