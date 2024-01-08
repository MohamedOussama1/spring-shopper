package uiass.eia.ecomapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Transient
    String title;
    @Transient
    double price;
    @Transient
    double rating;
    @Transient
    int stock;
    @Transient
    String brand;
    @Transient
    String description;
    @Transient
    String thumbnail;
    @Transient
    List<String> images = new ArrayList<>();
    @Transient
    double discountPercentage;
    @Transient
    String category;
    @Transient
    Boolean isAvailable;

    public Product(long i) {
    }
}
