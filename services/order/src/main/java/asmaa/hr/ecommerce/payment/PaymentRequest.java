package asmaa.hr.ecommerce.payment;

import asmaa.hr.ecommerce.customer.CustomerResponse;
import asmaa.hr.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
