package es.springframework.springrestclient.service;

import model.User;
import model.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApiServiceImpl implements ApiService {

    String apiUrl;

    public ApiServiceImpl(@Value("${api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Flux<User> getUsers(Integer limit) {
        return WebClient.create(apiUrl)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("per_page", limit).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserData.class)
                .flatMapIterable(UserData::getData);
    }

    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {
        return limit.flatMapMany(lim ->
                WebClient.create(apiUrl)
                        .get()
                        .uri(uriBuilder -> uriBuilder.queryParam("per_page", lim).build())
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(UserData.class)
                        .flatMapIterable(UserData::getData)
        );
    }
}
