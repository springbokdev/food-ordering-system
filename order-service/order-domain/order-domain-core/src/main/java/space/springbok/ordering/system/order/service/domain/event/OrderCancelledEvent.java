package space.springbok.ordering.system.order.service.domain.event;

import space.springbok.ordering.system.domain.event.DomainEvent;
import space.springbok.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
