package uiass.eia.ecomapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uiass.eia.ecomapi.model.Comment;
import uiass.eia.ecomapi.service.IServiceMetier;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
public class CommentController {
    @Autowired
    IServiceMetier serviceMetier;
    @GetMapping(path="/product/{productId}")
    public List<Comment> getCommentsByProductId(@PathVariable(value = "productId") Long id){
        return serviceMetier.getCommentsByProductId(id);
    }
    @PostMapping(path="", consumes = "application/json", produces = "application/json")
    public void postComment(@RequestBody Comment comment){
        serviceMetier.addComment(comment.getUser(), comment.getProduct().getId(), comment.getName(), comment.getDetails(), comment.getTitle(), comment.getRating());
    }
}
