package asmaa.hr.ecommerce.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer id can not be null")
        @NotEmpty(message = "Customer id can not be empty")
        @NotBlank(message = "Customer id can not be blank")
        String cutomerId,

        @NotEmpty(message = "You should at least purchase one product")
        @Valid
        List<PurchaseRequest> products
) {
}
