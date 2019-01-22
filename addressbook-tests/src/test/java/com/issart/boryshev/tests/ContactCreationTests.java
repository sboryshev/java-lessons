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
            .withAddress("address")
            .withEmail1("asd@asdf.qwe")
            .withEmail2("twerhh@ksdfg.vikd")
            .withEmail3("fasdkfjlkj@sdggh.awed")
            .withHomePhone("45621")
            .withMobilePhone("+7987454")
            .withWorkPhone("2213");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}