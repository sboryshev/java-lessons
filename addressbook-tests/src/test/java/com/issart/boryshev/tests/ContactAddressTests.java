package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                .withFirstName("name123")
                .withLastName("lastname123")
                .withMiddleName("midname123")
                .withTitle("title123")
                .withNickname("nick123")
                .withCompany("company123")
                .withAddress("address123")
                .withHomePhone("65434")
                .withFax("657")
                .withMobilePhone("+798545375242")
                .withWorkPhone("7984344")
                .withEmail1("asd@asdf.qwe")
                .withEmail2("twerhh@ksdfg.vikd")
                .withEmail3("fasdkfjlkj@sdggh.awed")
                .withHomepage("http://asdsga.com"));
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
