package io.github.codingzhengsz.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ArithmeticException.class)
  public String handleArithmeticException(ArithmeticException e) {
    return e.getMessage();
  }
}
