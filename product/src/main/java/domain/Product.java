package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private BigDecimal price;

    @Column(name="status", nullable = false)
    private ProductStatus status;

    @Column(name="inventoryCount", nullable = false)
    private int inventoryCount;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(nullable = true)
    private  LocalDateTime updatedAt;


}
