package org.example;


import lombok.RequiredArgsConstructor;
import org.example.user.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@RequiredArgsConstructor
public class Communication {

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public String getCookie() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        return headers.getFirst(HttpHeaders.SET_COOKIE);
    }

    public List<User> getAllUser() {
        ResponseEntity<List<User>> response =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        return response.getBody();
    }

    public String saveUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> response = restTemplate.postForEntity(URL, httpEntity, String.class);
        return response.getBody();
    }

    public String updateUser(HttpEntity<User> httpEntity) {
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        return response.getBody();
    }

    public String deleteUser(long id, HttpEntity<User> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        return responseEntity.getBody();
    }

}
