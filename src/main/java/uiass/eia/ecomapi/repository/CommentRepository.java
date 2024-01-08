package uiass.eia.ecomapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uiass.eia.ecomapi.model.Comment;
import uiass.eia.ecomapi.model.Product;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByProduct(Product product);
}
