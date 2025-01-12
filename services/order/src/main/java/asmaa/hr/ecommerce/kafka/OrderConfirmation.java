package asmaa.hr.ecommerce.kafka;

import asmaa.hr.ecommerce.customer.CustomerResponse;
import asmaa.hr.ecommerce.order.PaymentMethod;
import asmaa.hr.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> products
) {
}
