package space.springbok.ordering.system.order.service.domain.entity;

import space.springbok.ordering.system.domain.entity.BaseEntity;
import space.springbok.ordering.system.domain.valueobject.Money;
import space.springbok.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

    private String name;

    private Money price;

    public Product(ProductId productId, String name, Money price) {
        setId(productId);
        this.name = name;
        this.price = price;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }


}
