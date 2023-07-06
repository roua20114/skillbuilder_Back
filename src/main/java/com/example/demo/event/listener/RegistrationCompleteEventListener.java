package com.example.demo.event.listener;

import com.example.demo.event.RegistrationCompleteEvent;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;
    private final JavaMailSender mailSender;
    private User theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        theUser = event.getUser();

        String verificationToken = UUID.randomUUID().toString();

        userService.saveUserVerificationToken(theUser, verificationToken);

        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
        //try {
          //  sendVerificationEmail(url);
        //} catch (MessagingException | UnsupportedEncodingException e) {
          //  throw new RuntimeException(e);
       // }

        log.info("Click the link to verify your registration :  {}", url);

    }
    //public void sendVerificationEmail(String url)  throws MessagingException, UnsupportedEncodingException {
       // String subject="Email Verification";
        //String senderName="User Registration Portal Service";
        //String mailContent = "<p> Hi, "+ theUser.getFirstName()+ ", </p>"+
          //      "<p>Thank you for registering with us,"+"" +
            //    "Please, follow the link below to complete your registration.</p>"+
              //  "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                //"<p> Thank you <br> Users Registration Portal Service";
        //MimeMessage message = mailSender.createMimeMessage();
        //var messageHelper = new MimeMessageHelper(message);
        //messageHelper.setFrom("rouapfe@gmail.com", senderName);
        //messageHelper.setTo(theUser.getEmail());
        //messageHelper.setSubject(subject);
        //messageHelper.setText(mailContent, true);
        //mailSender.send(message);
    //}
}
