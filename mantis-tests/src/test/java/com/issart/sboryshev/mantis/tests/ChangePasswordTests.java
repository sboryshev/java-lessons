package com.issart.sboryshev.mantis.tests;

import java.net.MalformedURLException;
import java.util.List;
import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import com.issart.sboryshev.mantis.model.MailMessage;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

public class ChangePasswordTests extends TestBase {

    public ChangePasswordTests() throws MalformedURLException, ServiceException {
    }

    @Test
    public void testChangePassword() throws MessagingException {
        String new_password = "newPassword";
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.james().drainEmail(user, password);

        app.loginPage().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.adminPage().goToManageUserPage();
        app.adminPage().resetPasswordForUser(user);
        List<MailMessage> resetPasswordMessages = app.james().waitForMail(user, password, 60000);
        String confirmationResetLink = findConfirmationLink(resetPasswordMessages, email);
        app.registration().finish(confirmationResetLink, new_password);

        app.loginPage().login(user, new_password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
