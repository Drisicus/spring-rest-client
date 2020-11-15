package es.springframework.springrestclient.service;

import model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiService {
    Flux<User> getUsers(Integer limit);
    Flux<User> getUsers(Mono<Integer> limit);
}
