package ru.otus.library.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.model.BooksMDB;
import ru.otus.library.repository.AuthorsMDBRepository;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<BooksMDB> {

    private final AuthorsMDBRepository authorsMDBRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BooksMDB> event) {
        super.onBeforeConvert(event);
        val student = event.getSource();
        if (student.getAuthors() != null) {
            student.getAuthors().stream().filter(e -> Objects.isNull(e.get_id())).forEach(authorsMDBRepository::save);
        }
    }
}
