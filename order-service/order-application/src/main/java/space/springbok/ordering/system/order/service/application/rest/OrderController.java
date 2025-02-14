package space.springbok.ordering.system.order.service.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import space.springbok.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

    private final OrderApplicationService orderApplicationService;


    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {

        log.info("Create order for customer: {} at restaurant: {}", createOrderCommand.getCustomerId(),
                createOrderCommand.getRestaurantId());

        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("Order created with tracking id: {}", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/{trackingId")
    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable("trackingId") UUID trackingId) {

        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(TrackOrderQuery.builder()
                        .orderTrackingId(trackingId).build());
        log.info("Returning order status with tracking id: {}", trackOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(trackOrderResponse);
    }
}
