package uiass.eia.ecomapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderDto {
    Long id;
    List<CartItem> orderDetails = new ArrayList<>();
    Long userId;
    LocalDate date;

    public OrderDto(Long id, List<CartItem> orderDetails, Long userId, LocalDate date) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.userId = userId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", orderDetails=" + orderDetails +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
