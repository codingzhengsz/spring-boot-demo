package io.github.codingzhengsz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class OrderHandlerHolder implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  private final Map<OrderType, OrderHandler> container = new HashMap<>();

  @PostConstruct
  public void init() {
    Map<String, OrderHandler> orderHandlerMap =
        applicationContext.getBeansOfType(OrderHandler.class);
    if (isEmpty(orderHandlerMap)) {
      return;
    }
    // implement1(orderHandlerMap);
    implement2(orderHandlerMap);
  }

  private void implement1(Map<String, OrderHandler> orderHandlerMap) {
    orderHandlerMap.forEach(
        (beanName, orderHandler) -> {
          OrderTypeSelector orderTypeSelector =
              orderHandler.getClass().getAnnotation(OrderTypeSelector.class);
          if (nonNull(orderTypeSelector)) {
            this.container.put(orderTypeSelector.value(), orderHandler);
          }
        });
  }

  private void implement2(Map<String, OrderHandler> orderHandlerMap) {
    orderHandlerMap.forEach(
        (beanName, orderHandler) -> {
          if (nonNull(orderHandler.supportedOrderType())) {
            this.container.put(orderHandler.supportedOrderType(), orderHandler);
          }
        });
  }

  public OrderHandler select(OrderType orderType) {
    return container.get(orderType);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }
}
