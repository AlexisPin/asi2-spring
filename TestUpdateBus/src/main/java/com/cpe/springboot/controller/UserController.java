package com.cpe.springboot.controller;

import com.model.RichUserDTO;
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
    RestTemplate restTemplate = new RestTemplate();

    public boolean getUser(RichUserDTO user, String id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<RichUserDTO> requestUpdate = new HttpEntity<>(user, headers);
        ResponseEntity<Boolean> result = restTemplate.exchange(URL_User + id, HttpMethod.PUT, requestUpdate, Boolean.class);
        return result.getBody();
    }
    @RequestMapping(method= RequestMethod.POST,value="/update/{id}")
    public boolean update(@RequestBody RichUserDTO user, @PathVariable String id) {
        return getUser(user,id);
    }
}
