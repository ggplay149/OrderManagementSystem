package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "inventories")
@Entity
@Getter
@Setter
public class Inventory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "stock", nullable = false)
    private int stock;


}
