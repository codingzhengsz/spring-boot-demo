package io.github.codingzhengsz.controller;

import io.github.codingzhengsz.rest.BadRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

  private final BadRequestHandler badRequestHandler;

  @Autowired
  public PingController(BadRequestHandler badRequestHandler) {
    this.badRequestHandler = badRequestHandler;
  }

  @GetMapping
  public void ping() {
    badRequestHandler.callApi();
  }
}
