package com.nirvana.api.order;

import com.nirvana.api.product.Product;
import com.nirvana.api.product.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CustomerOrderRepository orders;
    private final ProductRepository products;
    public OrderController(CustomerOrderRepository orders, ProductRepository products){this.orders=orders;this.products=products;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public OrderResponse place(@Valid @RequestBody PlaceOrderRequest request) {
        BigDecimal subtotal=BigDecimal.ZERO;
        for (var line : request.items()) {
            Product product=products.findById(line.productId()).filter(Product::getActive)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found: "+line.productId()));
            if(product.getStock()<line.quantity()) throw new ResponseStatusException(HttpStatus.CONFLICT,"Insufficient stock for "+product.getName());
            product.setStock(product.getStock()-line.quantity());
            subtotal=subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(line.quantity())));
        }
        BigDecimal delivery=subtotal.compareTo(new BigDecimal("2999"))>=0?BigDecimal.ZERO:new BigDecimal("99");
        // Persist the server-calculated total; never trust a client-supplied price.
        CustomerOrder saved=orders.save(new CustomerOrder("NIR"+UUID.randomUUID().toString().replace("-","").substring(0,10).toUpperCase(), request, subtotal, delivery));
        for(var line : request.items()) {
            Product product=products.findById(line.productId()).orElseThrow();
            saved.addItem(new CustomerOrderItem(product.getId(),product.getName(),line.size(),line.quantity(),product.getPrice()));
        }
        saved=orders.save(saved);
        return new OrderResponse(saved.getOrderNumber(),saved.getStatus(),saved.getSubtotal(),saved.getDeliveryFee(),saved.getTotal(),saved.getCreatedAt());
    }
    public record OrderResponse(String orderNumber,String status,BigDecimal subtotal,BigDecimal deliveryFee,BigDecimal total,java.time.OffsetDateTime createdAt) {}
}
