package space.springbok.ordering.system.order.service.domain.mapper;

import org.springframework.stereotype.Component;
import space.springbok.ordering.system.domain.valueobject.CustomerId;
import space.springbok.ordering.system.domain.valueobject.Money;
import space.springbok.ordering.system.domain.valueobject.ProductId;
import space.springbok.ordering.system.domain.valueobject.RestaurantId;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import space.springbok.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import space.springbok.ordering.system.order.service.domain.dto.create.OrderAddress;
import space.springbok.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import space.springbok.ordering.system.order.service.domain.entity.Order;
import space.springbok.ordering.system.order.service.domain.entity.OrderItem;
import space.springbok.ordering.system.order.service.domain.entity.Product;
import space.springbok.ordering.system.order.service.domain.entity.Restaurant;
import space.springbok.ordering.system.order.service.domain.valueobject.StreetAddress;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId()))).collect(Collectors.toList()))
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(
            List<space.springbok.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {

        return orderItems.stream()
                .map(orderItem ->
                        OrderItem.builder()
                                .product(new Product(new ProductId(orderItem.getProductId())))
                                .price(new Money(orderItem.getPrice()))
                                .quantity(orderItem.getQuantity())
                                .subTotal(new Money(orderItem.getSubTotal()))
                                .build()).collect(Collectors.toList());

    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }

}
