package com.issart.boryshev.tests;

import java.util.Comparator;
import java.util.List;
import com.issart.boryshev.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreate() {
        app.getContactHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData contact = new ContactData("kefir", "nerd",
            "man", "nick", "title", "company", "address",
            null, null, null, null, null, null);
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
        before.add(contact);

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}