package ru.otus.spring10.repostory;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring10.domain.Email;

import java.util.List;

@Repository
public interface EmailRepository extends PagingAndSortingRepository<Email, Integer> {

    List<Email> findAll();

}
