package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name ="order_items")
@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "product_name_at_order", nullable = false) //주문당시 상품명
    private String productNameAtOrder;

    @Column(name = "product_price_at_order ", nullable = false) //주문당시 상품가격
    private BigDecimal productPriceAtOrder;

}
