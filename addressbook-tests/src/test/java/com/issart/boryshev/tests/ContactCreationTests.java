package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import com.issart.boryshev.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreate() {
        app.goTo().homePage();
        Contacts before = app.contact().all();

        ContactData contact = new ContactData()
            .withFirstName("kefir")
            .withLastName("nerd")
            .withMiddleName("man")
            .withNickname("nick")
            .withTitle("title")
            .withCompany("company")
            .withAddress("address");
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}