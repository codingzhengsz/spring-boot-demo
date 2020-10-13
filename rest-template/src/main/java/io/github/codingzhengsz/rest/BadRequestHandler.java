package io.github.codingzhengsz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class BadRequestHandler {

    @Value("${api.ping}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public BadRequestHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void callApi() throws HttpClientErrorException {
        String result = restTemplate.getForObject(apiUrl, String.class);

        System.out.println(result);
    }
}
