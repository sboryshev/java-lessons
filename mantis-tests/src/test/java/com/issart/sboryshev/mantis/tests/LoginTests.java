package com.issart.sboryshev.mantis.tests;

import java.io.IOException;
import com.issart.sboryshev.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        boolean login = session.login("administrator", "root");
        assertTrue(login);
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
