package com.cpe.springboot.controller;

import com.model.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:8083")
@RestController
public class UserController {

    static final String URL_User = "http://localhost:8083/user/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    public String updateUser(UserDTO user, String id) {
        HttpEntity<UserDTO> requestUpdate = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_User + id, HttpMethod.PUT, requestUpdate, String.class);
        return result.getBody();
    }

    public String addUser(@RequestBody UserDTO user) {
        HttpEntity<UserDTO> requestAdd = new HttpEntity<>(user, headers);
        ResponseEntity<String> result = restTemplate.exchange(URL_User, HttpMethod.POST, requestAdd, String.class);
        return result.getBody();
    }

    public Boolean deleteUser(String id) {
        HttpEntity<String> requestDelete = new HttpEntity<>(headers);
        ResponseEntity<Boolean> result = restTemplate.exchange(URL_User + id, HttpMethod.DELETE, requestDelete, Boolean.class);
        return result.getBody();
    }

    @RequestMapping(method= RequestMethod.PUT,value="/user/{id}")
    public String update(@RequestBody UserDTO user, @PathVariable String id) {
        return updateUser(user,id);
    }

    @RequestMapping(method=RequestMethod.POST,value="/user/")
    public String add(@RequestBody UserDTO user) {
        return addUser(user);
    }


    @RequestMapping(method=RequestMethod.DELETE,value="/user/{id}")
    public Boolean delete(@PathVariable String id) {
        return deleteUser(id);
    }

}
