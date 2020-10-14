package io.github.codingzhengsz.service;

import io.github.codingzhengsz.aop.E2ETracing;
import io.github.codingzhengsz.aop.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TestService {

  private static final Logger logger = LoggerFactory.getLogger(TestService.class);

  private final RestTemplate restTemplate;

  @Autowired
  TestService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @E2ETracing("E2E")
  @Process
  public void callApi(Map<String, String> myMap) {
    String result = restTemplate.getForObject("http://localhost:8502/ping/success", String.class);
    logger.info("result: {}", result);
  }
}
