package io.github.codingzhengsz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

  @Autowired
  private InitPrintChain chain;

  @GetMapping("/ping")
  public String ping() {
    chain.print(1);
    return "pong";
  }
}
