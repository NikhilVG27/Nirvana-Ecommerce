package com.nirvana.api.product;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank @Size(max=180) String name,
    @NotBlank @Size(max=40) String category,
    @NotBlank @Size(max=60) String type,
    @NotNull @DecimalMin("0.01") BigDecimal price,
    @DecimalMin("0.00") BigDecimal oldPrice,
    @DecimalMin("0.0") @DecimalMax("5.0") BigDecimal rating,
    @Size(max=40) String tag,
    @Size(max=80) String color,
    String[] sizes,
    @Size(max=1000) String image,
    String description,
    @NotNull @Min(0) Integer stock
) {
    public ProductRequest { if (sizes == null) sizes = new String[0]; }
}
