package space.springbok.ordering.system.order.service.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import space.springbok.ordering.system.order.service.domain.entity.Customer;
import space.springbok.ordering.system.order.service.domain.entity.Order;
import space.springbok.ordering.system.order.service.domain.entity.Restaurant;
import space.springbok.ordering.system.order.service.domain.event.OrderCreatedEvent;
import space.springbok.ordering.system.order.service.domain.exception.OrderDomainException;
import space.springbok.ordering.system.order.service.domain.mapper.OrderDataMapper;
import space.springbok.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import space.springbok.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import space.springbok.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderDataMapper orderDataMapper;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Created order with id {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer =  customerRepository.findCustomer(customerId);
        if (customer.isEmpty()) {
            log.warn("Could not find customer with id {}", customerId);
            throw new OrderDomainException("Could not find customer with id " + customerId);
        }
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(restaurant);
        if (optionalRestaurant.isEmpty()) {
            log.warn("Could not find restaurant with restaurant id {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id " +
                    createOrderCommand.getRestaurantId());
        }
        return optionalRestaurant.get();
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);

        if (orderResult == null) {
            log.error("Could not save order with id: {}", order.getId().getValue());
            throw new OrderDomainException("Could not save order with id: " + order.getId().getValue());
        }
        log.info("Order ia saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }

}
