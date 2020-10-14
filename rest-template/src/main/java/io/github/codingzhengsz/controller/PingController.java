package io.github.codingzhengsz.controller;

import io.github.codingzhengsz.rest.BadRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {

  private final BadRequestHandler badRequestHandler;

  private final static Logger logger = LoggerFactory.getLogger(PingController.class);

  @Autowired
  public PingController(BadRequestHandler badRequestHandler) {
    this.badRequestHandler = badRequestHandler;
  }

  @GetMapping
  public void ping() {
    badRequestHandler.callApi();
  }

  @GetMapping("/success")
  public String handleSuccessPingRequest(@RequestHeader HttpHeaders headers) {
    logger.info("[PingController] call in, app key header: {}", headers.get("appKey"));
    return "Pong";
  }
}
