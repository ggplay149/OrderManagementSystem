package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "inventory")
@Entity
@Getter
@Setter
public class Inventory {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "product_id",
            columnDefinition = "varbinary(16)",
            foreignKey = @ForeignKey(name = "fk_inventory_to_product")
    )
    private Product productId;

    @Column(name = "stock", nullable = false)
    private BigDecimal stock;


}
