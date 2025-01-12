package asmaa.hr.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "customer firstname is required")
        String firstname,

        @NotNull(message = "customer lastname is required")
        String lastname,

        @NotNull(message = "customer email is required")
        @Email(message = "customer email is not valid")
        String email,

        Address address
) {
}
