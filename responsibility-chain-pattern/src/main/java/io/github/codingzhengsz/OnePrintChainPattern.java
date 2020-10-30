package io.github.codingzhengsz;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class OnePrintChainPattern extends PrintChainPattern {

  @Override
  public String getString() {
    return "one";
  }
}
