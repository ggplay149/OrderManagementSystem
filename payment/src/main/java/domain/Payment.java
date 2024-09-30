package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "payments")
@Entity
@Getter
@Setter
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "amount", nullable = false)
    private Amount amount;

    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "method", nullable = false)
    private PaymentMethod method;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}


