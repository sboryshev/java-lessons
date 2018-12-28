package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name123", "midname123",
                "lastname123", "nick", "title",
                "company", "address", "home", "+79988654565", "987532", "879", "sdlkgjlkj@mail.ru",
                "http://asdsga.com"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
    }
}
