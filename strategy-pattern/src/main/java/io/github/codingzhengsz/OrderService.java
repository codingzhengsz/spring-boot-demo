package io.github.codingzhengsz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

  private final OrderHandlerHolder orderHandlerHolder;

  @Autowired
  public OrderService(OrderHandlerHolder orderHandlerHolder) {
    this.orderHandlerHolder = orderHandlerHolder;
  }

  public void handle(Order order) {
    OrderHandler orderHandler = orderHandlerHolder.select(order.getOrderType());
    orderHandler.handle(order);
  }
}
