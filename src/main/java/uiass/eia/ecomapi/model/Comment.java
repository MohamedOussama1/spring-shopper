package uiass.eia.ecomapi.model;

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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    int product;
    String name;
    String details;
    String title;
    double rating;

    public Comment(User user, int product, String name, String details, String title, double rating) {
        this.user = user;
        this.product = product;
        this.name = name;
        this.details = details;
        this.title = title;
        this.rating = rating;
    }
}
