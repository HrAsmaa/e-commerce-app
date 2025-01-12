package asmaa.hr.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PurchaseProductResponse (
        Integer id,

        @NotNull(message = "product name should not be null")
        String name,

        @NotNull(message = "product description should not be null")
        String description,

        @NotNull(message = "product categoryId should not be null")
        BigDecimal price,

        @Positive(message = "product quantity should greater than zero")
        Integer quantity
){
}
