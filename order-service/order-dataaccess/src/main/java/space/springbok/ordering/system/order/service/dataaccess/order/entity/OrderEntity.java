package space.springbok.ordering.system.order.service.dataaccess.order.entity;

import jakarta.persistence.*;
import lombok.*;
import space.springbok.ordering.system.domain.valueobject.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    private UUID id;

    private UUID customerId;

    private UUID restaurantId;

    private UUID trackingId;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String failureMessages;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
