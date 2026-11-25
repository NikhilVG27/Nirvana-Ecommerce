package com.nirvana.api.product;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins="http://localhost:5173")
public class ProductController {
  private final ProductRepository repository;
  public ProductController(ProductRepository repository){this.repository=repository;}
  @GetMapping public List<Product> all(){return repository.findAll();}
  @PostMapping public Product create(@RequestBody Product product){return repository.save(product);}
}
