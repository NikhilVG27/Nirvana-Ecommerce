package com.nirvana.api.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer_orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="order_number", nullable=false, unique=true, length=24) private String orderNumber;
    @Column(nullable=false, length=120) private String customerName;
    @Column(nullable=false, length=180) private String email;
    @Column(nullable=false, length=20) private String phone;
    @Column(nullable=false, length=300) private String address;
    @Column(nullable=false, length=100) private String city;
    @Column(nullable=false, length=6) private String pincode;
    @Column(nullable=false, length=40) private String paymentMethod;
    @Column(nullable=false, length=30) private String status;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal subtotal;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal deliveryFee;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal total;
    @Column(nullable=false) private OffsetDateTime createdAt;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<CustomerOrderItem> items = new ArrayList<>();

    protected CustomerOrder() {}
    public CustomerOrder(String orderNumber, PlaceOrderRequest r, BigDecimal subtotal, BigDecimal deliveryFee) {
        this.orderNumber=orderNumber; this.customerName=r.name(); this.email=r.email(); this.phone=r.phone();
        this.address=r.address(); this.city=r.city(); this.pincode=r.pincode(); this.paymentMethod=r.payment();
        this.status="PLACED"; this.subtotal=subtotal; this.deliveryFee=deliveryFee;
        this.total=subtotal.add(deliveryFee); this.createdAt=OffsetDateTime.now();
    }
    public void addItem(CustomerOrderItem item) { items.add(item); item.setOrder(this); }
    public Long getId(){return id;} public String getOrderNumber(){return orderNumber;}
    public String getCustomerName(){return customerName;} public String getEmail(){return email;}
    public String getPhone(){return phone;} public String getAddress(){return address;}
    public String getCity(){return city;} public String getPincode(){return pincode;}
    public String getPaymentMethod(){return paymentMethod;} public String getStatus(){return status;}
    public BigDecimal getSubtotal(){return subtotal;} public BigDecimal getDeliveryFee(){return deliveryFee;}
    public BigDecimal getTotal(){return total;} public OffsetDateTime getCreatedAt(){return createdAt;}
    public List<CustomerOrderItem> getItems(){return items;}
}
