package es.springframework.springrestclient.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.springframework.springrestclient.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Slf4j
@Controller
public class UserController {

    private ApiService apiService;
    ObjectMapper objectMapper;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping({"", "/", "index"})
    public String index(){
        return "index";
    }

    @PostMapping(value = "/usersJson", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String formPostJSON(Model model, @RequestBody String body) throws JsonProcessingException {
        JsonNode limitNode = objectMapper.readTree(body).findValue("limit");
        int limit = Optional.ofNullable(limitNode).map(JsonNode::asInt).orElse(5);
        Flux<User> users = apiService.getUsers(limit);
        model.addAttribute("users", users);
        return "userlist";
    }

    @PostMapping(value = "/usersForm", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String formPostServerWebExchange(Model model, ServerWebExchange serverWebExchange) {
        model.addAttribute("users",
                apiService.getUsers(serverWebExchange
                                .getFormData()
                                .map(data -> Integer.valueOf(data.getFirst("limit")))));
        return "userlist";
    }

}
