package domain;

import java.math.BigDecimal;

public class Price {

    private BigDecimal price;

    public Price(BigDecimal price){
        this.price = price;
    }

    public BigDecimal getPrice(BigDecimal price){
        return price;
    }
}
