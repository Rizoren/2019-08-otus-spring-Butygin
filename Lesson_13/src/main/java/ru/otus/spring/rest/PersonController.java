package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
@RestController
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    public List<PersonDto> getAll() {
        return repository.findAll().stream().map(PersonDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(
            @PathVariable("id")
            /*@RequestParam("id")*/ Integer id) {
        return repository.findById(id).get();
    }

    @PostMapping("/person/{id}")
    public @ResponseBody ResponseEntity<PersonDto> create(@RequestBody PersonDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(  PersonDto.toDto(  repository.save(  PersonDto.toDomainObject(dto)  )  )  );
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }
}
