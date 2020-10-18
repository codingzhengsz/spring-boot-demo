package io.github.codingzhengsz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

    GreetingWebClient gwv = new GreetingWebClient();
    System.out.println(gwv.getResult());
  }
}
