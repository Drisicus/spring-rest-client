package es.springframework.springrestclient.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void index() {
        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void formPostJSON() {
        String json = "{\"limit\":\"3\"}";
        webTestClient.post().uri("/usersJson")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(json))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void formPost() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("limit", "3");

        webTestClient.post().uri("/usersForm")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .expectStatus().isOk();
    }
}