package ru.otus.spring.microservice;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final Random random = new Random();
    @Override
    public Health health() {
        boolean serverIsDown = random.nextBoolean();
        return ( serverIsDown ? Health.down().status(Status.DOWN).withDetail("message", "WTF?!").build()
                              : Health.up().withDetail("message", "So good!").build() );
    }
}
