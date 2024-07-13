package space.springbok.ordering.system.domain.event.publisher;

import space.springbok.ordering.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
