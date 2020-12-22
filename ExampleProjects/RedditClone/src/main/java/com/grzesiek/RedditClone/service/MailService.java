package com.grzesiek.RedditClone.service;

import com.grzesiek.RedditClone.exceptions.SpringRedditException;
import com.grzesiek.RedditClone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {


    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;


    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }
//    Let us understand what we are doing in this class, so we have our sendMail method which takes NotificationEmail as input, and inside the method we are creating a MimeMessage by passing in the sender, recipient, subject and body fields. The message body we are receiving from the build() method of our MailContentBuilder class.
//
//    Once the email message is prepared, we send the email message. If there are any unexpected exceptions raised during sending the email, we are catching those exceptions and rethrowing them as custom exceptions. This is a good practice as we don’t expose the internal technical exception details to the user, by creating custom exceptions, we can create our own error messages and provide them to the users.
}
