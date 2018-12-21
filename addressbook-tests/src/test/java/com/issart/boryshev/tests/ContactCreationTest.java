package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreate() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactFields(new ContactData("name123", "midname123",
            "lastname123", "nick", "title",
            "company", "address", "home", "+79988654565", "987532", "879", "sdlkgjlkj@mail.ru",
            "http://asdsga.com"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }
}
