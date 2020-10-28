package io.github.codingzhengsz;

import org.springframework.stereotype.Component;

@Component
@OrderTypeSelector(OrderType.PAYMENT)
public class PaymentOrderHandler implements OrderHandler {
  @Override
  public void handle(Order order) {
    System.out.println("handle payment order");
  }

  @Override
  public OrderType supportedOrderType() {
    return OrderType.PAYMENT;
  }

}
