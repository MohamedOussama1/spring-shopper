package uiass.eia.ecomapi.model;

import jakarta.persistence.ManyToOne;

public class CommentDto {
    Long id;
    Long userId;
    int productId;
    String name;
    String details;
    String title;
    double rating;

    public CommentDto(Long id, Long userId, int productId, String name, String details, String title, double rating) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.name = name;
        this.details = details;
        this.title = title;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
