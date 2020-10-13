package io.github.codingzhengsz.config;

import io.github.codingzhengsz.interceptor.MyRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(
      ClientHttpRequestFactory factory, MyRestInterceptor myRestInterceptor) {
    RestTemplate restTemplate = new RestTemplate(factory);
    // Add Customer Interceptor to the Rest Template
    restTemplate.setInterceptors(Collections.singletonList(myRestInterceptor));
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(5000);
    factory.setConnectTimeout(15000);
    // 设置代理
    return factory;
  }
}
