package asmaa.hr.ecommerce.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product id should not be null")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        Integer quantity
) {
}
