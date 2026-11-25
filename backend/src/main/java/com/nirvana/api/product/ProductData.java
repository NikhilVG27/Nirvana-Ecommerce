package com.nirvana.api.product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
@Configuration
public class ProductData {
  @Bean CommandLineRunner seedProducts(ProductRepository repo) {
    return args -> {
      if (repo.count()==0) {
        repo.save(new Product("Sculpted Linen Shirt","Men",new BigDecimal("2499"),""));
        repo.save(new Product("The Everyday Tote","Women",new BigDecimal("1899"),""));
        repo.save(new Product("Minimal Form Jacket","Men",new BigDecimal("4299"),""));
      }
    };
  }
}
