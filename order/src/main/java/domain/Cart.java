package domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "carts")
@Entity
@Getter
@Setter
public class Cart {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "userId", nullable = false)
    private UUID userId;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<>();


}
