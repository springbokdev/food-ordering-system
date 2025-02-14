package space.springbok.ordering.system.order.service.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import space.springbok.ordering.system.order.service.domain.entity.Order;
import space.springbok.ordering.system.order.service.domain.exception.OrderNotFoundException;
import space.springbok.ordering.system.order.service.domain.mapper.OrderDataMapper;
import space.springbok.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import space.springbok.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));

        if (orderResult.isEmpty()) {
            log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
