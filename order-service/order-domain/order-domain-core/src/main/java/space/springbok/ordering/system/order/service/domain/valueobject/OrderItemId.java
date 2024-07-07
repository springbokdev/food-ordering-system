package space.springbok.ordering.system.order.service.domain.valueobject;

import space.springbok.ordering.system.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {

    public OrderItemId(Long value) {
        super(value);
    }
}
