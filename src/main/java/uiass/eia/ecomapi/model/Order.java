package uiass.eia.ecomapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    Long id;
    @OneToMany
    List<CartItem> orderDetails = new ArrayList<>();
    @ManyToOne
    User user;
    LocalDate date;

    public Order(List<CartItem> orderDetails, User user, LocalDate date) {
        this.orderDetails = orderDetails;
        this.user = user;
        this.date = date;
    }
}
