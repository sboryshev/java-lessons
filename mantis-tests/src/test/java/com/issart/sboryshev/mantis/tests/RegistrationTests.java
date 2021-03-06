package com.issart.sboryshev.mantis.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import com.issart.sboryshev.mantis.model.MailMessage;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    public RegistrationTests() throws MalformedURLException, ServiceException {
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
