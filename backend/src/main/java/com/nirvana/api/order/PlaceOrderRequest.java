package com.nirvana.api.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public record PlaceOrderRequest(
    @NotBlank @Size(max=120) String name,
    @NotBlank @Email @Size(max=180) String email,
    @NotBlank @Pattern(regexp="[0-9]{10}") String phone,
    @NotBlank @Size(max=300) String address,
    @NotBlank @Size(max=100) String city,
    @NotBlank @Pattern(regexp="[0-9]{6}") String pincode,
    @NotBlank @Pattern(regexp="Cash on delivery|Online payment \(demo\)") String payment,
    @NotEmpty @Valid List<Item> items
) {
    public record Item(@NotNull @Positive Long productId, @NotNull @Min(1) @Max(20) Integer quantity,
                       @NotBlank @Size(max=30) String size) {}
}
