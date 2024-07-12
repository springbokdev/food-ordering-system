package space.springbok.ordering.system.order.service.domain;

import space.springbok.ordering.system.order.service.domain.entity.Order;
import space.springbok.ordering.system.order.service.domain.entity.Restaurant;
import space.springbok.ordering.system.order.service.domain.event.OrderCancelledEvent;
import space.springbok.ordering.system.order.service.domain.event.OrderCreatedEvent;
import space.springbok.ordering.system.order.service.domain.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
