package com.ms.master.notification.sevice;

import com.ms.master.order.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
@Service
public class NotificationService {


    private final JavaMailSender javaMailSender;

    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlaceEvent) {
        System.out.println("Got messages from order-placed topic" + orderPlaceEvent);

        //Send Email to customer
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            messageHelper.setFrom("springshop@email.com");
            // messageHelper.setFrom("t1137ravi@gmail.com");
            messageHelper.setTo(orderPlaceEvent.getEmail().toString());
            messageHelper.setSubject(String.format("You order with orderNumber %s  placed SuccessFully!! ", orderPlaceEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                    Hi %s,%s
                    
                    Your order with order number %s Placed SuccessFully
                    
                    Best Regards,
                    Spring Shop
                    
                    """,
                    orderPlaceEvent.getFirstName().toString(),
                    orderPlaceEvent.getLastName().toString(),
                    orderPlaceEvent.getOrderNumber()));
        };
        try {
            javaMailSender.send(messagePreparator);
            System.out.println("Order Notification email sent !!");
        } catch (MailException e) {
            System.out.println("Exception occur sending Notification mail !!" + e);
            throw new RuntimeException("Exception occur When sending email to springshop@gmail.com", e);
        }
    }


}
