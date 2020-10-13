package io.github.codingzhengsz.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MyRestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        String actionId = UUID.randomUUID().toString();
        headers.add("actionId", actionId);
        System.out.println("Request ActionId: " + actionId);
        ClientHttpResponse response = execution.execute(request, body);
        HttpHeaders responseHeader = response.getHeaders();
        System.out.println("Response ActionId: " + responseHeader.get("actionId"));
        return response;
    }
}