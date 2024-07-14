package space.springbok.ordering.system.order.service.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import space.springbok.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;

    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }

}
