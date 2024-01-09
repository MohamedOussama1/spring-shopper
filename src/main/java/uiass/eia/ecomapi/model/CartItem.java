package uiass.eia.ecomapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "ref")
    Order order;
    int productId;
    int quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
