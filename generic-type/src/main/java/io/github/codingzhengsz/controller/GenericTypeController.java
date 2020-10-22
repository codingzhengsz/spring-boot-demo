package io.github.codingzhengsz.controller;

import io.github.codingzhengsz.service.GenericTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class GenericTypeController {

  private final GenericTypeService genericTypeService;

  @Autowired
  public GenericTypeController(GenericTypeService genericTypeService) {
    this.genericTypeService = genericTypeService;
  }

  @GetMapping("/string")
  public String testStringResult() {
    return genericTypeService.invokeMethod(String.class);
  }

  @GetMapping("/int")
  public Integer testIntegerResult() {
    return genericTypeService.invokeMethod(Integer.class);
  }
}
