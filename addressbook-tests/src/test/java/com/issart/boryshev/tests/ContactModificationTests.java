package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import com.issart.boryshev.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
                .withHomePhone("65434")
                .withFax("657")
                .withMobilePhone("+798545375242")
                .withWorkPhone("7984344")
                .withEmail1("company123@gmail.com")
                .withEmail2("twerhh@ksdfg.vikd")
                .withEmail3("fasdkfjlkj@sdggh.awed")
                .withHomepage("http://asdsga.com"));
        }
    }

    @Test
    public void testContactModify() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("name")
            .withMiddleName("test")
            .withLastName("surname")
            .withNickname("nick")
            .withTitle("title")
            .withCompany("company")
            .withAddress("address")
            .withHomePhone("home")
            .withMobilePhone("+79998884455")
            .withWorkPhone("98745")
            .withFax("5467")
            .withEmail1("asfdb@jkdhgf.com")
            .withEmail2("fdaskjfh@jgfdk")
            .withEmail3("kgpkdjflkguj@dsgslkhj.gfik")
            .withHomepage("jfghgjhg.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}