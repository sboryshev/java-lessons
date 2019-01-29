package com.issart.boryshev.appmanager;

import java.util.List;
import com.issart.boryshev.model.ContactData;
import com.issart.boryshev.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void goToContactCreationPage() {
        if (isElementPresent(By.tagName("h1"))
            && driver.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void fillContactFields(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
      //  attach(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCreation() {
        click(By.xpath("//input[@type='submit'][2]"));
    }

    public void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    private void editContactById(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@type='button' and @value='Delete']"));
    }

    public void acceptAlert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        goToContactCreationPage();
        fillContactFields(contact);
        submitContactCreation();
        contactCache = null;
        goToHomePage();
    }

    public void modify(ContactData contact) throws InterruptedException {
        editContactById(contact.getId());
        fillContactFields(contact);
        submitContactUpdate();
        Thread.sleep(1000);
        contactCache = null;
        goToHomePage();
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlert();
        Thread.sleep(1000);
        contactCache = null;
        goToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath("td[3]")).getText();
            String lastName = element.findElement(By.xpath("td[2]")).getText();
            String[] phones = element.findElement(By.xpath("td[6]")).getText().split("\n");
            String[] emails = element.findElement(By.xpath("td[5]")).getText().split("\n");
            String address = element.findElement(By.xpath("td[4]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("td[@class=\"center\"]//input")).getAttribute("id"));
            contactCache.add(new ContactData()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhone(phones[0])
                .withMobilePhone(phones[1])
                .withWorkPhone(phones[2])
                .withAddress(address)
                .withEmail1(emails[0])
                .withEmail2(emails[1])
                .withEmail3(emails[2]));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String email1 = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        driver.navigate().back();
        return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstName)
            .withLastName(lastName)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            .withAddress(address)
            .withEmail1(email1)
            .withEmail2(email2)
            .withEmail3(email3);
    }

    public void addToGroup(ContactData addingContact, int groupId) {
        selectContactById(addingContact.getId());
        WebElement element = driver.findElement(By.name("to_group"));
        element.click();
        element.findElement((By.xpath("./option[@value=" + groupId + "]"))).click();
        driver.findElement(By.name("add")).click();
    }

    public void deleteFromGroup(ContactData deletingContact, int id) {
        WebElement element = driver.findElement(By.name("group"));
        element.click();
        element.findElement((By.xpath("./option[@value=" + id + "]"))).click();
        selectContactById(deletingContact.getId());
        driver.findElement(By.name("remove")).click();
    }
}
