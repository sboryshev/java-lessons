package com.issart.sboryshev.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String user, String password) {
        driver.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.cssSelector("input[value=\"Login\"]"));
    }
}
