package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import com.issart.boryshev.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                .withFirstName("name123")
                .withLastName("lastname123")
                .withMiddleName("midname123")
                .withTitle("title123")
                .withNickname("nick123")
                .withCompany("company123")
                .withAddress("address123")
                .withHome("65434")
                .withFax("657")
                .withMobile("+798545375242")
                .withWork("7984344")
                .withEmail("company123@gmail.com")
                .withHomepage("http://asdsga.com"));
        }
    }

    @Test
    public void testContactDelete() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
