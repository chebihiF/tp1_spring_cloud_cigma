package cigma.glwa.productservice.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id @Column(length = 20)
    private String ref ;
    private String label;
    private double price;
    private int quantity ;
}
