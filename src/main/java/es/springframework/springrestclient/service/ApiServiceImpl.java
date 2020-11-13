package es.springframework.springrestclient.service;

import model.User;
import model.UserData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {
        UserData userData = restTemplate.getForObject("https://reqres.in/api/users?per_page=" + limit, UserData.class);
        return userData.getData();
    }
}
