package asmaa.hr.ecommerce.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationProducer {

    private KafkaTemplate<String, PaymentNotificationRequest> paymentNotifTemplate;

    public void sendNotification(PaymentNotificationRequest paymentNotificationRequest){
        log.info("Sending notification with body <{}>", paymentNotificationRequest);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(paymentNotificationRequest)
                .setHeader(TOPIC, "payment-topic")
                .build();
        paymentNotifTemplate.send(message);
    }

}
