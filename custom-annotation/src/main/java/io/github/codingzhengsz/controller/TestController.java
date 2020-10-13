package io.github.codingzhengsz.controller;

import io.github.codingzhengsz.annotation.Process;
import io.github.codingzhengsz.annotation.WebLog;
import io.github.codingzhengsz.processor.IncomingMessageProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping
  @WebLog
  public String testWebLog() {
    System.out.println("=============Success============");
    System.out.println(1 / 0);
    return "Success";
  }

  @PostMapping
  @Process(processors = {IncomingMessageProcessor.class})
  public String testCustomProcess() {
    return "Hello World";
  }
}
