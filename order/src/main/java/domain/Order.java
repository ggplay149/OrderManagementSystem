package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name="status", nullable = false)
    private OrderStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "cart_id",
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_cartItem_to_cart")
    )
    private Cart cartId;

    @Column(name="userId", nullable = false)
    private UUID userId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
