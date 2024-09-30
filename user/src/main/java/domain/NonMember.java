package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "NonMembers")
@Entity
@Getter
@Setter
public class NonMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id", nullable = false)
    private long orderId;
}
