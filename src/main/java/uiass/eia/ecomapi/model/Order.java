package uiass.eia.ecomapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "ref")
    List<CartItem> orderDetails = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    LocalDate date;

    public Order(List<CartItem> orderDetails, User user, LocalDate date) {
        this.orderDetails = orderDetails;
        this.user = user;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDetails=" + orderDetails +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
