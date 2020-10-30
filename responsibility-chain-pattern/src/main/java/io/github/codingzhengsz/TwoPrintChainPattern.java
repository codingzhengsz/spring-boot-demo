package io.github.codingzhengsz;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class TwoPrintChainPattern extends PrintChainPattern {

  @Override
  public String getString() {
    return "two";
  }
}
