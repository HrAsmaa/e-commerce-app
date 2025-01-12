package asmaa.hr.ecommerce.product;

import jakarta.validation.constraints.Positive;

public record PurchaseProductRequest(
        Integer productId,

        @Positive(message = "product quantity should be greater than zero")
        Integer quantity
) {
}
