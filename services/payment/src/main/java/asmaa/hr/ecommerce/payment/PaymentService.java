package asmaa.hr.ecommerce.payment;

import asmaa.hr.ecommerce.notification.NotificationMapper;
import asmaa.hr.ecommerce.notification.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationMapper notificationMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment( PaymentRequest paymentRequest) {
        var payment = this.paymentRepository.save(this.paymentMapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(
                this.notificationMapper.toPaymentNotificationRequest(paymentRequest)
        );
        return payment.getId();
    }
}
