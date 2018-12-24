package com.issart.boryshev.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {
    private FirefoxDriver driver;

    public NavigationHelper(FirefoxDriver driver) {
        super(driver);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToContactPage() {
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }
}
