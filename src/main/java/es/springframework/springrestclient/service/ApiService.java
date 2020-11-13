package es.springframework.springrestclient.service;

import model.User;

import java.util.List;

public interface ApiService {
    List<User> getUsers(Integer limit);
}
