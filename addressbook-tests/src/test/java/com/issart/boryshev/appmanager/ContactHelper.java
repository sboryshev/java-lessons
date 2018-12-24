package com.issart.boryshev.appmanager;

import com.issart.boryshev.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper (FirefoxDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

//    public void submitContactCreation() {
//        driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
//    }

    public void fillContactFields(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("homepage"), contactData.getHomepage());
    }

    public void submitContactCreation() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])" +
            "[1]/following::input[1]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])" +
            "[1]/following::input[2]"));
    }

    public void acceptAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    public void editContact() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='home+79988654565987532'])[1]/following::img[2]"));
    }

    public void submitContactUpdate() {
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
    }
}
