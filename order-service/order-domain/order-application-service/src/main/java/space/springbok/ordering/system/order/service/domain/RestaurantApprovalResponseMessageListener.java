package space.springbok.ordering.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import space.springbok.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import space.springbok.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurentApprovalResponseMessageListener;


@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListener implements RestaurentApprovalResponseMessageListener {

    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
