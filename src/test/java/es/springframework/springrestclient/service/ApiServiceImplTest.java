package es.springframework.springrestclient.service;

import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Test
    void getUsers() {
        List<User> users = apiService.getUsers(2).collectList().block();
        assertEquals(2, users.size());
    }
}