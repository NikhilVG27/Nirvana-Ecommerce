package com.nirvana.api.product;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="products")
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name;
  private String category;
  private BigDecimal price;
  private String imageUrl;
  protected Product() {}
  public Product(String name, String category, BigDecimal price, String imageUrl) {
    this.name=name; this.category=category; this.price=price; this.imageUrl=imageUrl;
  }
  public Long getId(){return id;}
  public String getName(){return name;}
  public String getCategory(){return category;}
  public BigDecimal getPrice(){return price;}
  public String getImageUrl(){return imageUrl;}
  public void setName(String v){name=v;}
  public void setCategory(String v){category=v;}
  public void setPrice(BigDecimal v){price=v;}
  public void setImageUrl(String v){imageUrl=v;}
}
