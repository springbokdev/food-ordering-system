package space.springbok.ordering.system.order.service.domain.ports.input.service;

import jakarta.validation.Valid;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
