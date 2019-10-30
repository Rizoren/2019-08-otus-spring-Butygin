package ru.otus.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repository.PersonRepository;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);

        repository.saveAll(Arrays.asList(
                new Person("Pushkin", 22),
                new Person("Lermontov", 22),
                new Person("Tolstoy", 60)
        )).subscribe(p -> System.out.println(p.getName()));

    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(PersonRepository repository) {

        PersonHandler handler = new PersonHandler(repository);

        RouterFunction<ServerResponse> route = route()
                .GET("/func/person", accept(APPLICATION_JSON), handler::list)
                .GET("/func/person/{id}", accept(APPLICATION_JSON),
                        request -> repository.findById(request.pathVariable("id"))
                                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                )
                .GET("/func/person/byname", queryParam("name", StringUtils::isNotEmpty),
                        request -> request.queryParam("name")
                                .map(repository::findAllByName)
                                .map(persons -> ok().body(persons, Person.class))
                                .orElse(notFound().build())
                )
                .build();

        return route;
    }

    static class PersonHandler {

        private PersonRepository repository;

        PersonHandler(PersonRepository repository) {
            this.repository = repository;
        }

        Mono<ServerResponse> list(ServerRequest request) {
            return ok().contentType(APPLICATION_JSON).body(repository.findAll(), Person.class);
        }

//        Mono<ServerResponse> listByName(ServerRequest request) {
//            return ok().contentType(APPLICATION_JSON).body(repository.findAllByName(request.queryParam("name").get()), Person.class);
//        }
//
//        Mono<ServerResponse> listByAge(ServerRequest request) {
//            return ok().contentType(APPLICATION_JSON).body(repository.findAllByAge(Integer.valueOf(request.queryParam("age").get())), Person.class);
//        }
    }
}


