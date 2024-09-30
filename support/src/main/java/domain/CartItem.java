package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cart_items")
@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
