package com.issart.boryshev.tests;

import java.util.Comparator;
import java.util.List;
import com.issart.boryshev.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModify() throws InterruptedException {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name123", "midname123",
                "lastname123", "nick", "title",
                "company", "address", "home", "+79988654565",
                "987532", "879", "sdlkgjlkj@mail.ru", "http://asdsga.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println(before);
        app.getContactHelper().editContact(0);
        ContactData contact = new ContactData(before.get(0).getId(),"name", "test",
            "surname", "nick", "title", "company", "address",
            "home", "+795843211", "465774", "1465564", "asfdb@jkdhgf.com",
            "fdghdfh.com");
        app.getContactHelper().fillContactFields(contact);
        app.getContactHelper().submitContactUpdate();
        Thread.sleep(1000);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}