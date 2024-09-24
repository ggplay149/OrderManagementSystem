package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cartItem")
@Entity
@Getter
@Setter
public class CartItem {
    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "cart_id",
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_cartItem_to_cart")
    )
    private Cart cart;

    @Column(name ="productId", nullable = false)
    private UUID productId;
}
