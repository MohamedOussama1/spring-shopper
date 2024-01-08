package uiass.eia.ecomapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uiass.eia.ecomapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long productId);
}
