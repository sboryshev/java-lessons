package com.issart.sboryshev.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminPageHelper extends HelperBase {

    public AdminPageHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUserPage() {
        click(By.xpath("//a[@href=\"/mantisbt-1.2.19/manage_overview_page.php\"]"));
        click(By.xpath("//a[@href=\"/mantisbt-1.2.19/manage_user_page.php\"]"));
    }

    public void resetPasswordForUser(String user) {
        click(By.xpath("//a[text()='" + user + "']"));
        click(By.xpath("//input[@value='Reset Password']"));
    }
}
