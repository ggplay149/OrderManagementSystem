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
public class Payment {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "orderId", columnDefinition = "varbinary(16)")
    private UUID orderId;

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


