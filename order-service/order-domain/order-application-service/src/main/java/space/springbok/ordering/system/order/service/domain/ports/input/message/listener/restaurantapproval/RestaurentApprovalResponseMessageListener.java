package space.springbok.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval;

import space.springbok.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurentApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
