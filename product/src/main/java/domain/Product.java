package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "products")
@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @Column(name = "seller_id", nullable = false)
    private long sellerId;

    @OneToOne(mappedBy = "product")
    private Inventory inventory;

}
