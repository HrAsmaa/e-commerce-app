package asmaa.hr.ecommerce.notification;

import asmaa.hr.ecommerce.payment.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationMapper {

    public PaymentNotificationRequest toPaymentNotificationRequest(PaymentRequest payment) {
        return new PaymentNotificationRequest(
                payment.orderReference(),
                payment.amount(),
                payment.paymentMethod(),
                payment.customer().firstname(),
                payment.customer().lastname(),
                payment.customer().email()
        );
    }
}
