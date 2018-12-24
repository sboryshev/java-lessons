package com.issart.boryshev.tests;

    import com.issart.boryshev.model.ContactData;
    import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModify() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactFields(new ContactData("name", "test",
            "surname", "nick", "title", "company", "address",
            "home", "+795843211","465774", "1465564", "asfdb@jkdhgf.com",
            "fdghdfh.com"));
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().returnToHomePage();
    }
}
