package space.springbok.ordering.system.order.service.dataaccess.order.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import space.springbok.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import space.springbok.ordering.system.order.service.dataaccess.order.repository.OrderJpaRepository;
import space.springbok.ordering.system.order.service.domain.entity.Order;
import space.springbok.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import space.springbok.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
                .save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
