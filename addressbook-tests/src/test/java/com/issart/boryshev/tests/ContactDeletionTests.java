package com.issart.boryshev.tests;

import java.util.List;
import com.issart.boryshev.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDelete() throws InterruptedException {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name123", "midname123",
                "lastname123", "nick", "title",
                "company", "address", "home", "+79988654565", "987532", "879", "sdlkgjlkj@mail.ru",
                "http://asdsga.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        Thread.sleep(1000);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }
}
