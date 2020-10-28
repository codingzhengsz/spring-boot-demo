package io.github.codingzhengsz;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderTypeSelector {
  OrderType value();
}
