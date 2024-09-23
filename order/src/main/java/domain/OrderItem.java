package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name ="orderItem")
@Entity
@Getter
@Setter
public class OrderItem {
    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_orderItem_to_order")
    )
    private Order order;

    @Column(name = "productId", nullable = false)
    private UUID productId;

    @Column(name = "orderName", nullable = false)
    private String orderName;

    @Column(name = "orderPrice", nullable = false)
    private UUID orderPrice;


}
