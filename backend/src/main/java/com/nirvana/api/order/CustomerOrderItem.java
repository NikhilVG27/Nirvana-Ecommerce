package com.nirvana.api.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="customer_order_items")
public class CustomerOrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="order_id") private CustomerOrder order;
    @Column(nullable=false) private Long productId;
    @Column(nullable=false, length=180) private String productName;
    @Column(nullable=false, length=30) private String size;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal unitPrice;
    protected CustomerOrderItem() {}
    public CustomerOrderItem(Long productId, String productName, String size, Integer quantity, BigDecimal unitPrice) {
        this.productId=productId; this.productName=productName; this.size=size; this.quantity=quantity; this.unitPrice=unitPrice;
    }
    void setOrder(CustomerOrder order){this.order=order;}
    public Long getId(){return id;} public Long getProductId(){return productId;} public String getProductName(){return productName;}
    public String getSize(){return size;} public Integer getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;}
}
