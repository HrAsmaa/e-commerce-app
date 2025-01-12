package asmaa.hr.ecommerce.kafka.payment;

import asmaa.hr.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        String customerFirstname,

        String customerLastname,

        String customerEmail

) {
}
