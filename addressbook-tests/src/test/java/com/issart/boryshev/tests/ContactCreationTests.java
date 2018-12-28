package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreate() {
        app.getContactHelper().goToContactCreationPage();
        app.getContactHelper().createContact(new ContactData("name123", "midname123",
            "lastname123", "nick", "title",
            "company", "address", null, null, null, null, null,
            null));
    }
}
