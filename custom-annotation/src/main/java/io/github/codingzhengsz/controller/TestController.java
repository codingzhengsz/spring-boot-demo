package io.github.codingzhengsz.controller;

import io.github.codingzhengsz.aop.Process;
import io.github.codingzhengsz.aop.WebLog;
import io.github.codingzhengsz.processor.IncomingMessageProcessor;
import io.github.codingzhengsz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;

  @Autowired
  public TestController(TestService testService) {
    this.testService = testService;
  }

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

  @GetMapping("/e2e")
  public void doE2ETracing() {
    Map<String, String> myMap = new HashMap<>();
    myMap.put("Hello", "Martin");
    testService.callApi(myMap);
  }
}
