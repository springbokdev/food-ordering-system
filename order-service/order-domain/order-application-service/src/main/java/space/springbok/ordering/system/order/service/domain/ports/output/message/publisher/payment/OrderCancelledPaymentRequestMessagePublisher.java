package space.springbok.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import space.springbok.ordering.system.domain.event.publisher.DomainEventPublisher;
import space.springbok.ordering.system.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
