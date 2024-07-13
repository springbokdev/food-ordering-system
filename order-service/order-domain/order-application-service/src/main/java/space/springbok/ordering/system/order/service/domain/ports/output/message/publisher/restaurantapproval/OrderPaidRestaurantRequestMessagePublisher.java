package space.springbok.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import space.springbok.ordering.system.domain.event.publisher.DomainEventPublisher;
import space.springbok.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
