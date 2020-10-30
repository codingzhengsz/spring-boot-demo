package io.github.codingzhengsz;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class ThreePrintChainPattern extends PrintChainPattern {

  @Override
  public String getString() {
    return "three";
  }
}
