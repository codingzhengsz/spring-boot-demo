package io.github.codingzhengsz;

public interface OrderHandler {
  void handle(Order order);

  OrderType supportedOrderType();
}
