package asmaa.hr.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,

        @NotNull(message = "product name should not be null")
        String name,

        @NotNull(message = "product description should not be null")
        String description,

        @PositiveOrZero(message = "product name should not be negative nor null")
        double availableQuantity,

        @Positive(message = "product price  can not be negative")
        BigDecimal price,

        @NotNull(message = "product description should not be null")
        Integer categoryId
) {
}
