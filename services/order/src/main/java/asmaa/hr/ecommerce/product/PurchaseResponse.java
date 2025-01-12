package asmaa.hr.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,

        String name,

        String description,

        BigDecimal price,

        Integer quantity
) {
}
